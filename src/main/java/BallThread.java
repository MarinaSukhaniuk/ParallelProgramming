import java.awt.*;

/**
 * Created by Marina on 28.09.2016.
 */
public class BallThread extends Thread {
    private boolean work = true;
    private Ball ball;
    private Dimension dimension;

    public BallThread(Dimension dimension, Color color) {
        this.ball = new Ball((int) dimension.getWidth(), (int) dimension.getHeight(), color);
        if(ball.getColor() == Color.RED){
            this.setPriority(8);
        }else {
            this.setPriority(5);
        }
        this.dimension = dimension;
    }

    @Override
    public void run() {
        while (work) {
            if(ball.toLose()){
                work=false;
                return;
            }
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BounceFrame.getBallPanel().move(50, ball);
        }
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
