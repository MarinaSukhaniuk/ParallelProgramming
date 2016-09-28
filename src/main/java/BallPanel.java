import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BallPanel extends JPanel {
    private static ArrayList<Ball> balls = new ArrayList();
    private SubPanel subpanel;
    private static Dimension dimension;
    private final int BOTTOM_PANE = 40;

    public static Ellipse2D l1;
    public static Ellipse2D l2;
    public static Ellipse2D l3;
    public static Ellipse2D l4;

    public BallPanel(Dimension dm) {
        Dimension subpaneDimensions = new Dimension(dm.width, BOTTOM_PANE);
        this.subpanel = new SubPanel(subpaneDimensions);
        System.out.println("Thread name = "
                + Thread.currentThread().getName());
        this.setLayout(new BorderLayout());
        this.add(subpanel, BorderLayout.SOUTH);
        dm.setSize(dm.getWidth(), dm.getHeight() - 2 * BOTTOM_PANE);
        this.dimension = dm;
        balls.add(new Ball((int) dimension.getWidth(), (int) dimension.getHeight()));

        this.l1 = new Ellipse2D.Double(0, (int) dimension.getHeight() - 20, 35, 35);
        this.l2 = new Ellipse2D.Double((int) (dimension.getWidth() - 35), 0, 35, 35);
        this.l3 = new Ellipse2D.Double(0, 0, 35, 35);
        this.l4 = new Ellipse2D.Double((int) dimension.getWidth() - 35, (int) dimension.getHeight() - 20, 35, 35);
        this.repaint();
    }

    public static void removeBall(){

    }

    /**
     * Move all ball
     */
    synchronized public void move(long sleeptime) {
        for (int i = 0; i < balls.size(); i++) {
            if (balls.get(i).move()) {
                increase(sleeptime);
            }
            if(balls.get(i).toLose()){
                balls.remove(i);
            }
        }
        this.repaint();
    }


    /**
     * Increase score label
     */
    public void increase(long sleeptime) {
        subpanel.increaseScore(sleeptime);
    }

    /**
     * Add ball to arraylist
     */
    public static void addBall() {
        balls.add(new Ball((int) dimension.getWidth(), (int) dimension.getHeight()));
    }

    public void add(Ball b) {
        this.balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLuse(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball bal : balls) {
            bal.draw(g2);
        }
    }

    private void drawLuse(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(l1);
        g2.fill(l2);
        g2.fill(l3);
        g2.fill(l4);
    }
}