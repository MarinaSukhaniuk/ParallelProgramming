import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {
    private final int frameHeight;
    private final int frameWidth;
    private Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    private Color color;

    public Ball(int frameWidth, int frameHeight, Color color) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        x = 150;
        y = 150;
        this.color = color;
    }

    /**
     * Draw ball
     *
     * @param g2 graphics
     */
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    /**
     * Move ball
     */
    public boolean move() {
        boolean done = false;
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
            done = true;
        }
        if (x + XSIZE >= frameWidth) {
            x = frameWidth - XSIZE;
            dx = -dx;
            done = true;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
            done = true;
        }
        if (y + YSIZE >= frameHeight) {
            y = frameHeight - YSIZE;
            dy = -dy;
            done = true;
        }
        return done;
    }

    //Check if ball hit to lose
    public boolean toLose() {
        if (BallPanel.l1.intersects(x, y, XSIZE, YSIZE)) {
            return true;
        }
        if (BallPanel.l2.intersects(x, y, XSIZE, YSIZE)) {
            return true;
        }
        if (BallPanel.l3.intersects(x, y, XSIZE, YSIZE)) {
            return true;
        }
        if (BallPanel.l4.intersects(x, y, XSIZE, YSIZE)) {
            return true;
        }
        return false;
    }

    public Color getColor() {
        return color;
    }
}
