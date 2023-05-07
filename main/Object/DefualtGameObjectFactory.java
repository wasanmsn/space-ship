package main.Object;

public class DefualtGameObjectFactory implements GameObjectFactory {
    public GameObject createGameObject(GameObjectType type, float posX, float posY, float sizeX, float sizeY, float desX, float desY) {
        switch (type) {
            case PLAYER:
                return new Player(posX, posY, sizeX, sizeY, desX, desY);
            case METEOR:
                return new Meteor(posX, posY, sizeX, sizeY, desX, desY);
            case BASE:
                return new Base(posX, posY, sizeX, sizeY, desX, desY);
            case BULLET:
                return new Bullet(posX, posY, sizeX, sizeY, desX, desY);
            case ALIEN_SHIP:
                return new AlienShip(posX, posY, sizeX, sizeY, desX, desY);
            default:
                throw new IllegalArgumentException("Invalid GameObjectType: " + type);
        }
    }
}