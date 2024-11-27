package com.example.egzaminzaw2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

private int licznikPolubien = 0;
        private TextView licznikTextView;
@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //inicjalizacja widoków
    licznikTextView = findViewById(R.id.likes_counter);
    Button polubButton = findViewById(R.id.like_button);
    Button usunButton = findViewById(R.id.unlike_button);

    //obsługa przycisku polub
    polubButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            licznikPolubien++;
            aktualizujLicznik();
        }
    });
    //obługa przycisku "USUŃ"
    usunButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (licznikPolubien > 0) {
                licznikPolubien--;

            }
            aktualizujLicznik();
        }
    });
}
//Aktualizacja licznika polubień
    private void aktualizujLicznik(){
    licznikTextView.setText(licznikPolubien + " polubień");
    }
    }
