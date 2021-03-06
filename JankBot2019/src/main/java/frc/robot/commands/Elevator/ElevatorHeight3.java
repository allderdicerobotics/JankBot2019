/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ElevatorHeight3 extends Command {
  public ElevatorHeight3() {
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Set the elevator to the level to place a hatch on level 3 of the rocket,
    //or place a ball in level 3 of the rocket. 
    //This changes based on whether the operator is holding down the "BACK" button
    if(Robot.operator.getRawButton(RobotMap.kButtonBack)) {
      Robot.elevator.goToLvl3Hatch();
      System.out.println("Set height to Hatch 3");
    }
    else {
      Robot.elevator.goToLvl3Ball();
      System.out.println("Set height to Ball 3");
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
