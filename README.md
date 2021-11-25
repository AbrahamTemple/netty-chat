# Websocket+Netty加密聊天项目

## 使用说明

- 项目由springboot(port：8882)内带netty(port:7000)跑起来

- 运行Netty服务器类与多个页面，先登录获取token才能点对点发送消息。

- 核心代码主要聚集在HandleWebSocketFrame方法中

- jwt搭配aop在springboot上使用，实现了全局的权限控制
