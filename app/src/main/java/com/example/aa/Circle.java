package com.example.aa;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Circle extends GameObjects {

    private Bitmap image;
    private String direction = "->";
    public int x, y, screen_x, screen_y;
    private int speed;

    public Circle(Bitmap circle, GameView view){
        image = circle;
        screen_x = view.getDimension().get(0);
        screen_y = view.getDimension().get(1);
        x = (25*screen_x)/100;
        y = (15*screen_y)/100;
        speed = 5;
    }

    public int getWidth(){return image.getWidth();}

    public int getHeight(){return image.getHeight();}

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {
        if (!(x < screen_x - image.getWidth() & x > 0)){reverse();}
        if (direction.equals("->")){x+=speed;}
        else{x-=speed;}
    }

    public void setSpeed(){speed+=2;}

    public void reverse(){
        if (direction.equals("->")){
            direction = "<-";}
        else{direction = "->";}
    }
}
