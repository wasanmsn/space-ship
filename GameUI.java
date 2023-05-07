import Object.*;
import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame {
    private GameObject[] components;
    private GameLogic game;

    public GameUI(){
        game = new GameLogic();
        setVisible(true);
        setPreferredSize(new Dimension(1200, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        new GameUI();
    }
}
