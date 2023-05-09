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
    private Random random = new Random();
    public Player player;
    public boolean isStart = false;
    private int level = 1;

    public int getLevel()
    {
        return level;
    }
    private int max_alien_count = 0;

    public ArrayList<GameObject> gameObjects = new ArrayList<>();

    private ArrayList<Timer> timers = new ArrayList<>();

    private Timer coutDownNextLevel;
    public boolean nextLevlUi = false;


    public GameLogic() {
        this.gameObjectFactory = new DefualtGameObjectFactory();
    }

    public Point randomPoint () {
        return new Point(random.nextInt(1000),random.nextInt(450));
    }

    public void reset(int lv) {
        gameObjects.clear();
        getGameObjects().clear();
        createGameObjects();
        max_alien_count = 0;
        level = lv;
        player.setLifepoint(3);
        player.isMove = true;
        retrunMouse();
        timers.forEach(Timer::stop);
        startTimer();
    }

    public void createGameObjects(){
        Player player = (Player) gameObjectFactory.createGameObject(GameObjectType.PLAYER,0,100,315* RATIO,125* RATIO,5,5);
        Base base = (Base) gameObjectFactory.createGameObject(GameObjectType.BASE, 1150, 180, 354* RATIO, 354* RATIO, 5, 5);
        this.player = player;
        gameObjects.add(player);
        gameObjects.add(base);

        collision_timer_tick(true);
    }

    public void collision_timer_tick(boolean bool) {
        Timer timers_collision = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (GameObject gameObject : gameObjects) {
                    if (gameObject != null && player.checkCollision(gameObject))
                    {
                        player.isMove = false;
                        player.setLifepoint(3);
                        nextLevlUi = true;
                        coutDownNextLevel = new Timer(2000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                player.isMove = true;
                                player.move(100,120);
                                retrunMouse();
                                level = level + 1;
                                nextLevlUi = false;
                                reset(level);
                                coutDownNextLevel.stop();
                            }
                        });
                        coutDownNextLevel.setRepeats(false);
                        coutDownNextLevel.start();
                        ((Timer) e.getSource()).stop();
                    }
                }
            }
        });
        if (bool) {
            timers_collision.start();
        }
        else
        {
            timers_collision.stop();
        }
    }

    public void startTimer() {
        Timer timerMeteor = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point point = randomPoint();
                Meteor meteor = (Meteor) gameObjectFactory.createGameObject(GameObjectType.METEOR,point.x,point.y,200*RATIO,180*RATIO,5,5);
                gameObjects.add(meteor);
            }
        });

        Timer timerAlein = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point point = randomPoint();
                ++max_alien_count;
                AlienShip alien = (AlienShip) gameObjectFactory.createGameObject(GameObjectType.ALIEN_SHIP,point.x,point.y,200*RATIO,180*RATIO,5,5);
                alien.getPlayer(player);
                gameObjects.add(alien);
            }
        });

        timers.add(timerMeteor);
        timers.add(timerAlein);
    }

    public ArrayList<GameObject> getGameObjects(){
        return gameObjects;
    }

    public void retrunMouse()
    {
        try {
            Robot robot = new Robot();

            int x = 100;
            int y = 250;

            robot.mouseMove(x, y);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    public void update(GameUI gamePanel, float deltaX, float deltaY) {

        if(player != null) {
            Rectangle Mouse_ = new Rectangle((int) deltaX - 20, (int) deltaY - 50, 50, 50);
            Rectangle Player_ = new Rectangle((int) player.getPosX(), (int) player.getPosY(), (int) player.getSizeX(), (int) player.getSizeY());

            if(Mouse_.intersects(Player_)) {
                player.move(deltaX, deltaY);
            }
        }


        if(isStart) {
            switch (level) {
                case 1 -> {
                    if (max_alien_count == 0)
                        timers.get(0).start();
                }
                case 2 -> {
                    if (max_alien_count == 0) {
                        timers.get(0).start();
                        timers.get(1).start();
                    }

                    if (max_alien_count == 3) {
                        timers.get(1).stop();
                    }

                }
                case 3 -> {
                    if (max_alien_count == 0) {
                        timers.get(0).start();
                        timers.get(1).start();
                    }

                    if (max_alien_count == 5) {
                        timers.get(1).stop();
                    }
                }
                case 4 -> {
                    timers.get(0).stop();
                    timers.get(1).stop();
                }

            }

            if (player.getLifepoint() <= 0) {
                level = 4;
                player.isMove = false;
            }

            for (GameObject gameObject : gameObjects)
            {
                if(!player.isMove && (gameObject instanceof  AlienShip))
                {
                    AlienShip alienShip = (AlienShip) gameObject;
                    alienShip.isMove = false;
                }
            }

        }
        gamePanel.repaint();
    }
}