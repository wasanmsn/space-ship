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
    public int level = 1;

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
        player.lifepoint = 3;
        player.isMove = true;

        try {
            Robot robot = new Robot();
            robot.mouseMove(100, 250);
        } catch (AWTException e) {
            e.printStackTrace();
        }

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
                    if (gameObject != null) {
                        if (gameObject instanceof AlienShip) {
                            AlienShip alienShip = (AlienShip) gameObject;
                            checkCollision(player, alienShip);
                        }
                        if (gameObject instanceof Bullet) {
                            Bullet bullet = (Bullet) gameObject;
                            checkCollision(player, bullet);
                        }
                        if (gameObject instanceof Meteor) {
                            Meteor meteor = (Meteor) gameObject;
                            checkCollision(player, meteor);
                        }
                        if (gameObject instanceof Base) {
                            Base base1 = (Base) gameObject;
                            checkCollision(player, base1);
                        }
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
                alien.player = player;
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



    public boolean checkCollision(GameObject object1, GameObject object2) {
        if (player.lifepoint > 0) {
            Rectangle bounds1 = new Rectangle((int) object1.getPosX(), (int) object1.getPosY(), (int) object1.getSizeX(), (int) object1.getSizeY());
            Rectangle bounds2 = new Rectangle((int) object2.getPosX(), (int) object2.getPosY(), (int) object2.getSizeX(), (int) object2.getSizeY());

            if (bounds1.intersects(bounds2) && player.isMove) {
                retrunMouse();

                if (object2 instanceof Base) {

                    player.isMove = false;
                    player.lifepoint = 3;
                    nextLevlUi = true;

                    coutDownNextLevel = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            player.isMove = true;
                            player.move(100,120);
                            retrunMouse();
                            coutDownNextLevel.stop();
                            level = level + 1;
                            nextLevlUi = false;
                            reset(level);
                        }
                    });

                    coutDownNextLevel.start();

                }
                else
                {
                    if(object2 instanceof Player)
                    {

                    }
                    else
                    {
                        player.lifepoint = player.lifepoint - 1;
                    }

                    if (object2 instanceof Bullet) {
                        System.out.println("Bullet Hit");
                    }

                }
                return true;
            }

        }
        return false;
    }


    public void update(GameUI gamePanel, float deltaX, float deltaY) {
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

            if (player.lifepoint <= 0) {
                level = 4;
                player.isMove = false;
            }

            player.move(deltaX, deltaY);
            Player.PlayerPosition = new Point((int) deltaX, (int) deltaY);
        }
        gamePanel.repaint();
    }
}
