package com.example.bubblesort;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService executorService;
    private ProgressBar progressBar;
    private SortingVisualizationView visualizationView;
    private int[] dataArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputCount = findViewById(R.id.inputCount);
        Button startButton = findViewById(R.id.startButton);
        progressBar = findViewById(R.id.progressBar);
        visualizationView = findViewById(R.id.visualizationView);

        startButton.setOnClickListener(v -> {
            int count = Integer.parseInt(inputCount.getText().toString());
            startSorting(count);
        });
    }

    private void startSorting(int count) {
        dataArray = generateRandomArray(count);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);
        visualizationView.updateArray(dataArray);
        executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            bubbleSort(dataArray);
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Sortowanie zakończone!", Toast.LENGTH_SHORT).show();
                updateVisualization();  //pokazanie posortowanej tablicy
            });
        });
    }

    private int[] generateRandomArray(int count) {
        Random random = new Random();
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = random.nextInt(1000);  //ograniczenie do 1000 zeby sie renderowalo a nie bugowalo
        }
        return array;
    }

    private void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;

            int progress = (int) ((i / (float) (n - 1)) * 100);
            updateProgress(progress);
            updateVisualization();
            try {
                Thread.sleep(55);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProgress(int progress) {
        runOnUiThread(() -> progressBar.setProgress(progress));
    }

    private void updateVisualization() {
        runOnUiThread(() -> {
            visualizationView.updateArray(dataArray);  // aktualizacja słupków
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}

//wczytywac to poszczegolnego elementu listy nastpene zadanie
//jsonarray
//retrofit
