import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallPanel extends JPanel {
    private static ArrayList<Ball> balls = new ArrayList();
    private SubPanel subpanel;
    private static Dimension dimension;
    private final int BOTTOM_PANE = 40;

    public BallPanel(Dimension dm) {
        Dimension subpaneDimensions = new Dimension(dm.width, BOTTOM_PANE);
        this.subpanel = new SubPanel(subpaneDimensions);
        System.out.println("Thread name = "
                + Thread.currentThread().getName());
        this.setLayout(new BorderLayout());
        this.add(subpanel, BorderLayout.SOUTH);
        dm.setSize(dm.getWidth(), dm.getHeight() - 2* BOTTOM_PANE);
        this.dimension = dm;
        balls.add(new Ball((int) dimension.getWidth(), (int) dimension.getHeight()));
        this.repaint();
    }

    /**
     * Move all ball
     */
    public void move(long sleeptime) {
        for (int i = 0 ; i < balls.size() ; i++){
            if(balls.get(i).move())
            {
                increase(sleeptime);
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
        Graphics2D g2 = (Graphics2D) g;
        for (Ball bal : balls) {
            bal.draw(g2);
        }
    }


}