package com.example.imagevieworsmth;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button imageInfoButton = findViewById(R.id.imageInfoButton);
        Button monochromeButton = findViewById(R.id.monochromeButton);
        TextView imageInfoText = findViewById(R.id.imageInfoText);
        ImageView imageView = findViewById(R.id.imageView);

        imageInfoButton.setOnClickListener((view) -> {
            Bitmap imageBitmap = ((imageView.getDrawable()) != null ? ((BitmapDrawable) imageView.getDrawable()).getBitmap() : null);
            if (imageBitmap == null) return;


            imageInfoText.setText("Image view size: h:" + imageView.getHeight() + "; w: " + imageView.getWidth() + " ///// Bitmap size: h:" + imageBitmap.getHeight() + "; w: " + imageBitmap.getWidth());
        });

        //action of monochromatic button
        monochromeButton.setOnClickListener((view) -> {
            Bitmap imageBitmap = ((imageView.getDrawable()) != null ? ((BitmapDrawable) imageView.getDrawable()).getBitmap() : null);

            if (imageBitmap != null) {
               //change colors bam
                Bitmap mutableBitmap = imageBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Paint paint = new Paint();
                ColorMatrix colorMatrix = new ColorMatrix();
                colorMatrix.setSaturation(0); //monochromatic color rh
                paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));


                Canvas canvas = new Canvas(mutableBitmap);
                canvas.drawBitmap(mutableBitmap, 0, 0, paint);


                imageView.setImageBitmap(mutableBitmap);
            }
        });
    }
}
