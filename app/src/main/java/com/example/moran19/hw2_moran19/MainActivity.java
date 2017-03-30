package com.example.moran19.hw2_moran19;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnTouchListener{

    //create variables
    private SeekBar redBar;
    private SeekBar greenBar;
    private SeekBar blueBar;
    private int redVal;
    private int greenVal;
    private int blueVal;
    private int myElementColor;
    private TextView textView;
    private MySurfaceView surface;
    private int isTouched;
    private CustomElement myElement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surface = (MySurfaceView) findViewById(R.id.surface_View);
        textView = (TextView) findViewById(R.id.EW);


        //initialize seekBars
        redBar = (SeekBar) findViewById(R.id.RedSeekBar);
        greenBar = (SeekBar) findViewById(R.id.greenSeekBar);
        blueBar = (SeekBar) findViewById(R.id.blueSeekBar);

        //set my listeners
        surface.setOnTouchListener(this);
        redBar.setOnSeekBarChangeListener(this);
        greenBar.setOnSeekBarChangeListener(this);
        blueBar.setOnSeekBarChangeListener(this);

    }

    //handles seek bar events
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        //updates color of an element
        if (isTouched > 0)
        {
            myElement = surface.getCustomElement(isTouched);
            updateElementColor();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
        //does nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        //does nothing
    }

    //updates the color of the shape on screen
    public void updateElementColor()
    {
        //edit rgb values
        redVal = redBar.getProgress();
        greenVal = greenBar.getProgress();
        blueVal = blueBar.getProgress();

        //set element on screen to new color
        myElementColor = 0xff000000 + redVal * 0x10000 + greenVal * 0x100 + blueVal;
        myElement.setColor(myElementColor);
    }


    //
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        CustomElement tempElement;
        int tempColor;

        if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            //updates seek bar values for each element, and updates text view
            for(int i = 1; i < 7; i++)
            {
                tempElement = surface.elementArray[i];
                tempColor = tempElement.getColor();

                if(tempElement.containsPoint((int)motionEvent.getX(), (int)motionEvent.getY()))
                {
                    isTouched = i;
                    surface.setIsTouched(i);
                    textView.setText(tempElement.getName());


                    redBar.setProgress((tempColor >> 16) & 255);
                    greenBar.setProgress((tempColor >> 8) & 255);
                    blueBar.setProgress(tempColor & 255);
                }
            }
        }
        surface.invalidate();
        return true;
    }
}


