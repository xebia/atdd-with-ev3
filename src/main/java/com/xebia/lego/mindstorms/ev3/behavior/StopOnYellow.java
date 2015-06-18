package com.xebia.lego.mindstorms.ev3.behavior;


import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import lejos.hardware.Sound;
import lejos.hardware.Sounds;
import lejos.robotics.Color;
import lejos.robotics.subsumption.Behavior;

public class StopOnYellow implements Behavior {

    public boolean takeControl() {
        try {

            System.out.println("Color is: " + Ev3BrickIO.colorSensor.getColorID());
            return Ev3BrickIO.running && Ev3BrickIO.colorSensor.getColorID() == 3;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Could not read color sensor (StopOnYellow)");
            return false;
        }

    }

    public void suppress() {
    }

    public void action() {
        System.out.println("Yellow found, GOAL Completed!");
        Ev3BrickIO.foundyellow = true;
        Ev3BrickIO.running = false;
        Sound.twoBeeps();

    }
}
