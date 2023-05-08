package main.Object;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Player extends GameObject {
    private final int gamePanelWidth = 1200;
    private final int gamePanelHeight = 500;

    public int lifepoint = 3;
    public static Point PlayerPosition;

    //Initiate Player object
    public Player(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY,new Ellipse2D.Float(posX,posY,sizeX,sizeY),"../resources/spaceship.png");

    }

    //Move logic goes here
    @Override
    public void move(float deltaX, float deltaY) {
        float newX = deltaX-50;
        float newY = deltaY-50;

        if (newX >= 0 && newX + getSizeX() <= gamePanelWidth) {
            setPosX(newX);
        }

        if (newY >= 0 && newY + getSizeY() <= gamePanelHeight-45) {
            setPosY(newY);
        }
    }


}
