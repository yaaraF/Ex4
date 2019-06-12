package com.example.ex4;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MyConectionRunable implements Runnable {
    String ip;
    int port;

    public MyConectionRunable(String ip,int port){
        this.ip = ip;
        this.port = port;
    }


    @Override
    public void run() {
        try {
            InetAddress serverAddr = InetAddress.getByName("10.0.2.2");
            while(true){
                System.out.println("ad");
            }
            //Socket socket = new Socket(serverAddr, 5402);
        } catch (IOException e) {
            Log.e("TCP", "C: Error", e);
            System.out.println(e.toString());
        }
    }
}
