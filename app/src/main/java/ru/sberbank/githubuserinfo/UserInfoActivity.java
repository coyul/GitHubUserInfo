package ru.sberbank.githubuserinfo;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static android.R.attr.data;

public class UserInfoActivity extends AppCompatActivity {

    private TextView mLoginView;
    private TextView mLocationView;
    private TextView mCompanyView;
    private TextView mNameView;
    private ImageView mAvatarView;

    private static final String TAG = "UserInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        mLoginView = (TextView) findViewById(R.id.login);
        mLocationView = (TextView) findViewById(R.id.location);
        mCompanyView = (TextView) findViewById(R.id.company);
        mNameView = (TextView) findViewById(R.id.name);
        mAvatarView = (ImageView) findViewById(R.id.avatar);

        setUpFields();
    }

    private void setUpFields() {
        User resultUser = (User) getIntent().getSerializableExtra("User");

        mLoginView.setText(getString(R.string.login_format, resultUser.getLogin()));
        mLocationView.setText(getString(R.string.location_format, resultUser.getLocation()));
        mCompanyView.setText(getString(R.string.company_format, resultUser.getCompany()));
        mNameView.setText(getString(R.string.name_format, resultUser.getName()));

        Picasso.with(UserInfoActivity.this).load(resultUser.getAvatarUrl()).into(mAvatarView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }
}
