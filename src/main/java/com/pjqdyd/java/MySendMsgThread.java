package com.pjqdyd.java;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**   
 * @Description:  [单独发送消息的线程]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MySendMsgThread implements Runnable{

    private Socket socket;

    public MySendMsgThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
            //接收键盘输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String input = scanner.next();
                sendMsg(input); //发送消息
            }
    }

    //发送消息
    public void sendMsg(String msg){
        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputStream);
            dos.writeUTF(msg);
        }catch (Exception e){
            System.out.println("连接断开");
        }
    }

}
