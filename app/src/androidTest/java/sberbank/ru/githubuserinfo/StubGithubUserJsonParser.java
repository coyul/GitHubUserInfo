package sberbank.ru.githubuserinfo;

import android.content.Context;

import org.json.JSONObject;

import ru.sberbank.githubuserinfo.JsonUtils;
import ru.sberbank.githubuserinfo.R;
import ru.sberbank.githubuserinfo.parser.UserJsonParser;
import ru.sberbank.githubuserinfo.User;

public class StubGithubUserJsonParser implements UserJsonParser {

    private Context mContext;
    public boolean getUserCall = false;
    public boolean getJsonCall = false;

    public StubGithubUserJsonParser(Context context) {
        mContext = context;
    }

    @Override
    public User getUserFromJson(JSONObject json) {
        getUserCall = true;
        return EntitiesGenerator.getSampleTestUser();
    }

    @Override
    public JSONObject getJsonByUserName(String userName) {
        getJsonCall = true;
        return JsonUtils.getJsonFromIS(mContext.getResources().openRawResource(R.raw.json_for_test));
    }
}
