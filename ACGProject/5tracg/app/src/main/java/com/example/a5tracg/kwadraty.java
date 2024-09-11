package com.example.a5tracg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class kwadraty extends View {
    //constructor
    public kwadraty(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public kwadraty(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public kwadraty(Context context) {
        super(context);
    }

    Random r = new Random();
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        int width = getWidth(), height = getHeight();
        Paint p = new Paint();




        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);

        for (int i = 0; i < 10; i++) {
            p.setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            canvas.drawCircle(r.nextInt(width), r.nextInt(height), r.nextInt(100), p);
        }

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        p.setColor(Color.YELLOW);
        canvas.drawRect(2, 2, width-3, height-3, p);

        super.onDraw(canvas);

    }
}
