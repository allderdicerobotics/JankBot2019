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
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax armMotor;
  private CANPIDController armMotorPidController;
  private CANEncoder armEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  public double currentPosition = 0;
  
  public Arm() {
    armMotor = new CANSparkMax(RobotMap.ARM_CAN, MotorType.kBrushless);
    armMotorPidController = armMotor.getPIDController();
    armEncoder = armMotor.getEncoder();
    currentPosition = armEncoder.getPosition();

    kP = 0.5;
    kI = 1e-5;
    kD = 0.5;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput = -1;

    // set PID coefficients
    armMotorPidController.setP(kP);
    armMotorPidController.setI(kI);
    armMotorPidController.setD(kD);
    armMotorPidController.setIZone(kIz);
    armMotorPidController.setFF(kFF);
    armMotorPidController.setOutputRange(kMinOutput, kMaxOutput);

    SmartDashboard.putNumber("Arm P Gain", kP);
    SmartDashboard.putNumber("Arm I Gain", kI);
    SmartDashboard.putNumber("Arm D Gain", kD);
    SmartDashboard.putNumber("Arm I Zone", kIz);
    SmartDashboard.putNumber("Arm Feed Forward", kFF);
    SmartDashboard.putNumber("Arm Max Output", kMaxOutput);
    SmartDashboard.putNumber("Arm Min Output", kMinOutput);
    SmartDashboard.putNumber("Arm Set Rotations", 0);
  }
  public void Init(){
    setPosition(0.0);
  }
  public void down() {
    double goalPosition = currentPosition - RobotMap.ARM_ENCODER_CHANGE;
    setPosition(goalPosition);
  }
  public void up() {
    double goalPosition = currentPosition + RobotMap.ARM_ENCODER_CHANGE;
    setPosition(goalPosition);
  }
  public void goToDownPosition() {
    setPosition(RobotMap.ARM_DOWN);
  }
  public void goToUpPosition() {
    setPosition(0.0);
  }
  public void setPosition(double goalPosition) {
    armMotorPidController.setReference(goalPosition, ControlType.kPosition);
    currentPosition = goalPosition;
    System.out.println("Set Arm Position to " + goalPosition);
  }
  public double getCurrentPosition() {
    return armEncoder.getPosition();
  }
  public void stop() {
  }
  public void armPID() {
  // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("Arm P Gain", 0);
    double i = SmartDashboard.getNumber("Arm I Gain", 0);
    double d = SmartDashboard.getNumber("Arm D Gain", 0);
    double iz = SmartDashboard.getNumber("Arm I Zone", 0);
    double ff = SmartDashboard.getNumber("Arm Feed Forward", 0);
    double max = SmartDashboard.getNumber("Arm Max Output", 0);
    double min = SmartDashboard.getNumber("Arm Min Output", 0);
    double rotations = SmartDashboard.getNumber("Arm Set Rotations", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { armMotorPidController.setP(p); kP = p; }
    if((i != kI)) { armMotorPidController.setI(i); kI = i; }
    if((d != kD)) { armMotorPidController.setD(d); kD = d; }
    if((iz != kIz)) { armMotorPidController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { armMotorPidController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      armMotorPidController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }

    armMotorPidController.setReference(rotations, ControlType.kPosition);
    SmartDashboard.putNumber("Arm setPoint:", rotations);
    SmartDashboard.putNumber("Current arm position is ", armEncoder.getPosition());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
