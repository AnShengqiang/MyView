package com.anshengqiang.android.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.anshengqiang.android.myview.model.Planet;
import com.anshengqiang.android.myview.model.TraceLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshengqiang on 2017/3/27.
 */

public class GalaxyView extends View {

    private static final String TAG = "MyView";

    private Planet earth;
    private Planet planetTest_1;
    private Planet planetTest_2;

    private PointF planetTest_1Point;
    private PointF earthPoint;
    private PointF planetTest_2Point;

    private Float sunRadius = 100f;
    private int color = randomColor();

    private Handler handler;
    private Rect rect;
    private List<TraceLine> traceLines = new ArrayList<>();
    private List<TraceLine> longTraceLines = new ArrayList<>();
    private long maxLine = 600;

    private int lineColor = randomColor();
    private int longLineColor = randomColor();

    private boolean shouldDrawLine;
    private boolean shouldDrawLongLine;

    public void drawLine(Canvas canvas){
        //planetTest_1Point = planetTest_1.getPointF();
        //earthPoint = earth.getPointF();
        //planetTest_2Point = planetTest_2.getPointF();
        planetTest_1Point = new PointF(planetTest_1.getPointF().x, planetTest_1.getPointF().y);
        planetTest_2Point = new PointF(planetTest_2.getPointF().x, planetTest_2.getPointF().y);
        earthPoint = new PointF(earth.getPointF().x, earth.getPointF().y);

        /*if (traceLines.size() > maxLine){
            traceLines.clear();
            longTraceLines.clear();
            Log.i(TAG, "数组元素为："+ traceLines.size() + ", 超过：" + maxLine);
        }*/
        if (shouldDrawLine) {
            traceLines.add(new TraceLine(planetTest_1Point, earthPoint));
        }else {
            traceLines.clear();
        }
        if (shouldDrawLongLine) {
            longTraceLines.add(new TraceLine(earthPoint, planetTest_2Point));
        }else {
            longTraceLines.clear();
        }

        Paint paint = new Paint();

        if (shouldDrawLine){
            paint.setColor(lineColor);

            for (int i = 0; i < traceLines.size(); i++){
                float[] floats = traceLines.get(i).getLineArray();
                canvas.drawLines(floats, paint);
            }
        }

        if (shouldDrawLongLine) {
            paint.setColor(longLineColor);
            for (int i = 0; i < longTraceLines.size(); i++) {
                float[] floats = longTraceLines.get(i).getLineArray();
                canvas.drawLines(floats, paint);
            }
        }

    }

    public void initPlanet(){
        earth = new Planet(300, 50, 250/1000f, randomColor(), rect);
        planetTest_1 = new Planet(200, 30, 150/1000f, randomColor(), rect);
        planetTest_2 = new Planet(450, 60, 60/4/1000f, randomColor(), rect);
    }

    public void init(){

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        invalidate();
                }
            }
        };

        startThread();

    }

    private void startThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(50);
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public int randomColor(){
        return Color.argb(255, randomInt(), randomInt(), randomInt());
    }

    public int randomInt() {
        double random = Math.random();

        random = random * 255;

        return (int) random;
    }

    public GalaxyView(Context context) {
        super(context);
        init();
    }

    public GalaxyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GalaxyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rect = new Rect(getLeft(), getTop(), getRight(), getBottom());
        initPlanet();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(this.color);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle((getRight() - getLeft())/2, (getBottom() - getTop())/2, sunRadius, paint);
        earth.draw(canvas);
        planetTest_1.draw(canvas);
        planetTest_2.draw(canvas);
        drawLine(canvas);
    }

    public Planet getEarth() {
        return earth;
    }

    public Planet getPlanetTest_1() {
        return planetTest_1;
    }

    public Planet getPlanetTest_2() {
        return planetTest_2;
    }

    public int getLineColor() {
        return lineColor;
    }

    public int getLongLineColor() {
        return longLineColor;
    }

    public void setShouldDrawLine(boolean shouldDrawLine) {
        this.shouldDrawLine = shouldDrawLine;
    }

    public void setShouldDrawLongLine(boolean shouldDrawLongLine) {
        this.shouldDrawLongLine = shouldDrawLongLine;
    }
}
