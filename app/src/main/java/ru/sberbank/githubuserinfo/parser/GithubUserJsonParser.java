package ru.sberbank.githubuserinfo.parser;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.sberbank.githubuserinfo.JsonUtils;
import ru.sberbank.githubuserinfo.R;
import ru.sberbank.githubuserinfo.User;


public class GithubUserJsonParser implements UserJsonParser {

    private static final String SINGLE_USER_GITHUB_API = "https://api.github.com/users/%s?client_id=%s&client_secret=%s";
    private static final String MY_SECRET_ID = "350c64030f15ed08c0ed";
    private static final String MY_SECRET_KEY = "";

    private static final String TAG = "GithubUserJsonParser";
    private Context mContext;

    public GithubUserJsonParser(Context context) {
        mContext = context;
    }

    //parse json object and create new user with parsed data
    public User getUserFromJson(JSONObject json) {
        User user = new User();

        try {
            user.setLogin(getStringByRequest(mContext.getString(R.string.login_request), json, mContext));
            user.setName(getStringByRequest(mContext.getString(R.string.name_request), json, mContext));
            user.setLocation(getStringByRequest(mContext.getString(R.string.location_request), json, mContext));
            user.setCompany(getStringByRequest(mContext.getString(R.string.company_request), json, mContext));
            user.setAvatarUrl(getStringByRequest(mContext.getString(R.string.avatar_url_request), json, mContext));

        } catch (JSONException e) {
            Log.e(TAG, "JSONException", e);
        }
        return user;
    }


    //get json object from input stream
    public JSONObject getJsonByUserName(String userName) {
        JSONObject result = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(String.format(SINGLE_USER_GITHUB_API, userName, MY_SECRET_ID, MY_SECRET_KEY));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                result = JsonUtils.getJsonFromIS(connection.getInputStream());
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        } finally {
            if (connection != null) connection.disconnect();
        }
        return result;
    }

    private static String getStringByRequest(String request, JSONObject json, Context context) throws JSONException {
        String result;
        if (json.isNull(request)) {
            result = context.getString(R.string.no_data_result);
        } else result = json.getString(request);
        return result;
    }


}
