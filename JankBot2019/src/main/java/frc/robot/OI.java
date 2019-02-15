/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.Arm.ArmAngle0;
import frc.robot.commands.Arm.ArmAngle1;
import frc.robot.commands.Arm.ArmDown;
import frc.robot.commands.Arm.ArmUp;
import frc.robot.commands.ClimbingElevator.BottomWheelBackward;
import frc.robot.commands.ClimbingElevator.BottomWheelForward;
import frc.robot.commands.ClimbingElevator.ClimbingElevatorDown;
import frc.robot.commands.ClimbingElevator.ClimbingElevatorHeight0;
import frc.robot.commands.ClimbingElevator.ClimbingElevatorHeight1;
import frc.robot.commands.ClimbingElevator.ClimbingElevatorUp;
import frc.robot.commands.Elevator.ElevatorDown;
import frc.robot.commands.Elevator.ElevatorUp;
import frc.robot.commands.Intake.IntakeIn;
import frc.robot.commands.Intake.IntakeOut;

import frc.robot.commands.Elevator.NudgeDown;
import frc.robot.commands.Elevator.NudgeUp;
import frc.robot.commands.GetHatch;
import frc.robot.commands.ReleaseHatch;
import frc.robot.commands.Elevator.SetOffset;
import frc.robot.commands.Elevator.ElevatorHeight0;
import frc.robot.commands.Elevator.ElevatorHeight1;
import frc.robot.commands.Elevator.ElevatorHeight2;
import frc.robot.commands.Elevator.ElevatorHeight3;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.AxisButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public OI() {
    //ELEVATOR
    //Buttons for the different levels the elevator will travel to. 
    Button operatorX = new JoystickButton(Robot.operator, RobotMap.kButtonX);
    Button operatorA = new JoystickButton(Robot.operator, RobotMap.kButtonA);
    Button operatorB = new JoystickButton(Robot.operator, RobotMap.kButtonB);
    Button operatorY = new JoystickButton(Robot.operator, RobotMap.kButtonY);
    operatorX.whenPressed(new ElevatorHeight0());
    operatorA.whenPressed(new ElevatorHeight1());
    operatorB.whenPressed(new ElevatorHeight2());
    operatorY.whenPressed(new ElevatorHeight3());

    //Trigger for the manual operation of the elevator
    Trigger operatorLeftAxisUp = new AxisButton(Robot.operator, RobotMap.kLeftStickY, true);
    Trigger operatorLeftAxisDown = new AxisButton(Robot.operator, RobotMap.kLeftStickY, false);
    operatorLeftAxisUp.whileActive(new ElevatorUp());
    operatorLeftAxisDown.whileActive(new ElevatorDown());

    //Buttons for nudging the elevator manually
    Button operatorLeftBumper = new JoystickButton(Robot.operator, RobotMap.kButtonLeftBumper);
    Button operatorRightBumper = new JoystickButton(Robot.operator, RobotMap.kButtonRightBumper);
    operatorLeftBumper.whenActive(new NudgeDown(true));
    operatorRightBumper.whenActive(new NudgeUp(true));

    //Buttons for the autonomous modes that retreive and release the hatch panels
    Button driverX = new JoystickButton(Robot.driver, RobotMap.kButtonX);
    Button driverY = new JoystickButton(Robot.driver, RobotMap.kButtonY);
    driverX.whenPressed(new ReleaseHatch());
    driverY.whenPressed(new GetHatch());

    //Button for zero'ing the encoder on the elevator
    Button operatorStart = new JoystickButton(Robot.operator, RobotMap.kButtonStart);
    operatorStart.whenActive(new SetOffset());
    //END OF ELEVATOR

    //INTAKE
    //Trigger for the manual operation of the intake
    Trigger operatorRightAxisUp = new AxisButton(Robot.operator, RobotMap.kRightStickY, true);
    Trigger operatorRightAxisDown = new AxisButton(Robot.operator, RobotMap.kRightStickY, false);
    operatorRightAxisUp.whileActive(new IntakeOut());
    operatorRightAxisDown.whileActive(new IntakeIn());

    //ARM
    //Arm designated levels (angles)
    POVButton operatorPOVUp = new POVButton(Robot.operator, 0);
    POVButton operatorPOVDown = new POVButton(Robot.operator, 180);
    operatorPOVUp.whenActive(new ArmAngle0());
    operatorPOVDown.whenActive(new ArmAngle1());

    //Manual operation of the arm
    Button driverLeftBumper = new JoystickButton(Robot.driver, RobotMap.kButtonLeftBumper);
    Button driverRightBumper = new JoystickButton(Robot.driver, RobotMap.kButtonRightBumper);
    driverLeftBumper.whileActive(new ArmDown());
    driverRightBumper.whileActive(new ArmUp());
    //END OF ARM

    //CLIMBING ELEVATOR
    //Climbing elevator levels
    POVButton operatorPOVRight = new POVButton(Robot.operator, 90);
    POVButton operatorPOVLeft = new POVButton(Robot.operator, 270);
    operatorPOVRight.whenActive(new ClimbingElevatorHeight0());
    operatorPOVLeft.whenActive(new ClimbingElevatorHeight1());

    //Manual Climbing Elevator control
    POVButton driverPOVUp = new POVButton(Robot.driver, 0);
    POVButton driverPOVDown = new POVButton(Robot.driver, 180);
    driverPOVUp.whenActive(new ClimbingElevatorUp());
    driverPOVDown.whenActive(new ClimbingElevatorDown());

    //Manual bottom wheel for climbing elevator control
    POVButton driverPOVRight = new POVButton(Robot.driver, 90);
    POVButton driverPOVLeft = new POVButton(Robot.driver, 270);
    driverPOVRight.whenActive(new BottomWheelForward());
    driverPOVLeft.whenActive(new BottomWheelBackward());
    //END OF CLIMBING ELEVATOR




    

  }
}
