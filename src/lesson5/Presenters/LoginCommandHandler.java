package lesson5.Presenters;

import lesson5.Models.IModel;
import lesson5.Views.IView;

public class LoginCommandHandler {
    protected IModel dataKeeper;
    protected IView view;
    public boolean authorize() {
        boolean success = dataKeeper.authorize(view.getData("Введите логин: "), view.getData("Введите пароль: "));
        if (success) {
            view.print("Добро пожаловать, " + dataKeeper.getLogin() + "!");
        } else {
            view.print("Неправильный логин или пароль.");
        }
        return success;
    }
    public boolean signUp() {
        boolean success = dataKeeper.signUp(view.getData("Введите логин: "), view.getData("Введите пароль: "));
        if (success) {
            view.print("Пользователь успешно зарегистрирован.");
        } else {
            view.print("Пользователь с таким логином уже есть.");
        }
        return success;
    }
    public void printUserList() {
        view.print("Зарегистрированные пользователи: \n");
        String userList = dataKeeper.getUserList();
        if (userList.equals("[]")) {
            view.print("Пользователей нет.");
        } else {
            view.print(dataKeeper.getUserList());
        }
    }

    public LoginCommandHandler(IModel dataKeeper, IView view) {
        this.dataKeeper = dataKeeper;
        this.view = view;
    }
}
