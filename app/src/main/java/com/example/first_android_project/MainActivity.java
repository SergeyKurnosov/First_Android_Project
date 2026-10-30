package com.example.first_android_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

/*
1.Приложение-пользователь вводит URL и открывает его в браузере через неявный интент.
*минимальная валидация URL → автоматическое добавление https:// при необходимости.

 */

public class MainActivity extends AppCompatActivity {
    SeekBar R_,G_,B_;

    Button input;
    EditText textInput;

    int back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        R_ = findViewById(R.id.seekBarR);
        G_ = findViewById(R.id.seekBarG);
        B_ = findViewById(R.id.seekBarB);

        input = findViewById(R.id.go);
        textInput = findViewById(R.id.url);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = textInput.getText().toString();

                String resultURL = sanitizeAndValidateUrl(url);

                if(resultURL!=null){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(resultURL));
                    startActivity(intent);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Url неправильна ?");
                    builder.setTitle("Ошибка !");
                    builder.setCancelable(false);

                }


            }
        });
//https://github.com/curadProgrammer/Java-Paint/blob/main/src/PaintGui.java

        R_.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBackColor(progress, G_.getProgress(), B_.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        G_.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBackColor(R_.getProgress(), progress, B_.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        B_.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBackColor(R_.getProgress(), G_.getProgress(), progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public String sanitizeAndValidateUrl(String userInput){
        if (userInput.isBlank()) {
            return null;
        }

        String trimmedInput = userInput.trim();
        if (!trimmedInput.startsWith("http://") &&
                !trimmedInput.startsWith("https://")) {
           trimmedInput = "https://"+trimmedInput;
        }
        String guessedUrl = URLUtil.guessUrl(trimmedInput);
        boolean isValid = Patterns.WEB_URL.matcher(guessedUrl).matches() &&
                (URLUtil.isHttpUrl(guessedUrl) || URLUtil.isHttpsUrl(guessedUrl));

         if (isValid) {
           return   guessedUrl;
         }

         else return  null;
    }

    void setBackColor(int red, int green, int blue){
        int r = red;
        int g = green;
        int b = blue;
        back = rgbToInt(r,g,b);

        findViewById(R.id.main).setBackgroundColor(Color.rgb( r, g, b));
    }

    int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    int rgbToInt(int red, int green, int blue) {
        red = clamp(red, 0, 255);
        green = clamp(green, 0, 255);
        blue = clamp(blue, 0, 255);
        return (red << 16) | (green << 8) | blue;
    }



}