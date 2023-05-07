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

    private boolean mouseLocked = true;
    private Point mouseLockedCenter;

    public GameUI(){
        game = new GameLogic();
        game.createGameObjects();
        gamePanel = new GamePanel(game);
        add(gamePanel);
        setVisible(true);
        setPreferredSize(new Dimension(1200, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        mouseLockedCenter = getMouseLockedCenter();

        setFocusable(true);
        setCursorLocation(mouseLockedCenter);

        pack();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            mouseLocked = !mouseLocked;

            if (mouseLocked) {
                setCursorLocation(mouseLockedCenter);
            }
        }
    }

    private Point getMouseLockedCenter() {
        Point lockedCenter = new Point(gamePanel.getWidth() / 2, gamePanel.getHeight() / 2);
        SwingUtilities.convertPointToScreen(lockedCenter, gamePanel);
        return lockedCenter;
    }
    private void setCursorLocation(Point point) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(point.x, point.y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
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
        if (e.getButton() == MouseEvent.BUTTON3) {
            mouseLocked = !mouseLocked;

            if (mouseLocked) {
                setCursorLocation(mouseLockedCenter);
            }
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
        if (mouseLocked) {
            Point newMouseLocation = e.getLocationOnScreen();
            float deltaX = newMouseLocation.x - mouseLockedCenter.x;
            float deltaY = newMouseLocation.y - mouseLockedCenter.y;

            game.updatePlayerPosition(this, deltaX, deltaY);
            setCursorLocation(mouseLockedCenter);
        }
    }
}
