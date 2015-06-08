package com.xebia.lego.mindstorms.ev3.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lejos.hardware.Sound;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"})
public class CucumberTest {


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
            motor.setSpeed(200);
            motor.setAcceleration(400);
        }
    }


}
