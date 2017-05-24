package ru.sberbank.githubuserinfo;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserNameText;
    private Button mButtonGetInfo;
    private String mCashedUserName;

    private static final int LOADER_ID = 1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            getSupportLoaderManager().initLoader(LOADER_ID, null, new MainActivity.UserCallBacks());
        } else {
            mCashedUserName = userName;
            getSupportLoaderManager().restartLoader(LOADER_ID, null, new MainActivity.UserCallBacks());
        }
    }

    private class UserCallBacks implements LoaderManager.LoaderCallbacks<User> {

        @Override
        public Loader<User> onCreateLoader(int id, Bundle args) {
            Log.e(TAG, "onCreateLoader");
            return new UserLoader(MainActivity.this, mCashedUserName);
        }

        @Override
        public void onLoadFinished(Loader<User> loader, User data) {

            if (data == null) {
                Toast.makeText(MainActivity.this, R.string.user_not_found, Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
                intent.putExtra("User", data);
                startActivity(intent);
            }

            Log.e(TAG, "onLoadFinished");
        }

        @Override
        public void onLoaderReset(Loader<User> loader) {
            Log.e(TAG, "onLoaderReset");
        }
    }
}
