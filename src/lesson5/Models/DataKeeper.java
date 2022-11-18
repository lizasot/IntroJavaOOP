package lesson5.Models;

import java.util.HashMap;

public class DataKeeper implements IModel {
    String currentUserName;
    HashMap<String, String> userList;

    @Override
    public boolean authorize(String login, String password) {
        if (userList.containsKey(login) && userList.get(login).equals(password)) {
            currentUserName = login;
            return true;
        }
        return false;
    }
    @Override
    public boolean signUp(String login, String password) {
        if (!userList.containsKey(login)) {
            userList.put(login, password);
            return true;
        }
        return false;
    }
    @Override
    public String getLogin() {
        return currentUserName;
    }

    @Override
    public String getUserList() {
        return userList.keySet().toString();
    }
    public DataKeeper() {
        currentUserName = "";
        userList = new HashMap<>();
    }
}
