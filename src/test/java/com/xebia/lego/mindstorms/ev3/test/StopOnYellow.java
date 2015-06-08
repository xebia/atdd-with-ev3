package com.xebia.lego.mindstorms.ev3.test;


import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;

public class StopOnYellow implements Behavior {

    private boolean suppressed = false;
    private boolean stopReading = false;

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
        try {
            suppressed = false;
            stopReading = true;
            System.out.println("StopOnYellow - Stop motors");
            StopTest.leftMotor.stop(true);
            StopTest.rightMotor.stop(true);

            while( StopTest.leftMotor.isMoving() && StopTest.rightMotor.isMoving() && !suppressed ) {
                Thread.yield();
            }

            System.out.println("StopOnYellow - Stop motors again");
            StopTest.leftMotor.stop(true);
            StopTest.rightMotor.stop(true);
            StopTest.leftMotor.close();
            StopTest.rightMotor.close();
            StopTest.colorSensor.close();


        } catch (RemoteException e) {
            System.out.println("StopOnYellow - Exception - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
