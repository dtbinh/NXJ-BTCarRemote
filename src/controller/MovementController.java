package controller;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import model.Configuration;

public class MovementController {

    private enum TurnDirection {
        LEFT, RIGHT, CENTER;

        private TurnDirection left;
        private TurnDirection right;

        static {
            LEFT.left = null;
            LEFT.right = CENTER;
            RIGHT.left = CENTER;
            RIGHT.right = null;
            CENTER.left = LEFT;
            CENTER.right = RIGHT;
        };

        public TurnDirection left() {
            return this.left;
        }

        public TurnDirection right() {
            return this.right;
        }
    };

    private RemoteMotor engine;
    private RemoteMotor steering;
    private Configuration config;
    private TurnDirection turnDirection = TurnDirection.CENTER;

    public MovementController(Configuration config) {
        this.engine = Motor.A;
        this.steering = Motor.B;
        this.config = config;
    }

    public void forward() {
        this.engine.setSpeed(this.config.getSpeed());
        this.engine.forward();
    }

    public void backward() {
        this.engine.setSpeed(this.config.getSpeed());
        this.engine.backward();
    }

    public void right() {
        TurnDirection right = this.turnDirection.right();
        if (right == null) {
            return;
        }
        this.turnDirection = right;
        this.steering.rotate(this.config.getRotationStep());
    }

    public void left() {
        TurnDirection left = this.turnDirection.left();
        if (left == null) {
            return;
        }
        this.turnDirection = left;
        this.steering.rotate(-this.config.getRotationStep());
    }

    public void stop() {
        this.engine.stop(false);
    }
}
