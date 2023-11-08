package sudoku.UI;

import sudoku.UI.GameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.UIManager;

public class NewGameUI {
    JFrame frame;
    JButton button1,button2,button3,button4;
    JPanel panel, panel2;

    JDialog dialog;
    JLabel label;

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








        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(button1);
        panel.add(button2);

        //弹窗提示是否覆盖历史对局
        dialog = new JDialog(frame,"提醒",true);
        dialog.setSize(300,200);
        dialog.setLocationRelativeTo(null);


        label = new JLabel("新游戏将覆盖历史进程");
        label.setBounds(85,30,180,20);

        button3 = new JButton("确定");
        button4 = new JButton("取消");
        button3.setBounds(70,70,60,40);
        button4.setBounds(170,70,60,40);

        // 为确定按钮添加点击事件监听器
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // 关闭对话框
                GameUI gameUI = new GameUI();
            }
        });

        // 为取消按钮添加点击事件监听器
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // 关闭对话框
                frame.setVisible(true);
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
