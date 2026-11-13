package com.example.first_android_project;

import java.util.List;

public class ChatResponse {

    List<Choice> choices;


    public static class Choice{
        Message message;
    }

    public static class Message{
        String role;
        String content;
    }


}
