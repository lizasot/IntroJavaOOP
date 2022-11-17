package lesson5;

import lesson5.Models.Model;
import lesson5.Presenters.Presenter;
import lesson5.Views.ConsoleView;
import lesson5.Views.IView;

public class App {
    Presenter Presenter;

    public void Run() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n====================\n");
        menu.append("[1] Авторизация\n");
        menu.append("[2] Регистрация\n");
        menu.append("[3] Вывод зарегестрированных пользователей\n");
        menu.append("[0] Выход\n");
        int choice;
        while (true) {
            choice = Presenter.getChoice(menu.toString());
            if (choice == 1) {
                Presenter.Authorize();
            } else if (choice == 2) {
                Presenter.SignUp();
            } else if (choice == 3) {
                Presenter.PrintUserList();
            } else if (choice == 0) {
                break;
            }
        }
    }

    public App() {
        Presenter = new Presenter(new Model(), new ConsoleView());
    }
}
