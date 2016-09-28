import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubPanel extends JPanel {
    private JLabel scoreLabel;
    private JLabel sleeptimeLabel;
    private int score;

    public SubPanel(Dimension dm) {
        score = 0;
        this.scoreLabel = addLabel();
        this.sleeptimeLabel = addLabeltime();
        this.setBackground(Color.PINK);
        this.add(addBtnAdd());
        this.add(addBtrRed());
        this.add(this.scoreLabel);
        this.add(this.sleeptimeLabel);
        this.setPreferredSize(dm);

    }

    /**
     * Add button add with click listener
     *
     * @return button
     */
    private JButton addBtnAdd() {
        JButton button = new JButton("Add red ball");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BallPanel.addBall(Color.RED);
            }
        });
        return button;
    }

    private JButton addBtrRed() {
        JButton button = new JButton("Add green ball");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BallPanel.addBall(Color.GREEN);
            }
        });
        return button;
    }

    /**
     * Generate score scoreLabel
     *
     * @return scoreLabel
     */
    private JLabel addLabel() {
        JLabel score = new JLabel("You score:");
        score.setForeground(Color.BLACK);
        return score;
    }
    private JLabel addLabeltime(){
        JLabel score = new JLabel("Your sleeptime:");
        score.setForeground(Color.BLACK);
        return score;
    }

    /**
     * Increase score scoreLabel
     */
    public void increaseScore(long sleeptime) {
        score++;
        this.scoreLabel.setText("You score: " + String.valueOf(score));
        this.sleeptimeLabel.setText("Your sleeptime: "+sleeptime);
    }
}
