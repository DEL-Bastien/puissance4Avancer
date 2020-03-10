package com.example.puissance4_test.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class PionView {
    int xc, yc, rayon;
    private Paint paint;

    public PionView(int x, int y, int rayon , String strColor) {
        xc = x;
        yc = y;
        this.rayon = rayon;
        paint = new Paint();
        paint.setColor(Color.parseColor(strColor));
    }


    public PionView(int x, int y, String strColor) {
        xc = x;
        yc = y;
        rayon = 50;
        paint = new Paint();
        paint.setColor(Color.parseColor(strColor));
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(xc, yc, rayon, paint);
    }
}
