/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  private Spark intakeMotorLeft;
  private Spark intakeMotorRight;
  private Joystick operator;

  public Intake() {
    intakeMotorLeft = new Spark(RobotMap.INTAKE_PWM_LEFT);
    intakeMotorRight = new Spark(RobotMap.INTAKE_PWM_RIGHT);
    this.operator = Robot.operator;
  }
  public void Init(){
    stop();
  }
  public void in() {
    setSpeed(-RobotMap.INTAKE_IN_SPEED);
  }
  public void out() {
    setSpeed(RobotMap.INTAKE_OUT_SPEED);
  }
  public void setSpeed(double speed) {
    speed = speed * Math.abs(operator.getRawAxis(RobotMap.kRightStickY));
    intakeMotorLeft.setSpeed(speed);
    intakeMotorRight.setSpeed(speed);
  }
  public void stop() {
    intakeMotorLeft.setSpeed(0.0);
    intakeMotorRight.setSpeed(0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
