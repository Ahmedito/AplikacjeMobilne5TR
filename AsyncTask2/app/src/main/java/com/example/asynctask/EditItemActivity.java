package com.example.asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends Activity {

    private EditText nameEditText;
    private EditText priceEditText;
    private EditText descriptionEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        // Inicjalizacja widoków
        nameEditText = findViewById(R.id.nameEditText);
        priceEditText = findViewById(R.id.priceEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.saveButton);

        // Pobierz dane z Intentu
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String description = intent.getStringExtra("description");

        // Ustaw dane w polach tekstowych
        nameEditText.setText(name);
        priceEditText.setText(price);
        descriptionEditText.setText(description);

        // Ustawienie listenera na przycisku Zapisz
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pobranie zmodyfikowanych danych z pól tekstowych
                String updatedName = nameEditText.getText().toString();
                String updatedPrice = priceEditText.getText().toString();
                String updatedDescription = descriptionEditText.getText().toString();


                Intent resultIntent = new Intent();
                resultIntent.putExtra("position", position);
                resultIntent.putExtra("name", updatedName);
                resultIntent.putExtra("price", updatedPrice);
                resultIntent.putExtra("description", updatedDescription);


                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
