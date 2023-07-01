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
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        startThread(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startThread(2);
    }

    public void startThread(int task) {
        multithreading thread = new multithreading();
        thread.setTask(task);
        thread.start();
    }

    class multithreading extends Thread {

        private int task = 0;

        public void setTask(int task) {
            this.task = task;
        }

        @Override
        public void run() {
            if (task == 1) {
                db = new MyDatabase(getApplicationContext());
            }
            if (task == 2) {
                db.OpenDBUsers();
            }
            if (task == 3) {
                db.CloseDBUsers();
            }
        }

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
        startThread(3);
    }
}