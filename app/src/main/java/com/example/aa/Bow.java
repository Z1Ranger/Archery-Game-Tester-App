package com.example.aa;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bow extends GameObjects {

    private Bitmap image;
    private int x, y;

    public Bow(Bitmap bow, GameView view){
        image = bow;
        x = 21*(view.getDimension().get(0))/100;
        y = 81*(view.getDimension().get(1))/100;
    }


    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }
}
