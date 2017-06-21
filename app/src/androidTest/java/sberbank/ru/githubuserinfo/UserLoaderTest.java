package sberbank.ru.githubuserinfo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import ru.sberbank.githubuserinfo.User;
import ru.sberbank.githubuserinfo.loader.UserLoader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class UserLoaderTest {

    private static final String TAG = "UserLoaderTest";
    private static final String TEST_NAME = "coyul";
    private UserLoader mUserLoader;
    private StubGithubUserJsonParser mFakeJsonParser;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        mFakeJsonParser = new StubGithubUserJsonParser(context);
        mUserLoader = new UserLoader(context, TEST_NAME, mFakeJsonParser);
    }

    @Test
    public void testLoaderWork() {
        User actual = mUserLoader.loadInBackground();

        assertThat(true, is(mFakeJsonParser.getJsonCall));
        assertThat(true, is(mFakeJsonParser.getUserCall));
        assertNotNull(actual);
    }
}
