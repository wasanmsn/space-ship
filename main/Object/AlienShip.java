package main.Object;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.Timer;

public class AlienShip extends GameObject {

    private GameObjectFactory gameObjectFactory;
    public Bullet bullet;
    private static final float MOVE_SPEED = 1.7f;
    public Graphics2D graphics2D_;

    public AlienShip(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY, new Ellipse2D.Float(posX, posY, sizeX, sizeY), "../resources/alienship.png");

        this.gameObjectFactory = new DefualtGameObjectFactory();
        bullet = (Bullet) gameObjectFactory.createGameObject(GameObjectType.BULLET,getPosX(),getPosY(),178,176,5,5);

        move(0,0);

    }


    private Timer timerAleinMove;

    public void move(float posX, float posY) {

        timerAleinMove = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                float deltaX = Player.PlayerPosition.x - getPosX();
                float deltaY = Player.PlayerPosition.y - getPosY();
                float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                if (distance > 0) {
                    float moveX = MOVE_SPEED * (deltaX - 50) / distance;
                    float moveY = MOVE_SPEED * (deltaY - 50) / distance;
                    setPosX(getPosX() + moveX);
                    setPosY(getPosY() + moveY);
                }
                timerAleinMove.restart();
            }
        });

        timerAleinMove.start();

    }


    private boolean hasRun;

    public boolean hasRun_() {
        return hasRun;
    }

    public void setHasRun(boolean hasRun) {
        this.hasRun = hasRun;
    }

    public void shoot() {
        System.out.println("Shoot!");
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);

        int cubeSize = 50;
        int posX = 100;
        int posY = 100;

        g2d.fillRect(posX, posY, cubeSize, cubeSize);
    }


}
