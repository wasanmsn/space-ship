package main.Object;

import java.awt.geom.Ellipse2D;

public class Meteor extends GameObject {

    //Initiate object
    public Meteor(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY,new Ellipse2D.Float(posX,posY,sizeX,sizeY),"../resources/menteor.png");

    }


    @Override
    public void move(float posX, float posY) {
        // Meteor move implementation
    }
}
