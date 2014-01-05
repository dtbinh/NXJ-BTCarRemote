package controller;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;

public class MovementController {

    private RemoteMotor engine;
    private RemoteMotor steering;

    public MovementController() {
	this.engine = Motor.A;
	this.steering = Motor.B;
    }

    public void forward() {
	this.engine.forward();
    }

    public void backward() {
	this.engine.backward();
    }

    public void right() {
	this.steering.rotate(10);
    }

    public void left() {
	this.steering.rotate(-10);
    }

    public void stop() {
	this.engine.stop(false);
    }
}
