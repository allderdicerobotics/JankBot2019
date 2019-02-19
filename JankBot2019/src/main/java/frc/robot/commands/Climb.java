/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.Arm.ArmDown;
import frc.robot.commands.Arm.ArmUp;
import frc.robot.commands.ClimbingElevator.BottomWheelBackward;
import frc.robot.commands.ClimbingElevator.ClimbingElevatorDown;
import frc.robot.commands.ClimbingElevator.ClimbingElevatorUp;


public class Climb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Climb() {
    requires(Robot.driveTrain);
    requires(Robot.climbingElevator);
    requires(Robot.arm);

    addSequential(new TeleopDrive(Robot.driveTrain, 0.2, 0.0), 0.3);
    addSequential(new TeleopDrive(Robot.driveTrain, -0.1, 0.0), 0.1);

    addSequential(new ArmDown(false), 3.0);

    //addParallel(new ArmDown(true), 1.0);
    addSequential(new ClimbingElevatorDown(false), 1.0);

    addParallel(new ClimbingElevatorDown(true), 3.0);
    //addParallel(new ArmDown(true), 1.0);
    addSequential(new BottomWheelBackward(), 1.0);
    addSequential(new ArmUp(), 2.0);
    
    addSequential(new ClimbingElevatorUp(), 1.5);

    addSequential(new TeleopDrive(Robot.driveTrain, -0.4, 0.0), 1.0);
  }
}
