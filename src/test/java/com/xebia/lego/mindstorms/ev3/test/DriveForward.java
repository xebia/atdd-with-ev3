package com.xebia.lego.mindstorms.ev3.test;

import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;


public class DriveForward implements Behavior{

    private boolean suppressed = false;

    public boolean takeControl() {
        return true;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        try {
            suppressed = false;
            System.out.println("DriveForward - Move forward");
            StopTest.leftMotor.forward();
            StopTest.rightMotor.forward();
            while( !suppressed ) {
                Thread.yield();
            }
            System.out.println("DriveForward - Stopping motor");
            StopTest.leftMotor.stop(true);
            StopTest.rightMotor.stop(true);
        } catch (RemoteException e) {
            System.out.println("DriveForward - Exception - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
