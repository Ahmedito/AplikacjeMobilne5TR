package com.example.bubblesort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SortingVisualizationView extends View {

    private int[] dataArray;

    public SortingVisualizationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.TRANSPARENT);  // Ustawienie przezroczystego tła
    }

    public void updateArray(int[] array) {
        this.dataArray = array;
        invalidate();  // Ponowne rysowanie widoku
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dataArray != null) {
            drawBars(canvas);
        }
    }

    private void drawBars(Canvas canvas) {
        int width = getWidth();   // Szerokość widoku
        int height = getHeight(); // Wysokość widoku

        // Obliczamy szerokość słupka, by zajęły całą szerokość widoku
        float barWidth = (float) width / dataArray.length;

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        for (int i = 0; i < dataArray.length; i++) {
            // Obliczamy wysokość słupka proporcjonalnie do wartości
            int barHeight = (int) ((dataArray[i] / 1000.0) * height);

            // Rysujemy prostokąt (słupek)
            canvas.drawRect(i * barWidth, height - barHeight, (i + 1) * barWidth, height, paint);
        }
    }
}
