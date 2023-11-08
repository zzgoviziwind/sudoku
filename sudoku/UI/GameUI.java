package sudoku.UI;

import sudoku.Game.SudokuGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class
GameUI {
    JFrame frame;
    JButton[][] cell = new JButton[9][9];
    JButton button;
    JPanel panel, panel2;
    JDialog dialog;
    int number, x = -1, y = -1;
    public GameUI() {
        frame = new JFrame("小呆呆做数独");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(810, 810);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());





        SudokuGame game = new SudokuGame();
        int[][] num = game.GenerateSimpleLevels();
        // 创建一个面板
        panel = new JPanel(new GridLayout(9,9));
        //游戏界面
        for (int col = 0; col < 9; col++){
            for (int row = 0; row < 9; row++){
                if (num[col][row] != 0){
                    cell[col][row] = new JButton(String.valueOf(num[col][row]));
                }else {
                    cell[col][row] = new JButton();
                }

                if (col <3){
                    if (row < 3){
                        cell[col][row].setBackground(Color.LIGHT_GRAY);
                    } else if (row < 6){
                        cell[col][row].setBackground(Color.PINK);
                    }else {
                        cell[col][row].setBackground(Color.WHITE);
                    }
                } else if (col < 6) {
                    if (row < 3){
                        cell[col][row].setBackground(Color.cyan);
                    } else if (row < 6){
                        cell[col][row].setBackground(Color.GRAY);
                    }else {
                        cell[col][row].setBackground(Color.green);
                    }
                } else {
                    if (row < 3){
                        cell[col][row].setBackground(Color.magenta);
                    } else if (row < 6){
                        cell[col][row].setBackground(Color.yellow);
                    }else {
                        cell[col][row].setBackground(Color.BLUE);
                    }
                }

                int finalCol = col;
                int finalRow = row;
                cell[col][row].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        int buttonX = cell[finalCol][finalRow].getLocationOnScreen().x;
                        int buttonY = cell[finalCol][finalRow].getLocationOnScreen().y;
                        dialog.setLocation(buttonX + cell[finalCol][finalRow].getWidth(), buttonY);
                        dialog.setVisible(true);
                        x = finalCol;
                        y = finalRow;
                    }
                });
                cell[col][row].setFont(new Font("微软雅黑", Font.BOLD, 30));
                panel.add(cell[col][row]);
            }
        }

        frame.add(panel, BorderLayout.CENTER);




        panel2 = new JPanel(new GridLayout(3,3));
        dialog = new JDialog();
        dialog.setSize(270, 270);

        for (int i = 1; i <= 9; i++) {
            button = new JButton(String.valueOf(i));
            button.setFont(new Font("微软雅黑", Font.BOLD, 30));
            int finalI = i;

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    number = finalI;
                    cell[x][y].setText(String.valueOf(number));
                    cell[x][y].setForeground(Color.RED);
                    dialog.setVisible(false);
                }
            });
            panel2.add(button);
        }
        dialog.add(panel2);
        dialog.setUndecorated(true);
        dialog.setLocationRelativeTo(null);






        frame.setVisible(true);
    }
}
