package View;

import Core.Helper;

import javax.swing.*;

public class Layout extends JFrame {
    //Showing the window in the centre of the screen
    public void guiInitialize(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Stopping the programme when the window is closed
        this.setTitle("Tourism Agency System");
        this.setSize(width, height);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        this.setVisible(true);
    }
}

