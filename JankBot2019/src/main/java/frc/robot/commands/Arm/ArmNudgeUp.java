/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArmNudgeUp extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ArmNudgeUp() {
    requires(Robot.arm);

    //Nudges the arm up at a slower speed, for a set amount of time (arm nudge time)
    addSequential(new ArmCallNudgeUp(), RobotMap.ARM_NUDGE_TIME);

    //Stops the arm
    addSequential(new ArmStop());
  }
}
