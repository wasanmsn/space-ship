package main.Object;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.Timer;

public class AlienShip extends GameObject {

    private GameObjectFactory gameObjectFactory;
    private final float RATIO = 0.25f;
    public Player player;

    public AlienShip thisAlienShip;
    public ArrayList<Bullet> bullet = new ArrayList<Bullet>();
    private static final float MOVE_SPEED = 2.7f;
    public boolean isMove = true;

    public AlienShip(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY, new Ellipse2D.Float(posX, posY, sizeX, sizeY), "../resources/alienship.png");
        this.gameObjectFactory = new DefualtGameObjectFactory();
        shoot();
        move(0,0);
        thisAlienShip = this;

    }

    private Timer timerAleinMove;

    public void move(float posX, float posY) {

        timerAleinMove = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isMove) {
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
            }
        });

        timerAleinMove.start();
    }

    public void shoot() {
        Timer BulletShoot = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bullet bullet_ = (Bullet) gameObjectFactory.createGameObject(GameObjectType.BULLET,getPosX(),getPosY(),50*RATIO,50*RATIO,5,5);
                bullet_.player = player;
                bullet_.alienShip = thisAlienShip;
                bullet.add(bullet_);
                Timer Bulletlife = new Timer(4000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(bullet != null) {
                            bullet.remove(bullet);
                        }
                    }
                });

                Bulletlife.start();

            }
        });

        BulletShoot.start();
    }

    public void Destory_bullet(Bullet bt)
    {
        player.lifepoint -= 1;
        bullet.remove(bt);
    }

}
