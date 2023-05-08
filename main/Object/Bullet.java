package main.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class Bullet extends GameObject {

    public float pointX;
    public float pointY;
    private Timer timerBullet;
    public Player player;
    public AlienShip alienShip;

    private Timer collisionTimer;
    private int collisionInterval = 100;

    public Bullet(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY,new Ellipse2D.Float(posX,posY,sizeX,sizeY),"../resources/bullet.png");
        move(Player.PlayerPosition.x - 20,Player.PlayerPosition.y -20);

        collisionTimer = new Timer(collisionInterval, e -> checkCollision());
        collisionTimer.start();

        pointX = getPosX();
        pointY = getPosY();
    }

    public float MOVE_SPEED = 20;
    float endX;
    float endY;

    public boolean checkCollision() {

        Rectangle bounds_bull = new Rectangle((int) this.getPosX(), (int)this.getPosY(), (int) getSizeX(), (int) getSizeY());
        Rectangle bounds2 = new Rectangle((int) player.getPosX(), (int) player.getPosY(), (int) player.getSizeX(), (int) player.getSizeY());

        if (bounds_bull.intersects(bounds2)) {
            collisionTimer.stop();
            alienShip.Destory_bullet(Bullet.this);
            return true;
        }
        return false;
    };


    @Override
    public void move(float posX, float posY) {
        float startX = getPosX();
        float startY = getPosY();
        endX = posX;
        endY = posY;
        float deltaX = endX - startX;
        float deltaY = endY - startY;
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        float moveX = (deltaX / distance) * MOVE_SPEED;
        float moveY = (deltaY / distance) * MOVE_SPEED;

        timerBullet = new Timer(10, new ActionListener() {
            private float currentX = startX;
            private float currentY = startY;

            @Override
            public void actionPerformed(ActionEvent e) {

                currentX += moveX;
                currentY += moveY;
                setPosX(currentX);
                setPosY(currentY);
                endX += moveX;
                endY += moveY;

            }
        });

        timerBullet.start();
    }



    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.yellow);

        int sphereSize = 10;

        g2d.fillOval((int) getPosX(), (int) getPosY(), sphereSize, sphereSize);

    }


}
