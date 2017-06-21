package sberbank.ru.githubuserinfo;


import ru.sberbank.githubuserinfo.User;

public class EntitiesGenerator {

    public static User getSampleTestUser() {
        User user = new User();
        user.setLogin("coyul");
        user.setName("no data");
        user.setCompany("no data");
        user.setLocation("Moscow");
        user.setAvatarUrl("https://avatars3.githubusercontent.com/u/26003155?v=3");
        return user;
    }
}
