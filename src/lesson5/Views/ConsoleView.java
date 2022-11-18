package lesson5.Views;

import java.util.Scanner;

public class ConsoleView implements IView {
    Scanner in = new Scanner(System.in);
    @Override
    public void print(String info) {
        System.out.print(info);
    }

    @Override
    public String getData(String prompt) {
        System.out.print(prompt);
        return in.nextLine();
    }
}
