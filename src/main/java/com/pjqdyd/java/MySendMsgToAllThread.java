package com.pjqdyd.java;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**   
 * @Description:  [广播消息的线程]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MySendMsgToAllThread implements Runnable{

    private Socket socket;
    private List<Socket> socketList;

    public MySendMsgToAllThread(Socket socket, List<Socket> socketList) {
        this.socket = socket;
        this.socketList = socketList;
    }

    @Override
    public void run() {
        try {
            //获取输入流对象
            InputStream inputStream = socket.getInputStream();
            //转换为DataInputStream
            DataInputStream dis = new DataInputStream(inputStream);

            String line = null; //定义变量接收值
            while ((line = dis.readUTF()) != null){
                System.out.println(socket.getInetAddress() + ":" + line);
                sendMsgToAll(line); //发送消息给其他客户端
            }

        }catch (Exception e){
            e.printStackTrace();
            socketList.remove(socket);
        }
    }

    //广播消息的方法
    private void sendMsgToAll(String msg){
        try {
            if (socketList == null){
                return;
            }
            for (Socket s: socketList){
                OutputStream outputStream = s.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputStream);

                dos.writeUTF(msg);
            }
        }catch(Exception e){

        }
    }

}
