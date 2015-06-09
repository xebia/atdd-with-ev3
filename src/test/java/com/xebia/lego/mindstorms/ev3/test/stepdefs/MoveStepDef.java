package com.xebia.lego.mindstorms.ev3.test.stepdefs;

import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import com.xebia.lego.mindstorms.ev3.behavior.DriveForward;
import com.xebia.lego.mindstorms.ev3.behavior.StopOnYellow;
import com.xebia.lego.mindstorms.ev3.behavior.TurnOnRed;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MoveStepDef {

    Arbitrator arbitrator;
    DriveForward driveForward = new DriveForward();
    Behavior stopOnYellow = new StopOnYellow();
    Behavior turnOnRed = new TurnOnRed();
    Behavior[] behaviorList;

    @When("^the robot is moving and encounters red$")
    public void the_robot_is_moving_and_encounters_red() throws Throwable {
        behaviorList = new Behavior[]{driveForward, turnOnRed};
        arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();
        assertThat(Ev3BrickIO.colorSensor.getColorID(), is(13));
    }

    @Then("^the robot turns$")
    public void the_robot_moves_backwards() throws Throwable {
        assertThat(Ev3BrickIO.leftMotor.isMoving(), is(true));
    }



    @When("^the robot is moving and encounters yellow$")
    public void the_robot_encounters_yellow() throws Throwable {
        behaviorList = new Behavior[]{driveForward, stopOnYellow};
        arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();
        assertThat(Ev3BrickIO.colorSensor.getColorID(), is(3));
    }

    @Then("^the robot stops$")
    public void the_robot_stops() throws Throwable {
        assertThat(Ev3BrickIO.leftMotor.isMoving(), is(false));
    }
}
