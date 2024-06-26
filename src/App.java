import Business.UserManager;
import Core.Helper;
import View.AdminView;
import View.LoginView;
import View.StaffView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        LoginView loginView = new LoginView(); // Bring up the login page by launching the app
    }
}