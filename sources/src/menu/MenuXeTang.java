/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author TxGVNN
 */
public class MenuXeTang {
/*
    public static final int NEWGAME =0;
    public static final int SOUNDS =1;
    public static final int HELP =2;
    public static final int ABOUT =3;
    public static final int EXIT =4;
    private static Image background;
    private int width;
    private int height;
    public MenuXeTang(){
        background = loadImage("background.png");
        setItem(NEWGAME, loadSprite("new_game.png",2)));
        setItem(SOUNDS, new MenuItem(loadSprite("sounds.png",2)));
        setItem(HELP, new MenuItem(loadSprite("help.png",2)));
        setItem(ABOUT, new MenuItem(loadSprite("about.png",2)));
        setItem(EXIT, new MenuItem(loadSprite("exit.png",2)));
        setSize(w, h);
    }    
  public final void setSize(int w, int h) {
        width = w;
        height = h;
        final int bgW = background.getWidth();
        final int bgH = background.getHeight();
        final int leftOffset = (width - bgW) / 2;
        final int topOffset = (height - bgH) / 2;
        final int menuW = Math.min(width, bgW);
        final int menuH = Math.min(height, bgH);
        int x = leftOffset + menuW / 2;
        int y = topOffset + menuH / 9;
        for (int i = 0; i < getSize(); i++) {
            MenuItem item = getItem(i);
            item.setCenter(x, y);
            y += item.getHeight();
        }
    }

    public void paint(Graphics g) {
        setFullScreenMode(true);
        g.setColor(0xaeb0a2);
        System.out.println(""+width+"x"+height);
        g.fillRect(0, 0, width, 320);
        g.drawImage(background, width / 2, height / 2, Graphics.HCENTER | Graphics.VCENTER);
        super.paint(g);
    }
    
    protected Image loadImage(String fileName) {
        try {
            return Image.createImage("/menu/" + fileName);
        }
        catch (IOException e) {
            return null;
        }
    }
*/    
    
}
