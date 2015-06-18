package com.xebia.lego.mindstorms.ev3;

import lejos.hardware.Sound;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Ev3BrickIO {
    public static EV3ColorSensor colorSensor;
    public static EV3TouchSensor touchSensor;
    public static RMIRegulatedMotor claw;
    public static RMIRegulatedMotor leftMotor;
    public static RMIRegulatedMotor rightMotor;
    public static String remoteEv3Ip =  "10.0.1.1";

    public static boolean running = true;
    public static boolean foundred = false;
    public static boolean foundyellow = false;
    public static boolean foundWhite = false;
    public static int forwardSpeed = 300;

    public static void init() throws RemoteException, NotBoundException, MalformedURLException {
        RemoteEV3 brick = new RemoteEV3(remoteEv3Ip);
        brick.setDefault();
        Sound.beep();
        colorSensor = new EV3ColorSensor(brick.getPort("S1"));
        touchSensor = new EV3TouchSensor(brick.getPort("S2"));

        claw = brick.createRegulatedMotor("B", 'S');
        leftMotor = brick.createRegulatedMotor("A", 'L');
        rightMotor = brick.createRegulatedMotor("D", 'L');
        resetMotor(leftMotor);
        resetMotor(rightMotor);
    }

    public static void tearDown() throws RemoteException {
        cleanUpSensor(colorSensor);
        cleanUpSensor(touchSensor);
        System.out.println("Cleanup: Closing Motors");
        closeMotor(leftMotor);
        closeMotor(rightMotor);
        closeMotor(claw);
    }

    private static void cleanUpSensor(BaseSensor sensor) {
        if (sensor != null) {
            sensor.close();
            System.out.println("Cleanup: Closing sensor");
        }
    }


    private static void closeMotor(RMIRegulatedMotor motor) throws RemoteException {
        if (motor != null) {
            motor.stop(true);
            motor.close();
        }
    }

    private static void resetMotor(RMIRegulatedMotor motor) throws RemoteException {
        System.out.println("Starting up: Resetting Motors");
        if (motor != null) {
            motor.resetTachoCount();
            motor.rotateTo(0);
            motor.setSpeed(forwardSpeed);
        }
    }
}
