package com.example.listcity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    // Buttons used
    Button addButton, deleteButton;
    // Used to store which city has been selected last for delete button function
    String selectedCity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // From lab
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        // List of cities in Alberta
        String[] cities = {
                "Calgary",
                "Edmonton",
                "Red Deer",
                "Lethbridge",
                "St. Albert",
                "Medicine Hat",
                "Grande Prairie",
                "Airdrie",
                "Spruce Grove",
                "Leduc"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Load in the buttons
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);


        // Function for when button is clicked it adds new city
        addButton.setOnClickListener(v -> {
            // Create a text input pop up to enter name of new city
            final EditText inputCity = new EditText(MainActivity.this);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("New City")
                    .setView(inputCity)
                    .setPositiveButton("Add", (dialog, which) -> {
                        String cityName = inputCity.getText().toString().trim();
                            dataList.add(cityName);
                            cityAdapter.notifyDataSetChanged();
                        Snackbar.make(findViewById(android.R.id.content), cityName + " has been added.", Snackbar.LENGTH_SHORT).show();
                    })
                    // Button to close text pop up if you don't want to add anything
                    .setNegativeButton("Cancel", null)
                    .show();
        });


        deleteButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                // Delete selected city
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                Snackbar.make(findViewById(android.R.id.content), selectedCity + " deleted.", Snackbar.LENGTH_SHORT).show();



                selectedCity = null; // After deleted remove selected city
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Select a city first", Snackbar.LENGTH_SHORT).show();

            }
        });

        // If a city is clicked it becomes selected
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            Snackbar.make(findViewById(android.R.id.content), selectedCity + " selected", Snackbar.LENGTH_SHORT).show();

        });
    }

}



