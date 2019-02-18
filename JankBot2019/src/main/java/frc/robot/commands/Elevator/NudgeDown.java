/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class NudgeDown extends Command {
  boolean smallNudge;

  public NudgeDown(boolean smallNudge) {
    requires(Robot.elevator);
    this.smallNudge = smallNudge;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //There are two types of nudges:
    //  A manual, small nudge that is activated by pressing the bumper buttons of the operator Gamepad
    //  A larger nudge that is used by the GetHatch and ReleaseHatch Command Groups
    if(smallNudge) {
      Robot.elevator.smallDownNudge();
    }
    else {
      Robot.elevator.releaseHatchNudge();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
