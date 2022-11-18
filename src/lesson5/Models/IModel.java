package lesson5.Models;

public interface IModel {
    boolean authorize(String login, String password);
    boolean signUp(String login, String password);
    String getLogin();
    String getUserList();
}
