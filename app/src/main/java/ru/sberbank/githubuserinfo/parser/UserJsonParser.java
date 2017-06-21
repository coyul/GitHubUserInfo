package ru.sberbank.githubuserinfo.parser;

import org.json.JSONObject;

import ru.sberbank.githubuserinfo.User;

public interface UserJsonParser {
    User getUserFromJson(JSONObject json);
    JSONObject getJsonByUserName(String userName);
}
