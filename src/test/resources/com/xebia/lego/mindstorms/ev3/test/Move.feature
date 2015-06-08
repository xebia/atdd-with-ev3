Feature: Move Robot

  Scenario: Robot stops moving when yellow detected
    When the robot is moving and encounters yellow
    Then the robot stops