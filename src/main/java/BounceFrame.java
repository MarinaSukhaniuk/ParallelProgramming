import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {

    private static BallPanel ballPanel;
    private boolean stopMove = false;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

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

    public static BallPanel getBallPanel() {
        return ballPanel;
    }
}
