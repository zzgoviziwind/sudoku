package sudoku.UI;

import javax.swing.*;
import java.awt.*;

public class
GameUI {
    JFrame frame;
    public GameUI() {
        frame = new JFrame("GGBond做数独");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setAlwaysOnTop(true);
        frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //frame.setLayout(null);

        frame.setVisible(true);
    }
}
