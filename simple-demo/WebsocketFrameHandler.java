package com.netty.websocket;

import com.alibaba.fastjson.JSON;
import com.encry.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Map;

import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.bouncycastle.tsp.TSPUtil;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.3
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
//TextWebSocketFrame表示文本帧
public class WebsocketFrameHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private WebSocketServerHandshaker handshaker;
    private final String wsUri = "/ws";
    private String wsFactoryUri = "";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof HttpRequest) {// 如果是HTTP请求，进行HTTP操作
            handleHttpRequest(ctx, (HttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {// 如果是Websocket请求，则进行websocket操作
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
        //回复消息
//        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间" + LocalDateTime.now());
    }

    /**
     * 刚开始握手
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        channelGroup.add(ctx.channel());
//        System.out.println("(通道被请求建立) => " + ctx.channel().id().asLongText());
        //id表示唯一值，LongText是唯一的，ShortText不是唯一的

    }

    /**
     * 握手终止
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("(通道已完全终止) => " + ctx.channel().id().asLongText());
        channelGroup.remove(ctx.channel());
    }

    /**
     * 读写空闲移除Channel
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent evnet = (IdleStateEvent) evt;
            // 判断Channel是否读空闲, 读空闲时移除Channel
            if (evnet.state().equals(IdleState.READER_IDLE)) {
                ChannelManger.removeChannel(ctx.channel());
                System.out.println("(空闲通道被断开) => " + ctx.channel().id().asLongText());
            }
        }
        ctx.fireUserEventTriggered(evt);
    }

    /**
     * 活跃报信
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("(客户端连上了通道) => " + ctx.channel().id().asLongText());
    }

    /**
     * 不活跃报信
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("(客户端离开了通道) => " + ctx.channel().id().asLongText());
    }

    /**
     * HTTP处理
     * @param ctx
     * @param req
     * @throws UnsupportedEncodingException
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, HttpRequest req) throws UnsupportedEncodingException {
        // 如果HTTP解码失败，返回HTTP异常
        if (req != null) {
            // 如果是websocket请求就握手升级
            System.out.println(req.uri());
            if (wsUri.equalsIgnoreCase(req.uri())) {
                System.out.println("(HTTP请求升级为WebSocket)");
                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                        wsFactoryUri, null, false);
                handshaker = wsFactory.newHandshaker(req);
                if (handshaker == null) {
                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                } else {
                    handshaker.handshake(ctx.channel(), req);
                }
            }

        }
    }

    /**
     * Websocket处理
     * @param ctx
     * @param frame
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 文本消息，不支持二进制消息
        if (frame instanceof TextWebSocketFrame) {
            // 返回应答消息
            String request = ((TextWebSocketFrame) frame).text();

            SendPackage pkg = JSON.parseObject(request, SendPackage.class);
            System.out.println("(Websocket数据包) => "+request);

            // 将通道加入通道管理器
            ChannelManger.addChannel(ctx.channel(),pkg.getMe());

            UserInfo userInfo = ChannelManger.getReceiver(ctx.channel());
            System.out.println("(新的用户Id) => "+ userInfo.getUserId());

            if(StringUtil.isNotEmpty(pkg.getFriend())){
                ChannelManger.broadcastMess(pkg.getFriend(),pkg.getMe(),pkg.getContent());
//                System.out.println("(发送成功)");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("(抛出异常) => " + cause.getMessage());
        ctx.close();
    }
}
