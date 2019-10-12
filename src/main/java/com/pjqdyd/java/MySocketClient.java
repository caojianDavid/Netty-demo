package com.pjqdyd.java;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

/**   
 * @Description:  [socket客户端]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MySocketClient {

    private Socket socket = null;

    public static void main(String[] args) {
        MySocketClient mySocketClient = new MySocketClient();
        mySocketClient.connectServer(); //连接到服务
    }

    //连接服务器
    public void connectServer(){
        try {
            socket = new Socket("localhost",8888);
            new Thread(new MySendMsgThread(socket)).start(); //启动一个发消息的线程
            readMsg(); //读消息
        }catch (Exception e){

        }
    }

    //读消息
    public void readMsg(){
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            String line = null;
            while ((line = dis.readUTF()) != null){
                System.out.println("接收到消息:" + line);
            }
            dis.close();
            inputStream.close();
        }catch (Exception e){
            System.out.println("服务器连接失败;");
        }
    }

}
