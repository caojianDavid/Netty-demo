package com.pjqdyd.java;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**   
 * @Description:  [服务端Socket]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MySocketServier {

    //创建一个集合对象, 用来存储Socket对象
    private List<Socket> socketList = new ArrayList<>();

    public static void main(String[] args) {
        new MySocketServier().start(); //启动服务
    }

    //启动服务的方法
    private void start(){
        try {
            //创建一个Socket对象
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("------------服务启动port:8888----------------->");
            while (true){
                Socket socket = serverSocket.accept(); //等待客户端连接
                socketList.add(socket);                //添加客户端连接到socket集合中
                System.out.println("客户端:" + socket.getInetAddress() + "连接进入;");
                System.out.println("已连接客户端数量:" + socketList.size());

                //启动一个线程, 来处理消息
                new Thread(new MySendMsgToAllThread(socket, socketList)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
