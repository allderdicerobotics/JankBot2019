/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.BackClimbingElevator.BackClimbingElevatorDown;
import frc.robot.commands.BackClimbingElevator.BackClimbingElevatorUp;
import frc.robot.commands.BackClimbingElevator.BackGoToHeight;
import frc.robot.commands.BackClimbingElevator.BackMoveToGround;
import frc.robot.commands.BackClimbingElevator.BackClimb0;
import frc.robot.commands.BackClimbingElevator.BackClimbLevel2;
import frc.robot.commands.BackClimbingElevator.BackClimbLevel3;
import frc.robot.commands.FrontClimbingElevator.BottomWheelBackward;
import frc.robot.commands.FrontClimbingElevator.FrontClimbingElevatorDown;
import frc.robot.commands.FrontClimbingElevator.FrontClimbingElevatorUp;
import frc.robot.commands.FrontClimbingElevator.FrontGoToHeight;
import frc.robot.commands.FrontClimbingElevator.FrontMoveToGround;
import frc.robot.commands.FrontClimbingElevator.FrontClimb0;
import frc.robot.commands.FrontClimbingElevator.FrontClimbLevel2;
import frc.robot.commands.FrontClimbingElevator.FrontClimbLevel3;;

public class ClimbZero extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbZero() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.
    System.out.println("zero");
    addParallel(new FrontClimb0());
    addSequential(new BackClimb0());

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
