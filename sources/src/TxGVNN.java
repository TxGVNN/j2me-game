
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @TxGVNN
 */
public class TxGVNN extends Canvas {

    private Image originalResizeImage;
    private Image reflectedImage;
    private Image load;
    private Sprite sprite;
    int color = 0xd1d1d1;
    private byte[] data = new byte[6887];
    private byte[] dataLoad = new byte[3283];
    private byte[] dataLogo = new byte[3615];
    private byte[] haha = {-119, 80, 78, 71, 13, 10, 26, 10};
    Timer timer;
    TimerTask task;

    TxGVNN() {
        try {
            InputStream is = Object.class.getResourceAsStream("/x");
            is.read(data);
            is.close();

            System.arraycopy(haha, 0, dataLogo, 0, 8);
            System.arraycopy(haha, 0, dataLoad, 0, 8);
            System.arraycopy(data, 0, dataLogo, 11, 3604);
            System.arraycopy(data, 3605, dataLoad, 11, 3272);

            Image originalImage = Image.createImage(dataLogo, 0, 3615);
            originalResizeImage = resize(originalImage, (int) (0.875 * getWidth()), (int) (0.25 * 0.875 * getWidth()));
            reflectedImage = createAnh(originalResizeImage, color);
            Image originalLoad = Image.createImage(dataLoad, 0, 3283);
            sprite = new Sprite(originalLoad, 70, 10);
            sprite.defineReferencePixel(35, 0);
//            load = resize(originalLoad,(int)(0.6*getWidth()),(int)(0.14*getWidth()));           
//            sprite = new Sprite(load, (int)(0.3*getWidth()), (int)(0.7*getWidth()));
//            sprite.defineReferencePixel((int) (0.15 * getWidth()), 0);
            sprite.setFrame(0);
            //public abstract class TimerTask extends Object implements Runnable
            // Create a Timer to update the display.
            task = new TimerTask() {
                public void run() {
                    sprite.nextFrame();
                    //        System.out.println("Repaint " + sprite.getFrame());
                    repaint();
                }
            };
            // 
            timer = new Timer();
            timer.schedule(task, 0, 400);


        } catch (IOException ex) {
            System.out.println("Loi TxGVNN()");
        }
    }

    protected void paint(Graphics g) {

        setFullScreenMode(true);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(reflectedImage, getWidth() / 2, getHeight() / 2, Graphics.BOTTOM | Graphics.HCENTER);
        g.setColor(0xff0000);
        sprite.setRefPixelPosition(getWidth() / 2, getHeight() / 2);
        sprite.paint(g);
        //System.out.println(sprite.getFrame());
    }

    private Image resize(Image oldIm, int newW, int newH) {

        Image newIm = null;
        int oldW = oldIm.getWidth();
        int oldH = oldIm.getHeight();

        // Create a array have  RGB array 
        int[] arrOld = new int[oldW * oldH];
        int[] arrNew = new int[newW * newH];

        oldIm.getRGB(arrOld, 0, oldW, 0, 0, oldW, oldH);
        // A old array ,addre offSet,length 1 rows ,toa do goc tren va duoi

        // anh xa tung diem qua New 
        for (int i = 0; i < newH; i++) {
            int temp = i * oldH / newH;
            for (int j = 0; j < newW; j++) {
                arrNew[i * newW + j] = arrOld[temp * oldW + j * oldW / newW];
            }
        }

        //tao 1 anh moi tu anh cu 
        newIm = Image.createRGBImage(arrNew, newW, newH, true);
        return newIm;
    }

    private static Image createAnh(Image image, int bgColor) {
        int w = image.getWidth();
        int h = image.getHeight();
        int reflectionHeight = h;
        Image reflectedImage = Image.createImage(w, h + reflectionHeight);

        Graphics g = reflectedImage.getGraphics();
        g.setColor(bgColor);
        g.fillRect(0, 0, w, h + reflectionHeight);

        g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);

        int[] rgba = new int[w];
        int currentY = -1;

        for (int i = 0; i < reflectionHeight; i++) {
            int y = (h - 1) - (i * h / reflectionHeight);

            if (y != currentY) {
                image.getRGB(rgba, 0, w, 0, y, w, 1);
            }

            int alpha = 0xff - (i * 0xff / reflectionHeight);
            for (int j = 0; j < w; j++) {
                int origAlpha = (rgba[j] >> 24);
                int newAlpha = (alpha & origAlpha) * alpha / 0xff;

                rgba[j] = (rgba[j] & 0x00ffffff);
                rgba[j] = (rgba[j] | (newAlpha << 24));
            }

            g.drawRGB(rgba, 0, w, 0, h + i, w, 1, true);
        }
        return reflectedImage;
    }

    public void stop() {
        task.cancel();
        //System.out.println("Huy TimerTask");
    }

    public void run() {
        //System.out.println("Tao moi TimerTask");
        task = new TimerTask() {
            public void run() {
                sprite.nextFrame();
                //System.out.println("Repaint " + sprite.getFrame());
                repaint();
            }
        };
        timer.schedule(task, 0, 400);
    }
}
