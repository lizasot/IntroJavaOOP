package lesson5.Models;

import java.util.HashMap;

public class Model {
    String CurrentUserName;
    HashMap<String, String> UserList;

    public boolean Authorize(String login, String password) {
        if (UserList.containsKey(login) && UserList.get(login).equals(password)) {
            CurrentUserName = login;
            return true;
        }
        return false;
    }
    public boolean SignUp(String login, String password) {
        if (!UserList.containsKey(login)) {
            UserList.put(login, password);
            return true;
        }
        return false;
    }
    public String GetLogin() {
        return CurrentUserName;
    }
    public String GetUserList() {
        return UserList.keySet().toString();
    }
    public Model() {
        CurrentUserName = "";
        UserList = new HashMap<>();
    }
}
