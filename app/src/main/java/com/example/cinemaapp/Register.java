package com.example.cinemaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.DataBase.MyDatabase;


public class Register extends AppCompatActivity {

    private EditText username, password;
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("4");
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        db = new MyDatabase(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db.OpenDBUsers();

    }

    public void CompleteRegistration(View view) {
        if (TextUtils.isEmpty(username.getText().toString())) {
            Toast.makeText(Register.this, "Заполните поле 'Логин'", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(Register.this, "Заполните поле 'Пароль'", Toast.LENGTH_LONG).show();
        } else {
            db.insertToUserDB(username.getText().toString(), password.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.CloseDBUsers();
    }
}