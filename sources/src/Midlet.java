import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;
import menu.MenuXeTang;
import menu.GameMenu;
/**
 * @author TxGVNN
 */
public class Midlet extends MIDlet implements CommandListener{
    Game canvas;
    TxGVNN txg;
    MenuXeTang menuXT;
    GameMenu gM;
    public void startApp() {
        txg = new TxGVNN();
        Display.getDisplay(this).setCurrent(txg);
        try{
            Thread.sleep(1000);
        } catch (Exception ex){
            
        }
        txg.stop();
        canvas = new Game(this);
        canvas.start();
//        menuXT = new MenuXeTang(240, 320);
        //gM = new GameMenu();
        Display.getDisplay(this).setCurrent(canvas);
    }

    public void pauseApp() {
        
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command cmnd, Displayable dsplbl) {
  
    }
}
