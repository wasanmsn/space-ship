package main.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class Player extends GameObject {
    
    private final int gamePanelWidth = 1200;
    private final int gamePanelHeight = 500;
    private int lifepoint = 3;

    public int getLifepoint() {
        return this.lifepoint;
    };

    public int setLifepoint(int lifepoint) {
        return this.lifepoint = lifepoint;
    };

    public boolean isMove;
    public  boolean inVisibelTime = false;

    Timer time;

    public Player(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY,new Ellipse2D.Float(posX,posY,sizeX,sizeY),"../resources/spaceship.png");

    }

    @Override
    public boolean checkCollision(GameObject object2) {

        Rectangle bounds1 = new Rectangle((int) this.getPosX(), (int) this.getPosY(), (int) this.getSizeX(), (int) this.getSizeY());
        Rectangle bounds2 = new Rectangle((int) object2.getPosX(), (int) object2.getPosY(), (int) object2.getSizeX(), (int) object2.getSizeY());

        if (bounds1.intersects(bounds2) && !(object2 instanceof Player) && !(object2 instanceof Base)) {
            Damage(1);
        }
        else if(bounds1.intersects(bounds2) && (object2 instanceof Base))
        {
            return true;
        }
        return false;
    }

    public void Damage(int dmg) {

        if (!inVisibelTime) {
            inVisibelTime();
            this.lifepoint -= dmg;
        }
    }

    @Override
    public void move(float deltaX, float deltaY) {

        if(isMove) {
            float newX = deltaX - 50;
            float newY = deltaY - 50;

            if (newX >= 0 && newX + getSizeX() <= gamePanelWidth) {
                setPosX(newX);
            }

            if (newY >= 0 && newY + getSizeY() <= gamePanelHeight - 45) {
                setPosY(newY);
            }
        }

    }

    public void inVisibelTime() {
        inVisibelTime = true;
         time = new Timer(899, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inVisibelTime = false;
                time.stop();
            }
        });
        time.start();
    };

}
