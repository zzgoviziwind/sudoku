package sudoku.UI;

import sudoku.Game.SudokuGame;
import sudoku.UI.GameUI;
import sudoku.Game.ArrayDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.UIManager;

public class NewGameUI {
    JFrame frame;
    JButton button1,button2,button3,button4;
    JPanel panel, panel2;
    JDialog dialog;
    JLabel label;
    int[][] num;

    public NewGameUI(){
        //windowStyle
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // 选择Nimbus外观
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("NewGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);

        //newGame Or Read Last Progress
        button1 = new JButton("newGame");
        button2 = new JButton("continueTheGame");
        button1.setBounds(20, 20, 60, 40);
        button2.setBounds(20, 50, 60, 40);

        //New game, pop-up dialog to choose the game difficulty
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                dialog.setVisible(true);
            }
        });

        //Continue the game, reading the two-dimensional array in the database to play
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                SudokuGame game = new SudokuGame();
                num = game.retrieveArray();
                if (num == null) {
                    button1.doClick();
                    return;
                }
                GameUI gameUI = new GameUI(num);
                try {
                    game.deleteArray();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(button1);
        panel.add(button2);

        //pop Up Window Prompts Difficulty Selection
        dialog = new JDialog(frame,"remind",true);
        dialog.setSize(300,200);
        dialog.setLocationRelativeTo(null);


        label = new JLabel("Please select the game difficulty");
        label.setBounds(60,30,190,20);

        button3 = new JButton("simpleness");
        button4 = new JButton("difficulty");
        button3.setBounds(40,80,100,40);
        button4.setBounds(150,80,100,40);

        // Button to add a click event listener
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // close Dialogue Box
                SudokuGame game = new SudokuGame();
                num = game.GenerateSimpleLevels();
                GameUI gameUI = new GameUI(num);
                try {
                    game.deleteArray();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // close Dialogue Box
                SudokuGame game = new SudokuGame();
                num = game.GenerateDifficultLevels();
                GameUI gameUI = new GameUI(num);
                try {
                    game.deleteArray();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dialog.dispose(); // close Dialogue Box
                frame.setVisible(true);
            }
        });

        dialog.setLayout(null);
        dialog.add(label);
        dialog.add(button3);
        dialog.add(button4);

        frame.add(panel);
        frame.setVisible(true);

    }

}
