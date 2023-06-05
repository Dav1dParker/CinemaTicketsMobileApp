package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinemaapp.DataBase.DataBaseManager;
import com.example.cinemaapp.DataBase.MyDbHelper;

public class Register extends AppCompatActivity {

    private EditText username, password;
    private DataBaseManager DataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        DataBaseManager = new DataBaseManager(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        DataBaseManager.openDB();
    }

    public void CompleteRegistration(View view) {
        if (TextUtils.isEmpty(username.getText().toString())) {
            Toast.makeText(Register.this, "Заполните поле 'Логин'", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(Register.this, "Заполните поле 'Пароль'", Toast.LENGTH_LONG).show();
        } else {
            DataBaseManager.insertToDB(username.getText().toString(), password.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataBaseManager.closeDB();
    }
}