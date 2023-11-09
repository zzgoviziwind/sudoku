package sudoku.UI;

import sudoku.Game.SudokuGame;
import sudoku.UI.GameUI;

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
        //窗口风格
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







        //新游戏or读取上次进度
        button1 = new JButton("新游戏");
        button2 = new JButton("继续游戏");
        button1.setBounds(20, 20, 60, 40);
        button2.setBounds(20, 50, 60, 40);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                dialog.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                SudokuGame game = new SudokuGame();
                num = game.retrieveArray();
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



        //弹窗提示难度选择
        dialog = new JDialog(frame,"提醒",true);
        dialog.setSize(300,200);
        dialog.setLocationRelativeTo(null);


        label = new JLabel("     请选择游戏难度");
        label.setBounds(85,30,180,20);

        button3 = new JButton("简单");
        button4 = new JButton("困难");
        button3.setBounds(70,70,60,40);
        button4.setBounds(170,70,60,40);

        // 按钮添加点击事件监听器
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // 关闭对话框
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
                dialog.dispose(); // 关闭对话框
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
                dialog.dispose(); // 关闭对话框
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
