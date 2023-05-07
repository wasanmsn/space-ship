package main.Panel;

import main.GameLogic;
import main.Object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel  {



    private GameLogic game;

    public GamePanel(GameLogic game){
        this.game = game;
        setPreferredSize(new Dimension(1200,500));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        for (GameObject gameObject : game.getGameObjects()) {
            graphics2D.drawImage(gameObject.getImage(),(int) gameObject.getPosX(),(int) gameObject.getPosY(),(int) gameObject.getSizeX(),(int) gameObject.getSizeY(),this);
            //need to add logic to check if the object is alien to render bullet
        }
    }




}
