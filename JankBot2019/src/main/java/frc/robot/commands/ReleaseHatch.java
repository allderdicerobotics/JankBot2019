/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.Elevator.ElevatorHeight0;
import frc.robot.commands.Elevator.NudgeDown;

public class ReleaseHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ReleaseHatch() {
    requires(Robot.elevator);

    //Moves the elevator down a small amount (in order to leave the hatch on the velcro of the rocket)
    addSequential(new NudgeDown(false));

    //Drives back for a small amount of time to clear away from the rocket
    addSequential(new TeleopDrive(Robot.driveTrain, true, false), RobotMap.HATCH_AUTO_DRIVE_TIME);

    //Moves the elevator back to the bottom
    addSequential(new ElevatorHeight0());
  }
}
