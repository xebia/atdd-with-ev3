package com.xebia.lego.mindstorms.ev3.test;

import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"}, tags = {"@red"})
public class CucumberTest {

    @BeforeClass
    public static void setup() throws RemoteException, NotBoundException, MalformedURLException {
        Ev3BrickIO.init();
    }

    @AfterClass
    public static void tearDown() throws RemoteException {
        Ev3BrickIO.tearDown();
    }

}
