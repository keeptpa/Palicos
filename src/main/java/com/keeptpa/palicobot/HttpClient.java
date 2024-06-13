package com.keeptpa.palicobot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpClient {
    static OkHttpClient client = new OkHttpClient();
    public static String get(String url, String[] args){
        Request req = new Request.Builder()
                .url(url)
                .build();

        Response resp = null;
        String result;
        try {
            resp = client.newCall(req).execute();
            result = resp.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
