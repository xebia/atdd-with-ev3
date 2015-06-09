Feature: Move Robot

  Scenario: Robot turns when a red line is detected
    When the robot is moving and encounters red
    Then the robot turns

  Scenario: Robot stops moving when yellow detected
    When the robot is moving and encounters yellow
    Then the robot stops

