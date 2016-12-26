
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import java.util.Vector;


/**
 * @author TxGVNN
 */
public class Game extends GameCanvas implements Runnable {

    private Midlet midlet;
    private static Vector vectorDan, vectorBot;
    private MyTank player;
    int heigh;
    int color = 0x00f0f0f2, black = 0x000000;
    Graphics g;
    boolean run;
//    private boolean ban;
    int[][] mapBot;
    private static int diem=0;

    public Game(Midlet midlet) {
        super(false);
        this.midlet = midlet;
        g = getGraphics();
        vectorBot = new Vector();
        vectorDan = new Vector();
    }

    public void start() {
        run = true;
        Thread t = new Thread(this);
        t.start();
    }

    private void init() {
        setFullScreenMode(true);
        g.setColor(0x838671);
        g.fillRect(0, 0, 240, 320);
        /*        g.setColor(0x838671);
         for (int i = 5; i < 320; i += 5) {
         g.drawLine(5, i, 235, i);
         }
         for (int i = 5; i < 240; i += 5) {
         g.drawLine(i, 5, i, 315);
         }
         */
        g.setColor(0x000000);
        g.drawLine(201, 0, 201, 320);
        g.drawLine(0, 317, 240, 317);
        /*        g.fillRect(0, 0, 240, 5);
         g.fillRect(0, 315, 240, 5);
         g.fillRect(0, 0, 5, 320);
         g.fillRect(235, 0, 5, 320);
         * 
         * 
         g.drawRect(5, 5, 230, 310);
  
         */
        System.out.println("Paint Game");
        mapBot = new int[4][2];
        mapBot[0][0] = 16;
        mapBot[0][1] = 16;
        mapBot[1][0] = 184;
        mapBot[1][1] = 16;
        mapBot[2][0] = 16;
        mapBot[2][1] = 300;
        mapBot[3][0] = 184;
        mapBot[3][1] = 300;
        player = new MyTank(100, 152);
        player.paint(g);
        for (int i = 0; i < 4; i++) {
            BotTank bot = new BotTank(mapBot[i][0], mapBot[i][1]);
            vectorBot.addElement(bot);
            bot.paint(g);
        }

    }

    private void updateGameState() {
        // Xoa bot neu bot khong hoat dong
        if (!vectorBot.isEmpty()) {
            for (int i = (vectorBot.size() - 1); i >= 0; i--) {
                BotTank bot = (BotTank) vectorBot.elementAt(i);
                if (!bot.active) {
                    vectorBot.removeElementAt(i);
                    diem++;
                    BotTank bots = new BotTank(mapBot[i][0], mapBot[i][1]);
                    vectorBot.addElement(bots);
                    
                }
            }
        }
        //89->132
        //Kiem tra xem bot va player co va cham nhau khong
        // Kiem tra xem dan co bi ban hay khong
        for (int i = 0; i < vectorBot.size(); i++) {
            BotTank bot = (BotTank) vectorBot.elementAt(i);
            if ((player.active) && bot.vaCham(player)) {
                bot.chuaDam = false;
            }
            if ((player.active)&&(player.vaCham(bot))) {
                player.chuaDam = false;
//                System.out.println("Va cham+++++++++++++++++");
            }

            for (byte k = 0; k < vectorBot.size(); k++) {
                BotTank botOther = (BotTank) vectorBot.elementAt(k);
                if ((k != i) && (bot.direction == botOther.direction) && (bot.vaCham(botOther))) {
                    bot.chuaDam = false;
                }

            }
            bot.auto();
            bot.refresh();
            
            for (byte j = 0; j < vectorDan.size(); j++) {
                Dan dan = (Dan) vectorDan.elementAt(j);
                if ((dan.type) && (bot.vaCham(dan))) {
                    bot.active = false;
                    dan.active = false;

                }
                if ((!dan.type) && (player.vaCham(dan)) && (player.active)) {
                    player.active = false;
                    dan.active = false;
                }
            }
            
            if (bot.active) {
                if (bot.duocBan % 15 == 0) {

                    Dan dan = new Dan(bot.x, bot.y, bot.direction, false);
                    vectorDan.addElement(dan);
                    bot.duocBan = 1;
                }
                bot.duocBan++;
            }
        }

        if (!vectorDan.isEmpty()) {
            for (int i = vectorDan.size() - 1; i >= 0; i--) {
                Dan dan = (Dan) vectorDan.elementAt(i);
                if (!dan.active) {
                    vectorDan.removeElementAt(i);
                }
            }
        }

        for (int i = 0; i < vectorDan.size(); i++) {
            Dan dan = (Dan) vectorDan.elementAt(i);
            dan.update();
        }
    }

