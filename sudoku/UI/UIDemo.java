package sudoku.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class UIDemo {
    public static void main(String[] args) {
        Frame frame = new Frame("G G Bond");

        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setLayout(null);



        Label label = new Label("G G Bond is my Hero!");
        label.setBounds(20,50,200,30);
        frame.add(label);
        label.setFont(new Font("SimSong",Font.BOLD,15));
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);

        TextField field = new TextField();
        field.setBounds(20,80,200,30);
        frame.add(field);
        field.setEchoChar('G');

        Checkbox checkbox = new Checkbox("小黑子");
        checkbox.setBounds(20,110,200,30);
        frame.add(checkbox);
        TextArea area = new TextArea();
        area.setBounds(240,50,150,120);
        frame.add(area);

        CheckboxGroup group = new CheckboxGroup();
        Checkbox checkbox1 = new Checkbox("小黑子");
        Checkbox checkbox2 = new Checkbox("真爱粉");
        checkbox1.setBounds(20,170,200,30);
        checkbox2.setBounds(20,200,200,30);
        frame.add(checkbox1);
        frame.add(checkbox2);
        checkbox1.setCheckboxGroup(group);
        checkbox2.setCheckboxGroup(group);


        Button button = new Button("Agree!");
        button.setBounds(30,140,100,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You are GG fans!");
                String text = field.getText();
                System.out.println("输入的文本是："+ text);
                System.out.println(checkbox.getState());
                System.out.println(group.getSelectedCheckbox().getLabel());
            }
        });
        frame.add(button);


        frame.setAlwaysOnTop(true);
        frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

    }
}
