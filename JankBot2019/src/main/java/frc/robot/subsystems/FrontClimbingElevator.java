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
  // private CANPIDController climbingElevatorMotorPidController;
  // private CANEncoder climbingElevatorEncoder;
  // public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  // public double currentPosition = 0;

  //Declaration of the wheel at the bottom
  private Spark bottomWheelMotor;

  public FrontClimbingElevator() {
    frontClimbingElevatorMotor = new CANSparkMax(RobotMap.CLIMBING_ELEVATOR_CAN, MotorType.kBrushless);
    // climbingElevatorMotorPidController = climbingElevatorMotor.getPIDController();
    // climbingElevatorEncoder = climbingElevatorMotor.getEncoder();
    // currentPosition = climbingElevatorEncoder.getPosition();

    //Initialization of the bottom wheel motor
    bottomWheelMotor = new Spark(RobotMap.CLIMBING_WHEEL);

    // kP = 0.5;
    // kI = 1e-5;
    // kD = 0.5;
    // kIz = 0;
    // kFF = 0;
    // kMaxOutput = 1;
    // kMinOutput = -1;

    // set PID coefficients
    // climbingElevatorMotorPidController.setP(kP);
    // climbingElevatorMotorPidController.setI(kI);
    // climbingElevatorMotorPidController.setD(kD);
    // climbingElevatorMotorPidController.setIZone(kIz);
    // climbingElevatorMotorPidController.setFF(kFF);
    // climbingElevatorMotorPidController.setOutputRange(kMinOutput, kMaxOutput);

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
    setSpeed(RobotMap.FRONT_CLIMBING_ELEVATOR_SPEED);
    //double goalPosition = currentPosition + RobotMap.CLIMBING_ELEVATOR_CHANGE;
    //setPosition(goalPosition);
  }
  public void down() {
    setSpeed(-RobotMap.FRONT_CLIMBING_ELEVATOR_SPEED);
    //double goalPosition = currentPosition - RobotMap.CLIMBING_ELEVATOR_CHANGE;
    //setPosition(goalPosition);
  }
  public void setSpeed(double speed) {
    if(speed > 0.8) {
      speed = 0.8;
    }
    frontClimbingElevatorMotor.set(speed);
  }
  public void stop() {
    setSpeed(0.0);
  }

  public void goToLevel2() {
    //setPosition(RobotMap.CLIMBING_ELEVATOR_SECOND_LEVEL);
  }
  public void goToLevel3() {
    //setPosition(RobotMap.CLIMBING_ELEVATOR_THIRD_LEVEL);
  }
  public void goToRestPosition() {
    //setPosition(0.0);
  }
  public void setPosition(double goalPosition) {
    //climbingElevatorMotorPidController.setReference(goalPosition, ControlType.kPosition);
    //currentPosition = goalPosition;
    //System.out.println("Set Climbing Elevator Position to " + goalPosition);
  }
  public double getCurrentPosition() {
    //return climbingElevatorEncoder.getPosition();
    return 0.0;
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
