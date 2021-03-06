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
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TeleopDrive extends Command {

  private DriveTrain driveTrain;
  private Joystick joystick;
  private boolean autoBack;
  private boolean climb;

  //private boolean climbMode;
  //private double climbSpeed;
  //private double climbRotation;

  public TeleopDrive(DriveTrain driveTrain, boolean autoBack, boolean climb) {
    requires(driveTrain);
    this.driveTrain = driveTrain;
    this.joystick = Robot.driver;
    this.autoBack = autoBack;
    this.climb = climb;
  }
  //  public TeleopDrive(DriveTrain driveTrain, double speed, double rotation) {
  //   this.driveTrain = driveTrain;
  //   this.climbSpeed = speed;
  //   this.climbRotation = rotation;
  //   climbMode = true;
  //  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    driveTrain.stop();
  }
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Robot.elevator.elevatorPID();
    //Robot.climbingElevator.climbingElevatorPID();
    if(autoBack) {
      //System.out.println("In autoback in TElop");
      driveTrain.arcadeDrive(RobotMap.HATCH_AUTO_DRIVE_SPEED, 0);
    } else if(climb) {
      driveTrain.arcadeDrive(0.45, 0);
    } else {
      double speed = RobotMap.THROTTLE_SCALE;
      double steer = RobotMap.STEERING_SCALE;

      if(joystick.getRawButton(RobotMap.kButtonLeftBumper) || (Robot.elevator.getCurrentPosition() > 195.0)) {
        speed = speed / RobotMap.SLOW_THROTTLE_SCALE;
        steer = steer / RobotMap.SLOW_STEERING_SCALE;
      }
      if(joystick.getRawButton(RobotMap.kButtonRightBumper)) {
        speed = speed * RobotMap.BOOST_THROTTLE_SCALE;
        steer = steer * RobotMap.BOOST_STEERING_SCALE;
      }

      speed = speed * -Math.pow(joystick.getRawAxis(RobotMap.kLeftStickY), 3);
      steer = steer * Math.pow(joystick.getRawAxis(RobotMap.kRightStickX), 3);

      if (speed >= RobotMap.MAX_SPEED) {
       speed = RobotMap.MAX_SPEED;
     }
      driveTrain.arcadeDrive(speed, steer);
    }
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
