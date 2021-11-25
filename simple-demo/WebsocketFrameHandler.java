package com.cloud.morsechat.netty;

import com.alibaba.fastjson.JSON;
import com.cloud.morsechat.aop.Permission;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.UnsupportedEncodingException;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.3
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
//ChannelInboundHandlerAdapter不支持Http升级成Websocket
public class WebsocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private WebSocketServerHandshaker HandShaker;
    private String JWT_TOKEN;

    /**
     * 读取Http请求数据
     * @param ctx
     * @param packet
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
        handleHttpRequest(ctx,packet);
    }

    /**
     * 读取Websocket请求数据
     * @param ctx
     * @param packet
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame packet) throws Exception {
        handleWebSocketFrame(ctx, packet);
    }


    /**
     * 刚开始握手
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        channelGroup.add(ctx.channel());
        //id表示唯一值，LongText是唯一的，ShortText不是唯一的
//        System.out.println("(通道被请求建立) => " + ctx.channel().id().asLongText());
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
    @Permission
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("(客户端连上了通道) => " + ctx.channel().id().asLongText());
    }

    /**
     * 不活跃报信
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("(客户端离开了通道) => " + ctx.channel().id().asLongText());
        ChannelManger.removeChannel(ctx.channel());
    }

    /**
     * HTTP处理
     * @param ctx
     * @param packet
     * @throws UnsupportedEncodingException
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, Object packet) throws Exception {

        if (packet instanceof FullHttpRequest) {// 如果是HTTP请求，进行HTTP操作

            FullHttpRequest request = (FullHttpRequest) packet;
            //拿到请求地址
            String uri = request.uri();
            //判断是不是websocket请求，如果是拿出我们传递的参数（我的是token）
            String origin = request.headers().get("Origin");
            if (null == origin) {
                ctx.close();
            } else {
                if (uri != null && uri.contains("/ws") && uri.contains("?")) {
                    String[] uriArray = uri.split("\\?");
                    if (uriArray.length > 1) {
                        String[] paramsArray = uriArray[1].split("=");
                        if (paramsArray.length > 1) {
                            JWT_TOKEN = paramsArray[1]; //
                        }
                    }
                    //重新设置请求地址
                    request.setUri(WebsocketServer.WEBSOCKET_PATH);
                }
                else {
                    //没有token
                    ctx.close();
                }
            }

            System.out.println(JWT_TOKEN);
            ChannelManger.addChannel(ctx.channel(), JWT_TOKEN);
            UserInfo userInfo = ChannelManger.getReceiver(ctx.channel());
            System.out.println("(新的用户Id) => "+ userInfo.getUserId());

//            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
//                    wsFactoryUri, null, false);
//                handshaker = wsFactory.newHandshaker(request);
//
//                if (handshaker == null) {
//                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
//                } else {
//                    handshaker.handshake(ctx.channel(), request);
//                }
        }

        //接着建立请求
        super.channelRead(ctx, packet);
        //回复消息
//        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间" + LocalDateTime.now());
    }

    /**
     * Websocket处理
     * @param ctx
     * @param frame
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            HandShaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
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

            Packet pkg = JSON.parseObject(request, Packet.class);
            System.out.println("(Websocket数据包) => "+request);

            ChannelManger.broadcastMess(pkg.getFriend(),pkg.getMe(),pkg.getContent());

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("(抛出异常) => " + cause.getMessage());
        ctx.close();
    }
}
