package sudoku;

import javax.swing.*;
import java.awt.*;

public class Framework {

    public Framework() {

        NewGameUI newGameUI = new NewGameUI();


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Framework::new);
    }
}
