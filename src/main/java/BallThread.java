import java.awt.*;

/**
 * Created by Marina on 28.09.2016.
 */
public class BallThread extends Thread {
    private boolean work = true;
    private Ball ball;
    private Dimension dimension;
    private long sleeptime;
    private long timeStart;
    //time division
    private static final int moveDiv = 200;

    public BallThread(Dimension dimension, Color color) {
        this.ball = new Ball((int) dimension.getWidth(), (int) dimension.getHeight(), color);
        if(ball.getColor() == Color.RED){
            this.setPriority(8);
        }else {
            this.setPriority(5);
        }
        sleeptime = 5;
        this.dimension = dimension;
        this.timeStart = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (work) {
            if(ball.toLose()){
                work=false;
                return;
            }
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BounceFrame.getBallPanel().move(50, ball);
            generateTime(timeStart);
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
        if (result > 1){
            sleeptime++;
            timeStart = System.currentTimeMillis();
        }
         return result;
    }

    public Ball getBall() {
        return ball;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }
}
