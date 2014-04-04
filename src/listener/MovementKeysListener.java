package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.MovementController;

public class MovementKeysListener implements KeyListener {

    private MovementController movementController;

    public MovementKeysListener(MovementController movementController) {
        this.movementController = movementController;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.processKeyCode(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void processKeyCode(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.movementController.left();
                break;
            case KeyEvent.VK_RIGHT:
                this.movementController.right();
                break;
            case KeyEvent.VK_UP:
                this.movementController.forward();
                break;
            case KeyEvent.VK_DOWN:
                this.movementController.backward();
                break;
            case KeyEvent.VK_SPACE:
                this.movementController.stop();
                break;
        }
    }
}
