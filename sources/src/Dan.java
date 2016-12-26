
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TxGVNN
 */
public class Dan extends Entity {

    Image img;
    boolean active = true;
    boolean type;
    int speed = 6;
    public Dan(int x, int y, int direction, boolean type) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.type = type;
        try {
            if (type) 
                img = Image.createImage("/entity/dan.png");
             else 
                img = Image.createImage("/entity/danBot.png");
            
        } catch (IOException ex) {
            System.out.println("Err: Dan()");
        }
        createSprite();
    }

    protected void createSprite() {
        sprite = new Sprite(img);
        sprite.defineReferencePixel(5, 5);
        switch (direction) {
            case 0:
                y -= 16;
                break;
            case 1:
                x += 16;
                break;
            case 2:
                y += 16;
                break;
            case 3:
                x -= 16;
                break;
        }

    }

    public void paint(Graphics g) {
        if (active) {
            sprite.setRefPixelPosition(x, y);
            sprite.paint(g);
        }
    }

    public void update() {
        if (!this.canRun()) {
            active = false;
        } else {
            this.move(8);
        }
    }
        protected boolean canRun(){
        boolean can = true;
        if ( ((x<=5)&&( direction == DIRECTION_LEFT))||((x>=195)&&(direction == DIRECTION_RIGHT))||((y<=5)&&(direction == DIRECTION_UP))||((y>=311)&&(direction== DIRECTION_DOWN))){
            can = false;
        }
        return can;
    }

}
