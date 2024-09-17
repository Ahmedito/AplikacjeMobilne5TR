package com.example.supercomponent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomCircleView extends View {

    private Paint paint;
    private int circleColor = Color.RED;
    private float circleRadius = 200f;
    private float circleX, circleY;
    private boolean isDragging = false;

    public CustomCircleView(Context context) {
        super(context);
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(circleColor);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (circleX == 0 || circleY == 0) {
            circleX = getWidth() / 2;
            circleY = getHeight() / 2;
        }

        //rysowanie
        canvas.drawCircle(circleX, circleY, circleRadius, paint);
    }

    //zmiana koloru
    public void setCircleColor(int newColor) {
        circleColor = newColor;
        paint.setColor(circleColor);
        invalidate();  //odsiewzanie
    }

    //zmiana promienia
    public void setCircleRadius(float newRadius) {
        circleRadius = newRadius;
        invalidate();
    }

    //zmiana pozycji
    public void setCirclePosition(float x, float y) {
        circleX = x;
        circleY = y;
        invalidate();
    }

    //przeciaganie
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                float dx = event.getX() - circleX;
                float dy = event.getY() - circleY;
                if (Math.sqrt(dx * dx + dy * dy) <= circleRadius) {
                    isDragging = true;
                    setCircleColor(Color.MAGENTA); //zmiana koloru po dotknieciu
                    return true;
                }
                return false;

            case MotionEvent.ACTION_MOVE:

                if (isDragging) {
                    circleX = event.getX();
                    circleY = event.getY();
                    invalidate();
                    return true;
                }
                return false;

            case MotionEvent.ACTION_UP:

                if (isDragging) {
                    isDragging = false;
                    return true;
                }
                return false;

            default:
                return false;
        }
    }
}
