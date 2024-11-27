package com.example.recyclerview1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {

    private EditText editTaskName, editTaskDescription;
    private Button saveTaskButton;
    private int taskPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        editTaskName = findViewById(R.id.editTaskName);
        editTaskDescription = findViewById(R.id.editTaskDescription);
        saveTaskButton = findViewById(R.id.saveTaskButton);

        Intent intent = getIntent();
        taskPosition = intent.getIntExtra("taskPosition", -1);
        String taskName = intent.getStringExtra("taskName");
        String taskDescription = intent.getStringExtra("taskDescription");

        editTaskName.setText(taskName);
        editTaskDescription.setText(taskDescription);

        saveTaskButton.setOnClickListener(v -> {
            String updatedName = editTaskName.getText().toString();
            String updatedDescription = editTaskDescription.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("taskPosition", taskPosition);
            resultIntent.putExtra("updatedName", updatedName);
            resultIntent.putExtra("updatedDescription", updatedDescription);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
