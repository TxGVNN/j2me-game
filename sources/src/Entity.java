
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.Sprite;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TxGVNN
 */
public abstract class Entity {

    int oldX, oldY;
    int x, y;
    int speed = 4;
    int width, height;
    public Sprite sprite = null;
    protected boolean active = true;
    public static final int DIRECTION_UP = 0;//2
    public static final int DIRECTION_RIGHT = 1;//6
    public static final int DIRECTION_DOWN = 2;//8
    public static final int DIRECTION_LEFT = 3;//4
    protected volatile int direction = DIRECTION_UP;
    private volatile boolean updateDirection = false;
    private volatile boolean updatePosition = true;
    public boolean chuaDam = true;
    public int tdNong, tdVai, tdMong, tdChan, tdTrai, tdPhai;
    // Tao nhan vat game
    public Entity(){
        
                    tdNong= y -16;
                    tdVai = y-5;
                    tdMong = y+5;
                    tdChan = y+16;
                    tdTrai = x-16;
                    tdPhai = x+16;

    }
    protected abstract void createSprite();

    /**
     * ve len man hinh
     */
    public abstract void paint(Graphics g);

    /**
     * Method responsible for update the GameState
     */
    public abstract void update();

    public void refresh() {
                    switch (direction) {
                case DIRECTION_UP:
                    tdNong= y -16;
                    tdVai = y-5;
                    tdMong = y+5;
                    tdChan = y+16;
                    tdTrai = x-16;
                    tdPhai = x+16;
                    break;
                case DIRECTION_DOWN:
                    tdNong= y +16;
                    tdVai = y+5;
                    tdMong = y-5;
                    tdChan = y-16;
                    tdTrai = x+16;
                    tdPhai = x-15;
                    break;
                case DIRECTION_LEFT:
                    tdNong= x -16;
                    tdVai = x-5;
                    tdMong = x+5;
                    tdChan = x+16;
                    tdTrai = y+15;
                    tdPhai = y-15;

                    break;
                case DIRECTION_RIGHT:
                    tdNong= x +16;
                    tdVai = x+5;
                    tdMong = x-5;
                    tdChan = x-16;
                    tdTrai = y-15;
                    tdPhai = y+15;

                    break;
            }

        // Di chuyen xe tang

        if (updatePosition) {
            if (canRun()&& chuaDam) {
                move(speed);
            }
        }

        // Thay doi huong di chuyen
        if (updateDirection) {
            chuaDam = true;
            updateDirection = false;
            updatePosition = true;

            switch (direction) {
                case DIRECTION_UP:
//                    sprite.setTransform(Sprite.TRANS_NONE);
                    sprite.setTransform(Sprite.TRANS_MIRROR);
                    break;
                case DIRECTION_DOWN:
                    sprite.setTransform(Sprite.TRANS_MIRROR_ROT180);
                    break;
                case DIRECTION_LEFT:
                    sprite.setTransform(Sprite.TRANS_MIRROR_ROT270);
                    break;
                case DIRECTION_RIGHT:
                    sprite.setTransform(Sprite.TRANS_MIRROR_ROT90);
                    break;
            }
        }

    }

    protected boolean changeDirection(int newDirection) {
        boolean changed = direction != newDirection;
        direction = newDirection;
        if (changed) {
            updateDirection = true;
            updatePosition = false;
        }
        return changed;
    }

    protected void move(int distance) {

        switch (direction) {
            case DIRECTION_UP:
                this.y -= distance;
                break;
            case DIRECTION_DOWN:
                this.y += distance;
                break;
            case DIRECTION_LEFT:
                this.x -= distance;
                break;
            case DIRECTION_RIGHT:
                this.x += distance;
                break;
        }
        sprite.setRefPixelPosition(this.x, this.y);
    }

    protected boolean canRun() {
        boolean can = true;
        if (((x <= 16) && (direction == DIRECTION_LEFT)) || ((x >= 183) && (direction == DIRECTION_RIGHT)) || ((y <= 16) && (direction == DIRECTION_UP)) || ((y >= 300) && (direction == DIRECTION_DOWN))) {
            can = false;
        }
        return can;
    }

    public boolean vaCham(Entity other) {
/*        boolean vaCham = false;
        switch (other.direction) {
            case DIRECTION_UP:
                if (direction==0){
//                    System.out.println("Thoa man dic =0");
                if ((tdNong <= other.tdChan+1)&&((x-5<= other.tdTrai+11)||(x+5>= other.tdPhai-11))) {
                    vaCham = true;
  //                  System.out.println("yhanh cong");
                }
                
                }
                 break;
            case DIRECTION_DOWN:

                break;
            case DIRECTION_LEFT:
                break;
            case DIRECTION_RIGHT:

                break;
        }
        return vaCham;
  */
        return this.sprite.collidesWith(other.sprite, true);
    }
}
