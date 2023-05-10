package ru.mirea.kucheras.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import ru.mirea.kucheras.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String group = sharedPref.getString("GROUP ", "unknown");
        int number = sharedPref.getInt("NUMBER ", 0);
        String film = sharedPref.getString("FILM OR SERIAL ", "unknown");
        binding.editTextTextPersonName.setText(group);
        binding.editTextTextPersonName2.setText(String.valueOf(number));
        binding.editTextTextPersonName3.setText(film);
        binding.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("GROUP ", binding.editTextTextPersonName.getText().toString());
                editor.putInt("NUMBER ", Integer.parseInt(binding.editTextTextPersonName2.getText().toString()));
                editor.putString("FILM OR SERIAL ", binding.editTextTextPersonName3.getText().toString());
                editor.apply();
            }

        });


    }
}