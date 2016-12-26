
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import java.util.Random;

/**
 *
 * @author TxGVNN
 */
public class BotTank extends Entity {

    Image img;
    Random rd;
    int duocBan;
    boolean active = true;
    private int mangHuong[];
    private int lanMove, huongTamThoi;

    public BotTank(int x, int y) {
        rd = new Random();
        duocBan = rd.nextInt(15);
        mangHuong = new int[16];
        lanMove = rd.nextInt(9);
        for (int i = 0; i < 4; i++) {
            mangHuong[i] = 0;
        }
        for (int i = 4; i < 8; i++) {
            mangHuong[i] = 1;
        }
        for (int i = 8; i < 12; i++) {
            mangHuong[i] = 2;
        }
        for (int i = 12; i < 16; i++) {
            mangHuong[i] = 3;
        }
        this.x = x;
        this.y = y;
        this.direction = rd.nextInt(3);
        try {
            img = Image.createImage("/entity/xeBot.png");
        } catch (IOException ex) {
            System.out.print("Loi tao anh");
        }
        createSprite();
    }

    protected final void createSprite() {
        sprite = new Sprite(img);
        sprite.defineReferencePixel(16, 16);

    }

    public void paint(Graphics g) {
        sprite.setRefPixelPosition(x, y);
        sprite.paint(g);

    }

    public void update() {

//            if (super.canRun()){    
//            super.move(1);        
//        } else lanMove = 9;
    }

    public void auto() {
        if (lanMove >= 9) {
            lanMove = 0;
            huongTamThoi = mangHuong[rd.nextInt(16)];
        }
        lanMove++;
        super.changeDirection(huongTamThoi);

    }

}
