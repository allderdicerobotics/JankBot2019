/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private CANSparkMax elevator1Motor;
  private CANSparkMax elevator2Motor;
  private CANPIDController elevatorMotorPidController;
  private CANEncoder elevatorEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  public double currentOffset = 0;
  public double currentPosition;
  public String currentState;

  public Elevator() {
    elevator1Motor = new CANSparkMax(RobotMap.ELEVATOR_1_CAN, MotorType.kBrushless);
    elevator2Motor = new CANSparkMax(RobotMap.ELEVATOR_2_CAN, MotorType.kBrushless);
    elevatorMotorPidController = elevator1Motor.getPIDController();
    elevatorEncoder = elevator1Motor.getEncoder();
    currentPosition = elevatorEncoder.getPosition();

    kP = 0.5;
    kI = 1e-5;
    kD = 0.5;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput = -1;

    // set PID coefficients
    elevatorMotorPidController.setP(kP);
    elevatorMotorPidController.setI(kI);
    elevatorMotorPidController.setD(kD);
    elevatorMotorPidController.setIZone(kIz);
    elevatorMotorPidController.setFF(kFF);
    elevatorMotorPidController.setOutputRange(kMinOutput, kMaxOutput);

    // // display PID coefficients on SmartDashboard
    // SmartDashboard.putNumber("P Gain", kP);
    // SmartDashboard.putNumber("I Gain", kI);
    // SmartDashboard.putNumber("D Gain", kD);
    // SmartDashboard.putNumber("I Zone", kIz);
    // SmartDashboard.putNumber("Feed Forward", kFF);
    // SmartDashboard.putNumber("Max Output", kMaxOutput);
    // SmartDashboard.putNumber("Min Output", kMinOutput);
    // SmartDashboard.putNumber("Set Rotations", 0);
  }
  public void Init() {
    stop();
    elevator2Motor.follow(elevator1Motor);
  }
  public void up() {
    double goalPosition = currentPosition - RobotMap.ELEVATOR_ENCODER_CHANGE;
    setPosition(goalPosition);
    currentState = "moving up manually";
  }
  public void down() {
    double goalPosition = currentPosition + RobotMap.ELEVATOR_ENCODER_CHANGE;
    setPosition(goalPosition);
    currentState = "moving down manually";
  }
  public void getHatchNudge() {
    double goalPosition = currentPosition + RobotMap.ELEVATOR_NUDGE_CHANGE_UP;
    setPosition(goalPosition);
    currentState = "autonomously retreiving the hatch";
  }
  public void releaseHatchNudge() {
    double goalPosition = currentPosition - RobotMap.ELEVATOR_NUDGE_CHANGE_DOWN;
    setPosition(goalPosition);
    currentState = "autonomously releasing the hatch";
  }
  public void smallUpNudge() {
    double goalPosition = currentPosition + RobotMap.ELEVATOR_SMALL_NUDGE_CHANGE;
    setPosition(goalPosition);
  }
  public void smallDownNudge() {
    double goalPosition = currentPosition - RobotMap.ELEVATOR_SMALL_NUDGE_CHANGE;
    setPosition(goalPosition);
  }
  public void goToLvl1Ball() {
    setPosition(RobotMap.BALL_1);
    currentState = "going to level 1 to drop a ball";
  }
  public void goToLvl2Ball() {
    setPosition(RobotMap.BALL_2);
    currentState = "going to level 2 to drop a ball";
  }
  public void goToLvl3Ball() {
    setPosition(RobotMap.BALL_3);
    currentState = "going to level 3 to drop a ball";
  }
  public void goToLvl1Hatch() {
    setPosition(RobotMap.HATCH_1);
    currentState = "going to level 1 to place a hatch panel";
  }
  public void goToLvl2Hatch() {
    setPosition(RobotMap.HATCH_2);
    currentState = "going to level 2 to place a hatch panel";
  }
  public void goToLvl3Hatch() {
    setPosition(RobotMap.HATCH_3);
    currentState = "going to level 3 to place a hatch panel";
  }
  public void goToLvlGetHatch() {
    setPosition(RobotMap.GET_HATCH_1);
    currentState = "going to the retreival zone height to get a hatch panel";
  }
  public void goToBottom() {
    setPosition(0.0);
    currentState = "Going to the bottom";
  }
  public void setPosition(double goalPosition) {
    elevatorMotorPidController.setReference(goalPosition + currentOffset, ControlType.kPosition);
    currentPosition = goalPosition;
    System.out.println("setPosition to " + currentPosition);
  }
  public void setSpeed(double speed) {
  }
  public void stop() {
  }
  public void setOffset() {
    currentOffset = elevatorEncoder.getPosition();
  }
  public double getCurrentPosition(){
    return elevatorEncoder.getPosition();
  }

  public void elevatorPID() {
    // // read PID coefficients from SmartDashboard
    // double p = SmartDashboard.getNumber("P Gain", 0);
    // double i = SmartDashboard.getNumber("I Gain", 0);
    // double d = SmartDashboard.getNumber("D Gain", 0);
    // double iz = SmartDashboard.getNumber("I Zone", 0);
    // double ff = SmartDashboard.getNumber("Feed Forward", 0);
    // double max = SmartDashboard.getNumber("Max Output", 0);
    // double min = SmartDashboard.getNumber("Min Output", 0);
    // double rotations = SmartDashboard.getNumber("Set Rotations", 0);

    // // if PID coefficients on SmartDashboard have changed, write new values to controller
    // if((p != kP)) { elevatorMotorPidController.setP(p); kP = p; }
    // if((i != kI)) { elevatorMotorPidController.setI(i); kI = i; }
    // if((d != kD)) { elevatorMotorPidController.setD(d); kD = d; }
    // if((iz != kIz)) { elevatorMotorPidController.setIZone(iz); kIz = iz; }
    // if((ff != kFF)) { elevatorMotorPidController.setFF(ff); kFF = ff; }
    // if((max != kMaxOutput) || (min != kMinOutput)) { 
    //   elevatorMotorPidController.setOutputRange(min, max); 
    //   kMinOutput = min; kMaxOutput = max; 
    // }

    //elevatorMotorPidController.setReference(rotations, ControlType.kPosition);
    
    // SmartDashboard.putNumber("SetPoint", rotations);
    SmartDashboard.putNumber("Current Position is ", elevatorEncoder.getPosition());
    SmartDashboard.putNumber("Current offset is ", currentOffset);
    SmartDashboard.putString("Elevator is ", currentState);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
