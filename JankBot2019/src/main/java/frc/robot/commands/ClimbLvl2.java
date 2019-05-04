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
import frc.robot.commands.FrontClimbingElevator.BottomWheelBackward;
import frc.robot.commands.FrontClimbingElevator.FrontClimbingElevatorDown;
import frc.robot.commands.FrontClimbingElevator.FrontClimbingElevatorUp;

public class ClimbLvl2 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbLvl2() {
    addSequential(new BackClimbingElevatorUp(), 1.1);
    addSequential(new FrontClimbingElevatorUp(), 0.3);

    addParallel(new BackClimbingElevatorUp(), 3.57);
    addSequential(new FrontClimbingElevatorUp(), 3.6);

    addSequential(new BottomWheelBackward(), 1.15);
    addSequential(new BackClimbingElevatorUp(), 0.14);
    addSequential(new FrontClimbingElevatorDown(), 3.6);
    addSequential(new BackClimbingElevatorUp(), 0.64);

    addSequential(new TeleopDrive(Robot.driveTrain, false, true), 0.9);
    addSequential(new BackClimbingElevatorDown(), 3.0);
    //addSequential(new TeleopDrive(Robot.driveTrain, 0.3, 0.0), 0.2);


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
