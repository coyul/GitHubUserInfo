package ru.sberbank.githubuserinfo;

import android.content.Context;
import android.content.res.Resources;
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
    private static final String MY_SECRET_KEY = "";

    private static final String TAG = "JsonDataLoader";


    //get json object from input stream
    public static JSONObject getJsonObject(String userName) {
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

    //parse json object and create new user with parsed data
    public static User getUserFromJsonObject(Context context, JSONObject json) {
        User user = new User();
        try {
            user.setLogin(getStringByRequest(context.getString(R.string.login_request), json, context));
            user.setName(getStringByRequest(context.getString(R.string.name_request), json, context));
            user.setLocation(getStringByRequest(context.getString(R.string.location_request), json, context));
            user.setCompany(getStringByRequest(context.getString(R.string.company_request), json, context));
            user.setAvatarUrl(getStringByRequest(context.getString(R.string.avatar_url_request), json, context));

        } catch (JSONException e) {
            Log.e(TAG, "JSONException", e);
        }
        return user;
    }


    private static String getStringByRequest(String request, JSONObject json, Context context) throws JSONException {

        String result;
        if (json.isNull(request)) {
            result = context.getString(R.string.no_data_result);
        } else result = json.getString(request);
        return result;
    }


}
