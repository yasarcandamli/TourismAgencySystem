package View;

import Business.UserManager;
import Core.Helper;
import Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout { //extends from JFrame because using the JFrame properties
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private final UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(400, 500);

        //What to do when the button is clicked
        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_password};
            if (Helper.isFieldListEmpty(checkFieldList)) { //Querying whether text fields are empty or not, sending error if empty
                Helper.showMessage("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_password.getText());
                if (loginUser == null) { //If the user does not exist, it gives a not found warning
                    Helper.showMessage("notFound");
                } else {
                    if (loginUser.getUserType().toString().equals("ADMIN")) {
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    } else if (loginUser.getUserType().toString().equals("STAFF")) {
                        StaffView staffView = new StaffView(loginUser);
                        dispose();
                    } else {
                        Helper.showMessage("error");
                    }
                }
            }
        });
    }
}
