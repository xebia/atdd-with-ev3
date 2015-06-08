package com.xebia.lego.mindstorms.ev3.test;

import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;


public class DriveForward implements Behavior{

    private boolean suppressed = false;

    public boolean takeControl() {
        return !suppressed;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        try {
            Ev3BrickIO.leftMotor.forward();
            Ev3BrickIO.rightMotor.forward();
            while( !suppressed ) {
                Thread.yield();
            }
            Ev3BrickIO.leftMotor.stop(true);
            Ev3BrickIO.rightMotor.stop(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
