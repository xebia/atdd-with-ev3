package com.xebia.lego.mindstorms.ev3.test.stepdefs;

import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import com.xebia.lego.mindstorms.ev3.behavior.Beep;
import com.xebia.lego.mindstorms.ev3.behavior.Drive;
import com.xebia.lego.mindstorms.ev3.behavior.Stop;
import com.xebia.lego.mindstorms.ev3.behavior.Turn;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MoveStepDef {

    Arbitrator arbitrator;
    Drive driveForward = new Drive();
    Behavior stopOnColor = new Stop();
    Behavior turnOnColor = new Turn();
    Behavior beepOnColor = new Beep();
    Behavior[] behaviorList;


    //Turn on color

    @Given("^the robot is moving$")
    public void the_robot_is_moving() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the robot encounters a red line$")
    public void and_encounters_a_red_line() throws Throwable {
        behaviorList = new Behavior[]{driveForward, turnOnColor};
        arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();

        assertThat(Ev3BrickIO.foundred, is(true));
    }

    @Then("^the robot turns$")
    public void the_robot_turns() throws Throwable {

        assertThat(Ev3BrickIO.rightMotor.getTachoCount(), is(-360));

        arbitrator.stop();
    }

    //Beep on color


    @When("^the robot encounters white$")
    public void the_robot_encounters_white() throws Throwable {
        behaviorList = new Behavior[]{driveForward, turnOnColor, beepOnColor};
        arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();
    }

    @Then("^the robot beeps$")
    public void the_robot_beeps() throws Throwable {

        assertThat(Ev3BrickIO.beeping, is(true));
    }


    @When("^the robot encounters yellow$")
    public void the_robot_encounters_yellow() throws Throwable {

        behaviorList = new Behavior[]{driveForward, stopOnColor};
        arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();

        while (Ev3BrickIO.colorSensor.getColorID() != Ev3BrickIO.stopColor) {
            behaviorList = new Behavior[]{driveForward, stopOnColor};
            arbitrator = new Arbitrator(behaviorList, true);
            arbitrator.start();
        }
        assertThat(Ev3BrickIO.colorSensor.getColorID(), is(Ev3BrickIO.stopColor));
    }

    @Then("^the robot stops$")
    public void the_robot_stops() throws Throwable {

        assertThat(Ev3BrickIO.leftMotor.isMoving(), is(false));

    }
}


