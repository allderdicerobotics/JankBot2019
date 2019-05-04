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
import frc.robot.commands.Elevator.NudgeUp;

public class GetHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public GetHatch() {
    requires(Robot.elevator);

    //Moves the elevator up a small amount (in order to grab onto the hatch in the retreival zone)
    addSequential(new NudgeUp(false));

    //Drives back for a small amount of time to clear away from the retreival zone
    addSequential(new TeleopDrive(Robot.driveTrain, true, false), RobotMap.HATCH_AUTO_DRIVE_TIME);
  }
}
