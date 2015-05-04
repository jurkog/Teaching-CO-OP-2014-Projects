import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Created by Jurko on 20/02/14.
 */
public class TicTacToe extends JComponent {

    char[] GRID = new char[3 * 3]; // [row * column] OR [x * y]
    // [0, 0][1, 0][2, 0]
    // [0, 1][1, 1][2, 1] A coordinate representation of the TicTacToe GRID.
    // [0, 2][1, 2][2, 2]

    // [0] [1] [2]
    // [3] [4] [5]
    // [6] [7] [8]

    int[][] patterns = {{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // These are all the possible winning patterns.
                        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // 8
                        {0, 4, 8}, {6, 4, 2}};

    // Final static variables we do not plan on changing for the remainder of the program.
    final static int GRID_HEIGHT = 3;
    final static Dimension GAME_DIMENSION = new Dimension(280, 350);
    final static int PATTERNS_COUNT = 8;
    JFrame gameFrame = new JFrame("TicTacToe ICS4U Edition");
    static int X_SCORE = 0;
    static int O_SCORE = 0;

    // Simple variable we use to determine who's turn it is.
    // To see who goes first, we randomly pick 0 or 1 to determine who goes first.
    Random random = new Random();
    int TICK = random.nextInt(2); // Random number being chosen: 0 or 1
    int currentGame = 1;
    int spotsFilled = 0;

     TicTacToe() {
         super();
         setSize(GAME_DIMENSION); // JComponent.setSize(Dimension d);
         System.out.println(TICK);

         // Configuring the actual JFrame that will hold our game.
         gameFrame = new JFrame("TicTacToe ICS4U Edition");
         gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         gameFrame.setSize(GAME_DIMENSION);
         gameFrame.setResizable(false);
         gameFrame.getContentPane().add(this, BorderLayout.CENTER);

         // We have to add the MouseListener we created to this JFrame in order to track MouseEvents inside this JFrame.
         gameFrame.addMouseListener(playerListener);

         // Let's indicate who goes first.
         if (TICK % 2 == 0) {
             JOptionPane.showMessageDialog(gameFrame, "O gets the first move!");
         } else {
             JOptionPane.showMessageDialog(gameFrame, "X gets the first move!");
         }

         // Make the gameFrame visible
         gameFrame.setVisible(true);
     }

    @Override
    protected void paintComponent(Graphics g) {
        // We are inheriting a method, we will call its constructor before we implement it.
        super.paintComponent(g);

        // Drawing the four black lines that are our boundaries.
        g.setColor(Color.BLACK);
        g.drawLine(90, 0, 90, 270);
        g.drawLine(180, 0, 180, 270);
        g.drawLine(0, 90, 270, 90);
        g.drawLine(0, 180, 270, 180);

        // Drawing the String of text regarding how many games we have played.
        Font newFont = new Font(g.getFont().getFamily(), Font.BOLD, 32);
        g.setFont(newFont);
        g.drawString("X:  " + X_SCORE + "               O: " + O_SCORE, 0, 315);

        // Drawing our X's and O's on the GRID based on the entries in our character array.
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (GRID[y * GRID_HEIGHT + x] == 'O') {
                    g.setColor(Color.BLUE);
                    g.drawOval(x * 90, y * 90, 90, 90);
                } else if (GRID[y * GRID_HEIGHT + x] == 'X')  {
                    g.setColor(Color.RED);
                    g.drawLine(x * 90, y * 90, x * 90 + 90, y * 90 + 90); // Top left to bottom right line of the 'X'
                    g.drawLine(x * 90, y * 90 + 90, x * 90 + 90, y * 90); // Bottom left to top right line of the 'X'
                }
            }
        }
    }

    MouseListener playerListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            int x = e.getX();
            int y = e.getY();
            char PLAYER_KEY;

            // To ensure we are not out of bounds AND .
            if (x < 270 && y < 270 && GRID[(y / 90) * GRID_HEIGHT + (x / 90)] == '\u0000' ) { // '\u0000' is the code for an empty character.

                if (TICK % 2 == 0) {
                    PLAYER_KEY = 'O';
                    GRID[(y / 90) * GRID_HEIGHT + (x / 90)] = PLAYER_KEY;
                }
                else
                {
                    PLAYER_KEY = 'X';
                    GRID[(y / 90) * GRID_HEIGHT + (x / 90)] = PLAYER_KEY;
                }

                // repaint(); directly calls the paintComponent(Graphics g) method above.
                repaint();

                // Since we are adding to TICK, we are alternating the X's and O's to be drawn next.
                TICK++;
                spotsFilled++;

                // Let's see if we won or not.
                for (int i = 0; i < PATTERNS_COUNT; i++) {

                    if (GRID[patterns[i][0]] == PLAYER_KEY && GRID[patterns[i][1]] == PLAYER_KEY && GRID[patterns[i][2]] == PLAYER_KEY)
                    {
                        JOptionPane.showMessageDialog(gameFrame, "Game over. Player " + PLAYER_KEY + " wins.");
                        if (PLAYER_KEY == 'X')
                            X_SCORE++;
                        else
                            O_SCORE++;

                        TICK = random.nextInt(2);
                        if (TICK % 2 == 0) {
                            JOptionPane.showMessageDialog(gameFrame, "O gets the first move!");
                        } else {
                            JOptionPane.showMessageDialog(gameFrame, "X gets the first move!");
                        }
                        GRID = new char[3 * 3];
                        spotsFilled = 0;
                        repaint();
                    }
                }

                // If nobody wins by
                if (spotsFilled == 9) {
                    JOptionPane.showMessageDialog(gameFrame, "Game over. The result is a tie!");

                    TICK = random.nextInt(2);
                    if (TICK % 2 == 0) {
                        JOptionPane.showMessageDialog(gameFrame, "O gets the first move!");
                    } else {
                        JOptionPane.showMessageDialog(gameFrame, "X gets the first move!");
                    }
                    GRID = new char[3 * 3];
                    spotsFilled = 0;
                    repaint();
                }


            }
        }
    };

    public static void main(String[] args) {
        new TicTacToe();
    }

}
