/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  private Victor rightMotor1;
  private Victor rightMotor2;
  private SpeedControllerGroup rightMotors;

  private Victor leftMotor1;
  private Victor leftMotor2;
  private SpeedControllerGroup leftMotors;

  private DifferentialDrive differentialDrive;

  public DriveTrain() {
    leftMotor1 = new Victor(RobotMap.DRIVE_TRAIN_LEFT_1);
    leftMotor2 = new Victor(RobotMap.DRIVE_TRAIN_LEFT_2);
    rightMotor1 = new Victor(RobotMap.DRIVE_TRAIN_RIGHT_1);
    rightMotor2 = new Victor(RobotMap.DRIVE_TRAIN_RIGHT_2);

    leftMotors = new SpeedControllerGroup(leftMotor1, leftMotor2);
    rightMotors = new SpeedControllerGroup(rightMotor1, rightMotor2);

    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }
  public void arcadeDrive(double speed, double rotation) {
    differentialDrive.arcadeDrive(speed, rotation);
  }
  public void tankDrive(double leftSpeed, double rightSpeed) {
    differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }
  public void stop() {
    tankDrive(0.0, 0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
