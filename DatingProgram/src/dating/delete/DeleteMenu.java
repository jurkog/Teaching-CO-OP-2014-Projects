package dating.delete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Jurko on 01/04/14.
 */
public class DeleteMenu {

    public DeleteMenu() {
        JFrame mainWindow = new JFrame("Database delete");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

        // Retrieving Files
        File folder = new File("C:\\Questionnaire\\");
        File[] files = folder.listFiles();

        final JList userList = new JList(files);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setLayoutOrientation(JList.VERTICAL);
        mainWindow.getContentPane().add(userList);
        mainWindow.pack();

        JButton deleteButton = new JButton("Delete Entry");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!userList.isSelectionEmpty()) {
                    File file = (File)userList.getSelectedValue();
                    file.delete();
                    File folder = new File("C:\\Questionnaire\\");
                    File[] files = folder.listFiles();
                    userList.setListData(files);
                }
            }
        });
        mainWindow.getContentPane().add(deleteButton, BorderLayout.SOUTH);

    }


}
