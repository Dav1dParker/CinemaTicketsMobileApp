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


    public void startThread(int task) {
        multi thread = new multi();
        thread.setTask(task);
        thread.start();
    }

    class multi extends Thread {

        private int task = 0;

        public void setTask(int task) {
            this.task = task;
        }


        @Override
        public void run() {
            if (task == 1) {
                myDatabase = new MyDatabase(getApplicationContext());
            }
            if (task == 2) {
                myDatabase.OpenDBUsers();
            }
            if (task == 3) {
                if (myDatabase.checkAccount(username.getText().toString(), password.getText().toString())) {
                    PosterHandler.UserName = username.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_LONG).show();
                }
            }
            if (task == 4) {
                myDatabase.CloseDBUsers();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        startThread(1);
    }


    @Override
    protected void onResume() {
        super.onResume();
        username.getText().clear();
        password.getText().clear();
        startThread(2);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        username.getText().clear();
        password.getText().clear();
        startThread(2);
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
        } else {
            startThread(3);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        startThread(4);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startThread(4);
    }
}