package com.xebia.lego.mindstorms.ev3.test;


import lejos.robotics.subsumption.Behavior;

public class StopOnYellow implements Behavior {

    private final Behavior driver;
    private boolean suppressed = false;

    public StopOnYellow(Behavior driver) {
        this.driver = driver;
    }
    public boolean takeControl() {
        return !suppressed && StopTest.colorSensor.getColorID() == 3;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        suppressed = true;
        driver.suppress();
    }
}
