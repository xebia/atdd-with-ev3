package com.xebia.lego.mindstorms.ev3.behavior;

import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;

import java.rmi.RemoteException;

public class Beep implements Behavior {

    private boolean suppressed = false;

    public boolean takeControl() {
        try {

            System.out.println("Color is: " + Ev3BrickIO.colorSensor.getColorID());
            return Ev3BrickIO.running && Ev3BrickIO.colorSensor.getColorID() == Ev3BrickIO.beepColor;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Could not read color sensor (Beep)");
            return false;
        }
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        suppressed = false;


        System.out.println("!! Beeping !!");
        Ev3BrickIO.beeping = true;

        Sound.playNote(Sound.PIANO, 10, 5);
    }
}
