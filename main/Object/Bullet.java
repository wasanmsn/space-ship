package main.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Bullet extends GameObject {

    public float pointX;
    public float pointY;
    public Bullet(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY,new Ellipse2D.Float(posX,posY,sizeX,sizeY),"../resources/bullet.png");
        move(Player.PlayerPosition.x - 20,Player.PlayerPosition.y -20);



        pointX = getPosX();
        pointY = getPosY();
    }

    public Timer timerBullet;
    public float MOVE_SPEED = 5;
    float endX;
    float endY;
    float endX_;
    float endY_;

    @Override
    public void move(float posX, float posY) {

        endX = posX;
        endY = posY;
        timerBullet = new Timer(25, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                float deltaX = endX - getPosX();
                float deltaY = endY - getPosY();
                float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                float moveX = MOVE_SPEED * deltaX / distance;
                float moveY = MOVE_SPEED * deltaY / distance;
                setPosX(getPosX() + moveX);
                setPosY(getPosY() + moveY);

            }
        });

        timerBullet.start();
    }



    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);

        int sphereSize = 10;

        g2d.fillOval((int) getPosX(), (int) getPosY(), sphereSize, sphereSize);

//        g2d.setColor(Color.BLACK);
//        g2d.drawLine((int) pointX, (int) pointY, (int) pointX + 1000, (int) pointY);
//        g2d.drawLine((int) pointX, (int) pointY, (int) pointX + -1000, (int) pointY);
//        g2d.drawLine((int) pointX, (int) pointY, (int) pointX, (int) pointY+1000);
//        g2d.drawLine((int) pointX, (int) pointY, (int) pointX, (int) pointY-1000);
//
//        g2d.drawLine((int) pointX, (int) pointY, (int) endX, (int) endY);
//
//
//
//
//        g2d.fillOval((int) endX, (int) endY, sphereSize, sphereSize);
//        g2d.setColor(Color.BLUE);
//
//        g2d.drawLine((int) endX, (int) endY, (int) endX_, (int) endY_);


    }




}
