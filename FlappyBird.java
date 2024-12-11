// FlappyBird.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlappyBird implements ActionListener, KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JFrame frame;
    private Timer timer;
    private int yMotion;
    private int score;
    private boolean gameOver, started;

    public FlappyBird() {
        frame = new JFrame("Flappy Bird");
        timer = new Timer(20, this);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        frame.setVisible(true);

        yMotion = 0;
        score = 0;
        gameOver = false;
        started = false;

        timer.start();
    }

    public void paint(Graphics g) {
        if (!started) {
            g.drawString("Press SPACE to Start", WIDTH / 2 - 50, HEIGHT / 2 - 50);
        } else if (gameOver) {
            g.drawString("Game Over! Score: " + score, WIDTH / 2 - 50, HEIGHT / 2 - 50);
        } else {
            g.fillRect(WIDTH / 2 - 10, HEIGHT / 2 - yMotion, 20, 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (started) {
            yMotion += 2;

            if (yMotion > HEIGHT) {
                gameOver = true;
                timer.stop();
            }

            frame.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                yMotion = 0;
                score = 0;
                gameOver = false;
                started = true;
                timer.start();
            } else if (!started) {
                started = true;
            } else {
                yMotion -= 10;
                score++;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new FlappyBird();
    }
}
