package com.example.first_android_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    /*

https://github.com/public-apis/public-apis // apis

{
"coord":{"lon":37.6156,"lat":55.7522},
    "weather":[{"id":804,"main":"Clouds","description":"пасмурно","icon":"04d"}],
    "base":"stations",
    "main":{"temp":3.27,"feels_like":0.6,"temp_min":3.15,"temp_max":4.65,"pressure":1013,"humidity":54,"sea_level":1013,"grnd_level":994},
    "visibility":10000,
    "wind":{"speed":2.79,"deg":171,"gust":6.43},
    "clouds":{"all":90},
    "dt":1794397904,
    "sys":{"type":2,"id":2095214,"country":"RU","sunrise":1794372964,"sunset":1794403867},"timezone":10800,"id":524901,"name":"Москва",
    "cod":200}
     */

    private final String API_KEY = "a7b76e3c606d48effb4e257c3ff96b01";
    private final String BASE_URL = "https://api.openweathermap.org";

EditText edCity;
Button buttonWeather;
TextView textWeather;
WeatherApi weatherApi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edCity = findViewById(R.id.editCity);
        buttonWeather = findViewById(R.id.buttonWeather);
        textWeather = findViewById(R.id.textWeather);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);

        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edCity.getText().toString();

                if(city.isEmpty()){
                    edCity.setText("Введите город");
                    return;
                }
                textWeather.setText("Загрузка");

                getWeather(city);
            }
        });

    }

    private void getWeather(String city) {
    Call<JsonObject> call = weatherApi.getWeather(city, API_KEY,"metric","ru");

    call.enqueue(new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if(response.isSuccessful()&&response.body()!=null){
                JsonObject json = response.body();
                String cityName = json.get("name").getAsString();
                JsonObject main = json.getAsJsonObject("main");

                double temp = main.get("temp").getAsDouble();
                int humidity = main.get("humidity").getAsInt();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //        Данные дополнительные  :
                //"feels_like":0.6,"temp_min":3.15,"temp_max":4.65,"pressure":1013
                double temp_feels_like =  main.get("feels_like").getAsDouble();
                double temp_min =  main.get("temp_min").getAsDouble();
                double temp_max =  main.get("temp_max").getAsDouble();
                int pressure = main.get("pressure").getAsInt();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                JsonArray weatherArray = json.getAsJsonArray("weather");
                JsonObject weather = weatherArray.get(0).getAsJsonObject();
                String description = weather.get("description").getAsString();
                JsonObject wind = json.getAsJsonObject("wind");
                double windSpeed = wind.get("speed").getAsDouble();

                String result = "City "+cityName+
                        "\nTemp "+temp+
                        "\nTemp (feel) "+temp_feels_like+
                        "\nTemp (min) "+temp_min+
                        "\nTemp (max) "+temp_max+
                        "\n Влажность "+humidity+
                        "\n Давление (мм рт. ст.) "+pressure+
                        "\nПогода "+description+
                        "\nСкорость ветра "+ windSpeed;
                textWeather.setText(result);
            }else {
                textWeather.setText("Ошибка сервера "+response.code());
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable throwable) {
            textWeather.setText("Ошибка подключения  "+throwable.getMessage());
        }
    });
    }
}







