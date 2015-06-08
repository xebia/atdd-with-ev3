package com.xebia.lego.mindstorms.ev3.test.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lejos.hardware.Button;

import static com.xebia.lego.mindstorms.ev3.test.CucumberTest.left;
import static com.xebia.lego.mindstorms.ev3.test.CucumberTest.right;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MoveStepDef {

    @Given("^the robot is moving$")
    public void the_robot_is_moving() throws Throwable {
        left.forward();
        right.forward();
        assertThat(left.isMoving(), is(false));
        Button.waitForAnyPress();
    }

    @When("^the robot encounters yellow$")
    public void the_robot_encounters_yellow() throws Throwable {
        //System.out.print("Color ID: " + colorSensor.getColorID());
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the robot stops$")
    public void the_robot_stops() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
