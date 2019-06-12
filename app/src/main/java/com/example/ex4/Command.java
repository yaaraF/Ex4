package com.example.ex4;

import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Command {
    String ip;
    int port;
    private Thread thread;
    private Runnable runable;
    protected Socket socket;


//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }

    public void Connect(final String ip, final int port) {

        this.runable = new Runnable(){
            @Override
            public void run(){

                    try {
                        InetAddress serverAddr = InetAddress.getByName(ip);
                        socket = new Socket(serverAddr, port);
                    } catch (IOException e) {
                        Log.e("TCP", "C: Error", e);
                        System.out.println(e.toString());
                    }

            }
        };
        this.thread = new Thread(runable);
        thread.start();
        /*try {
            InetAddress serverAddr = InetAddress.getByName(ip);
            Socket socket = new Socket(serverAddr, port);
            try {
                OutputStream outputStream = socket.getOutputStream();
                //  FileInputStream fis = new FileInputStream(pic);
                //outputStream.write(imgbyte);
                outputStream.flush();
            } catch (Exception e) {
                Log.e("TCP", "S: Error", e);
            } finally {
                socket.close();
            }
        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
        }*/
    }
}
