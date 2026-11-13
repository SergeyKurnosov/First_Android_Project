package com.example.first_android_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private EditText editText , editType;
    private Button button;
    private TextView textView;

    // API-ключ DeepSeek
    private static final String API_KEY = "sk-bd3d6422f39e4ca5a51c309f3ec82e38";

    private final String BASE_URL = "https://api.deepseek.com/";

    // Выбираем модель
    // Для Pro достаточно заменить название на deepseek-v4-pro
    private static final String MODEL = "deepseek-flash";
    //private final String MODEL = "deepseek-v4-pro";

    // Системная инструкция для нейросети
    //   private final String SYSTEM_PROMPT = "Ты должен отвечать в стиле Ивана Грозного.Используй характерную для эпохи лексику но сохраняй понятность и точность ответов";

    // Объект для работы с DeepSeek API
    private DeepSeekApi api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editType = findViewById(R.id.editTextType);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

           HttpLoggingInterceptor logging =
                new HttpLoggingInterceptor();


        logging.redactHeader("Authorization");

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(DeepSeekApi.class);

        button.setOnClickListener(v->{
            String userMessage = editText.getText()
                    .toString()
                    .trim();

            String typeBot = editType.getText()
                    .toString()
                    .trim();

            // Проверяем, что поле не пустое
            if (userMessage.isEmpty()) {
                editText.setError("Введите сообщение");
                return;
            }
            if (typeBot.isEmpty()) {
                editText.setError("Введите стиль бота");
                return;
            }

            textView.setText("DeepSeek думает...");

            // Блокируем кнопку на время запроса
            button.setEnabled(false);

            ChatRequest request = new ChatRequest(
                    typeBot,
                    userMessage
            );

            request.model = MODEL;

            api.sendMessage(
                    "Bearer " + API_KEY,
                    request
            ).enqueue(new Callback<ChatResponse>() {
                @Override
                public void onResponse(
                        Call<ChatResponse> call,
                        Response<ChatResponse> response) {

                    // Разблокируем кнопку
                    button.setEnabled(true);

                    // Проверяем успешность ответа
                    if (response.isSuccessful()
                            && response.body() != null
                            && response.body().choices != null
                            && !response.body().choices.isEmpty()
                            && response.body().choices.get(0).message != null) {

                        // Получаем текст ответа
                        String answer = response.body()
                                .choices.get(0)
                                .message.content;

                        // Отображаем ответ в TextView
                        if (answer != null && !answer.isEmpty()) {
                            textView.setText(answer);
                        } else {
                            textView.setText("Сервер вернул пустой ответ");
                        }

                    } else {

                        // Обрабатываем ошибку API
                        String error = "Неизвестная ошибка";

                        if (response.errorBody() != null) {
                            try {
                                error = response.errorBody().string();
                            } catch (IOException e) {
                                error = e.getMessage();
                            }
                        }

                        // Показываем код HTTP-ошибки
                        textView.setText(
                                "Ошибка API: " + response.code()
                                        + "\n" + error
                        );
                    }
                }

                @Override
                public void onFailure(
                        Call<ChatResponse> call,
                        Throwable t) {

                    // Разблокируем кнопку
                    button.setEnabled(true);

                    // Показываем причину ошибки
                    textView.setText(
                            "Ошибка соединения: " + t.getMessage()
                    );
                }
            });
        });
    }
}







