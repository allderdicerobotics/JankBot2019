/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class TeleopDrive extends Command {

  private DriveTrain driveTrain;
  private Joystick joystick;

  public TeleopDrive(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    requires(this.driveTrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    driveTrain.stop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = RobotMap.THROTTLE_SCALE;
    double steer = RobotMap.STEERING_SCALE;

    if(joystick.getRawButton(RobotMap.kButtonA)) {
      speed = speed / RobotMap.SLOW_THROTTLE_SCALE;
      steer = steer / RobotMap.SLOW_STEERING_SCALE;
    }
    if(joystick.getRawButton(RobotMap.kButtonY)) {
      speed = speed * RobotMap.BOOST_THROTTLE_SCALE;
      steer = steer * RobotMap.BOOST_STEERING_SCALE;
    }
    if (speed >= RobotMap.MAX_SPEED) {
      speed = RobotMap.MAX_SPEED;
    }

    speed = speed * joystick.getRawAxis(RobotMap.kLeftStickY);
    steer = steer * joystick.getRawAxis(RobotMap.kLeftStickX);

    driveTrain.arcadeDrive(speed, steer);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
