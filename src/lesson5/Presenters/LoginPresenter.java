package lesson5.Presenters;

import lesson5.Models.IModel;
import lesson5.Views.IView;

public class LoginPresenter extends LoginCommandHandler {
    public int getChoice(String menu) {
        view.print(menu);
        String choice = view.getData("Введите число: ");
        int choiceButNumb;
        try {
            choiceButNumb = Integer.parseInt(choice);
        }
        catch (NumberFormatException e) {
            choiceButNumb = -1;
        }
        return choiceButNumb;
    }
    public LoginPresenter(IModel dataKeeper, IView view) {
        super(dataKeeper, view);
    }
}
