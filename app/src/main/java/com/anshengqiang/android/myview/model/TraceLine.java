package com.anshengqiang.android.myview.model;

import android.graphics.PointF;
import android.nfc.Tag;
import android.util.Log;

/**
 * Created by anshengqiang on 2017/3/28.
 */

public class TraceLine {
    private PointF startPoint;
    private PointF endPoint;

    public TraceLine(PointF startPoint, PointF endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public float[] getLineArray(){
        float[] floats = {startPoint.x, startPoint.y, endPoint.x, endPoint.y};
        return floats;
    }
}
