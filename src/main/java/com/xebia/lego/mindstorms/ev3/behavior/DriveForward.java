package com.xebia.lego.mindstorms.ev3.behavior;

import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;


public class DriveForward implements Behavior{

    private boolean suppressed = false;

    public boolean takeControl() {
        return !suppressed;
    }

    public void unsuppress() {
        suppressed = false;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        try {
            System.out.println("Going forward!");
            Ev3BrickIO.leftMotor.forward();
            Ev3BrickIO.rightMotor.forward();
            System.out.println("Going forward done!");
            while( !suppressed ) {
                //System.out.println("Yielding");
                Thread.yield();
            }
            System.out.println("Stopping!");
            Ev3BrickIO.leftMotor.stop(true);
            Ev3BrickIO.rightMotor.stop(true);
            System.out.println("Stopping Completed!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
