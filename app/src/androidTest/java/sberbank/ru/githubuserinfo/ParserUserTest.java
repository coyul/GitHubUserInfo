package sberbank.ru.githubuserinfo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import ru.sberbank.githubuserinfo.JsonUtils;
import ru.sberbank.githubuserinfo.parser.GithubUserJsonParser;
import ru.sberbank.githubuserinfo.R;
import ru.sberbank.githubuserinfo.User;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ParserUserTest {

    private static final String TAG = "ParserUserTest";
    private JSONObject mTestJsonUser;
    private GithubUserJsonParser mLoader;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        mLoader = new GithubUserJsonParser(context);
        mTestJsonUser = JsonUtils.getJsonFromIS(context.getResources().openRawResource(R.raw.json_for_test));
    }

    @Test
    public void testUserFromJson() {
        User expected = EntitiesGenerator.getSampleTestUser();
        User actual = mLoader.getUserFromJson(mTestJsonUser);
        Log.e(TAG, expected.toString());
        Log.e(TAG, actual.toString());

        assertThat(actual, is(expected));
    }


}
