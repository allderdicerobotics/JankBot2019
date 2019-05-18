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
public class BackClimbingElevator extends Subsystem {
  private CANSparkMax backClimbingElevatorMotor;
  private CANPIDController backClimbingElevatorPID;
  private CANEncoder backClimbingElevatorEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  public double backGoalPosition;
  public BackClimbingElevator() {
    backClimbingElevatorMotor = new CANSparkMax(RobotMap.BACK_CLIMGING_ELEVATOR_CAN, MotorType.kBrushless);
    backClimbingElevatorMotor.setInverted(true);
    backClimbingElevatorPID = backClimbingElevatorMotor.getPIDController();
    backClimbingElevatorEncoder = backClimbingElevatorMotor.getEncoder();

    // PID coefficients
    kP = 0.5; 
    kI = 0.0;
    kD = 1.0; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 0.5; 
    kMinOutput = -0.5;

    // set PID coefficients
    backClimbingElevatorPID.setP(kP);
    backClimbingElevatorPID.setI(kI);
    backClimbingElevatorPID.setD(kD);
    backClimbingElevatorPID.setIZone(kIz);
    backClimbingElevatorPID.setFF(kFF);
    backClimbingElevatorPID.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("Back P Gain", kP);
    SmartDashboard.putNumber("Back I Gain", kI);
    SmartDashboard.putNumber("Back D Gain", kD);
    SmartDashboard.putNumber("Back I Zone", kIz);
    SmartDashboard.putNumber("Back Feed Forward", kFF);
    SmartDashboard.putNumber("Back Max Output", kMaxOutput);
    SmartDashboard.putNumber("Back Min Output", kMinOutput);
    SmartDashboard.putNumber("Back Set Rotations", 0);
  }
  public void Init() {
  }
  public void zero() {
    setPosition(0.0);
  }
  public void moveToGround(){
    setPosition(RobotMap.BACK_OFFSET);
  }
  public void level2() {
    setPosition(RobotMap.CLIMB_LEVEL_2 + RobotMap.BACK_OFFSET);
  } 
  public void level3() {
    setPosition(RobotMap.CLIMB_LEVEL_3 + RobotMap.BACK_OFFSET);
  }
  public boolean atPosition() {
    if(backGoalPosition - RobotMap.CLIMB_AUTO_TOLERANCE < backClimbingElevatorEncoder.getPosition()
    && backGoalPosition + RobotMap.CLIMB_AUTO_TOLERANCE > backClimbingElevatorEncoder.getPosition()) {
      return true;
    } else {
      return false;
    }
  }
  public void up() {
  }
  public void down() {
  }
  public void setPosition(double position) {
    // read PID coefficients from SmartDashboard
      // double p = SmartDashboard.getNumber("Back P Gain", 0);
      // double i = SmartDashboard.getNumber("Back I Gain", 0);
      // double d = SmartDashboard.getNumber("Back D Gain", 0);
      // double iz = SmartDashboard.getNumber("Back I Zone", 0);
      // double ff = SmartDashboard.getNumber("Back Feed Forward", 0);
      // double max = SmartDashboard.getNumber("Back Max Output", 0);
      // double min = SmartDashboard.getNumber("Back Min Output", 0);
      // double rotations = SmartDashboard.getNumber("Back Set Rotations", 0);

      // // if PID coefficients on SmartDashboard have changed, write new values to controller
      // if((p != kP)) { backClimbingElevatorPID.setP(p); kP = p; }
      // if((i != kI)) { backClimbingElevatorPID.setI(i); kI = i; }
      // if((d != kD)) { backClimbingElevatorPID.setD(d); kD = d; }
      // if((iz != kIz)) { backClimbingElevatorPID.setIZone(iz); kIz = iz; }
      // if((ff != kFF)) { backClimbingElevatorPID.setFF(ff); kFF = ff; }
      // if((max != kMaxOutput) || (min != kMinOutput)) { 
      //   backClimbingElevatorPID.setOutputRange(min, max); 
      //   kMinOutput = min; kMaxOutput = max; 
    

    /**
     * PIDController objects are commanded to a set point using the 
     * SetReference() method.
     * 
     * The first parameter is the value of the set point, whose units vary
     * depending on the control type set in the second parameter.
     * 
     * The second parameter is the control type can be set to one of four 
     * parameters:
     *  com.revrobotics.ControlType.kDutyCycle
     *  com.revrobotics.ControlType.kPosition
     *  com.revrobotics.ControlType.kVelocity
     *  com.revrobotics.ControlType.kVoltage
     */
    backClimbingElevatorPID.setReference(position, ControlType.kPosition);
    backGoalPosition = position;
    //SmartDashboard.putNumber("Back SetPoint", rotations);
    //SmartDashboard.putNumber("Back ProcessVariable", backClimbingElevatorEncoder.getPosition());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
