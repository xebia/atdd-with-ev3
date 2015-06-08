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
    public static RMIRegulatedMotor left;
    public static RMIRegulatedMotor right;
    static String remoteEv3Ip =  "10.0.1.1";

    @BeforeClass
    public static void setup() throws RemoteException, NotBoundException, MalformedURLException {
        RemoteEV3 brick = new RemoteEV3(remoteEv3Ip);
        brick.setDefault();
        Sound.beep();
        colorSensor = new EV3ColorSensor(brick.getPort("S1"));
        left = brick.createRegulatedMotor("A", 'L');
        right = brick.createRegulatedMotor("D", 'L');
    }

    @AfterClass
    public static void tearDown() throws RemoteException {
        if (colorSensor != null) {
            colorSensor.close();
        }

        closeMotor(left);
        closeMotor(right);
    }

    private static void closeMotor(RMIRegulatedMotor motor) throws RemoteException {
        if (motor != null) {
            motor.stop(true);
            motor.close();
        }
    }

}