    private void checkUserInput() {
        int state = getKeyStates();
        /*      if ((state & GameCanvas.FIRE_PRESSED) > 0) {
         ban = true;
         banDan();
         }
         else
         */
        if ((state & GameCanvas.UP_PRESSED) > 0) {
            player.changeDirection(MyTank.DIRECTION_UP);
            player.refresh();
        } else if ((state & GameCanvas.RIGHT_PRESSED) > 0) {
            player.changeDirection(MyTank.DIRECTION_RIGHT);
            player.refresh();
        } else if ((state & GameCanvas.DOWN_PRESSED) > 0) {
            player.changeDirection(MyTank.DIRECTION_DOWN);
            player.refresh();
        } else if ((state & GameCanvas.LEFT_PRESSED) > 0) {
            player.changeDirection(MyTank.DIRECTION_LEFT);
            player.refresh();
        }
    }

    private void updateGameScreen(Graphics g) {
        // stores width and height
        // set background color
        setFullScreenMode(true);
        g.setColor(0x838671);
        g.fillRect(0, 0, 240, 320);
        g.setColor(0x000000);
        g.drawLine(201, 0, 201, 320);
        g.drawLine(0, 317, 240, 317);
        g.drawString(String.valueOf(diem), 201, 0, 20);

        
        
        for (byte i = 0; i < vectorBot.size(); i++) {
            BotTank bot = (BotTank) vectorBot.elementAt(i);
            bot.paint(g);
        }
        for (int i = 0; i < vectorDan.size(); i++) {
            Dan dan = (Dan) vectorDan.elementAt(i);
            dan.paint(g);
        }
        if (player.active) {
            player.paint(g);
        } else{
            g.drawImage(player.gameOver,120 , 160, Graphics.VCENTER|Graphics.HCENTER);
        }
        flushGraphics();

    }

    public void run() {
        init();
        while (run) {
//            System.out.println("Co "+vectorDan.size());
            updateGameState();
            // check user input
            checkUserInput();
            // render screen
            updateGameScreen(getGraphics());
            // redraws screen
            flushGraphics();
            // controls at which rate the updates are done
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                System.out.println("loi sleep");
            }
        }
    }

    protected void keyPressed(int keyCode) {
    //    System.out.println("X = "+player.x+" Y = "+player.y);
        //  System.out.println("Nong = "+ player.tdNong+";Vai= "+player.tdVai+"; Mong ="+player.tdMong+"; Chan="+ player.tdChan);
        switch (getGameAction(keyCode)) {
            case GameCanvas.FIRE:
                banDan();
                break;
            /*          case GameCanvas.UP:
             player.changeDirection(MyTank.DIRECTION_UP);
             player.refresh();
             break;
             case GameCanvas.RIGHT:

             player.changeDirection(MyTank.DIRECTION_RIGHT);
             player.refresh();
             break;
             case GameCanvas.LEFT:
             player.changeDirection(MyTank.DIRECTION_LEFT);
             player.refresh();

             break;
             case GameCanvas.DOWN:
             player.changeDirection(MyTank.DIRECTION_DOWN);
             player.refresh();

             break;
             */
        }
    }

    void banDan() {
        if (player.active) {
            Dan dan = new Dan(player.x, player.y, player.direction, true);
            vectorDan.addElement(dan);
        }
    }
    
    void gameOver(){
        
    }

    /*  
     protected void keyRepeated(int keyCode){
     switch (getGameAction(keyCode)){
     case GameCanvas.FIRE:             
     System.out.println("Fire"); 
     Thread th = new Thread() {
     public void run() {
     ban();
     }
     };
     th.start();
            
                
     * 
     * 
     break;
     /*          case GameCanvas.UP:             
     player.changeDirection(2);
     player.refresh();
     break;
     case GameCanvas.RIGHT:             
     System.out.println("right");
     player.changeDirection(4);
     player.refresh();
     break;
     case GameCanvas.LEFT:       
     player.changeDirection(6);
     player.refresh();
     System.out.println("left");
     break;
     case GameCanvas.DOWN:       
     player.changeDirection(8);
     player.refresh();
     System.out.println("down");                
     break;

     }

     }
     */
}
