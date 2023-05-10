package ru.mirea.kucheras.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.view.View;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ru.mirea.kucheras.securesharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    SharedPreferences secureSharedPreferences = null;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        try {
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            secureSharedPreferences = EncryptedSharedPreferences.create("secret_shared_prefs", mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            String name = secureSharedPreferences.getString("secure", "unknown");
            String foto = secureSharedPreferences.getString("foto", "unknown");
            binding.editTextTextPersonName.setText(name);
            binding.editTextTextPersonName2.setText(foto);

            binding.imageView2.setImageResource(getResources().getIdentifier(foto, "drawable", getPackageName()));
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                secureSharedPreferences.edit().putString("secure", binding.editTextTextPersonName.getText().toString()).apply();
                secureSharedPreferences.edit().putString("foto", binding.editTextTextPersonName2.getText().toString()).apply();
            }

        });
    }
}