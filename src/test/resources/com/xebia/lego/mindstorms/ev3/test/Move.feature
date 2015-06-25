@Moving
Feature: The robot can move and perform actions

  Scenario: Robot turns when a red line is detected
    Given the robot is moving
    When the robot encounters a red line
    Then the robot turns

  Scenario: Robot stops moving when yellow detected
    Given the robot is moving
    When the robot encounters yellow
    Then the robot stops

  Scenario: Robot beeps when a white line is detected
    Given the robot is moving
    When the robot encounters white
    Then the robot beeps


