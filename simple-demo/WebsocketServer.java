package com.cloud.morsechat.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.2
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Component
public class WebsocketServer {

    static final String WEBSOCKET_PATH =  "/ws";

    public void start(InetSocketAddress address) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //配置
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)  //使用的管道方式
                    .option(ChannelOption.SO_BACKLOG, 128) //设置线程队列得到的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast(new HttpServerCodec());

                            //大量数据时http分段传输，这就是将这些端聚合
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));

                            pipeline.addLast(new WebsocketFrameHandler());

                            //websocket是逐帧的，这个是访问的断言
                            pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH,null, true, 10485760));


                        }
                    });

            System.out.println("服务器已经正常运行...");

            //绑定一个端口并且同步，启动服务器
            ChannelFuture channelFuture = bootstrap.bind(address).sync();

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
