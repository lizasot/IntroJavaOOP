package lesson5;

import lesson5.Models.DataKeeper;
import lesson5.Presenters.LoginPresenter;
import lesson5.Views.ConsoleView;

public class App {
    LoginPresenter loginPresenter;

    public void run() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n====================\n");
        menu.append("[1] Авторизация\n");
        menu.append("[2] Регистрация\n");
        menu.append("[3] Вывод зарегестрированных пользователей\n");
        menu.append("[0] Выход\n");
        int choice;
        while (true) {
            choice = loginPresenter.getChoice(menu.toString());
            if (choice == 1) {
                loginPresenter.authorize();
            } else if (choice == 2) {
                loginPresenter.signUp();
            } else if (choice == 3) {
                loginPresenter.printUserList();
            } else if (choice == 0) {
                break;
            }
        }
    }

    public App() {
        loginPresenter = new LoginPresenter(new DataKeeper(), new ConsoleView());
    }
}
