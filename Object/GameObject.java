package Object;
public abstract class GameObject {
    private float posX;
    private float posY;
    private float sizeX;
    private float sizeY;
    private float desX;
    private float desY;

    public GameObject(float posX, float posY, float sizeX, float sizeY, float desX, float desY){
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.desX = desX;
        this.desY = desY;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public abstract void move();

}