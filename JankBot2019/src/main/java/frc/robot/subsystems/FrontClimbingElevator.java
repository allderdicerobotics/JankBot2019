/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class FrontClimbingElevator extends Subsystem {
   private CANSparkMax frontClimbingElevatorMotor;

  //Declaration of the wheel at the bottom
  private Spark bottomWheelMotor;

  public FrontClimbingElevator() {
    frontClimbingElevatorMotor = new CANSparkMax(RobotMap.FRONT_CLIMBING_ELEVATOR_CAN, MotorType.kBrushless);

    //Initialization of the bottom wheel motor
    bottomWheelMotor = new Spark(RobotMap.CLIMBING_WHEEL);
  }
  public void Init() {
  }
  public void up() {
    setSpeed(-RobotMap.FRONT_CLIMBING_ELEVATOR_SPEED);
  }
  public void down() {
    setSpeed(RobotMap.FRONT_CLIMBING_ELEVATOR_SPEED);
  }
  public void setSpeed(double speed) {
    if(speed > RobotMap.FRONT_CLIMBING_ELEVATOR_MAX_SPEED) {
      speed = RobotMap.FRONT_CLIMBING_ELEVATOR_MAX_SPEED;
    }
    frontClimbingElevatorMotor.set(speed);
  }
  public void stop() {
    setSpeed(0.0);
  }

//CONTROLLING THE WHEEL AT THE BOTTOM OF THE ELEVATOR
  public void wheelForward() {
    wheelSetSpeed(RobotMap.CLIMBING_WHEEL_NORMAL_SPEED);
  }
  public void wheelBackward() {
    wheelSetSpeed(-RobotMap.CLIMBING_WHEEL_NORMAL_SPEED);
  }
  public void wheelSetSpeed(double speed) {
    double speedGoal = speed;
    if(speedGoal > RobotMap.CLIMBING_WHEEL_MAX_SPEED) {
      speedGoal = RobotMap.CLIMBING_WHEEL_MAX_SPEED;
    }
    bottomWheelMotor.set(speedGoal);
  }
  public void wheelStop() {
    wheelSetSpeed(0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
