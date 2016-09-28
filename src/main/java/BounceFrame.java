import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {

    private BallPanel ballPanel;
    private boolean stopMove = false;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    //time division
    private static final int moveDiv = 200;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Ball simple programm");
        this.setResizable(false);
        this.ballPanel = new BallPanel(this.getSize());
        this.add(ballPanel);
        setToCenter();
    }

    /**
     * Set frame to center
     */
    private void setToCenter() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    /**
     * Start game
     */
    public void simulate() {

        Ball ball = new Ball(getWidth(), getHeight());
        ballPanel.addBall();
        Thread thread = new BallThread(ball);

        System.out.println("Thread name = " + thread.getName());

        long start = System.currentTimeMillis();
        while (true && !stopMove) {
            long sleepTime = generateTime(start);
            ballPanel.move(sleepTime);
            //thread.start();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * Slow balls
     *
     * @param startTimeStamp program start time
     * @return thread sleep time
     */
    private long generateTime(long startTimeStamp) {
        long currentMillis = System.currentTimeMillis();
        long result = (currentMillis - startTimeStamp) / moveDiv;
        return result;
    }
}
