package main.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class Bullet extends GameObject {

    public float MOVE_SPEED = 20;
    float endX;
    float endY;
    public float pointX;
    public float pointY;
    public Player Player_;
    public Player getPlayer(Player player_) {
        if(player_ != null) {move(player_.getPosX() + 20, player_.getPosY() + 20);}
        return Player_= player_;
    };
    public AlienShip AlienShip;
    public AlienShip getAlienShip(AlienShip AlienShip_) {
        return AlienShip= AlienShip_;
    };


    private Timer timerBullet;

    public Bullet(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY,new Ellipse2D.Float(posX,posY,sizeX,sizeY),"../resources/bullet.png");
        pointX = getPosX();
        pointY = getPosY();
    }

    @Override
    public boolean checkCollision(GameObject object2) {
        Rectangle bounds1 = new Rectangle((int) this.getPosX(), (int) this.getPosY(), (int) this.getSizeX(), (int) this.getSizeY());
        Rectangle bounds2 = new Rectangle((int) object2.getPosX(), (int) object2.getPosY(), (int) object2.getSizeX(), (int) object2.getSizeY());
        if (bounds1.intersects(bounds2) && (object2 instanceof Player)) {
            Player_.Damage(1);
            return true;
        }
        return false;
    }

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
                checkCollision(Player_);
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
