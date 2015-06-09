package com.xebia.lego.mindstorms.ev3.behavior;


import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;

public class TurnOnRed implements Behavior {

    //private final Behavior turn;
    private boolean suppressed = false;
    //private final Behavior driver;

    public boolean takeControl() {
        try {
            //System.out.println("Gevonden kleur is: " + Ev3BrickIO.colorSensor.getColorID());
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

        try {
            System.out.println("Red found, turning robot!");

            Ev3BrickIO.leftMotor.setSpeed(200);
            Ev3BrickIO.rightMotor.setSpeed(200);
            Ev3BrickIO.leftMotor.rotate(360, true);
            Ev3BrickIO.rightMotor.rotate(-360, true);


            while((Ev3BrickIO.leftMotor.isMoving() || Ev3BrickIO.rightMotor.isMoving()) && !suppressed)
                Thread.yield();  // wait till turn is complete or suppressed is called

            System.out.println("Turn Completed!");
            Ev3BrickIO.leftMotor.setSpeed(400);
            Ev3BrickIO.rightMotor.setSpeed(400);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}


