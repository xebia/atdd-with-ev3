package com.xebia.lego.mindstorms.ev3.behavior;


import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;

public class TurnOnRed implements Behavior {

    private boolean suppressed = false;

    public boolean takeControl() {
        try {
            return Ev3BrickIO.running && Ev3BrickIO.colorSensor.getColorID() == 13;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Could not read color sensor (TurnOnRed");
            return false;
        }
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {

        suppressed = false;
        Ev3BrickIO.foundred = true;

        try {
            System.out.println("Red found, turning robot!");

            Ev3BrickIO.leftMotor.resetTachoCount();
            Ev3BrickIO.rightMotor.resetTachoCount();
            Ev3BrickIO.leftMotor.rotate(360, true);
            Ev3BrickIO.rightMotor.rotate(-360, true);

            while((Ev3BrickIO.leftMotor.isMoving() || Ev3BrickIO.rightMotor.isMoving()) && !suppressed)
                Thread.yield();
            System.out.println("Turn Completed!");
            System.out.println("Angle: " +Ev3BrickIO.rightMotor.getTachoCount());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}


