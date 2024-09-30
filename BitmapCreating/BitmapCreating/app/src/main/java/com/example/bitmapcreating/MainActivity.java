package com.example.bitmapcreating;

/*
stworzyc wlasna bitmape
 button1 : przycisk kreuj - tworzy bitmape i podaje jej wielkosc a gdy
cos juz jest narysowane to robi przezroczyste tlo lub biale, obok przycisku pokazuje tez
wiadomosc zwrotna czy bitmapa zostala stworzona i jaka ma wielkosc
aby cokolwiek narysowac trzeba kliknac przycisk kreuj i po tym wybrac dopiero
co chcemy rysowac np. pierw klikamy kreuj a pozniej linie i rysuja sie linie
i znowu klikamy kreuj i bitmapa zmienia kolor na bialy lub przezroczysty i np. prostokaty
i rysuja sie prostakaty
button2: czysc - klikniecie ma crashowac bitmape
button3: linie - tworzy linie na bitmapie losowa ilosc, losowa wielkosc i kolor
ale tak zeby nie wychodzily za bitmape
button4: elipsy - tworzy elipsy na bitmapie losowa ilosc, losowa wielkosc i kolor
ale tak zeby nie wychodzily za bitmape
button5: prostokaty - tworzy prostkaty na bitmapie losowa ilosc, losowa wielkosc i kolor
ale tak zeby nie wychodzily za bitmape

randomowa ilosc tych elementow, randomowa wielkosc, randomowe kolory,
ale nie maja wychodzic za bitmape
ponownie klikniecie kreuj robi przezroczyste tlo lub bialo tło czyli tak jakby czysci bitmape
pod tym wlasna bitmapa (tlo przezroczyste)



klika sie przycisk kreuj i pozniej linie i rysuja sie linie po tym klikamy czysc
i bitmapa sie czysci i tak samo z elipsami i prostokatami


jesli elementow bedzie duzo to bitmapa musi sie zwiekszyc
kreuj zbiera wysokosc i szerokosc bitmapy bitmap.create.bitmap
 */
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private ImageView imageView;
    private int bitmapWidth = 500;
    private int bitmapHeight = 500;
    private boolean isBitmapDrawn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        paint = new Paint();

        Button buttonCreate = findViewById(R.id.buttonCreate);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonLines = findViewById(R.id.buttonLines);
        Button buttonEllipses = findViewById(R.id.buttonEllipses);
        Button buttonRectangles = findViewById(R.id.buttonRectangles);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBitmap();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBitmap();
            }
        });

        buttonLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBitmapDrawn) {
                    drawRandomLines();
                    isBitmapDrawn = true;
                } else {
                    Toast.makeText(MainActivity.this, "Najpierw wyczyść bitmapę", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonEllipses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBitmapDrawn) {
                    drawRandomEllipses();
                    isBitmapDrawn = true;
                } else {
                    Toast.makeText(MainActivity.this, "Najpierw wyczyść bitmapę", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRectangles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBitmapDrawn) {
                    drawRandomRectangles();
                    isBitmapDrawn = true;
                } else {
                    Toast.makeText(MainActivity.this, "Najpierw wyczyść bitmapę", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createBitmap() {
        bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT);
        imageView.setImageBitmap(bitmap);
        Toast.makeText(this, "Bitmapa utworzona: " + bitmapWidth + "x" + bitmapHeight, Toast.LENGTH_SHORT).show();
        isBitmapDrawn = false;
    }

    private void clearBitmap() {
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
            imageView.setImageBitmap(null);
            Toast.makeText(this, "Bitmapa została usunięta", Toast.LENGTH_SHORT).show();
            isBitmapDrawn = false;  
        }
    }

    private void drawRandomLines() {
        if (canvas == null) return;
        Random random = new Random();
        int count = random.nextInt(10) + 1;
        for (int i = 0; i < count; i++) {
            int startX = random.nextInt(bitmapWidth);
            int startY = random.nextInt(bitmapHeight);
            int stopX = random.nextInt(bitmapWidth);
            int stopY = random.nextInt(bitmapHeight);
            paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            paint.setStrokeWidth(5);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
        imageView.setImageBitmap(bitmap);
    }

    private void drawRandomEllipses() {
        if (canvas == null) return;
        Random random = new Random();
        int count = random.nextInt(10) + 1;
        for (int i = 0; i < count; i++) {
            int left = random.nextInt(bitmapWidth / 2);
            int top = random.nextInt(bitmapHeight / 2);
            int right = left + random.nextInt(bitmapWidth / 2);
            int bottom = top + random.nextInt(bitmapHeight / 2);
            paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            canvas.drawOval(left, top, right, bottom, paint);
        }
        imageView.setImageBitmap(bitmap);
    }

    private void drawRandomRectangles() {
        if (canvas == null) return;
        Random random = new Random();
        int count = random.nextInt(10) + 1;
        for (int i = 0; i < count; i++) {
            int left = random.nextInt(bitmapWidth / 2);
            int top = random.nextInt(bitmapHeight / 2);
            int right = left + random.nextInt(bitmapWidth / 2);
            int bottom = top + random.nextInt(bitmapHeight / 2);
            paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            canvas.drawRect(left, top, right, bottom, paint);
        }
        imageView.setImageBitmap(bitmap);
    }
}
