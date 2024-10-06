package com.example.asynctask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int EDIT_ITEM_REQUEST_CODE = 1;
    private static final int ADD_ITEM_REQUEST_CODE = 2;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);


        addSampleProducts();


        listView.setVisibility(View.GONE);

        findViewById(R.id.showAllButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.notifyDataSetChanged();

                listView.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.addNewItemButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nowa aktywnosc dodawania elementu
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
            }
        });

        // długie przytrzymanie elementu listy, aby go edytować
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String item = itemList.get(position);
                String[] parts = item.split(" - ");
                String name = parts[0];  // pobieranie nazwy produktu
                String price = parts[1];  // pobieranie ceny
                String description = parts[2];  // pobieranie opisu


                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("name", name);
                intent.putExtra("price", price);
                intent.putExtra("description", description);
                startActivityForResult(intent, EDIT_ITEM_REQUEST_CODE);
                return true;
            }
        });
    }

    private void addSampleProducts() {
        itemList.add("Mleko - 10 - Smaczne");
        itemList.add("Chleb - 5 - Świeży");
        itemList.add("Ser - 15 - Pyszny");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                // Dodaj nowy element do listy
                String newName = data.getStringExtra("name");
                String newPrice = data.getStringExtra("price");
                String newDescription = data.getStringExtra("description");
                itemList.add(newName + " - " + newPrice + " - " + newDescription);
                adapter.notifyDataSetChanged();
            }
        }

        if (requestCode == EDIT_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                //  zaktualizuj element po eydjci
                int position = data.getIntExtra("position", -1);
                String updatedName = data.getStringExtra("name");
                String updatedPrice = data.getStringExtra("price");
                String updatedDescription = data.getStringExtra("description");

                //aktualizcja itemu na liscie
                itemList.set(position, updatedName + " - " + updatedPrice + " - " + updatedDescription);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
