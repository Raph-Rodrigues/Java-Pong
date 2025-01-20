import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {
    public Graphics2D g2;
    public Kl keyListener = new Kl();
    public Rect playerOne, ai, ball;
    public PlayerController playerController;

    public Window() {
        this.setSize(Constants.screenWidth, Constants.screenHeight);
        this.setTitle(Constants.title);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);

        Constants.toolbarHeight = this.getInsets().top;
        Constants.insetsBottom = this.getInsets().bottom;
        g2 = (Graphics2D)this.getGraphics();

        playerOne = new Rect(Constants.horizontalPadding, 40, Constants.paddleWidth, Constants.paddleHeight, Constants.paddleColor);
        playerController = new PlayerController(playerOne, keyListener);

        ai = new Rect(Constants.screenWidth - Constants.paddleWidth - Constants.horizontalPadding, 40, Constants.paddleWidth, Constants.paddleHeight, Constants.paddleColor);
        ball = new Rect(Constants.screenWidth / 2, Constants.screenHeight / 2, Constants.ballWidth, Constants.ballWidth, Constants.paddleColor);
    }

    public void Update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        playerController.Update(dt);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0,Constants.screenWidth, Constants.screenHeight);

        playerOne.draw(g2);
        ai.draw(g2);
        ball.draw(g2);
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;

        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            Update(deltaTime);

        }
    }
}
