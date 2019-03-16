/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
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
public class BackClimbingElevator extends Subsystem {
  private CANSparkMax backClimbingElevatorMotor;
  private CANPIDController backClimbingElevatorMotorPidController;
  private CANEncoder backClimbingElevatorEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  public double currentPosition = 0;

  public BackClimbingElevator() {
    backClimbingElevatorMotor = new CANSparkMax(RobotMap.BACK_CLIMBING_ELEVATOR_CAN, MotorType.kBrushless);
    backClimbingElevatorMotorPidController = backClimbingElevatorMotor.getPIDController();
    backClimbingElevatorEncoder = backClimbingElevatorMotor.getEncoder();
    currentPosition = backClimbingElevatorEncoder.getPosition();

    kP = 0.5;
    kI = 1e-5;
    kD = 0.5;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput = -1;

    // set PID coefficients
    backClimbingElevatorMotorPidController.setP(kP);
    backClimbingElevatorMotorPidController.setI(kI);
    backClimbingElevatorMotorPidController.setD(kD);
    backClimbingElevatorMotorPidController.setIZone(kIz);
    backClimbingElevatorMotorPidController.setFF(kFF);
    backClimbingElevatorMotorPidController.setOutputRange(kMinOutput, kMaxOutput);

    // // display PID coefficients on SmartDashboard
    // SmartDashboard.putNumber("BackClimbingElevator P Gain", kP);
    // SmartDashboard.putNumber("BackClimbingElevator I Gain", kI);
    // SmartDashboard.putNumber("BackClimbingElevator D Gain", kD);
    // SmartDashboard.putNumber("BackClimbingElevator I Zone", kIz);
    // SmartDashboard.putNumber("BackClimbingElevator Feed Forward", kFF);
    // SmartDashboard.putNumber("BackClimbingElevator Max Output", kMaxOutput);
    // SmartDashboard.putNumber("BackClimbingElevator Min Output", kMinOutput);
    // SmartDashboard.putNumber("BackClimbingElevator Set Rotations", 0);
  }
  public void Init() {
    goToRestPosition();
  }
  public void up() {
    double goalPosition = currentPosition + RobotMap.BACK_CLIMBING_ELEVATOR_CHANGE_VALUE;
    setPosition(goalPosition);
  }
  public void down() {
    double goalPosition = currentPosition - RobotMap.BACK_CLIMBING_ELEVATOR_CHANGE_VALUE;
    setPosition(goalPosition);
  }

  public void goToLevel2() {
    setPosition(RobotMap.BACK_LEVEL_2_CLIMB);
  }
  public void goToLevel3() {
    setPosition(RobotMap.BACK_LEVEL_3_CLIMB);
  }
  public void goToRestPosition() {
    setPosition(0.0);
  }
  public void setPosition(double goalPosition) {
    backClimbingElevatorMotorPidController.setReference(goalPosition, ControlType.kPosition);
    currentPosition = goalPosition;
    System.out.println("Set Back Climbing Elevator Position to " + goalPosition);
  }
  public double getCurrentPosition() {
    return backClimbingElevatorEncoder.getPosition();
  }

  public void BackClimbingElevatorPID() {
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
    
    //SmartDashboard.putNumber("ClimbingElevator SetPoint", rotations);
    //SmartDashboard.putNumber("Current ClimbingElevator Position is ", climbingElevatorEncoder.getPosition());
  }
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
