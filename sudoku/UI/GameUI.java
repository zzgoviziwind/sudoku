package sudoku.UI;

import sudoku.Game.SudokuGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.gray;

public class GameUI {
    JFrame frame;
    JMenuBar menuBar;
    JMenu menu1, menu2;
    JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    JPanel panel;
    JButton[][] cell =  new JButton[9][9];
    JButton[] numberButton = new JButton[9];;
    JPanel buttonPanel, buttonPanel2;
    JLabel label;
    //记录点击button index
    int x = -1;
    int y = -1;
    public GameUI() {
        frame = new JFrame("GGBond做数独");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setAlwaysOnTop(true);
        frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        //格子
        panel = new JPanel();
        panel.setLayout(new GridLayout(9, 9));


        SudokuGame game = new SudokuGame();
        int[][] array = game.GenerateSimpleLevels();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (array[i][j] == 0) {
                    cell[i][j] = new JButton();
                }else{
                    cell[i][j] = new JButton(String.valueOf(array[i][j]));
                }

                cell[i][j].setSize(new Dimension(30,30));
                // 为每个格子添加点击事件

                int finalI = i;
                int finalJ = j;
                cell[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x = finalI;
                        y = finalJ;

                    }
                });

                cell[i][j].setFont(new Font("Arial", Font.BOLD,30));
                panel.add(cell[i][j]);
            }
        }


        buttonPanel = new JPanel();
        for (int num = 0; num < 9; num++) {
            numberButton[num] = new JButton(String.valueOf(num+1));

            numberButton[num].setPreferredSize(new Dimension(40, 20));

            int finalNum = num;
            numberButton[num].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cell[x][y].setText(numberButton[finalNum].getText());
                    cell[x][y].setForeground(Color.green);
                }
            });
            buttonPanel.add(numberButton[num]);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);



        //菜单栏
        menuBar = new JMenuBar();
        menu1 = new JMenu("收藏夹");
        menu2 = new JMenu("工具");
        menuItem1 = new JMenuItem("收藏当前对局");
        menuItem2 = new JMenuItem("打开收藏库");
        menuItem3 = new JMenuItem("清空收藏");
        menuItem4 = new JMenuItem("螺丝刀");
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);
        menu2.add(menuItem4);
        menuBar.add(menu1);
        menuBar.add(menu2);


        frame.setJMenuBar(menuBar);


        frame.setVisible(true);
    }
}

