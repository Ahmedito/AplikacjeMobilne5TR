package com.example.nasaphotoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView photoImageView;
    private TextView titleTextView, descriptionTextView;
    private Button loadTodayPhotoButton, selectDateButton;
    private NasaApiService nasaApiService;
    private final String apiKey = "xCfJQrd8h9hJaJODqjAvCwuBK3ni9wG2SshNOYVT"; // Twój klucz API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //widoki
        photoImageView = findViewById(R.id.photoImageView);
        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        loadTodayPhotoButton = findViewById(R.id.loadTodayPhotoButton);
        selectDateButton = findViewById(R.id.selectDateButton);

        //inicjalizacja retrofita
        nasaApiService = ApiClient.getClient().create(NasaApiService.class);

        //akcja loadtodayphoto
        loadTodayPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTodayPhoto();
            }
        });

        //akcja select date
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void loadTodayPhoto() {
        Call<NasaPhoto> call = nasaApiService.getTodayPhoto(apiKey);
        call.enqueue(new Callback<NasaPhoto>() {
            @Override
            public void onResponse(Call<NasaPhoto> call, Response<NasaPhoto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NasaPhoto photo = response.body();
                    titleTextView.setText(photo.getTitle());
                    descriptionTextView.setText(photo.getExplanation());
                    Picasso.get().load(photo.getUrl()).into(photoImageView);
                } else {
                    Toast.makeText(MainActivity.this, "Nie znaleziono zdjęcia na dzisiaj", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NasaPhoto> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Błąd połączenia: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    // Formatowanie daty w formacie YYYY-MM-DD
                    String date = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth);
                    loadPhotoByDate(date);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void loadPhotoByDate(String date) {
        Call<NasaPhoto> call = nasaApiService.getPhotoByDate(apiKey, date);
        call.enqueue(new Callback<NasaPhoto>() {
            @Override
            public void onResponse(Call<NasaPhoto> call, Response<NasaPhoto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NasaPhoto photo = response.body();
                    titleTextView.setText(photo.getTitle());
                    descriptionTextView.setText(photo.getExplanation());
                    Picasso.get().load(photo.getUrl()).into(photoImageView);
                } else {
                    Toast.makeText(MainActivity.this, "Nie znaleziono zdjęcia dla tej daty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NasaPhoto> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Błąd połączenia: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
