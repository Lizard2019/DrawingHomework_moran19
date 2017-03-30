package com.example.moran19.hw2_moran19;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by moran19 on 3/27/2017.
 */
public class MySurfaceView extends SurfaceView{

    //create variables
    public CustomElement[] elementArray;
    private int isTouched;

    //constructor
    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setWillNotDraw(false);
        //this.setOnTouchListener(this);


        //initialize my element array with custom elements
        elementArray = new CustomElement[7];

        elementArray[1] = new CustomCircle("Sun",Color.YELLOW, 400, 200, 100);
        elementArray[2] = new CustomCircle("Orange", Color.rgb(255,160,0), 900, 200, 40);
        elementArray[3] = new CustomCircle("Golf Ball", Color.WHITE, 1400, 200, 10);
        elementArray[4] = new CustomRect("Dollar", Color.GREEN, 300, 600, 500, 700);
        elementArray[5] = new CustomRect("Pool", Color.BLUE, 700, 500, 1200, 700);
        elementArray[6] = new CustomRect("Chocolate", Color.rgb(84,52,1), 1400, 600, 1450, 700);
    }



    //draws objects on canvas
    @Override
    public void onDraw(Canvas canvas)
    {
        for(int i = 1; i < 7; i++)
        {
            elementArray[i].drawMe(canvas);
            if(isTouched == i)
            {
                elementArray[i].drawHighlight(canvas);
            }
        }
    }

    //sets new value for isTouched
    public void setIsTouched(int newIsTouched)
    {
        isTouched = newIsTouched;
    }

    //get object corresponding to isTouched int
    public CustomElement getCustomElement(int i)
    {
        return elementArray[i];
    }

}
