package dating.create;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by Jurko on 17/02/14.
 */
public class DateEntry extends JFrame {
    // Declarations of the things we will be using
    // Information for the Random Access File declarations
    public static String USER_NAME = null; // 3 - 18 Letters
    public static int USER_AGE = 0; // 13 - 18
    public static char USER_SEX = '?'; // 'M' or 'F' (case insensitive)
    public final static char[] USER_ANSWERS = new char[15];


    // Visual Component Declarations
    JFrame currentEntry = this;
    JLabel questionLabel;
    JRadioButton choiceA;
    JRadioButton choiceB;
    JRadioButton choiceC;
    JRadioButton choiceD;
    JRadioButton choiceE;
    ButtonGroup choices;

    // Extra - Global variables we will use throughout the program that we just want to declare now
    int currentQuestionNumber = 0;
    String currentQ;
    String answerA;
    String answerB;
    String answerC;
    String answerD;
    String answerE;

    public DateEntry() {
        // Initializing the JFrame for the questionnaire
        super("Dating Questionnaire"); // super() method calls the constructor in the JFrame class since we extend it.
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container content = getContentPane();
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(new BorderLayout());



        // Assigning our DateEntry instance's Name, Age and Sex

        // Setting the name - Keep asking the user for a name till its length is between 3 and 24 letters
        while (USER_NAME == null) {
            USER_NAME = JOptionPane.showInputDialog("What is your name?");
            if (USER_NAME.length() > 18 || USER_NAME.length() < 3) {
                JOptionPane.showMessageDialog(this, "Please keep your name 3 - 18 letters long.");
                USER_NAME = null;
            }
        }

        // Setting the age - Keep asking till
        USER_AGE = Integer.parseInt(JOptionPane.showInputDialog("How old are you?"));
        if (USER_AGE < 13 || USER_AGE > 18) {
            JOptionPane.showMessageDialog(this, "Sorry you are too young/old to use this application. Goodbye.");
            System.exit(0);
        }

        // We can determine the male or female variable with a little bit of logic
        while (USER_SEX == '?') {
            String genderParse = JOptionPane.showInputDialog("Are you male or female?");
            if (genderParse.startsWith("M") || genderParse.startsWith("m")) {
                USER_SEX = 'M';
            } else if (genderParse.startsWith("F") || genderParse.startsWith("f")) {
                USER_SEX = 'F';
            } else { // In case they wrote something like "Boy" or "Girl"
                JOptionPane.showMessageDialog(this, "Could you please be more specific? (ie. Male or Female, M or F)");
            }
        }

        // Let's make sure that our program has recorded our credentials.
        System.out.println("Name: " + USER_NAME + " Age: " + USER_AGE + " Sex: " + USER_SEX);

        /* ____________________________________ QUESTIONNAIRE CODE BELOW _________________________________________*/

        // Let's create arrays for all the questions and answers
        final String[] DATE_QUESTIONS = {"Q1: Are you a very organized individual?",
                "Q2: Do you prefer to complete projects ahead of schedule?",
                "Q3: Are you an aggressive individual?",
                "Q4: Do you tend to lose motivation when dilemmas get very difficult or unpleasent?",
                "Q5: Are you more dominant than submissive?",
                "Q6: Are you calm in tense situations?",
                "Q7: Are you extremely indecisive when it comes to small, unimportant situations?",
                "Q8: Do you prefer to be popular or unpopular?",
                "Q9: Do you back down when threatened?",
                "Q10: Do you frequently help others but forget about yourself?",
                "Q11: Do you require much time to recharge?",
                "Q12: Are you assertive?",
                "Q13: Do you prefer being organized or unpredictable?",
                "Q14: Are you attracted to things associated with sadness?",
                "Q15: Are you typically carefree?"};


        final String[] Q1_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q2_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q3_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q4_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q5_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q6_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q7_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q8_ANSWERS =
                {"A: Very Popular",
                        "B: Popular",
                        "C: Average",
                        "D: Unpopular",
                        "E: Very unpopular"};

        final String[] Q9_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q10_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q11_ANSWERS =
                {"A: Plenty of time",
                        "B: Enough time",
                        "C: Some time",
                        "D: Not enough time",
                        "E: No time"};

        final String[] Q12_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q13_ANSWERS =
                {"A: Organized",
                        "B: Somewhat organized",
                        "C: Average",
                        "D: Somewhat messy",
                        "E: Unpredictable"};

        final String[] Q14_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        final String[] Q15_ANSWERS =
                {"A: Very Accurate",
                        "B: Accurate",
                        "C: Somewhat Accurate",
                        "D: Inaccurate",
                        "E: Very Inaccurate"};

        // [Row][Column] Double Array
        final String[][] ANSWER_ARRAY = {Q1_ANSWERS, Q2_ANSWERS, Q3_ANSWERS, Q4_ANSWERS, Q5_ANSWERS, Q6_ANSWERS, Q7_ANSWERS, Q8_ANSWERS, Q9_ANSWERS, Q10_ANSWERS, Q11_ANSWERS, Q12_ANSWERS, Q13_ANSWERS, Q14_ANSWERS, Q15_ANSWERS};

        // Creating a String representation of the Current Question and its corresponding answers.

        currentQ = DATE_QUESTIONS[currentQuestionNumber];
        answerA = ANSWER_ARRAY[currentQuestionNumber][0];
        answerB = ANSWER_ARRAY[currentQuestionNumber][1];
        answerC = ANSWER_ARRAY[currentQuestionNumber][2];
        answerD = ANSWER_ARRAY[currentQuestionNumber][3];
        answerE = ANSWER_ARRAY[currentQuestionNumber][4];

        // Creating a label for the question and corresponding checkboxes for the answers.
        questionLabel = new JLabel(currentQ);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(questionLabel, gridBagConstraints);

        choiceA = new JRadioButton(answerA);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(choiceA, gridBagConstraints);

        choiceB = new JRadioButton(answerB);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(choiceB, gridBagConstraints);

        choiceC = new JRadioButton(answerC);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(choiceC, gridBagConstraints);

        choiceD = new JRadioButton(answerD);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(choiceD, gridBagConstraints);

        choiceE = new JRadioButton(answerE);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(choiceE, gridBagConstraints);

        // Creating a "Next Question" button.
        JButton nextQuestion = new JButton("Next Question");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        content.add(nextQuestion, BorderLayout.SOUTH);

        // Adding an actionListener (We use this to give our buttons a command to execute when we press them.
        nextQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean answerFilled = false;

                if (choiceA.isSelected()) {
                    USER_ANSWERS[currentQuestionNumber] = 'A';
                    answerFilled = true;
                }
                else if (choiceB.isSelected()) {
                    USER_ANSWERS[currentQuestionNumber] = 'B';
                    answerFilled = true;
                } else if (choiceC.isSelected()) {
                    USER_ANSWERS[currentQuestionNumber] = 'C';
                    answerFilled = true;
                } else if (choiceD.isSelected()) {
                    USER_ANSWERS[currentQuestionNumber] = 'D';
                    answerFilled = true;
                } else if (choiceE.isSelected()) {
                    USER_ANSWERS[currentQuestionNumber] = 'E';
                    answerFilled = true;
                } else if (choices.getSelection().equals(null)){
                    answerFilled = false;
                }

                // If there is an answer and we are not on the last question.
                if (answerFilled && currentQuestionNumber < 14) {
                    System.out.println("Answer for question #"+(currentQuestionNumber + 1) + ": " + USER_ANSWERS[currentQuestionNumber]);
                    currentQuestionNumber++;
                    currentQ = DATE_QUESTIONS[currentQuestionNumber];
                    answerA = ANSWER_ARRAY[currentQuestionNumber][0];
                    answerB = ANSWER_ARRAY[currentQuestionNumber][1];
                    answerC = ANSWER_ARRAY[currentQuestionNumber][2];
                    answerD = ANSWER_ARRAY[currentQuestionNumber][3];
                    answerE = ANSWER_ARRAY[currentQuestionNumber][4];

                    questionLabel.setText(currentQ);
                    choiceA.setText(answerA);
                    choiceB.setText(answerB);
                    choiceC.setText(answerC);
                    choiceD.setText(answerD);
                    choiceE.setText(answerE);
                } else if (answerFilled && currentQuestionNumber == 14) {
                    // Since we're done the questionnaire lets save the data somewhere.
                    System.out.println("Done the questionnaire.");
                    JOptionPane.showMessageDialog(currentEntry, "Thank you for your input into the database.");
                    dispose();

                    // Let's save it to our 'Questionnaire' folder in our H:/ drive
                    try {
                        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Questionnaire\\"+USER_NAME+USER_AGE+USER_SEX+".txt"), "utf-8"));
                        for (int i = 0; i < 15; i++) {
                            writer.write(USER_ANSWERS[i]);
                            writer.flush();
                        }
                    } catch (Exception exception) {
                        System.out.println("We had some trouble writing the file to the drive.");
                        exception.printStackTrace();
                    }
                }
            }
        });

        // Adding the choices to a ButtonGroup - This allows the user to make only one choice.
        choices = new ButtonGroup();
        choices.add(choiceA);
        choices.add(choiceB);
        choices.add(choiceC);
        choices.add(choiceD);
        choices.add(choiceE);

        content.add(mainPanel, BorderLayout.WEST);
        this.setSize(400, 200);
    }
}
