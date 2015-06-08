package com.xebia.lego.mindstorms.ev3.test.stepdefs;

import com.xebia.lego.mindstorms.ev3.test.CucumberTest;
import com.xebia.lego.mindstorms.ev3.test.DriveForward;
import com.xebia.lego.mindstorms.ev3.test.StopOnYellow;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MoveStepDef {

    Arbitrator arbitrator;
    Behavior driveForward = new DriveForward();
    Behavior stopOnYellow = new StopOnYellow();
    Behavior[] behaviorList;

    @When("^the robot is moving and encounters yellow$")
    public void the_robot_encounters_yellow() throws Throwable {
        behaviorList = new Behavior[]{driveForward, stopOnYellow};
        arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();
        assertThat(CucumberTest.colorSensor.getColorID(), is(3));
    }

    @Then("^the robot stops$")
    public void the_robot_stops() throws Throwable {
        assertThat(CucumberTest.leftMotor.isMoving(), is(false));
    }

}