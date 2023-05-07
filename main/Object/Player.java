package main.Object;

import java.awt.geom.Ellipse2D;

public class Player extends GameObject {

    //Initiate Player object
    public Player(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY,new Ellipse2D.Float(posX,posY,sizeX,sizeY),"../resources/spaceship.png");

    }

    //Move logic goes here
    @Override
    public void move(float deltaX, float deltaY) {
        float newX = getPosX() + deltaX;
        float newY = getPosY() + deltaY;

        setPosX(newX);
        setPosY(newY);
    }


}
