package dispatcher;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDispatcher implements KeyEventDispatcher {
    private KeyListener keyListener;

    public KeyDispatcher(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        switch (e.getID()) {
            case KeyEvent.KEY_PRESSED:
                this.keyListener.keyPressed(e);
                break;
            case KeyEvent.KEY_RELEASED:
                this.keyListener.keyReleased(e);
                break;
            case KeyEvent.KEY_TYPED:
                this.keyListener.keyTyped(e);
                break;
        }

        return false;
    }
}
