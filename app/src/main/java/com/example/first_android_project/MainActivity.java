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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    /*
    Задача 1. WebView: мини-браузер
Что сделать:
На экране поле EditText, кнопка «Перейти» и WebView под ними.
По нажатию на кнопку загружается адрес из поля (если ввёл без https://, дописать его в коде).
Ссылки внутри страницы открываются в самом приложении, а не во внешнем браузере.
Кнопка «Назад» идёт по истории WebView, а когда истории нет, закрывает приложение.

2.Service: счётчик в Logcat
Что сделать:
Создать CounterService (started-сервис) и объявить его в манифесте.
В onCreate, onStartCommand и onDestroy писать в лог (Log.d("SVC", ...)).
После запуска сервис каждую секунду пишет в лог «Тик 1», «Тик 2», «Тик 3» и так далее.
В MainActivity две кнопки: «Старт» (startService) и «Стоп» (stopService).
После нажатия «Стоп» тики должны прекратиться.

Log.d

     */

    EditText editText;
    Button button;
    WebView webView;
    WebSettings webSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        webView = findViewById(R.id.webview);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(webView.canGoBack()){
                    webView.goBack();
                }
                else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if(text.length()>5){
                    if(!text.startsWith("https://")){
                        text = "https://"+text;
                    }
                    webView.loadUrl(text);

                }
            }
        });


    }


}





