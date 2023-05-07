package main;

import main.Object.*;
import main.Panel.GamePanel;

import java.util.ArrayList;

public class GameLogic {
    private final GameObjectFactory gameObjectFactory;
    private final float RATIO = 0.25f;
    private boolean isOver;

    private int level;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public GameLogic() {
        this.gameObjectFactory = new DefualtGameObjectFactory();
    }

    void createGameObjects(){
        Player player = (Player) gameObjectFactory.createGameObject(GameObjectType.PLAYER,0,100,315* RATIO,125* RATIO,5,5);
        Meteor meteor = (Meteor) gameObjectFactory.createGameObject(GameObjectType.METEOR, 0, 200, 237* RATIO, 235* RATIO, 5, 5);
        Base base = (Base) gameObjectFactory.createGameObject(GameObjectType.BASE, 1150, 180, 354* RATIO, 354* RATIO, 5, 5);
        gameObjects.add(player);
        gameObjects.add(meteor);
        gameObjects.add(base);

    }

    public ArrayList<GameObject> getGameObjects(){
        return gameObjects;
    }

    void createEnemyObjects(){
        Bullet bullet = (Bullet) gameObjectFactory.createGameObject(GameObjectType.BULLET, 0, 0, 10, 10, 5, 5);
        AlienShip alienShip = (AlienShip) gameObjectFactory.createGameObject(GameObjectType.ALIEN_SHIP, 0, 0, 10, 10, 5, 5);
    }

    public void updatePlayerPosition(GameUI gamePanel, float deltaX, float deltaY) {
        // Assuming player is the first object in the gameObjects list
        Player player = (Player) gameObjects.get(0);
        player.move(deltaX, deltaY);
        gamePanel.repaint();
    }
}
