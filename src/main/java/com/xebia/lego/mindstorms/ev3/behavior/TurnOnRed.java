package com.xebia.lego.mindstorms.ev3.behavior;


import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;

public class TurnOnRed implements Behavior {

    private final DriveForward driveforward;
    //private final Behavior turn;
    private boolean suppressed = false;
    //private final Behavior driver;

    public TurnOnRed(DriveForward driveforward) {
        this.driveforward = driveforward;
    }

    public boolean takeControl() {
        System.out.println("Gevonden kleur is: " + Ev3BrickIO.colorSensor.getColorID());
        return !suppressed && Ev3BrickIO.colorSensor.getColorID() == 0;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {

        System.out.println("action");
        suppressed = false;
        driveforward.suppress();

        try {
            System.out.println("turning");

            Ev3BrickIO.leftMotor.rotate(360, true);
            Ev3BrickIO.rightMotor.rotate(-360, true);
            driveforward.unsuppress();
            //Ev3BrickIO.leftMotor.forward();
            //Ev3BrickIO.leftMotor.forward();
            System.out.println("done");
            suppressed = true;

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //suppressed = false; //while(Ev3BrickIO.colorSensor.getColorID() == 0 && !suppressed )
             //   System.out.println("yield");
               // Thread.yield();
        }

    }


