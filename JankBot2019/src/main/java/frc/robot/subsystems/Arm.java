/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
   private CANSparkMax armMotor;

  public Arm() {
    armMotor = new CANSparkMax(RobotMap.ARM_CAN, MotorType.kBrushless);
  }
  public void Init(){
    setSpeed(0.0);
  }
  public void down() {
    setSpeed(-RobotMap.ARM_SPEED);
  }
  public void up() {
    setSpeed(RobotMap.ARM_SPEED);
  }
  public void nudgeUp() {
    setSpeed(RobotMap.ARM_SPEED/1.6);
  }
  public void nudgeDown() {
    setSpeed(-RobotMap.ARM_SPEED/1.6);
  }
  public void stop() {
    setSpeed(0.0);
  }
  public void setSpeed(double speed) {
    armMotor.set(speed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
