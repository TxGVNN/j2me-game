
import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author TxGVNN
 */
public class MyTank extends Entity {

    private Image img;
    public Image gameOver;
    public MyTank(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            img = Image.createImage("/entity/xe.png");
            gameOver = Image.createImage("/entity/gameOver.png");
        } catch (IOException ex) {
            System.out.print("Loi tao anh");
        }
        createSprite();
    }

    protected final void createSprite() {
        sprite = new Sprite(img, 33,33);
        sprite.defineReferencePixel(16, 16);
        sprite.setFrame(0);
        // sprite.setRefPixelPosition(15, 15);
    }

    public void paint(Graphics g) {
        //      sprite.setPosition(x, y);
        sprite.setRefPixelPosition(x, y);
        sprite.nextFrame();
        sprite.paint(g);
    }

    public void update() {
        System.out.println("TxGVNN");
    }
}
