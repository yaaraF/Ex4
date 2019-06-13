package com.example.ex4;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Command {
    String ip;
    int port;
    private Thread thread;
    private Runnable runable;
    protected Socket socket;
    private PrintWriter mBufferOut;
    private Map<String,String> simolatorPlace;


    private void InitTheMap(){
        this.simolatorPlace.put("aileron", "/controls/flight/aileron");
        this.simolatorPlace.put("elevator", "/controls/flight/elevator");
    }

    public Command(){
        simolatorPlace = new HashMap<>();
        this.InitTheMap();
    }


    public void Connect(final String ip, final int port) {

        this.runable = new Runnable(){
            @Override
            public void run(){

                    try {
                        InetAddress serverAddr = InetAddress.getByName(ip);
                        socket = new Socket(serverAddr, port);
                        mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    } catch (IOException e) {
                        Log.e("TCP", "C: Error", e);
                        System.out.println(e.toString());
                    }

            }
        };
        this.thread = new Thread(runable);
        thread.start();

    }
    public void sendMessage(String location,float num) {

        final String message = "set " +this.simolatorPlace.get(location)+" "+Float.toString(num)+"\r\n";
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mBufferOut != null) {
                    Log.d(TAG, "Sending: " + message);
                    mBufferOut.println(message);
                    mBufferOut.flush();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


}
