package com.example.recyclerview1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskName, taskDescription;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskName = findViewById(R.id.taskName);
        taskDescription = findViewById(R.id.taskDescription);
        saveButton = findViewById(R.id.saveButton);


        Intent intent = getIntent();
        String name = intent.getStringExtra("taskName");
        String description = intent.getStringExtra("taskDescription");
        int position = intent.getIntExtra("taskPosition", -1);

        if (name != null && description != null) {
            taskName.setText(name);
            taskDescription.setText(description);
        }

        saveButton.setOnClickListener(v -> {
            String updatedName = taskName.getText().toString();
            String updatedDescription = taskDescription.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("taskName", updatedName);
            resultIntent.putExtra("taskDescription", updatedDescription);

            if (position != -1) {
                resultIntent.putExtra("taskPosition", position); 
            }

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
