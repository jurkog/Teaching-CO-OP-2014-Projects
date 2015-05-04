package dating;


import dating.create.DateEntry;
import dating.delete.DeleteMenu;
import dating.display.DisplayMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Jurko on 17/02/14.
 */
public class Menu {

    public static void display() {
        try {
            new DisplayMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void create() {
        new DateEntry();

    }

    public static void delete() {
        new DeleteMenu();
    }

    public static void main (String[] args) {
        // Creating Menu menu
        final JFrame mainFrame = new JFrame("ICS4U Dating Program");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setLocationRelativeTo(null);

        // Showing simple intro message
        JOptionPane.showMessageDialog(mainFrame, "Welcome to the ICS4U Dating Program!");


        // Creating a container to add all the buttons into
        Container content = mainFrame.getContentPane();

        // Creating display, create and delete buttons
        JButton displayButton = new JButton("Display");
        content.add(displayButton, BorderLayout.NORTH);

        JButton createButton = new JButton("Create");
        content.add(createButton, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete");
        content.add(deleteButton, BorderLayout.SOUTH);

        // Creating the size of the frame.
        mainFrame.pack();
        mainFrame.setSize(400, mainFrame.getHeight());

        // Adding functions to our previously added buttons
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enter your Display Button code here
                System.out.println("'Display' button pressed!");
                mainFrame.dispose();
                display();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("'Create' button pressed!");
                mainFrame.dispose();
                create();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("'Delete' button pressed!");
                mainFrame.dispose();
                delete();
            }
        });

    }



}
