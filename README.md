### Netty使用案例

#### 项目采用Gradle构建.

#### 项目结构:
 ```
  ├─src/main/java/com/pjqdyd
      ├─java        java.net包实现socket发送消息
      ├─http        netty简单实现http连接服务端
      ├─socket      netty实现socket服务端和客户端
      ├─chat        netty实现多客户连接聊天
      ├─ping        netty实现心跳检测机制
      └─springboot  springboot整合netty
 ```


#### 依赖版本:
  Netty       4.1.39.Final.
  
  SpringBoot  2.1.8.RELEASE