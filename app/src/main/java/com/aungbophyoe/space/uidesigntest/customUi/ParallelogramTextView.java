package com.aungbophyoe.space.uidesigntest.customUi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;

import com.aungbophyoe.space.uidesigntest.R;

public class ParallelogramTextView extends View {

    Paint mInnerPaint;

    public ParallelogramTextView(Context context) {
        super(context);
        init();
    }

    public ParallelogramTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ParallelogramTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray));
        mInnerPaint.setStyle(Paint.Style.FILL);
        mInnerPaint.setTextSize(20f);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Path path = new Path();
        path.moveTo(getWidth(),0);
        path.lineTo(getWidth()/10, 0);
        path.lineTo(0, getHeight());
        path.lineTo(getWidth() - (getWidth()/10),getHeight());
        path.lineTo(getWidth(), getWidth());
        canvas.drawPath(path, mInnerPaint);
    }
}