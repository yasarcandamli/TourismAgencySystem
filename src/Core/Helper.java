package Core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    //Querying whether text fields are empty or not
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

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

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }
}
