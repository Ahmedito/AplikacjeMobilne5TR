package com.example.recyclerview1;
asdf
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, 1);
        });

        // Ustawiamy listenera dla kliknięcia na element
        taskAdapter.setOnItemClickListener(position -> {
            // Przekazujemy dane zadania do aktywności edycji
            Task task = taskList.get(position);
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            intent.putExtra("taskName", task.getName());
            intent.putExtra("taskDescription", task.getDescription());
            intent.putExtra("taskPosition", position);
            startActivityForResult(intent, 2);
        });
    }

    // Odbieranie danych z AddTaskActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                String name = data.getStringExtra("taskName");
                String description = data.getStringExtra("taskDescription");
                taskList.add(new Task(name, description));
                taskAdapter.notifyDataSetChanged();
            } else if (requestCode == 2) {
                String name = data.getStringExtra("taskName");
                String description = data.getStringExtra("taskDescription");
                int position = data.getIntExtra("taskPosition", -1);
                if (position != -1) {
                    Task task = taskList.get(position);
                    task.setName(name);
                    task.setDescription(description);
                    taskAdapter.notifyItemChanged(position);
                }
            }
        }
    }
}
