package com.example.first_android_project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeepSeekApi {
    @Headers("Content-Type: application/json")
    @POST("chat/completions")
    Call<ChatResponse> sendMessage(

            // Передаем ключ API в HTTP-заголовке
            @Header("Authorization") String apiKey,

            // Передаем тело запроса
            @Body ChatRequest request
    );


}
