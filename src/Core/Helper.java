package Core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    //Replacing the UI interface with one called "Nimbus"
    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
        }
    }

    //Displaying messages according to specific situations
    public static void showMessage(String string) {
        String message;
        String title;
        switch (string) {
            case "fill" -> {
                message = "Please, enter the all fields!";
                title = "Error";
            }
            case "done" -> {
                message = "Operation successful!";
                title = "Result";
            }
            case "notFound" -> {
                message = "Record not found!";
                title = "Not found";
            }
            case "error" -> {
                message = "You have made an incorrect operation!";
                title = "Error";
            }
            default -> {
                message = string;
                title = "Message";
            }
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String string) {
        String message;
        if (string.equals("sure")) {
            message = "Are you sure to continue?";
        } else {
            message = string;
        }
        return JOptionPane.showConfirmDialog(null, message, "Are you sure?", JOptionPane.YES_NO_OPTION) == 0;
    }

    //Querying whether text field is empty or not
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    //Querying whether text fields are empty or not
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    //Setting the location of the window to the centre of the screen
    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }
}
