package com.pankaj.readcsv;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String[] data;
    TextView text;
    private EditText input;
    Button btn_click;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        input = findViewById(R.id.inputField);
        btn_click = findViewById(R.id.click_me);

        btn_click.setOnClickListener(v -> {
            name = input.getText().toString().toLowerCase(Locale.ROOT).trim();
            //Method to fetch the value from csv file and display on TextView
            display_data(name);
        });
    }

    private void display_data(String name) {

        //data is my csv file into raw folder.
        //You can change the csv file.
        //You can also Update the csv file.

        InputStream inputStream = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                data = csvLine.split(",");

                try {
                    if (data[0].equals(name)) {
                        text.setText("Age: " + data[1]);
                        break;
                    } else {
                        text.setText("Not Found in Dataset !.. ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}