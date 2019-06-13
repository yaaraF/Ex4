package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.text.AttributedCharacterIterator;
import java.util.jar.Attributes;

public class JoystickView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hatRadius;
    private JoystickListener JoystickCollable;
    private final int rat = 5;

    public interface JoystickListener

    {
        void onJoystickMoved(float xPercent, float yPercent, int source);

    }


    private void setupDim() {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / 3;
        hatRadius = Math.min(getWidth(), getHeight()) / 5;
    }


    public JoystickView(Context context) {
        super(context);
        setOnTouchListener(this);
        if(context instanceof JoystickListener){
            JoystickCollable = (JoystickListener)context;
        }
    }

    public JoystickView(Context context, AttributeSet attributes, int style) {
        super(context, attributes, style);
        setOnTouchListener(this);
        if(context instanceof JoystickListener){
            JoystickCollable = (JoystickListener)context;
        }
    }

    public JoystickView(Context context, AttributeSet attributes) {
        super(context, attributes);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener){
            JoystickCollable = (JoystickListener)context;
        }
    }

    private void DrawJoystick(float newX, float newY) {
        if (getHolder().getSurface().isValid()) {
            Canvas myCanvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            myCanvas.drawRGB(250,235,215);

            colors.setARGB(255, 169,169,169);
            myCanvas.drawCircle(centerX, centerY, baseRadius, colors);


            for(int i  = 0;i<=(int)(hatRadius/rat);i++){
                colors.setARGB(255,65,105,225);
                myCanvas.drawCircle(newX,newY,hatRadius-(float)i*(rat)/2,colors);
            }
            getHolder().unlockCanvasAndPost(myCanvas);


        }
    }

    public boolean onTouch(View v, MotionEvent e) {

        if (v.equals(this)) {
            if (v.equals(this)) {
                if (e.getAction() != e.ACTION_UP) {
                    float displacement = (float)Math.sqrt((Math.pow(e.getX()-centerX,2))+
                                    Math.pow(e.getY()-centerY,2));
                    if(displacement<baseRadius){
                        DrawJoystick(e.getX(), e.getY());
                        JoystickCollable.onJoystickMoved((e.getX()-centerX)/baseRadius
                                ,(e.getY()-centerY)/baseRadius,getId());

                    }else{
                        float ratio = baseRadius/displacement;
                        float constraiedX = centerX+(e.getX()-centerX)*ratio;
                        float  constraiedY  =centerY + (e.getY()-centerY)*ratio;
                        DrawJoystick(constraiedX,constraiedY);
                        JoystickCollable.onJoystickMoved((constraiedX-centerX)/baseRadius
                                ,(constraiedY-centerY)/baseRadius,getId());
                    }

                } else {
                    DrawJoystick(centerX, centerY);
                    JoystickCollable.onJoystickMoved(0,0,getId());
                }
            }
        } else {
            DrawJoystick(centerX, centerY);
        }
        return true;

    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        setupDim();
        DrawJoystick(centerX, centerY);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int high) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
