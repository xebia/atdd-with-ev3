package com.xebia.lego.mindstorms.ev3.test;


import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;

public class StopOnYellow implements Behavior {

    private final Behavior driver;
    private boolean suppressed = false;
    private boolean stopReading = false;

    public StopOnYellow(Behavior driver) {
        this.driver = driver;
    }
    public boolean takeControl() {
        boolean result = false;
        if (!stopReading && StopTest.colorSensor.getColorID() == 3) {
            System.out.println("Yellow detected!");
            result = true;
        }
        return result;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
            suppressed = false;
            stopReading = true;
            driver.suppress();
    }
}
