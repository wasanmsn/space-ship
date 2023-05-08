package main;

import main.Object.GameObject;
import main.Panel.GamePanel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameUI extends JFrame implements MouseInputListener, KeyListener {
    private ArrayList<GameObject> components;
    private GamePanel gamePanel;
    private GameLogic game;
    private Point mouseLockedCenter = new Point(0,0) ;

    public GameUI(){
        game = new GameLogic();
        gamePanel = new GamePanel(game);
        gamePanel.setBackground(new Color(30,30,30));
        gamePanel.gameUI = this;
        add(gamePanel);
        setVisible(true);
        setPreferredSize(new Dimension(1200, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        game.createGameObjects();
        game.startTimer();

        setFocusable(true);
        pack();

        while(true){
            game.update(this, mouseLockedCenter.x, mouseLockedCenter.y);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    public static void main(String[] args) {
        new GameUI();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() >= 570 && e.getX() <= 570 + 90 && e.getY() >= 300 && e.getY() <= 300 + 90 && !game.isStart) {
            game.isStart = true;
            game.reset(1);
        }

        if (e.getX() >= 570 && e.getX() <= 570 + 90 && e.getY() >= 300 && e.getY() <= 300 + 90 && game.level == 4) {
            game.reset(1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        Point newMouseLocation = e.getPoint();

        float deltaX =  newMouseLocation.x;
        float deltaY =  newMouseLocation.y;

        mouseLockedCenter.x = (int) deltaX;
        mouseLockedCenter.y = (int) deltaY;

    }

}
