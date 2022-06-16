package com.utils;

import com.DTO.GoogleDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class GoogleUtils {
    public static GoogleDTO getInfo(String urlParameters) throws IOException {

        URLConnection connection = new URL("https://accounts.google.com/o/oauth2/token").openConnection();
        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(urlParameters);
        writer.flush();

        //get BODY of the response
        String line, outputString = "";
        try (BufferedReader is = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            while ((line = is.readLine()) != null) {
                outputString += line;
            }
        }

        JsonObject json = (JsonObject) JsonParser.parseString(outputString);
        String access_token = json.get("access_token").getAsString();

        //get user info
        connection = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token).openConnection();
        outputString = "";
        try (BufferedReader is = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            while ((line = is.readLine()) != null) {
                outputString += line;
            }
        }

        // Convert JSON response into Pojo class
        GoogleDTO data = new Gson().fromJson(outputString, GoogleDTO.class);
        writer.close();
        return data;
    }
}
