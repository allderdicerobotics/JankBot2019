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
  
  private CANSparkMax elevatorMotor;
  private CANPIDController elevatorMotorPidController;
  private CANEncoder elevatorEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  public Elevator() {
    elevatorMotor = new CANSparkMax(RobotMap.ELEVATOR_CAN, MotorType.kBrushless);
    elevatorMotorPidController = elevatorMotor.getPIDController();
    elevatorEncoder = elevatorMotor.getEncoder();

    // PID coefficients
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

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);
  }
  public void Init() {
    stop();
  }
  public void up() {
    elevatorMotor.set(RobotMap.ELEVATOR_SPEED);
  }
  public void down() {
    elevatorMotor.set(-RobotMap.ELEVATOR_SPEED);
  }
  public void upNudge() {
    elevatorMotor.set(-RobotMap.NUDGE_SPEED);
  }
  public void downNudge() {
    elevatorMotor.set(RobotMap.NUDGE_SPEED);
  }
  public void setSpeed(double speed) {
    //Put limit switch stuff here (if statements)
    elevatorMotor.set(speed);
  }
  public void stop() {
    elevatorMotor.set(0.0);
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
    double rotations = SmartDashboard.getNumber("Set Rotations", 0);

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

    elevatorMotorPidController.setReference(rotations, ControlType.kPosition);
    
    SmartDashboard.putNumber("SetPoint", rotations);
    SmartDashboard.putNumber("ProcessVariable", elevatorEncoder.getPosition());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
