package com.xebia.lego.mindstorms.ev3.behavior;


import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;

public class TurnOnRed implements Behavior {

    //private final Behavior turn;
    private boolean suppressed = false;
    //private final Behavior driver;

    public TurnOnRed(Behavior driver) {
        //this.driver = driver;
        //System.out.println(driver);
    }

    public boolean takeControl() {
        System.out.println("Gevonden kleur is: " + Ev3BrickIO.colorSensor.getColorID());
        return Ev3BrickIO.colorSensor.getColorID() == 0;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {

        System.out.println("action");
        suppressed = false;

        try {
            Ev3BrickIO.leftMotor.rotate(360, true);
            Ev3BrickIO.rightMotor.rotate(-360, true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
            while(Ev3BrickIO.colorSensor.getColorID() == 0 && !suppressed )
                System.out.println("yield");
                Thread.yield();
        }

    }


