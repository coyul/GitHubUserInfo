package ru.sberbank.githubuserinfo.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONObject;

import ru.sberbank.githubuserinfo.User;
import ru.sberbank.githubuserinfo.parser.UserJsonParser;


public class UserLoader extends AsyncTaskLoader<User> {

    private static final String TAG = "User Loader";
    private String mUserName;
    private User mCashedUser;
    private UserJsonParser mDataJsonLoader;

    public UserLoader(Context context, String userName, UserJsonParser dataJsonLoader) {
        super(context);
        this.mUserName = userName;
        this.mDataJsonLoader = dataJsonLoader;
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

        JSONObject jsonResult = mDataJsonLoader.getJsonByUserName(mUserName);
        if (jsonResult != null) {
            result = mDataJsonLoader.getUserFromJson(jsonResult);
        }
        return result;
    }
}
