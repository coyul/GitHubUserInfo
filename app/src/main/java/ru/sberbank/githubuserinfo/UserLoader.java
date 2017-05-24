package ru.sberbank.githubuserinfo;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class UserLoader extends AsyncTaskLoader<User> {

    private static final String TAG = "UserLoader";
    private String mUserName;
    private User mCashedUser;

    public UserLoader(Context context, String userName) {
        super(context);
        this.mUserName = userName;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e(TAG, "onStartLoading");
        if (mCashedUser == null || takeContentChanged()) {
            forceLoad();
            Log.e(TAG, "onStartLoading forceLoad");
        } else {
            deliverResult(mCashedUser);
            Log.e(TAG, "onStartLoading deliverResult");
        }
    }

    @Override
    public void deliverResult(User data) {
        Log.e(TAG, "deliverResult");
        super.deliverResult(data);
        mCashedUser = data;
    }

    @Override
    public User loadInBackground() {
        Log.e(TAG, "loadInBackground");

        User result = null;
        JSONObject jsonResult = JsonDataLoader.getJsonObject(getContext(), mUserName);
        if (jsonResult != null) {
            result = new UserInfoGetter(jsonResult).getUserFromJsonObject();
        }
        return result;

    }


    private class UserInfoGetter {

        private JSONObject mJson;

        public UserInfoGetter(JSONObject mObject) {
            this.mJson = mObject;
        }

        private User getUserFromJsonObject() {
            User user = new User();
            try {
                user.setLogin(getStringByRequest(getContext().getString(R.string.login_request)));
                user.setName(getStringByRequest(getContext().getString(R.string.name_request)));
                user.setLocation(getStringByRequest(getContext().getString(R.string.location_request)));
                user.setCompany(getStringByRequest(getContext().getString(R.string.company_request)));
                user.setAvatarUrl(getStringByRequest(getContext().getString(R.string.avatar_url_request)));

            } catch (JSONException e) {
                Log.e(TAG, "JSONException", e);
            }
            return user;
        }


        private String getStringByRequest(String request) throws JSONException {
            String result = mJson.getString(request);
            if (result.equals(getContext().getString(R.string.json_null_result)))
                result = getContext().getString(R.string.no_data_result);
            return result;
        }
    }
}
