import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BallPanel extends JPanel {
    public static volatile ArrayList<BallThread> threadsList = new ArrayList<>();
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
        //balls.add(new Ball((int) dimension.getWidth(), (int) dimension.getHeight()));

        this.l1 = new Ellipse2D.Double(0, (int) dimension.getHeight() - 20, 35, 35);
        this.l2 = new Ellipse2D.Double((int) (dimension.getWidth() - 35), 0, 35, 35);
        this.l3 = new Ellipse2D.Double(0, 0, 35, 35);
        this.l4 = new Ellipse2D.Double((int) dimension.getWidth() - 35, (int) dimension.getHeight() - 20, 35, 35);
        this.repaint();
    }
    /**
     * Move all ball
     */
     synchronized public void move(long sleeptime, Ball ball) {
        if (ball.move()) {
            increase(sleeptime);
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
    public static void addBall(Color color) {
        BallThread ballThread = new BallThread(dimension, color);
        threadsList.add(ballThread);
        ballThread.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLuse(g);
        for (int i = 0; i < threadsList.size(); i++) {
            if (threadsList.get(i).isWork()) {
                threadsList.get(i).getBall().draw((Graphics2D) g);
            } else {
                threadsList.remove(i);
            }
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