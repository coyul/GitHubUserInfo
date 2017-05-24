package ru.sberbank.githubuserinfo;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JsonDataLoader {

    private static final String SINGLE_USER_GITHUB_API = "https://api.github.com/users/%s?client_id=%s&client_secret=%s";
    private static final String MY_SECRET_ID = "350c64030f15ed08c0ed";
    private static final String MY_SECRET_KEY = "0a5071b2eef6c872f78af2874e4f75c85ea5f3e2";

    private static final String TAG = "JsonDataLoader";

    public static JSONObject getJsonObject(Context context, String userName) {
        JSONObject result = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(String.format(SINGLE_USER_GITHUB_API, userName, MY_SECRET_ID, MY_SECRET_KEY));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                reader.close();

                result = new JSONObject(sb.toString());

            }
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        } catch (JSONException t) {
            Log.e(TAG, "JSONExeption", t);
        } finally {
            if (connection != null) connection.disconnect();
        }


        return result;
    }


}
