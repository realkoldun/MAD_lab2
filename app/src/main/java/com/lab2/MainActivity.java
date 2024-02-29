package com.lab2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

enum Guessing {
    gGuess,
    gBigger,
    gLess,
    gError;
} // класс Guessing для проверки числа

public class MainActivity extends AppCompatActivity {

    int computerNumber;
    int userNumber;
    final int RANGE_NUM = 100;

    void showDialog(Guessing g) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        if (g.equals(Guessing.gBigger)) dlgAlert.setMessage("Ваше число больше");
        if (g.equals(Guessing.gLess)) dlgAlert.setMessage("Ваше число меньше");
        if (g.equals(Guessing.gGuess)) dlgAlert.setMessage("Вы угадали");
        if (g.equals(Guessing.gError)) dlgAlert.setMessage("Неверный формат ввода");
        dlgAlert.setTitle("Угадай число");
        dlgAlert.setPositiveButton("Понял", null);
        dlgAlert.create().show();
    } //показ диалогового окна с разными сообщениями
    void newRNumber() {
        computerNumber = new Random().nextInt(RANGE_NUM);
    }
    public void getValue(View view) {
        EditText userText = findViewById(R.id.inputNumber);
        try {
            userNumber = Integer.parseInt(userText.getText().toString());
            userNumber = Integer.parseInt(userText.getText().toString());
            if(computerNumber == userNumber) {
                showDialog(Guessing.gGuess);
                newRNumber();
            } else {
                if(computerNumber < userNumber) showDialog(Guessing.gBigger);
                else showDialog(Guessing.gLess);
            }
        } catch(NumberFormatException e) {
            showDialog(Guessing.gError);
        }
    } // функция, срабатывающая после нажатия кнопки (описано в activity_main)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newRNumber();
        //System.console().printf(String.valueOf(computerNumber));
    } // запуск приложения
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    } // после сворачивания приложения, процессы завершаются
}