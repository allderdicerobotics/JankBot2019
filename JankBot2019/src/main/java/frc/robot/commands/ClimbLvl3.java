/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

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
import frc.robot.commands.FrontClimbingElevator.BottomWheelForward;
import frc.robot.commands.FrontClimbingElevator.FrontClimbingElevatorDown;
import frc.robot.commands.FrontClimbingElevator.FrontClimbingElevatorUp;
import frc.robot.commands.FrontClimbingElevator.FrontGoToHeight;
import frc.robot.commands.FrontClimbingElevator.FrontMoveToGround;
import frc.robot.commands.FrontClimbingElevator.FrontClimb0;
import frc.robot.commands.FrontClimbingElevator.FrontClimbLevel2;
import frc.robot.commands.FrontClimbingElevator.FrontClimbLevel3;;

public class ClimbLvl3 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbLvl3() {
    addParallel(new FrontClimb0());  //Move both elevators to their zero position
    addSequential(new BackClimb0());

    addParallel(new FrontMoveToGround()); //Move both elevators to the ground
    addSequential(new BackMoveToGround());

    addParallel(new FrontGoToHeight(20)); //Move both elevators to the height of the platform in increments
    addSequential(new BackGoToHeight(20));
    addParallel(new FrontGoToHeight(40));
    addSequential(new BackGoToHeight(40));
    addParallel(new FrontGoToHeight(60));
    addSequential(new BackGoToHeight(60));
    addParallel(new FrontGoToHeight(90));
    addSequential(new BackGoToHeight(90));

    addSequential(new BottomWheelBackward(), 1.25); //Move the robot forward so the front wheels are the platform

    addSequential(new FrontClimb0()); //Move the front elevator up to its zero position

    addSequential(new BackGoToHeight(120)); //Tilt the robot forward

    addSequential(new TeleopDrive(Robot.driveTrain, false, true), 0.9); //Move the whole robot onto the platform

    addSequential(new BackClimb0()); //Lift the back elevator to complete the climb

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

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
