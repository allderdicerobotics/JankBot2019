/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax elevatorMotor;

  public Elevator() {
  elevatorMotor = new CANSparkMax(1, MotorType.kBrushless);
  }
  public void Init() {
    stop();
  }
  public void up() {
    elevatorMotor.set(RobotMap.ELEVATOR_SPEED);
  }
  public void down() {
    elevatorMotor.set(-RobotMap.ELEVATOR_SPEED);
  }
  public void upNudge() {
    elevatorMotor.set(RobotMap.NUDGE_SPEED);
  }
  public void downNudge() {
    elevatorMotor.set(-RobotMap.NUDGE_SPEED);
  }
  public void setSpeed(double speed) {
    //Put limit switch stuff here (if statements)
    elevatorMotor.set(speed);
  }
  public void stop() {
    elevatorMotor.set(0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
