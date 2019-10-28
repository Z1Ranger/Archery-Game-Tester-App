package com.example.aa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    GameObjects bow;
    Circle circle;
    List<Arrow> arrowList = new ArrayList<>();
    int width, height;
    private int highscore = 0;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public List<Integer> getDimension() {
        List<Integer> dimensionList = new ArrayList<>();
        dimensionList.add(width);
        dimensionList.add(height);
        return dimensionList;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bow = new Bow(BitmapFactory.decodeResource(getResources(), R.drawable.bow), this);
        circle = new Circle(BitmapFactory.decodeResource(getResources(), R.drawable.circle),
                this);
        arrowList.add(new Arrow(BitmapFactory.decodeResource(getResources(), R.drawable.arrow),
                this));

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        circle.update();
        Arrow moving_arrow = arrowList.get(0);
        if (moving_arrow.getTouch()) {
            moving_arrow.update();
            if ((circle.x <= moving_arrow.x & moving_arrow.x <= circle.x + circle.getWidth()) & (circle.y<= moving_arrow.y & moving_arrow.y <= circle.y+ circle.getHeight())){
                highscore += 1;
                circle.setSpeed();
                arrowList.remove(0);
            }
            if (moving_arrow.getReach()){
                arrowList.remove(0);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Arrow moving_arrow = arrowList.get(0);
        if (action == MotionEvent.ACTION_DOWN) {
            moving_arrow.setTouch(true);
            if (arrowList.size() <= 1){
                arrowList.add(new Arrow(BitmapFactory.decodeResource(getResources(), R.drawable.arrow),
                        this));
            }
        }
        invalidate();
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            circle.draw(canvas);
            bow.draw(canvas);
            for (int i = 0; i < arrowList.size(); i++){
            arrowList.get(i).draw(canvas);}
        }
    }

}


