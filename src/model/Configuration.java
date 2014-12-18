package model;

public class Configuration {
    private int rotationStep = 30;
    private int speed = 2000;
    private int originAngle = 0;

    public int getRotationStep() {
        return rotationStep;
    }

    public void setRotationStep(int rotationStep) {
        this.rotationStep = rotationStep;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getOriginAngle() {
        return originAngle;
    }

    public void setOriginAngle(int originAngle) {
        this.originAngle = originAngle;
    }
}
