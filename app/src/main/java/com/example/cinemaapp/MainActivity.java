package com.example.cinemaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.DataBase.DataBaseManager;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    private DataBaseManager DataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        DataBaseManager = new DataBaseManager(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        DataBaseManager.openDB();
    }

    public void onClickToRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void onClickToMainScreen(View view) {
        if (TextUtils.isEmpty(username.getText().toString())) {
            Toast.makeText(MainActivity.this, "Заполните поле 'Логин'", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(MainActivity.this, "Заполните поле 'Пароль'", Toast.LENGTH_LONG).show();
        } else
        {
            if (DataBaseManager.checkAccount(username.getText().toString(),password.getText().toString()))
            {
                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainScreen.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataBaseManager.closeDB();
    }
}