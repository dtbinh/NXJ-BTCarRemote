package controller;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import model.Configuration;

public class MovementController {

    private RemoteMotor engine;
    private RemoteMotor steering;
    private Configuration config;

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
        this.steering.rotate(this.config.getRotationStep());
    }

    public void left() {
        this.steering.rotate(-this.config.getRotationStep());
    }

    public void stop() {
        this.engine.stop(false);
    }
}
