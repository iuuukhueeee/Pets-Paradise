package com.utils;

import com.DTO.GoogleDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GoogleUtils {
    public static GoogleDTO getInfo(String urlParameters) throws MalformedURLException, IOException {
        URL url = new URL("https://accounts.google.com/o/oauth2/token");
        URLConnection urlConn = url.openConnection();
        urlConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
        writer.write(urlParameters);
        writer.flush();

        String line, outputString = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                urlConn.getInputStream()));
        while ((line = reader.readLine()) != null) {
            outputString += line;
        }
        System.out.println(outputString);
        JsonObject json = (JsonObject) new JsonParser().parse(outputString);
        String access_token = json.get("access_token").getAsString();
        System.out.println(access_token);

        //get User Info
        url = new URL(
                "https://www.googleapis.com/oauth2/v1/userinfo?access_token="
                        + access_token);
        urlConn = url.openConnection();
        outputString = "";
        reader = new BufferedReader(new InputStreamReader(
                urlConn.getInputStream()));
        while ((line = reader.readLine()) != null) {
            outputString += line;
        }

        // Convert JSON response into Pojo class
        GoogleDTO data = new Gson().fromJson(outputString, GoogleDTO.class);
        writer.close();
        reader.close();

        return data;
    }
}
