package main.Panel;

import main.GameLogic;
import main.GameUI;
import main.Object.AlienShip;
import main.Object.Bullet;
import main.Object.GameObject;
import main.Object.Player;

import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;



public class GamePanel extends JPanel  {

    private GameLogic game;

    public GameUI gameUI;
    private static final int ROWS = 12;
    private static final int COLS = 30;
    private static final int CHANNEL_SIZE = 2;
    private static final int CHANNEL_GAP = 40;

    public GamePanel(GameLogic game){
        this.game = game;
        setPreferredSize(new Dimension(1200,500));

    }

    public void drawLevel(Graphics2D graphics2D)
    {
        Font customFont = null;
        try {
            File fontFile = new File("assets/ARCADECLASSIC.TTF");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        Font font = customFont.deriveFont(Font.TRUETYPE_FONT, 36);
        graphics2D.setFont(font);
        graphics2D.setColor(Color.white);

        String text = null;

        if(game.level != 4) {
            text = "LV " + game.level;
        }else if(game.level >= 4)
        {
            text = "";
        }

        graphics2D.drawString(text, 1050, 40);
    }

    public void drawCenterScreen(Graphics2D graphics2D)
    {
        Font customFont = null;
        try {
            File fontFile = new File("assets/ARCADECLASSIC.TTF");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        Font font = customFont.deriveFont(Font.TRUETYPE_FONT, 128);
        graphics2D.setFont(font);
        graphics2D.setColor(Color.white);

        String text = null;

        if(game.level >= 4 && game.player.lifepoint > 1) {
            text = "WON!";
            graphics2D.drawString(text, 450, 250);

        }else if(game.level == 4 && game.player.lifepoint <= 0)
        {
            text = "GAME OVER!";
            graphics2D.drawString(text, 350, 250);

            try {
                File imageFile = new File("main/resources/replay.png");
                Image replay = ImageIO.read(imageFile);

                graphics2D.drawImage(replay, 570, 300,90,90, null);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (game.nextLevlUi)
        {
            text = "LEVEL UP!";
            graphics2D.drawString(text, 350, 250);

        }

    }

    public void drawStartScreen(Graphics2D graphics2D) {
        Font customFont = null;
        try {
            File fontFile = new File("assets/ARCADECLASSIC.TTF");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        Font font = customFont.deriveFont(Font.TRUETYPE_FONT, 128);
        graphics2D.setFont(font);
        graphics2D.setColor(Color.white);

        String text = null;

        if (!game.isStart) {
            text = "START";
            graphics2D.drawString(text, 450, 250);

            try {
                File imageFile = new File("main/resources/play.png");
                Image play = ImageIO.read(imageFile);

                graphics2D.drawImage(play, 570, 300,90,90, null);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void LifePointIcon(GameObject gameObject,Graphics2D graphics2D)
    {
        if(gameObject instanceof Player) {
            Player player = (Player) gameObject;

            try {
                File imageFile = new File("main/resources/heart.png");
                Image heartImage = ImageIO.read(imageFile);

                int x = 1000;
                int y = 20;

                switch (player.lifepoint) {
                    case 1: {
                        graphics2D.drawImage(heartImage, x, y, 30, 25, null);
                        break;
                    }
                    case 2: {
                        graphics2D.drawImage(heartImage, x, y, 30, 25, null);
                        graphics2D.drawImage(heartImage, x - 50, y, 30, 25, null);
                        break;
                    }
                    case 3: {
                        graphics2D.drawImage(heartImage, x, y, 30, 25, null);
                        graphics2D.drawImage(heartImage, x - 50, y, 30, 25, null);
                        graphics2D.drawImage(heartImage, x - 100, y, 30, 25, null);
                        break;

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void CheckState()
    {
        if(game.level >= 4)
        {
            for (GameObject gameObject : game.getGameObjects()) {
                if(gameObject instanceof AlienShip && gameObject != null) {
                    AlienShip alienShip = (AlienShip) gameObject;
                    alienShip.isMove = false;
                }
                if(gameObject instanceof Player && gameObject != null) {
                    Player player = (Player) gameObject;
                    player.isMove = false;
                }
            }

        }
    }

    public void drawBackground(Graphics2D graphics2D)
    {

        int startX = 0;
        int startY = 0;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if ((row + col) % 2 == 0) {
                    graphics2D.setColor(Color.GRAY);
                } else {
                    graphics2D.setColor(Color.LIGHT_GRAY);
                }

                int x = startX + col * (CHANNEL_SIZE + CHANNEL_GAP);
                int y = startY + row * (CHANNEL_SIZE + CHANNEL_GAP);

                graphics2D.fillRect(x, y, CHANNEL_SIZE, CHANNEL_SIZE);
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawBackground(graphics2D);

        drawStartScreen(graphics2D);

        drawLevel(graphics2D);
        drawCenterScreen(graphics2D);


        for (GameObject gameObject : game.getGameObjects()) {
            LifePointIcon(gameObject, graphics2D);
            graphics2D.drawImage(gameObject.getImage(), (int) gameObject.getPosX(), (int) gameObject.getPosY(), (int) gameObject.getSizeX(), (int) gameObject.getSizeY(), this);
            if(gameObject instanceof AlienShip){
                AlienShip alienShip = (AlienShip) gameObject;
                if(alienShip.bullet != null)
                {
                    for (Bullet bullet : alienShip.bullet)
                    {
                        bullet.draw(graphics2D);
                    }}

            }
        }

        CheckState();
    }


}


