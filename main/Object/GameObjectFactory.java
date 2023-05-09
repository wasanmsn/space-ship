package main.Object;

public interface GameObjectFactory {
    GameObject createGameObject(GameObjectType type,float posX, float posY, float sizeX, float sizeY, float desX, float desY);
}
