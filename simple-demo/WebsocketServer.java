package com.netty.websocket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.2
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public class WebsocketServer {

    public static void main(String[] args) throws InterruptedException {
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
                            pipeline.addLast(new HttpObjectAggregator(8030));

                            //websocket是逐帧的，这个是访问的断言
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

                            pipeline.addLast(new WebsocketFrameHandler());
                        }
                    });

            System.out.println("服务器已经正常运行...");

            //绑定一个端口并且同步，启动服务器
            ChannelFuture channelFuture = bootstrap.bind(7000).sync();

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
