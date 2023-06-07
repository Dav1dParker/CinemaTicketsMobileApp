package com.example.cinemaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.DataBase.MyDatabase;
import com.example.cinemaapp.movies.PosterHandler;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        myDatabase = new MyDatabase(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        username.getText().clear();
        password.getText().clear();
        myDatabase.OpenDBUsers();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        username.getText().clear();
        password.getText().clear();
        myDatabase.OpenDBUsers();
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

            if (myDatabase.checkAccount(username.getText().toString(),password.getText().toString()))
            {
                PosterHandler.UserName = username.getText().toString();
                Intent intent = new Intent(this, MainScreen.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_LONG).show();
            }

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        myDatabase.CloseDBUsers();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDatabase.CloseDBUsers();
    }
}