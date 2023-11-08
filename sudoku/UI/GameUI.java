package sudoku.UI;

import sudoku.Game.SudokuGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class
GameUI {
    JFrame frame;
    JButton[][] cell = new JButton[9][9];
    JButton button;
    JPanel panel, panel2;
    JDialog dialog, dialog2;
    JMenuBar menuBar;
    JMenu menu, menu2;
    JLabel label;
    JMenuItem menuItem, menuItem2;
    int[][]  num2;

    int number, x = -1, y = -1;
    public GameUI(int[][] num) {
        frame = new JFrame("小呆呆做数独");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SudokuGame sudokuGame = new SudokuGame();
                try {
                    sudokuGame.storeArray(1,"未完成", num2);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });








        menuBar = new JMenuBar();
        menu = new JMenu("菜单");

        menuItem = new JMenuItem("提交");
        menuItem2 = new JMenuItem("保存");
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
                label.setBounds(100, 80, 40, 80);
                dialog2 = new JDialog();
                dialog2.add(label);
                dialog2.setSize(200, 130);
                dialog2.setVisible(true);
                dialog2.setLocationRelativeTo(null);
                try {
                    sudokuGame.storeArray(1,"未完成", num2);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        //保存
        menuItem2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SudokuGame sudokuGame = new SudokuGame();
                for (int i = 0; i < 9; i++){
                    for (int j = 0; j < 9; j++){
                        if (num2[i][j] == 0 && !cell[i][j].getText().equals("")){
                            num2[i][j] = Integer.parseInt(cell[i][j].getText());
                        }
                    }
                }
                try {
                    sudokuGame.storeArray(1,"未完成", num2);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }) ;

        menu.add(menuItem);
        menu.add(menuItem2);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);






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
                            // 对话框未可见，显示它
                            dialog.setVisible(!dialog.isVisible()); // 对话框已可见，隐藏它
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
        dialog.setSize(200, 200);

        for (int i = 1; i <= 9; i++) {
            button = new JButton(String.valueOf(i));
            button.setFont(new Font("微软雅黑", Font.BOLD, 20));
            button.setForeground(Color.RED);
            int finalI = i;

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    number = finalI;
                    cell[x][y].setText(String.valueOf(number));
                    cell[x][y].setForeground(Color.RED);
                    dialog.dispose();

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
