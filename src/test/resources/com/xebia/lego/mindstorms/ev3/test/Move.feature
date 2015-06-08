Feature: Move Robot

  Scenario: Robot stops moving when yellow detected
    Given the robot is moving
    When the robot encounters yellow
    Then the robot stops

