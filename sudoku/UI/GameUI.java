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
    JDialog dialog, dialog2;
    JMenuBar menuBar;
    JMenu menu;
    JLabel label;
    JMenuItem menuItem, menuItem2;
    int[][] num, num2;

    int number, x = -1, y = -1;
    public GameUI() {
        frame = new JFrame("小呆呆做数独");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(810, 810);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());







        menuBar = new JMenuBar();
        menu = new JMenu("提交");
        menuItem = new JMenuItem("提交");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++){
                    for (int j = 0; j < 9; j++){
                        if (num2[i][j] == 0 && !cell[i][j].getText().equals("")){
                            num2[i][j] = Integer.parseInt(cell[i][j].getText());
                        }
                    }
                }
                SudokuGame sudokuGame = new SudokuGame();
                label = new JLabel(sudokuGame.isGameCorrect(num2));
                label.setFont(new Font("微软雅黑", Font.BOLD, 30));
                dialog2 = new JDialog();
                dialog2.add(label);
                dialog2.setSize(200, 200);
                dialog2.setVisible(true);
                dialog2.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
                dialog2.setLocationRelativeTo(null);
            }
        });

        menu.add(menuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);















        SudokuGame game = new SudokuGame();
        num = game.GenerateSimpleLevels();
        num2 = num;
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

                        if ( num[finalCol][finalRow] == 0){
                            int buttonX = cell[finalCol][finalRow].getLocationOnScreen().x;
                            int buttonY = cell[finalCol][finalRow].getLocationOnScreen().y;
                            dialog.setLocation(buttonX + cell[finalCol][finalRow].getWidth(), buttonY);
                            dialog.setVisible(true);
                            x = finalCol;
                            y = finalRow;
                        }
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
            button.setForeground(Color.RED);
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
