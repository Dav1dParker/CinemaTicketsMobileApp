package com.example.cinemaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
    }

    public void onClickToRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void onClickToMainScreen(View view) {
        Intent intent = new Intent(this, MainScreen.class);
        if (TextUtils.isEmpty(username.getText().toString())) {
            Toast.makeText(MainActivity.this, "Заполните поле 'Логин'", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(MainActivity.this, "Заполните поле 'Пароль'", Toast.LENGTH_LONG).show();
        } else startActivity(intent);
    }
}