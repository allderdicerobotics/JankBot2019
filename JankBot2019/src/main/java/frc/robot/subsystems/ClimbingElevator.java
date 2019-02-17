/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class ClimbingElevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax climbingElevatorMotor;
  private CANPIDController climbingElevatorMotorPidController;
  private CANEncoder climbingElevatorEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  public double currentPosition = 0;

  //for the wheel at the bottom
  private Victor bottomWheelMotor;

  public ClimbingElevator() {
    climbingElevatorMotor = new CANSparkMax(RobotMap.CLIMBING_ELEVATOR_CAN, MotorType.kBrushless);
    climbingElevatorMotorPidController = climbingElevatorMotor.getPIDController();
    climbingElevatorEncoder = climbingElevatorMotor.getEncoder();
    currentPosition = climbingElevatorEncoder.getPosition();

    //for the wheel at the bottom
    bottomWheelMotor = new Victor(RobotMap.CLIMBING_WHEEL);

    kP = 0.5;
    kI = 1e-5;
    kD = 0.5;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput = -1;

    // set PID coefficients
    climbingElevatorMotorPidController.setP(kP);
    climbingElevatorMotorPidController.setI(kI);
    climbingElevatorMotorPidController.setD(kD);
    climbingElevatorMotorPidController.setIZone(kIz);
    climbingElevatorMotorPidController.setFF(kFF);
    climbingElevatorMotorPidController.setOutputRange(kMinOutput, kMaxOutput);

    // // display PID coefficients on SmartDashboard
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
    //goToRestPosition();
  }
  public void up() {
    double goalPosition = currentPosition + RobotMap.CLIMBING_ELEVATOR_CHANGE;
    setPosition(goalPosition);
  }
  public void down() {
    double goalPosition = currentPosition - RobotMap.CLIMBING_ELEVATOR_CHANGE;
    setPosition(goalPosition);
  }
  public void goToLevel2() {
    setPosition(RobotMap.CLIMBING_ELEVATOR_SECOND_LEVEL);
  }
  public void goToLevel3() {
    setPosition(RobotMap.CLIMBING_ELEVATOR_THIRD_LEVEL);
  }
  public void goToRestPosition() {
    setPosition(0.0);
  }
  public void setPosition(double goalPosition) {
    climbingElevatorMotorPidController.setReference(goalPosition, ControlType.kPosition);
    currentPosition = goalPosition;
    System.out.println("Set Climbing Elevator Position to " + goalPosition);
  }
  public double getCurrentPosition() {
    return climbingElevatorEncoder.getPosition();
  }
  public void stop() {
  }

  //methods for the wheel at the bottom
  public void wheelForward() {
    bottomWheelMotor.set(RobotMap.CLIMBING_WHEEL_NORMAL_SPEED);
  }
  public void wheelBackward() {
    bottomWheelMotor.set(-RobotMap.CLIMBING_WHEEL_NORMAL_SPEED);
  }
  public void wheelSetSpeed(double speed) {
    double speedGoal = speed;
    if(speedGoal > RobotMap.CLIMBING_WHEEL_MAX_SPEED) {
      speedGoal = RobotMap.CLIMBING_WHEEL_MAX_SPEED;
    }
    bottomWheelMotor.set(speedGoal);
  }
  public void wheelStop() {
    bottomWheelMotor.set(0.0);
  }


  public void climbingElevatorPID() {
    // // read PID coefficients from SmartDashboard
    // double p = SmartDashboard.getNumber("ClimbingElevator P Gain", 0);
    // double i = SmartDashboard.getNumber("ClimbingElevator I Gain", 0);
    // double d = SmartDashboard.getNumber("ClimbingElevator D Gain", 0);
    // double iz = SmartDashboard.getNumber("ClimbingElevator I Zone", 0);
    // double ff = SmartDashboard.getNumber("ClimbingElevator Feed Forward", 0);
    // double max = SmartDashboard.getNumber("ClimbingElevator Max Output", 0);
    // double min = SmartDashboard.getNumber("ClimbingElevator Min Output", 0);
    // double rotations = SmartDashboard.getNumber("ClimbingElevator Set Rotations", 0);

    // // if PID coefficients on SmartDashboard have changed, write new values to controller
    // if((p != kP)) { climbingElevatorMotorPidController.setP(p); kP = p; }
    // if((i != kI)) { climbingElevatorMotorPidController.setI(i); kI = i; }
    // if((d != kD)) { climbingElevatorMotorPidController.setD(d); kD = d; }
    // if((iz != kIz)) { climbingElevatorMotorPidController.setIZone(iz); kIz = iz; }
    // if((ff != kFF)) { climbingElevatorMotorPidController.setFF(ff); kFF = ff; }
    // if((max != kMaxOutput) || (min != kMinOutput)) { 
    //   climbingElevatorMotorPidController.setOutputRange(min, max); 
    //   kMinOutput = min; kMaxOutput = max; 
    // }

    //climbingElevatorMotorPidController.setReference(rotations, ControlType.kPosition);
    
    // SmartDashboard.putNumber("ClimbingElevator SetPoint", rotations);
    //SmartDashboard.putNumber("Current ClimbingElevator Position is ", climbingElevatorEncoder.getPosition());
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
