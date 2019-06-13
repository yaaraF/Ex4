package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity {

    private Button button;
    private EditText ipText;
    private EditText portText;
    String ip;
    int port;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ipText = (EditText) findViewById(R.id.ipTextBox);
        portText = (EditText) findViewById(R.id.portTextBox);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ip = ipText.getText().toString();
                port =  Integer.parseInt(portText.getText().toString());


               // SingletonCommand.getInstance().Connect(ip,port);

                //Connect(ip,port);



                openJoystickActivity();
             }
        });


    }

    public void Connect(String ip1,int port1) {

        Runnable runable = new Runnable(){
            @Override
            public void run(){

                try {
                    InetAddress serverAddr = InetAddress.getByName("10.0.2.2");
                    Socket socket = new Socket(serverAddr, 5402);
                } catch (IOException e) {
                    Log.e("TCP", "C: Error", e);
                    System.out.println(e.toString());
                }

            }
        };
        Thread thread = new Thread(runable);
        thread.start();
    }


    public void openJoystickActivity() {
        Intent intent = new Intent(this, JoystickActivity.class);
        startActivity(intent);
    }

}
