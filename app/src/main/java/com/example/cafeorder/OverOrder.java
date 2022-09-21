package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OverOrder extends AppCompatActivity {
    TextView Login;
    TextView Password;
    TextView Drink;
    TextView Addition;
    TextView Type_drink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_order);

        Login = findViewById(R.id.text_login);
        Password = findViewById(R.id.text_password);
        Drink = findViewById(R.id.text_drink);
        Addition = findViewById(R.id.text_addition);
        Type_drink = findViewById(R.id.text_type_drink);

        Bundle arguments = getIntent().getExtras();

        String login = arguments.get("Login").toString();
        String password = arguments.get("Password").toString();
        String drink = arguments.get("Drink").toString();
        String addition = arguments.get("Addition").toString();
        String type_drink = arguments.get("Type_drink").toString();

        Login.setText(login);
        Password.setText(password);
        Drink.setText(drink);
        Addition.setText(addition);
        Type_drink.setText(String.format("Ваш %s. Спасибо за заказ, хорошего дня.", type_drink));
    }
}