package com.example.supercomponent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CustomCircleView customCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //WIDOK
        customCircleView = findViewById(R.id.custom_circle_view);


        customCircleView.setCircleRadius(300f);  //zmiana promienia
        customCircleView.setCirclePosition(500f, 800f);  //przesuniecie
    }
}
