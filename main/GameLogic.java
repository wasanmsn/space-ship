package main;

import main.Object.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class GameLogic {
    private final GameObjectFactory gameObjectFactory;
    private final float RATIO = 0.25f;
    private boolean isOver;

    private Random random = new Random();

    private int level = 2;

    private int max_alien_count = 0;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    private ArrayList<Timer> timers = new ArrayList<>();
    public GameLogic() {
        this.gameObjectFactory = new DefualtGameObjectFactory();
    }

    public Point randomPoint () {
        return new Point(random.nextInt(1200),random.nextInt(500));
    }
    void createGameObjects(){
        Player player = (Player) gameObjectFactory.createGameObject(GameObjectType.PLAYER,0,100,315* RATIO,125* RATIO,5,5);
        Base base = (Base) gameObjectFactory.createGameObject(GameObjectType.BASE, 1150, 180, 354* RATIO, 354* RATIO, 5, 5);
        gameObjects.add(player);
        gameObjects.add(base);
    }
    public void startTimer() {
        Timer timerMeteor = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your code to execute every 1 second
                Point point = randomPoint();
                ++max_alien_count;
                Meteor meteor = (Meteor) gameObjectFactory.createGameObject(GameObjectType.METEOR,point.x,point.y,200*RATIO,180*RATIO,5,5);
                gameObjects.add(meteor);
            }
        });
        Timer timerAlein = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your code to execute every 1 second
                Point point = randomPoint();
                ++max_alien_count;
                AlienShip alien = (AlienShip) gameObjectFactory.createGameObject(GameObjectType.ALIEN_SHIP,point.x,point.y,200*RATIO,180*RATIO,5,5);
                gameObjects.add(alien);
            }
        });
        Timer timerBullet = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your code to execute every 1 second
                Point point = randomPoint();
                Bullet bullet = (Bullet) gameObjectFactory.createGameObject(GameObjectType.BULLET,point.x,point.y,200*RATIO,180*RATIO,5,5);
                gameObjects.add(bullet);
            }
        });

        timers.add(timerMeteor);
        timers.add(timerAlein);
        timers.add(timerBullet);
    }



    public ArrayList<GameObject> getGameObjects(){
        return gameObjects;
    }

    void createEnemyObjects(){
        Bullet bullet = (Bullet) gameObjectFactory.createGameObject(GameObjectType.BULLET, 0, 0, 10, 10, 5, 5);
        AlienShip alienShip = (AlienShip) gameObjectFactory.createGameObject(GameObjectType.ALIEN_SHIP, 0, 0, 10, 10, 5, 5);
    }

    public void update(GameUI gamePanel, float deltaX, float deltaY) {
        // Assuming player is the first object in the gameObjects list
        switch (level) {
            case 0 -> {
                timers.get(0).stop();
                timers.get(1).stop();
                timers.get(2).stop();
            }
            case 1 -> {
                if(max_alien_count == 0) timers.get(0).start();
            }
            case 2 -> {
                if(max_alien_count == 0){
                    timers.get(1).start();
                    timers.get(2).start();
                }
                if(max_alien_count  == 3){
                    timers.get(1).stop();
                }
            }
            case 3 ->{
                if(max_alien_count == 5){
                    timers.get(1).stop();
                }
            }
        }
        Player player = (Player) gameObjects.get(0);
        player.move(deltaX, deltaY);
        gamePanel.repaint();
    }
}
