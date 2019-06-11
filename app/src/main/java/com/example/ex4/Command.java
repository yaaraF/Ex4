package com.example.ex4;

import android.util.Log;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Command {
    String ip;
    int port;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void Connect() {
        try {
            InetAddress serverAddr = InetAddress.getByName("10.0.0.2");
            Socket socket = new Socket(serverAddr, 1234);
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
        }
    }
}
