# Websocket+Netty加密聊天项目

## 使用说明

- 项目由Springboot（port：8882）内带Netty（port:7000）跑起来

- 运行Netty服务器类与多个页面，先登录获取token才能点对点发送消息。

- Aop获取请求头的token做JWT验证，控制全局Http数据访问权限

- 基于Hibernate 4.3.x的联表查询

## 核心代码

> 核心的代码主要聚集在WebsocketFrameHandler类中

``` java
/**
* 读取并处理Http请求数据
* @param ctx
* @param packet
* @throws Exception
*/
@Override
public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
  handleHttpRequest(ctx,packet);
}

/**
* 读取并处理Websocket请求数据
* @param ctx
* @param packet
* @throws Exception
*/
@Override
protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame packet) throws Exception {
  handleWebSocketFrame(ctx, packet);
}
```
