package com.xebia.lego.mindstorms.ev3;

import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.Color;

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
    public static boolean beeping = false;
    public static int forwardSpeed = 300;
    public static int stopColor = 3;
    public static int turnColor = 0;
    public static int beepColor = 2;

    /*
    Available colors:
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int YELLOW = 3;
    public static final int MAGENTA = 4;
    public static final int ORANGE = 5;
    public static final int WHITE = 6;
    public static final int BLACK = 7;
    public static final int PINK = 8;
    public static final int GRAY = 9;
    public static final int LIGHT_GRAY = 10;
    public static final int DARK_GRAY = 11;
    public static final int CYAN = 12;
    public static final int BROWN = 13;
    public static final int NONE = -1;
    */


    public static void init() throws RemoteException, NotBoundException, MalformedURLException {
        RemoteEV3 brick = new RemoteEV3(remoteEv3Ip);
        brick.setDefault();
        Sound.beep();
        colorSensor = new EV3ColorSensor(brick.getPort("S1"));
        touchSensor = new EV3TouchSensor(brick.getPort("S2"));

        claw = brick.createRegulatedMotor("B", 'M');
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
