package main.Object;

import javax.imageio.ImageIO;
import java.awt.*;
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

}