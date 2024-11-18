package com.example.egzaminzaw0;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Deklaracja zmiennych
    private ImageView[] diceImages;
    private int[] diceValues;
    private TextView tvRollResult, tvTotalScore;
    private int totalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja elementów
        diceImages = new ImageView[]{
                findViewById(R.id.dice_1),
                findViewById(R.id.dice_2),
                findViewById(R.id.dice_3),
                findViewById(R.id.dice_4),
                findViewById(R.id.dice_5),
        };
        diceValues = new int[5];
        tvRollResult = findViewById(R.id.tvRollResult);
        tvTotalScore = findViewById(R.id.tvTotalScore);

        Button btnRollDice = findViewById(R.id.btnRollDice);
        Button btnReset = findViewById(R.id.btnReset);

        // Obsługa przycisku "RZUĆ KOŚĆMI"
        btnRollDice.setOnClickListener(v -> rollDice());

        // Obsługa przycisku "RESETUJ WYNIK"
        btnReset.setOnClickListener(v -> resetGame());
    }

    private void rollDice() {
        Random random = new Random();
        int[] counts = new int[6];
        int roundScore = 0;

        for (int i = 0; i < 5; i++) {
            diceValues[i] = random.nextInt(6) + 1; 
            counts[diceValues[i] - 1]++; // Zwiększenie licznika dla danej liczby
            updateDiceImage(i, diceValues[i]);
        }

        // Obliczanie wyniku rundy
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= 2) { // Jeśli liczba wystąpiła dwa razy lub więcej
                roundScore += (i + 1) * counts[i];
            }
        }

        totalScore += roundScore;
        tvRollResult.setText("Wynik tego losowania: " + roundScore);
        tvTotalScore.setText("Wynik gry: " + totalScore);
    }

    private void resetGame() {
        totalScore = 0;
        tvRollResult.setText("Wynik tego losowania: 0");
        tvTotalScore.setText("Wynik gry: 0");

        for (ImageView diceImage : diceImages) {
            diceImage.setImageResource(R.drawable.question);
        }
    }

    private int calculateScore(int value) {
        // Obliczenie punktów
        return value; // Dodaje wartość każdej kostki
    }

    private void updateDiceImage(int index, int value) {
        int resourceId = getResources().getIdentifier("dice_" + value, "drawable", getPackageName());
        diceImages[index].setImageResource(resourceId);
    }
}
