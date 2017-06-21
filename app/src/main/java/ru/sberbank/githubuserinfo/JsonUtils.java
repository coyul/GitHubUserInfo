package ru.sberbank.githubuserinfo;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static JSONObject getJsonFromIS(InputStream is) {
        JSONObject json = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            json = new JSONObject(sb.toString());
            reader.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        } catch (JSONException t) {
            Log.e(TAG, "JSONException", t);
        }
        return json;
    }
}
