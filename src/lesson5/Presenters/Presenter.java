package lesson5.Presenters;

import lesson5.Models.Model;
import lesson5.Views.IView;

public class Presenter {
    private Model Model;
    private IView View;
    public int getChoice(String menu) {
        View.print(menu);
        String choice = View.getData("Введите число: ");
        int choiceButNumb;
        try {
            choiceButNumb = Integer.parseInt(choice);
        }
        catch (NumberFormatException e) {
            choiceButNumb = -1;
        }
        return choiceButNumb;
    }
    public boolean Authorize() {
        boolean success = Model.Authorize(View.getData("Введите логин: "), View.getData("Введите пароль: "));
        if (success) {
            View.print("Добро пожаловать, " + Model.GetLogin() + "!");
        } else {
            View.print("Неправильный логин или пароль.");
        }
        return success;
    }
    public boolean SignUp() {
        boolean success = Model.SignUp(View.getData("Введите логин: "), View.getData("Введите пароль: "));
        if (success) {
            View.print("Пользователь успешно зарегистрирован.");
        } else {
            View.print("Пользователь с таким логином уже есть.");
        }
        return success;
    }
    public void PrintUserList() {
        View.print("Зарегистрированные пользователи: \n");
        String userList = Model.GetUserList();
        if (userList == "[]") {
            View.print("Пользователей нет.");
        } else {
            View.print(Model.GetUserList());
        }
    }

    public Presenter(Model model, IView view) {
        Model = model;
        View = view;
    }
}
