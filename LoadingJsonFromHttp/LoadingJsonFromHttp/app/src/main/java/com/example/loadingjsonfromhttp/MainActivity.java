package com.example.loadingjsonfromhttp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Message> messageList;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        messageList = new ArrayList<>();
        adapter = new MessageAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new FetchMessagesTask().execute("http://json.itmargen.com/5TR/");
    }

    private class FetchMessagesTask extends AsyncTask<String, Void, String> {
        private String errorMessage = null;

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadData(urls[0]);
            } catch (UnknownHostException e) {
                errorMessage = "Brak połączenia z serwerem.";
                Log.e("MainActivity", "Błąd: " + errorMessage, e);
            } catch (Exception e) {
                errorMessage = "Błąd podczas pobierania danych";
                Log.e("MainActivity", "Błąd podczas pobierania danych: ", e);
            }
            return null;
        }

        private String downloadData(String urlString) throws Exception {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                return result.toString();
            } finally {
                urlConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    Log.d("JSON_RESPONSE", result); // Logowanie odpowiedzi
                    JSONArray jsonArray = new JSONArray(result);
                    messageList.clear(); // Wyczyść listę przed dodaniem nowych danych
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");
                        String date = jsonObject.getString("date");
                        String author = jsonObject.getString("author");
                        String content = jsonObject.getString("content");

                        // Dodawanie danych do listy jako obiekt Message
                        Message message = new Message(title, description, date, author, content);
                        messageList.add(message);
                    }

                    // Aktualizacja adaptera
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.e("MainActivity", "Błąd podczas parsowania JSON: ", e);
                    Toast.makeText(MainActivity.this, "Błąd podczas parsowania danych", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.e("MainActivity", "Brak danych z serwera.");
                if (errorMessage != null) {
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Brak danych z serwera", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
