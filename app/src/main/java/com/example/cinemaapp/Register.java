package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.Login);
        password = findViewById(R.id.Password);

    }
    public void CompleteRegistration(View view) {
        Intent intent = new Intent(this, MainScreen.class);
        if (TextUtils.isEmpty(username.getText().toString())) {
            Toast.makeText(Register.this, "Заполните поле 'Логин'", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(Register.this, "Заполните поле 'Пароль'", Toast.LENGTH_LONG).show();
        } else startActivity(intent);
    }
}