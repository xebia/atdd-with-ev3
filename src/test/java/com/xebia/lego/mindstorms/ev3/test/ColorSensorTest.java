package com.xebia.lego.mindstorms.ev3.test;


import org.junit.Test;

import java.rmi.RemoteException;

public class ColorSensorTest extends EV3Test {

    @Test
    public void shouldGetColorAndDrive() throws RemoteException {
        System.out.print("Color ID: " + colorSensor.getColorID());
        left.forward();
        right.forward();
        left.stop(true);
        right.stop(true);
        // TODO: Needs assertion!!
    }

}
