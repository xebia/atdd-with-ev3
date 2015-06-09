package com.xebia.lego.mindstorms.ev3.behavior;


import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import lejos.robotics.subsumption.Behavior;

public class StopOnYellow implements Behavior {

    private final Behavior driveforward;
    private boolean suppressed = false;

    public StopOnYellow(Behavior driveforward) {
        this.driveforward = driveforward;
    }
    public boolean takeControl() {
        return !suppressed && Ev3BrickIO.colorSensor.getColorID() == 3;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        suppressed = true;
        driveforward.suppress();
    }
}
