package main.Panel;

import main.GameLogic;
import main.Object.GameObject;
import main.Object.Player;

import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;



public class GamePanel extends JPanel  {

    private GameLogic game;


    public GamePanel(GameLogic game){
        this.game = game;
        setPreferredSize(new Dimension(1200,500));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

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
        String text = "LV " + game.level;
        graphics2D.drawString(text, 1050, 40);

        for (GameObject gameObject : game.getGameObjects()) {
            graphics2D.drawImage(gameObject.getImage(),(int) gameObject.getPosX(),(int) gameObject.getPosY(),(int) gameObject.getSizeX(),(int) gameObject.getSizeY(),this);

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
//            if (gameObject instanceof AlienShip) {
//                AlienShip alienShip = (AlienShip) gameObject;
//                alienShip.graphics2D_ = graphics2D;
//
//                alienShip.bullet.draw(alienShip.graphics2D_);
//
//                if(!alienShip.hasRun_() && alienShip != null) {
//                    alienShip.shoot();
//                    alienShip.setHasRun(true);
        }
    }
}


