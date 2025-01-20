import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {
    Graphics2D g2;
    Kl keyListener = new Kl();

    public Window() {
        this.setSize(Constants.screenWidth, Constants.screenHeight);
        this.setTitle(Constants.title);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        g2 = (Graphics2D)this.getGraphics();
    }

    public void Update(double dt) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0,Constants.screenWidth, Constants.screenHeight);

        if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            System.out.println("The Player is going Up (Arrow Key)");
        }
    }


    @Override
    public void run() {
        double lastFrameTime = 0.0;

        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            Update(deltaTime);

            try {
                Thread.sleep(30);
            } catch (Exception e) {

            }
        }
    }
}
