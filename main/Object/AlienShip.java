package main.Object;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class AlienShip extends GameObject {

    private Point2D playerPosition;

    public AlienShip(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY,new Ellipse2D.Float(posX,posY,sizeX,sizeY),"../resources/alienship.png");
    }

    @Override
    public void move(float posX, float posY) {
        //move to player
    }
    //Add spawn bullet timer
}
