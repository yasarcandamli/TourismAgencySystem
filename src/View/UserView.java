package View;

import Business.UserManager;
import Core.Helper;
import Entity.User;

import javax.swing.*;

public class UserView extends Layout {
    private JPanel container;
    private JLabel lbl_user_header;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JTextField fld_user_password;
    private JLabel lbl_user_password;
    private JComboBox<User.UserType> cmb_user_type;
    private JLabel lbl_user_type;
    private JButton btn_user_save;
    private User user;
    private UserManager userManager;

    //We will use the new user creation and update features on the same window. If we send an empty user we will create a new user, if we send an existing user we will update it
    public UserView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(300, 300);

        this.cmb_user_type.setModel(new DefaultComboBoxModel<>(User.UserType.values()));

        if (this.user.getUserId() != 0) {
            this.fld_username.setText(this.user.getUserName());
            this.fld_user_password.setText(this.user.getUserPassword());
            this.cmb_user_type.getModel().setSelectedItem(this.user.getUserType());
        }

        this.btn_user_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_username, this.fld_user_password})) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.user.setUserName(fld_username.getText());
                this.user.setUserPassword(fld_user_password.getText());
                this.user.setUserType((User.UserType) cmb_user_type.getSelectedItem());
                if (this.user.getUserId() != 0) {
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.add(this.user);
                }

                if (result){
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}
