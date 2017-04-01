package com.anshengqiang.android.myview.model;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by anshengqiang on 2017/3/27.
 */

public class Planet {

    private float distance;
    private float radius;
    private double speed;
    private int color;
    private double degree;
    private PointF pointF;


    public Planet(float distance, float radius, double speed, int color, Rect rect){
        this.distance = distance;
        this.radius = radius;
        this.speed = speed;
        this.color = color;
        this.degree = 10.0f;
        this.pointF = new PointF((float) ((rect.right - rect.left)/2 + distance * Math.cos(degree)),
                                 (float) ((rect.bottom - rect.top)/2 + distance * Math.sin(degree)));
    }

    public void draw(Canvas canvas){
        drawPlanet(canvas);
        drawEquator(canvas);
    }

    public void drawPlanet(Canvas canvas){
        degree = degree + speed;
        pointF.set((float) (canvas.getWidth()/2 + distance * Math.cos(degree)),
                   (float) (canvas.getHeight()/2 + distance * Math.sin(degree)));

        Paint paint = new Paint();
        paint.setColor(this.color);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(pointF.x, pointF.y, radius, paint);
    }

    public void drawEquator(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(this.color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{30, 20}, 0));

        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, distance, paint);
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setSpeed(float speed) {
        this.speed = speed/1000;
    }

    public PointF getPointF(){
        return  pointF;
    }

    public int getColor() {
        return color;
    }
}
