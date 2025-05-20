package com.example;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.*;

public class ChatGPTClient {
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", "Hello!");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(message));

        RequestBody body = RequestBody.create(
                mapper.writeValueAsString(requestBody),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
