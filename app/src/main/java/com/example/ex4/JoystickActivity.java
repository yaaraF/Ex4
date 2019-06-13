package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class JoystickActivity extends AppCompatActivity implements JoystickView.JoystickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JoystickView joystickView= new JoystickView(this);
        setContentView(R.layout.activity_joystick);
    }

    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int source) {
                SingletonCommand.getInstance().sendMessage("aileron",xPercent);
                SingletonCommand.getInstance().sendMessage("elevator",yPercent);
                Log.d("left","xpre"+xPercent+"ypre"+yPercent);

    }
}