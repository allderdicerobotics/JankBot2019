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
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * Add your docs here.
 */
public class FrontClimbingElevator extends Subsystem {
  private CANSparkMax frontClimbingElevatorMotor;
  private CANPIDController frontClimbingElevatorPID;
  private CANEncoder frontClimbingElevatorEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  public double frontGoalPosition;

  //Declaration of the wheel at the bottom
  private Spark bottomWheelMotor;

  public FrontClimbingElevator() {
    frontClimbingElevatorMotor = new CANSparkMax(RobotMap.FRONT_CLIMBING_ELEVATOR_CAN, MotorType.kBrushless);
    frontClimbingElevatorPID = frontClimbingElevatorMotor.getPIDController();
    frontClimbingElevatorEncoder = frontClimbingElevatorMotor.getEncoder();

    //Initialization of the bottom wheel motor
    bottomWheelMotor = new Spark(RobotMap.CLIMBING_WHEEL);

    // PID coefficients
    kP = 0.5; 
    kI = 0.0;
    kD = 1.0; 
    kIz = 0; 
    kFF = 0.0; 
    kMaxOutput = 0.59;
    kMinOutput = -0.59;

    // set PID coefficients
    frontClimbingElevatorPID.setP(kP);
    frontClimbingElevatorPID.setI(kI);
    frontClimbingElevatorPID.setD(kD);
    frontClimbingElevatorPID.setIZone(kIz);
    frontClimbingElevatorPID.setFF(kFF);
    frontClimbingElevatorPID.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("Front P Gain", kP);
    SmartDashboard.putNumber("Front I Gain", kI);
    SmartDashboard.putNumber("Front D Gain", kD);
    SmartDashboard.putNumber("Front I Zone", kIz);
    SmartDashboard.putNumber("Front Feed Forward", kFF);
    SmartDashboard.putNumber("Front Max Output", kMaxOutput);
    SmartDashboard.putNumber("Front Min Output", kMinOutput);
    SmartDashboard.putNumber("Front Set Rotations", 0);
  }
  public void Init() {
  }
  public void zero() {
    setPosition(0.0);
  }
  public void moveToGround() {
    setPosition(RobotMap.FRONT_OFFSET);
  }
  public void level2() {
    setPosition(RobotMap.CLIMB_LEVEL_2 + RobotMap.FRONT_OFFSET);
  }
  public void level3() {
    setPosition(RobotMap.CLIMB_LEVEL_3 + RobotMap.FRONT_OFFSET);
  }
  public boolean atPosition() {
    if(frontGoalPosition - RobotMap.CLIMB_AUTO_TOLERANCE < frontClimbingElevatorEncoder.getPosition()
    && frontGoalPosition + RobotMap.CLIMB_AUTO_TOLERANCE > frontClimbingElevatorEncoder.getPosition()) {
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
    // double p = SmartDashboard.getNumber("Front P Gain", 0);
    // double i = SmartDashboard.getNumber("Front I Gain", 0);
    // double d = SmartDashboard.getNumber("Front D Gain", 0);
    // double iz = SmartDashboard.getNumber("Front I Zone", 0);
    // double ff = SmartDashboard.getNumber("Front Feed Forward", 0);
    // double max = SmartDashboard.getNumber("Front Max Output", 0);
    // double min = SmartDashboard.getNumber("Front Min Output", 0);
    // double rotations = SmartDashboard.getNumber("Front Set Rotations", 0);

    // // if PID coefficients on SmartDashboard have changed, write new values to controller
    // if((p != kP)) { frontClimbingElevatorPID.setP(p); kP = p; }
    // if((i != kI)) { frontClimbingElevatorPID.setI(i); kI = i; }
    // if((d != kD)) { frontClimbingElevatorPID.setD(d); kD = d; }
    // if((iz != kIz)) { frontClimbingElevatorPID.setIZone(iz); kIz = iz; }
    // if((ff != kFF)) { frontClimbingElevatorPID.setFF(ff); kFF = ff; }
    // if((max != kMaxOutput) || (min != kMinOutput)) { 
    //   frontClimbingElevatorPID.setOutputRange(min, max);
    //   kMinOutput = min; kMaxOutput = max; 
    //}

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
    frontClimbingElevatorPID.setReference(position, ControlType.kPosition);
    frontGoalPosition = position;
    
    // SmartDashboard.putNumber("Front SetPoint", rotations);
    // SmartDashboard.putNumber("Front ProcessVariable", frontClimbingElevatorEncoder.getPosition());
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
