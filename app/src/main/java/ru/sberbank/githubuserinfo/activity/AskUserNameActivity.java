package ru.sberbank.githubuserinfo.activity;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.sberbank.githubuserinfo.parser.GithubUserJsonParser;
import ru.sberbank.githubuserinfo.R;
import ru.sberbank.githubuserinfo.User;
import ru.sberbank.githubuserinfo.loader.UserLoader;

public class AskUserNameActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserNameText;
    private Button mButtonGetInfo;
    private String mCashedUserName;

    private static final int LOADER_ID = 1;
    private static final String TAG = "AskUserName Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_user_name);
        Log.e(TAG, "onCreate");

        mUserNameText = (EditText) findViewById(R.id.edit_user_name);
        mButtonGetInfo = (Button) findViewById(R.id.button_info);
        mButtonGetInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick");
        String userName = mUserNameText.getText().toString();
        if (mCashedUserName == null) mCashedUserName = userName;

        if (mCashedUserName.equals(userName)) {
            Log.e(TAG, "onClick initLoader");
            getSupportLoaderManager().initLoader(LOADER_ID, null, new AskUserNameActivity.UserCallBacks());
        } else {
            mCashedUserName = userName;
            Log.e(TAG, "onClick restartLoader");
            getSupportLoaderManager().restartLoader(LOADER_ID, null, new AskUserNameActivity.UserCallBacks());

        }
    }


    private class UserCallBacks implements LoaderManager.LoaderCallbacks<User> {

        @Override
        public Loader<User> onCreateLoader(int id, Bundle args) {
            Log.e(TAG, "onCreateLoader");
            return new UserLoader(AskUserNameActivity.this, mCashedUserName, new GithubUserJsonParser(AskUserNameActivity.this));
        }

        @Override
        public void onLoadFinished(Loader<User> loader, User data) {
            Log.e(TAG, "onLoadFinished");
            if (data == null) {
                Toast.makeText(AskUserNameActivity.this, R.string.user_not_found, Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
                intent.putExtra("User", data);
                startActivity(intent);
            }
        }

        @Override
        public void onLoaderReset(Loader<User> loader) {
            Log.e(TAG, "onLoaderReset");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        getSupportLoaderManager().destroyLoader(LOADER_ID);
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }


}
