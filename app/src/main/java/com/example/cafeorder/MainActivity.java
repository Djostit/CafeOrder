package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText password;
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
    }

    public void CreateOrder(View view) {
        Intent intent = new Intent(this, CreateOrder.class);

        String message_login = login.getText().toString();
        String message_password = password.getText().toString();
        String message_email = email.getText().toString();
        if (message_login.isEmpty() ||
        message_password.isEmpty() ||
        message_email.isEmpty()){
            Toast toast = Toast.makeText(this, "There's nothing in here.",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        intent.putExtra("Login", message_login);
        intent.putExtra("Password", message_password);
        intent.putExtra("Email", message_email);
        startActivity(intent);
    }
}