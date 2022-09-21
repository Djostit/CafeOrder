package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateOrder extends AppCompatActivity {
    TextView user;
    TextView choice;
    TextView choice_view;

    RadioButton tea;
    RadioButton coffee;

    LinearLayout group;

    Spinner spinner;

    CheckBox check_one;
    CheckBox check_two;
    CheckBox check_three;

    Button buttonorder;

    String message_login;
    String message_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        user = findViewById(R.id.welcome);
        choice = findViewById(R.id.choice);
        choice_view = findViewById(R.id.choice_view);

        tea = findViewById(R.id.tea);
        coffee = findViewById(R.id.coffee);

        group = findViewById(R.id.group_check);
        spinner = findViewById(R.id.spinner);

        check_one = findViewById(R.id.check_one);
        check_two = findViewById(R.id.check_two);
        check_three = findViewById(R.id.check_three);

        buttonorder = findViewById(R.id.buttonorder);

        group.setAlpha(0);
        choice_view.setAlpha(0);
        spinner.setAlpha(0);
        buttonorder.setAlpha(0);

        Bundle arguments = getIntent().getExtras();
        message_login = arguments.get("Login").toString();
        message_password = arguments.get("Password").toString();

        user.setText(String.format("Привет, %s! Что будете заказывать?", message_login));
    }

    private String GetTea(int selectedItemPosition) {
        String[] stringArray = getResources().getStringArray(R.array.array_tea);
        return stringArray[selectedItemPosition];
    }
    private String GetCoffee(int selectedItemPosition) {
        String[] stringArray = getResources().getStringArray(R.array.array_coffee);
        return stringArray[selectedItemPosition];
    }

    public void SetDrink(View view) {
        if (tea.isChecked()){
            choice.setText("Что добавить в ваш чай?");
            group.setAlpha(1);

            check_one.setText("Корица(+30₽)");
            check_two.setText("Лимон(+15₽)");
            check_three.setText("Ваниль(+30₽)");

            choice_view.setAlpha(1);

            ArrayAdapter<?> adapter =
                    ArrayAdapter.createFromResource(this, R.array.array_tea,
                            android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);

            spinner.setAlpha(1);
            buttonorder.setAlpha(1);
        }
        else if (coffee.isChecked()){
            check_three.setEnabled(true);
            check_one.setEnabled(true);
            choice.setText("Что добавить в ваше кофе?");
            group.setAlpha(1);

            check_one.setText("Карамель(+45₽)");
            check_two.setText("Ваниль(+35₽)");
            check_three.setText("Кокос(+50₽)");

            choice_view.setAlpha(1);

            ArrayAdapter<?> adapter =
                    ArrayAdapter.createFromResource(this, R.array.array_coffee,
                            android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);

            spinner.setAlpha(1);
            buttonorder.setAlpha(1);
        }
    }

    public void OverOrder(View view) {
        Intent intent = new Intent(this, OverOrder.class);

        intent.putExtra("Login", message_login);
        intent.putExtra("Password", message_password);

        String drink = null;
        String addition = null;
        String type_drink = null;

        if(tea.isChecked()) {
            drink = "Чай";
            if(check_one.isChecked()){
               addition += ", корица";
            }
            if(check_two.isChecked()){
                addition += ", лимон";
            }
            if(check_three.isChecked()){
                addition += ", ваниль";
            }
            type_drink = GetTea(spinner.getSelectedItemPosition());
        }
        else if(coffee.isChecked()) {
            drink = "Кофе";
            if(check_one.isChecked()){
                addition += ", карамель";
            }
            if(check_two.isChecked()){
                addition += ", ваниль";
            }
            if(check_three.isChecked()){
                addition += ", кокос";
            }
            type_drink = GetCoffee(spinner.getSelectedItemPosition());
        }
        if(addition == null){
            addition = "ничего";
        }
        else{
            addition = addition.substring(6);
        }
        intent.putExtra("Drink", drink);
        intent.putExtra("Addition", addition);
        intent.putExtra("Type_drink", type_drink);

        startActivity(intent);

        addition = null;
    }

    public void OneClick(View view) {
        if (tea.isChecked()){
            if(check_one.isChecked()){
                check_three.setEnabled(false);
            }
            else if(!check_one.isChecked()){
                check_three.setEnabled(true);
            }
        }
    }

    public void ThreeClick(View view) {
        if (tea.isChecked()){
            if(check_three.isChecked()){
                check_one.setEnabled(false);
            }
            else if(!check_three.isChecked()){
                check_one.setEnabled(true);
            }
        }
    }
}