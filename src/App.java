import Business.UserManager;
import Core.Helper;
import View.AdminView;
import View.LoginView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
//        LoginView loginView = new LoginView();
        UserManager userManager = new UserManager();
        AdminView adminView = new AdminView(userManager.findByLogin("admin", "1234"));
    }
}