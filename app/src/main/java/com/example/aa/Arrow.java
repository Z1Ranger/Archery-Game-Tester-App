package com.example.aa;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Arrow extends GameObjects implements TouchEvent {

    public Bitmap image;
    public int x, y;
    private boolean touch, reach_end;

    public Arrow(Bitmap arrow, GameView view){
        image = arrow;
        x = view.getDimension().get(0)-570;
        y = view.getDimension().get(1)-550;
        touch = false;
        reach_end = false;
    }

    public boolean getTouch(){return touch;}

    public void setTouch(boolean touch){this.touch = touch;}

    public boolean getReach(){return reach_end;}

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }

    public void update(){
        if (y > 20){
        y-=20;}
        else{
            reach_end = true;
        }
    }
}
