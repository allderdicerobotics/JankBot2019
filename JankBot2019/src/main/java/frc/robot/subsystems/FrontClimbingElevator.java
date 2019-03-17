/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class FrontClimbingElevator extends Subsystem {
  private CANSparkMax frontClimbingElevatorMotor;
  private CANPIDController frontClimbingElevatorMotorPidController;
  private CANEncoder frontClimbingElevatorEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  public double currentPosition = 0;

  //Declaration of the wheel at the bottom
  private Spark bottomWheelMotor;

  public FrontClimbingElevator() {
    frontClimbingElevatorMotor = new CANSparkMax(RobotMap.FRONT_CLIMBING_ELEVATOR_CAN, MotorType.kBrushless);
    frontClimbingElevatorMotorPidController = frontClimbingElevatorMotor.getPIDController();
    frontClimbingElevatorEncoder = frontClimbingElevatorMotor.getEncoder();
    currentPosition = frontClimbingElevatorEncoder.getPosition();

    //Initialization of the bottom wheel motor
    bottomWheelMotor = new Spark(RobotMap.CLIMBING_WHEEL);

    kP = 0.5;
    kI = 1e-5;
    kD = 0.5;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput = -1;

    // set PID coefficients
    frontClimbingElevatorMotorPidController.setP(kP);
    frontClimbingElevatorMotorPidController.setI(kI);
    frontClimbingElevatorMotorPidController.setD(kD);
    frontClimbingElevatorMotorPidController.setIZone(kIz);
    frontClimbingElevatorMotorPidController.setFF(kFF);
    frontClimbingElevatorMotorPidController.setOutputRange(kMinOutput, kMaxOutput);

    //  // display PID coefficients on SmartDashboard
    // SmartDashboard.putNumber("ClimbingElevator P Gain", kP);
    // SmartDashboard.putNumber("ClimbingElevator I Gain", kI);
    // SmartDashboard.putNumber("ClimbingElevator D Gain", kD);
    // SmartDashboard.putNumber("ClimbingElevator I Zone", kIz);
    // SmartDashboard.putNumber("ClimbingElevator Feed Forward", kFF);
    // SmartDashboard.putNumber("ClimbingElevator Max Output", kMaxOutput);
    // SmartDashboard.putNumber("ClimbingElevator Min Output", kMinOutput);
    // SmartDashboard.putNumber("ClimbingElevator Set Rotations", 0);
  }
  public void Init() {
    goToRestPosition();
  }
  public void up() {
    double goalPosition = currentPosition + RobotMap.FRONT_CLIMBING_ELEVATOR_CHANGE_VALUE;
    setPosition(goalPosition);
  }
  public void down() {
    double goalPosition = currentPosition - RobotMap.FRONT_CLIMBING_ELEVATOR_CHANGE_VALUE;
    setPosition(goalPosition);
  }
  public void goToLevel2() {
    setPosition(RobotMap.FRONT_LEVEL_2_CLIMB);
  }
  public void goToLevel3() {
    setPosition(RobotMap.FRONT_LEVEL_3_CLIMB);
  }
  public void goToRestPosition() {
    setPosition(0.0);
  }
  public void setPosition(double goalPosition) {
    frontClimbingElevatorMotorPidController.setReference(goalPosition, ControlType.kPosition);
    currentPosition = goalPosition;
    System.out.println("Set Front Climbing Elevator Position to " + goalPosition);
  }
  public double getCurrentPosition() {
    return frontClimbingElevatorEncoder.getPosition();
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

  public void FrontClimbingElevatorPID() {
    // // read PID coefficients from SmartDashboard
    // double p = SmartDashboard.getNumber("FrontClimbingElevator P Gain", 0);
    // double i = SmartDashboard.getNumber("FrontClimbingElevator I Gain", 0);
    // double d = SmartDashboard.getNumber("FrontClimbingElevator D Gain", 0);
    // double iz = SmartDashboard.getNumber("FrontClimbingElevator I Zone", 0);
    // double ff = SmartDashboard.getNumber("FrontClimbingElevator Feed Forward", 0);
    // double max = SmartDashboard.getNumber("FrontClimbingElevator Max Output", 0);
    // double min = SmartDashboard.getNumber("FrontClimbingElevator Min Output", 0);
    // double rotations = SmartDashboard.getNumber("FrontClimbingElevator Set Rotations", 0);

    // // if PID coefficients on SmartDashboard have changed, write new values to controller
    // if((p != kP)) { frontClimbingElevatorMotorPidController.setP(p); kP = p; }
    // if((i != kI)) { frontClimbingElevatorMotorPidController.setI(i); kI = i; }
    // if((d != kD)) { frontClimbingElevatorMotorPidController.setD(d); kD = d; }
    // if((iz != kIz)) { frontClimbingElevatorMotorPidController.setIZone(iz); kIz = iz; }
    // if((ff != kFF)) { frontClimbingElevatorMotorPidController.setFF(ff); kFF = ff; }
    // if((max != kMaxOutput) || (min != kMinOutput)) { 
    //   frontClimbingElevatorMotorPidController.setOutputRange(min, max); 
    //   kMinOutput = min; kMaxOutput = max; 
    // }

    // frontClimbingElevatorMotorPidController.setReference(rotations, ControlType.kPosition);
    
    // SmartDashboard.putNumber("ClimbingElevator SetPoint", rotations);
    // SmartDashboard.putNumber("Current ClimbingElevator Position is ", frontClimbingElevatorEncoder.getPosition());
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
