import Business.UserManager;
import Core.Helper;
import View.AdminView;
import View.LoginView;
import View.StaffView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
//        LoginView loginView = new LoginView();
        UserManager userManager = new UserManager();
        StaffView staffView = new StaffView(userManager.findByLogin("staff", "1234"), userManager);
//        AdminView adminView = new AdminView(userManager.findByLogin("admin", "1234"), userManager);
    }
}