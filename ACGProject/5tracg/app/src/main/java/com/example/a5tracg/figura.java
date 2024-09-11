package com.example.a5tracg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class figura extends View {

    public figura(Context context) {
        super(context);
    }

    public figura(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public figura(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Random r = new Random();
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        int szer = getWidth();
        int szer2 = szer/2;

        int wys = getHeight();
        int rozmiar = (szer2 < wys ? szer2 : wys) - 10;

        int x, y, dx, dy;
        Paint p = new Paint();

        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);

        p.setColor(Color.GRAY);

        canvas.drawRect(0, 0, szer-1, wys-1, p);

        CharSequence opis = getContentDescription();
        if (opis == null) {
            opis="brak";
        }

        for (int i = 0; i < 10; i++) {
            p.setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));

            if (opis.equals("kolo")) {
                dx = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dx);

                canvas.drawCircle(x, y, dx, p);
            }
            else if (opis.equals("elipsa")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dy);

                RectF rect = new RectF(x, y, x + dx, y + dx);
                canvas.drawOval(rect, p);
            }
            else if (opis.equals("prostokat")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dy);

                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawRect(rect, p);
            }
            else if (opis.equals("prostokat okragly")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dy);

                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawRoundRect(rect, 10, 10, p);
            }
            else if (opis.equals("luk")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dy);
                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawArc(rect, r.nextInt(360), r.nextInt(360), false, p);
            }
            else if (opis.equals("linia")) {
                dx = r.nextInt(szer2);
                dy = r.nextInt(wys);
                x = r.nextInt(szer2);
                y = r.nextInt(wys);

                canvas.drawLine(x, y, dx, dy, p);
            }
        }

        p.setTextSize(getResources().getDimension(R.dimen.wys_napisu));
        p.setTextAlign(Paint.Align.RIGHT);
        p.setColor(Color.BLUE);
        canvas.drawText((String) opis, szer - 20, wys/2, p);

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(1);
        canvas.drawRect(0, 0, szer - 1, wys - 1, p);
        super.onDraw(canvas);
    }
}
