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
            suppressed = false;
            StopTest.leftMotor.forward();
            StopTest.rightMotor.forward();
            while( !suppressed ) {
                Thread.yield();
            }
            StopTest.leftMotor.stop(true);
            StopTest.rightMotor.stop(true);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
