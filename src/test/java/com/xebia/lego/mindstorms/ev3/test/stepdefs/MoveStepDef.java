package com.xebia.lego.mindstorms.ev3.test.stepdefs;

import com.xebia.lego.mindstorms.ev3.Ev3BrickIO;
import com.xebia.lego.mindstorms.ev3.behavior.BeepOnWhite;
import com.xebia.lego.mindstorms.ev3.behavior.DriveForward;
import com.xebia.lego.mindstorms.ev3.behavior.StopOnYellow;
import com.xebia.lego.mindstorms.ev3.behavior.TurnOnRed;

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
    Behavior beepOnWhite = new BeepOnWhite();
    Behavior[] behaviorList;

    @When("^the robot encounters a red line$")
    public void and_encounters_a_red_line() throws Throwable {
        behaviorList = new Behavior[]{driveForward, turnOnRed};
        arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();

        assertThat(Ev3BrickIO.foundred, is(true));
    }

    @Then("^the robot turns$")
    public void the_robot_turns() throws Throwable {

        assertThat(Ev3BrickIO.rightMotor.getTachoCount(), is(-360));


        arbitrator.stop();
    }

    @When("^the robot encounters yellow$")
    public void the_robot_encounters_yellow() throws Throwable {

        behaviorList = new Behavior[]{driveForward, stopOnYellow};
        arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();

        while(Ev3BrickIO.colorSensor.getColorID() != 3)
        {
            behaviorList = new Behavior[]{driveForward, stopOnYellow};
            arbitrator = new Arbitrator(behaviorList, true);
            arbitrator.start();
        }

        assertThat(Ev3BrickIO.colorSensor.getColorID(), is(3));
        }

    @Then("^the robot stops$")
    public void the_robot_stops ()throws Throwable {

        assertThat(Ev3BrickIO.leftMotor.isMoving(), is(false));
        //assertThat(Ev3BrickIO.running, is(false));

        }

    @When("^the robot encounters white$")
    public void the_robot_encounters_white() throws Throwable {
        behaviorList = new Behavior[]{driveForward, turnOnRed, beepOnWhite};
        arbitrator = new Arbitrator(behaviorList, true);
        arbitrator.start();
    }

    @Then("^the robot beeps$")
    public void the_robot_beeps() throws Throwable {

    }

    }

