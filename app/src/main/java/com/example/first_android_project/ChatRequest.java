package com.example.first_android_project;

import android.os.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatRequest {
    String model;
    int max_tokens;

    Thinking thinking;

    String reasoning_effort;
    List<Message> messages;

    public ChatRequest(String systemPrompt, String userMessage) {
        this.model = "deepseek-flash";
        this.max_tokens = 300;
        this.thinking = new Thinking("disabled");
        messages = new ArrayList<>();
        this.messages.add(new Message("system", systemPrompt));
        this.messages.add(new Message("user", userMessage));
        this.reasoning_effort = "none";


    }

    public static class Thinking {

        String type;

        public Thinking(String type) {
            this.type = type;
        }
    }

    public static class Message{
        String role;
        String content;


        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

}
