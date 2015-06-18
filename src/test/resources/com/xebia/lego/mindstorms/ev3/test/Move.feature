Feature: The robot can move and perform actions

  Scenario: Robot turns when a red line is detected
    When the robot encounters a red line
    Then the robot turns

  Scenario: Robot stops moving when yellow detected
   When the robot encounters yellow
    Then the robot stops

  Scenario: Robot beeps when a white line is detected
    When the robot encounters white
    Then the robot beeps


  Scenario: Robot beeps when blue is detected.
