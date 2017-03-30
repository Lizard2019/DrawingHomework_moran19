package com.example.moran19.hw2_moran19;

import android.graphics.Canvas;

/**
 * Created by moran19 on 3/29/2017.
 */
public class DrawText extends CustomElement {

    private float x;
    private float y;
    private int size;
    private int color;

    public DrawText(String name, int color, float x, float y)
    {
        super(name,color);
        this.x = x;
        this.y = y;
        size = 100;

    }



    @Override
    public void drawMe(Canvas canvas)
    {
        canvas.drawText(myName,x,y, myPaint); //draws text on SurfaceView
    }

    @Override
    public boolean containsPoint(int x, int y) {
        return false;
    }

    @Override
    public int getSize() {
        return 100;
    }

    @Override
    public void drawHighlight(Canvas canvas) {

    }
}
