package com.avanade.todo.service;

import com.avanade.todo.model.dto.Activity;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class BoredApiService {
    @Value("${boredapi.url.activity.oneparticipant}")
    private String url;
    public Activity callBoredApi(){
        Activity activity = null;
        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();
            Gson gson = new Gson();
            activity = gson.fromJson(response.toString(),Activity.class);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        if(activity == null){
            throw new RestClientException("Invalid response from BoredAPI");
        }
        return activity;
    }
}
