import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public Kl keyListener;

    public PlayerController(Rect rect, Kl keyListener) {
        this.rect = rect;
        this.keyListener = keyListener;
    }

    public void Update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            if ((rect.y + Constants.paddleSpeed * dt) + rect.height < Constants.screenHeight - Constants.insetsBottom)
                this.rect.y += Constants.paddleSpeed * dt;
        } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            if (rect.y - Constants.paddleSpeed * dt > Constants.toolbarHeight)
                this.rect.y -= Constants.paddleSpeed * dt;
        }
    }
}
