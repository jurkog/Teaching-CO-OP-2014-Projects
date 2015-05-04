package dating.display;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by WECDSB User on 2/19/14.
 */
public class DisplayMenu extends JFrame{
    ArrayList<Entry> USERS_MALE = new ArrayList<Entry>();
    ArrayList<Entry> USERS_FEMALE = new ArrayList<Entry>();
    Entry CURRENT_ENTRY = null;


    public DisplayMenu() throws IOException {
        // Let's begin by listing all our entries, their name and age.
        String path = "C:\\Questionnaire\\";

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        FileInputStream fileInputStream;

        // Now we are retrieving all the DateEntry objects we store in h:\Questionnaire and turning them into individual records.
        for (int i = 0; i < listOfFiles.length; i++) {
            String currentFile = listOfFiles[i].getName().trim();

            // Retrieving Name, Age and Sex just from the name of the file we saved.
            String name = currentFile.substring(0, currentFile.length()-7); // ".txt" is 4 characters. "##G" is another 3. We take a substring of 7 characters off the name to get our name.
            int age = Integer.parseInt(currentFile.substring(currentFile.length()-7, currentFile.length()-5));
            char sex = currentFile.charAt(currentFile.length()-5);

            // Now let's open that file we are looking at and retrieve the answers.
            try {
                fileInputStream = new FileInputStream(path + currentFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                String answers = reader.readLine();
                System.out.println(answers);
                fileInputStream.close();

                // Let's sort our entries by male and female.
                if (sex == 'M') {
                    USERS_MALE.add(new Entry(name, age, sex, answers));
                } else {
                    USERS_FEMALE.add(new Entry(name, age, sex, answers));
                }
            }
            catch (Exception e) {
                System.out.println("Something went wrong when opening the file: " + currentFile);
            }
        }

        // Since we have retrieved all the Entries in our database, lets start working on the Graphical User Interface
        JFrame displayFrame = new JFrame("ICS4U Dating database display");
        displayFrame.setSize(800, 600);

        //
        final JList maleList = new JList(USERS_MALE.toArray());
        maleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        maleList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane maleListScroller = new JScrollPane(maleList);
        maleListScroller.setPreferredSize(new Dimension(140, 235));


        //
        final JList femaleList = new JList(USERS_FEMALE.toArray());
        femaleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        femaleList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane femaleListScroller = new JScrollPane(femaleList);
        femaleListScroller.setPreferredSize(new Dimension(140, 235));

        // ListSelectionListener - Make sure there is not 2 selections.
        maleList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                femaleList.clearSelection();
                CURRENT_ENTRY = (Entry)maleList.getSelectedValue();
            }
        });

        femaleList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                maleList.clearSelection();
                CURRENT_ENTRY = (Entry)femaleList.getSelectedValue();
            }
        });

        // JPanel used to group all the components on the left
        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(new JLabel("List of males: "));
        userPanel.add(maleListScroller);
        userPanel.add(new JLabel("List of females: "));
        userPanel.add(femaleListScroller);
        userPanel.setPreferredSize(new Dimension(160, 600));
        JButton compare = new JButton("Find compatibilities");
        userPanel.add(compare);
        displayFrame.getContentPane().add(userPanel, BorderLayout.WEST);

        // Center Panel
        final JTextArea comparisons = new JTextArea();
        comparisons.setEditable(false);
        displayFrame.getContentPane().add(comparisons, BorderLayout.CENTER);

        // Button listener
        compare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comparisons.setText("");
                char sex = CURRENT_ENTRY.SEX;
                ArrayList<String> compatibilities = new ArrayList<String>();
                
                if (sex == 'M') {
                    for (Entry femaleEntry : USERS_FEMALE) {
                        int score = 0;
                        for (int i = 0; i < 15; i++) {
                            if (CURRENT_ENTRY.ANSWERS[i] == femaleEntry.ANSWERS[i])
                                score++;
                        }
                        double percentScore = (double)score / 15 * 100;
                        String entry = CURRENT_ENTRY.NAME + " has a " + (int) percentScore + "% compatibility with " + femaleEntry.NAME;
                        compatibilities.add(entry);

                    }
                } else {
                    for (Entry maleEntry : USERS_MALE) {
                        int score = 0;
                        for (int i = 0; i < 15; i++) {
                            if (CURRENT_ENTRY.ANSWERS[i] == maleEntry.ANSWERS[i])
                                score++;
                        }
                        double percentScore = (double)score / 15 * 100;
                        String entry = CURRENT_ENTRY.NAME + " has a " + (int) percentScore + "% compatibility with " + maleEntry.NAME;
                        compatibilities.add(entry);
                    }

                }

                for (String token : compatibilities) {
                    comparisons.append(token + '\n');
                }
            }
        });


        displayFrame.setVisible(true);

    }
}
