package edu.neu.fitness_38.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import edu.neu.fitness_38.R;

public class CircleView extends View{

    private static final String TAG = "MyCircleProgress";

    private Paint paint;
    private RectF rectF;
    private Rect rect;
    private float current = 0, max = 100;
    private float arcWidth = 50;
    private float width;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        rectF = new RectF();
        rect = new Rect();
    }

    public void SetCurrent(float current) {
        Log.i(TAG, "Current：" + current + "，Max：" + max);
        this.current = current;
        invalidate();
    }

    public void SetMax(int max) {
        this.max = max;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeWidth(arcWidth);
        paint.setColor(Color.GRAY);

        float bigCircleRadius = width / 2;

        float smallCircleRadius = bigCircleRadius - arcWidth;

        canvas.drawCircle(bigCircleRadius, bigCircleRadius, smallCircleRadius, paint);
        paint.setColor(getResources().getColor(R.color.gold));
        rectF.set(arcWidth, arcWidth, width - arcWidth, width - arcWidth);


        canvas.drawArc(rectF, 90, current * 360 / max, false, paint);

        String txt = current * 100 / max + "%";
        paint.setStrokeWidth(0);
        paint.setTextSize(70);
        paint.getTextBounds(txt, 0, txt.length(), rect);
        paint.setColor(getResources().getColor(R.color.gold));

        canvas.drawText(txt, bigCircleRadius - rect.width() / 2, bigCircleRadius + rect.height() / 2, paint);
    }


}
