package com.xebia.lego.mindstorms.ev3.test;

import lejos.hardware.Sound;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StopTest {

    public static EV3ColorSensor colorSensor;
    public static RMIRegulatedMotor leftMotor;
    public static RMIRegulatedMotor rightMotor;
    static String remoteEv3Ip =  "10.0.1.1";

    @BeforeClass
    public static void setup() throws RemoteException, NotBoundException, MalformedURLException {
        RemoteEV3 brick = new RemoteEV3(remoteEv3Ip);
        brick.setDefault();
        Sound.beep();
        colorSensor = new EV3ColorSensor(brick.getPort("S1"));
        leftMotor = brick.createRegulatedMotor("A", 'L');
        rightMotor = brick.createRegulatedMotor("D", 'L');
        resetMotor(leftMotor);
        resetMotor(rightMotor);

    }

    @AfterClass
    public static void tearDown() throws RemoteException {
        if (colorSensor != null) {
            colorSensor.close();
        }

        closeMotor(leftMotor);
        closeMotor(rightMotor);
    }

    private static void closeMotor(RMIRegulatedMotor motor) throws RemoteException {
        if (motor != null) {
            motor.stop(true);
            motor.close();
        }
    }

    private static void resetMotor(RMIRegulatedMotor motor) throws RemoteException {
        if (motor != null) {
            motor.resetTachoCount();
            motor.rotateTo(0);
            motor.setSpeed(400);
            //motor.setAcceleration(200);
        }
    }

    @Test
    public void testStop() throws RemoteException {
        Behavior b1 = new DriveForward();
        Behavior b2 = new StopOnYellow();
        Behavior[] behaviorList = {b1, b2};
        Arbitrator arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();
        arbitrator.stop();
    }
}
