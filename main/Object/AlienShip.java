package main.Object;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.Timer;

public class AlienShip extends GameObject {

    public Player getPlayer(Player player_) {
        return Player = player_;
    };
    public ArrayList<Bullet> bullet = new ArrayList<Bullet>();
    public boolean isMove = true;

    private GameObjectFactory gameObjectFactory;
    private final float RATIO = 0.25f;
    private Player Player;
    private Timer timerAleinMove;
    private AlienShip thisAlienShip;
    private static final float MOVE_SPEED = 2.7f;

    public AlienShip(float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        super(posX, posY, sizeX, sizeY, desX, desY, new Ellipse2D.Float(posX, posY, sizeX, sizeY), "../resources/alienship.png");
        this.gameObjectFactory = new DefualtGameObjectFactory();
        shoot();
        move(0,0);
        thisAlienShip = this;
    }

    public void move(float posX, float posY) {
        timerAleinMove = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isMove) {
                    float deltaX = (Player.getPosX() +50) - getPosX();
                    float deltaY = (Player.getPosY() +50) - getPosY();
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

        if (isMove) {
            Timer BulletShoot = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Bullet bullet_ = (Bullet) gameObjectFactory.createGameObject(GameObjectType.BULLET, getPosX(), getPosY(), 50 * RATIO, 50 * RATIO, 5, 5);
                    bullet_.getPlayer(Player);
                    bullet_.getAlienShip(thisAlienShip);
                    bullet.add(bullet_);
                    Timer Bulletlife = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (bullet != null) {
                                bullet.remove(bullet);
                                ((Timer) e.getSource()).stop();
                            }
                        }
                    });

                    Bulletlife.start();
                    ((Timer) e.getSource()).stop();
                }
            });

            BulletShoot.start();
        }
    }
}
