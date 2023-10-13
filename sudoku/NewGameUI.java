package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameUI {
    JFrame frame;
    JButton button1,button2;
    JPanel panel;
    JDialog dialog;

    public NewGameUI(){
        frame = new JFrame("NewGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);

        dialog = new JDialog(frame,"新游戏将覆盖历史进程",true);
        dialog.setSize(300,200);
        dialog.setLocationRelativeTo(null);


        button1 = new JButton("新游戏");
        button2 = new JButton("继续游戏");
        button1.setBounds(20, 20, 60, 40);
        button2.setBounds(20, 50, 60, 40);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                dialog.setVisible(true);
                GameUI gameUI = new GameUI();
            }
        });



        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(button1);
        panel.add(button2);



        frame.add(panel);
        frame.setVisible(true);

    }

}
