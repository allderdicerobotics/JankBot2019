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
  private CANSparkMax elevator1Motor;
  private CANSparkMax elevator2Motor;
  private CANPIDController elevatorMotorPidController;
  private CANEncoder elevatorEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;
  public double currentOffset;
  public double currentPosition;
  public String currentState = "";

  public Elevator() {
    elevator1Motor = new CANSparkMax(RobotMap.ELEVATOR_1_CAN, MotorType.kBrushless);
    elevator2Motor = new CANSparkMax(RobotMap.ELEVATOR_2_CAN, MotorType.kBrushless);
    elevator2Motor.follow(elevator1Motor, true);
    elevatorMotorPidController = elevator1Motor.getPIDController();
    elevatorEncoder = elevator1Motor.getEncoder();
    currentPosition = elevatorEncoder.getPosition();

    currentOffset = 0.0;
    currentPosition = 0.0;

    kP = 1e-4;
    kI = 1e-6;
    kD = 0;
    kIz = 0;
    kFF = 1e-4;
    kMaxOutput = 1;
    kMinOutput = -1;
    maxRPM = 5700;

    //Smart Motion Coefficients
    maxVel = 20000; //rpm
    maxAcc = 6500;

    //set PID coefficients
    elevatorMotorPidController.setP(kP);
    elevatorMotorPidController.setI(kI);
    elevatorMotorPidController.setD(kD);
    elevatorMotorPidController.setIZone(kIz);
    elevatorMotorPidController.setFF(kFF);
    elevatorMotorPidController.setOutputRange(kMinOutput, kMaxOutput);

    int smartMotionSlot = 0;
    elevatorMotorPidController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
    elevatorMotorPidController.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
    elevatorMotorPidController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
    elevatorMotorPidController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);

    SmartDashboard.putNumber("Max Velocity", maxVel);
    SmartDashboard.putNumber("Min Velocity", minVel);
    SmartDashboard.putNumber("Max Acceleration", maxAcc);
    SmartDashboard.putNumber("Allowed Closed Loop Error", allowedErr);
    SmartDashboard.putNumber("Set Position", 0);
    SmartDashboard.putNumber("Set Velocity", 0);

    // button to toggle between velocity and smart motion modes
    SmartDashboard.putBoolean("Mode", true);
  }
  public void Init() {
  }
  public void up() {
    double goalPosition = currentPosition - RobotMap.ELEVATOR_MANUAL_CHANGE;
    setPosition(goalPosition);
    currentState = "moving up manually";
  }
  public void down() {
    double goalPosition = currentPosition + RobotMap.ELEVATOR_MANUAL_CHANGE;
    setPosition(goalPosition);
    currentState = "moving down manually";
  }
  public void getHatchNudge() {
    double goalPosition = currentPosition + RobotMap.ELEVATOR_GET_HATCH_CHANGE;
    setPosition(goalPosition);
    currentState = "autonomously retreiving the hatch";
  }
  public void releaseHatchNudge() {
    double goalPosition = currentPosition - RobotMap.ELEVATOR_RELEASE_HATCH_CHANGE;
    setPosition(goalPosition);
    currentState = "autonomously releasing the hatch";
  }
  public void smallUpNudge() {
    double goalPosition = currentPosition + RobotMap.ELEVATOR_NUDGE_CHANGE;
    setPosition(goalPosition);
  }
  public void smallDownNudge() {
    double goalPosition = currentPosition - RobotMap.ELEVATOR_NUDGE_CHANGE;
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
    setPosition(RobotMap.GET_BALL);
    currentState = "going to the retreival zone height to get a hatch panel";
  }
  public void goToBottom() {
    setPosition(0.0);
    currentState = "Going to the bottom";
  }
  public void setPosition(double goalPosition) {
    elevatorMotorPidController.setReference(goalPosition - currentOffset, ControlType.kSmartMotion);
    currentPosition = goalPosition;
  }
  public void setOffset() {
    currentOffset = elevatorEncoder.getPosition();
    setPosition(0.0);
  }
  public double getCurrentPosition(){
    return elevatorEncoder.getPosition();
  }
  public void elevatorPID() {
    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double maxV = SmartDashboard.getNumber("Max Velocity", 0);
    double minV = SmartDashboard.getNumber("Min Velocity", 0);
    double maxA = SmartDashboard.getNumber("Max Acceleration", 0);
    double allE = SmartDashboard.getNumber("Allowed Closed Loop Error", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { elevatorMotorPidController.setP(p); kP = p; }
    if((i != kI)) { elevatorMotorPidController.setI(i); kI = i; }
    if((d != kD)) { elevatorMotorPidController.setD(d); kD = d; }
    if((iz != kIz)) { elevatorMotorPidController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { elevatorMotorPidController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      elevatorMotorPidController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }
    if((maxV != maxVel)) { elevatorMotorPidController.setSmartMotionMaxVelocity(maxV,0); maxVel = maxV; }
    if((minV != minVel)) { elevatorMotorPidController.setSmartMotionMinOutputVelocity(minV,0); minVel = minV; }
    if((maxA != maxAcc)) { elevatorMotorPidController.setSmartMotionMaxAccel(maxA,0); maxAcc = maxA; }
    if((allE != allowedErr)) { elevatorMotorPidController.setSmartMotionAllowedClosedLoopError(allE,0); allE = allowedErr; }

    double setPoint, processVariable;
    boolean mode = SmartDashboard.getBoolean("Mode", false);
    System.out.println("test");
    if(mode) {
      System.out.println("VELOCITY MODE");
      setPoint = SmartDashboard.getNumber("Set Velocity", 0);
      elevatorMotorPidController.setReference(setPoint, ControlType.kVelocity);
      processVariable = elevatorEncoder.getVelocity();
    } else {
      System.out.println("SMART MOTION MODE");
      setPoint = SmartDashboard.getNumber("Set Position", 0);
      /**
       * As with other PID modes, Smart Motion is set by calling the
       * setReference method on an existing pid object and setting
       * the control type to kSmartMotion
       */
      elevatorMotorPidController.setReference(setPoint, ControlType.kSmartMotion);
      processVariable = elevatorEncoder.getPosition();
    }
    
    SmartDashboard.putNumber("SetPoint", setPoint);
    SmartDashboard.putNumber("Process Variable", processVariable);
    SmartDashboard.putNumber("Output", elevator1Motor.getAppliedOutput());

    SmartDashboard.putNumber("Current Elevator Position is ", elevatorEncoder.getPosition());
    SmartDashboard.putNumber("Current Elevator offset is ", currentOffset);
    SmartDashboard.putString("Elevator is ", currentState);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
