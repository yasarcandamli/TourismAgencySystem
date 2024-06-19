package View;

import Core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {
    //Showing the window in the centre of the screen
    public void guiInitialize(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Stopping the programme when the window is closed
        this.setTitle("Tourism Agency System");
        this.setSize(width, height);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        this.setVisible(true);
    }

    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns); //Defining the columns of the model
        table.setModel(model); //Defining the model of the table
        table.getTableHeader().setReorderingAllowed(false); //Preventing manually changing column locations in the table
        table.getColumnModel().getColumn(0).setMaxWidth(75); //The size was made small because the first columns are id
        table.setEnabled(false); //Prevent the table from being editable when double-clicked

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel(); //Empty the table so that it is not overwritten every time the table starts
        clearModel.setRowCount(0);

        if (rows == null) {
            rows = new ArrayList<>();
        }

        for (Object[] row : rows) {
            model.addRow(row); //Adding rows to the table model
        }
    }

    public int getTableSelectedRow(JTable table, int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());
    }

    public void selectRow(JTable table, JPopupMenu popupMenu) {
        table.addMouseListener(new MouseAdapter() { //When clicking somewhere in the table, the clicked row is selected
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint()); //Finding the coordinate where the mouse is located
                table.setRowSelectionInterval(selected_row, selected_row);
                //To make a pop_up menu appear on right click
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });
    }
}

