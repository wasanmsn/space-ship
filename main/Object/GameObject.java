package main.Object;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public abstract class GameObject {
    private float posX;
    private float posY;
    private float sizeX;
    private float sizeY;
    private float desX;
    private float desY;

    private Shape shape;

    private Image image;

    public GameObject(float posX, float posY, float sizeX, float sizeY, float desX, float desY,Shape shape,String imagePath){
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.desX = desX;
        this.desY = desY;
        this.shape = shape;
        try{
            this.image = ImageIO.read(getClass().getResource(imagePath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }
    public Shape getShape(){
        return shape;
    }
    public Image getImage(){
        return image;
    }

    public float getSizeX(){
        return sizeX;
    }

    public float getSizeY(){
        return sizeY;
    }
    public abstract void move(float posX,float posY);

    public boolean checkCollision(GameObject object2) {

        Rectangle bounds1 = new Rectangle((int) this.getPosX(), (int) this.getPosY(), (int) this.getSizeX(), (int) this.getSizeY());
        Rectangle bounds2 = new Rectangle((int) object2.getPosX(), (int) object2.getPosY(), (int) object2.getSizeX(), (int) object2.getSizeY());

        if (bounds1.intersects(bounds2)) {
            return true;
        }

        return false;
    }

}