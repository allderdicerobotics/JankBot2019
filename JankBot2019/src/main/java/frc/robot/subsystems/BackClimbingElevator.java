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
public class BackClimbingElevator extends Subsystem {
  private CANSparkMax backClimbingElevatorMotor;

  public BackClimbingElevator() {
    backClimbingElevatorMotor = new CANSparkMax(RobotMap.BACK_CLIMGING_ELEVATOR_CAN, MotorType.kBrushless);
  }
  public void Init() {
  }
  public void up() {
    setSpeed(RobotMap.BACK_CLIMBING_ELEVATOR_SPEED);
  }
  public void down() {
    setSpeed(-RobotMap.BACK_CLIMBING_ELEVATOR_SPEED);
  }
  public void setSpeed(double speed) {
    if(speed > RobotMap.BACK_CLIMBING_ELEVATOR_MAX_SPEED) {
      speed = RobotMap.BACK_CLIMBING_ELEVATOR_MAX_SPEED;
    }
    backClimbingElevatorMotor.set(speed);
  }
  public void stop() {
    setSpeed(0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
