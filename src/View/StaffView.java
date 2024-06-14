package View;

import Business.UserManager;
import Entity.User;

import javax.swing.*;

public class StaffView extends Layout {
    private JPanel container;
    private User user;

    public StaffView(User user, UserManager userManager) {
        this.add(container);
        this.guiInitialize(1000, 500);
        this.user = user;

        if (this.user == null) {
            dispose();
        }
    }
}
