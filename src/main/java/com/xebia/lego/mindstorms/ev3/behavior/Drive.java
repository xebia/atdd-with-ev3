package com.xebia.lego.mindstorms.ev3.behavior;

import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import lejos.robotics.*;
import lejos.robotics.subsumption.Behavior;

import java.awt.*;
import java.rmi.RemoteException;

public class Drive implements Behavior{

    private boolean suppressed = false;

    public boolean takeControl() {
        return Ev3BrickIO.running;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        try {
            suppressed = false;

            //lejos.robotics.Color
            System.out.println("DriveForward, Going forward!");
            System.out.println("Color is: " + Ev3BrickIO.colorSensor.getColorID());

            Ev3BrickIO.leftMotor.forward();
            Ev3BrickIO.rightMotor.forward();
            while( !suppressed ) {
                //System.out.println("Yielding");
                Thread.yield();
            }
            System.out.println("DriveForward, Stopping!");
            Ev3BrickIO.leftMotor.stop(true);
            Ev3BrickIO.rightMotor.stop(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
