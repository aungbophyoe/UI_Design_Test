package com.aungbophyoe.space.uidesigntest.customUi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.aungbophyoe.space.uidesigntest.R;

public class ByRateBackgroundOff extends View {

    Paint mBoarderPaint;
    Paint mInnerPaint;

    public ByRateBackgroundOff(Context context) {
        super(context);
        init();
    }

    public ByRateBackgroundOff(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ByRateBackgroundOff(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mBoarderPaint = new Paint();
        mBoarderPaint.setAntiAlias(true);
        mBoarderPaint.setColor(ContextCompat.getColor(getContext(), R.color.buttonColor));
        mBoarderPaint.setStyle(Paint.Style.STROKE);
        mBoarderPaint.setStrokeWidth(6);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Path path = new Path();
        path.moveTo(getWidth(),0);
        path.lineTo(getWidth()/10, 0);
        path.lineTo(0, getHeight());
        path.lineTo(getWidth() - (getWidth()/10),getHeight());
        path.lineTo(getWidth(),getHeight());
        path.moveTo(getWidth(),0);
        path.lineTo(getWidth(),getHeight());
        canvas.drawPath(path, mBoarderPaint);
    }
}
