package ru.sberbank.githubuserinfo;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class UserLoader extends AsyncTaskLoader<User> {

    private static final String TAG = "User Loader";
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
        JSONObject jsonResult = JsonDataLoader.getJsonObject(mUserName);
        if (jsonResult != null) {
            result = JsonDataLoader.getUserFromJsonObject(getContext(), jsonResult);
        }
        return result;

    }
}
