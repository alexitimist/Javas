import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Game implements ActionListener {

    private Move move;
    private boolean isEnd;

    JFrame jframe = new JFrame();
    JLabel text = new JLabel();
    JPanel jpanel = new JPanel();
    JPanel buttons = new JPanel();
    JButton[] button = new JButton[9];

    Game() {
        this.move = Move.X;
        this.isEnd = false;
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(900, 900);
        jframe.setResizable(false);
        jframe.getContentPane().setBackground(new Color(30, 30, 30));
        jframe.setLayout(new BorderLayout());
        jframe.setTitle("Tic Tac Toe Game Draft");
        jframe.setVisible(true);


        text.setBackground(new Color(26, 26, 26));
        text.setForeground(new Color(25, 255, 0));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(new Font(Font.SERIF, Font.PLAIN, 100));
        text.setText("Krzyżulec");
        text.setOpaque(true);

        jpanel.setLayout(new BorderLayout());
        jpanel.setBounds(0, 0, 400, 200);
        jpanel.add(text);

        buttons.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            buttons.add(button[i]);
            button[i].setPreferredSize(new Dimension(200, 200));
            button[i].setBackground(new Color(30, 30, 30));
            button[i].setFont(new Font(Font.SERIF, Font.BOLD, 175));
            button[i].setFocusable(false);
            button[i].addActionListener(this);
        }


        jframe.add(jpanel, BorderLayout.NORTH);
        jframe.add(buttons);
        jframe.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isEnd) {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == button[i]) {
                    if (move.equals(move.X)) {
                        if (button[i].getText() == "") {
                            button[i].setForeground(Color.BLUE);
                            button[i].setText("X");
                            this.move = move.O;
                            text.setText("Turn of O");
                            checkEnd();
                        }
                    } else {
                        if (button[i].getText() == "") {
                            button[i].setForeground(Color.RED);
                            button[i].setText("O");
                            this.move = move.X;
                            text.setText("Turn of X");
                            checkEnd();
                        }
                    }
                    button[i].setFont(new Font(Font.SERIF, Font.BOLD, 50));
                }
            }
        }
    }

    public void checkEnd() {
        if (button[0].getText().equals(button[1].getText()) &&
                button[0].getText().equals(button[2].getText()) &&
                !button[0].getText().isEmpty())
            endGame(0, 2);
        else if (button[3].getText().equals(button[4].getText()) &&
                button[4].getText().equals(button[5].getText()) &&
                !button[3].getText().isEmpty())
            endGame(3, 5);
        else if (button[6].getText().equals(button[7].getText()) &&
                button[7].getText().equals(button[8].getText()) &&
                !button[6].getText().isEmpty())
            endGame(6, 8);
        else if (button[0].getText().equals(button[3].getText()) &&
                button[3].getText().equals(button[6].getText()) &&
                !button[0].getText().isEmpty())
            endGame(0, 6);
        else if (button[1].getText().equals(button[4].getText()) &&
                button[4].getText().equals(button[7].getText()) &&
                !button[1].getText().isEmpty())
            endGame(1, 7);
        else if (button[2].getText().equals(button[5].getText()) &&
                button[5].getText().equals(button[8].getText()) &&
                !button[2].getText().isEmpty())
            endGame(2, 8);
        else if (button[0].getText().equals(button[4].getText()) &&
                button[4].getText().equals(button[8].getText()) &&
                !button[0].getText().isEmpty())
            endGame(0, 8);

    }


    public void endGame(int a, int b) {
        String winner = String.valueOf(button[a]);
        text.setText("Winner is" + winner);
        button[a].setBackground(new Color(25, 255, 0, 176));
        button[(a + b) / 2].setBackground(new Color(25, 255, 0, 176));
        button[b].setBackground(new Color(25, 255, 0, 176));
        isEnd = true;
    }
}
