/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ArmAngle0;
import frc.robot.commands.ArmAngle1;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;

import frc.robot.commands.NudgeDown;
import frc.robot.commands.NudgeUp;
import frc.robot.commands.GetHatch;
import frc.robot.commands.ReleaseHatch;
import frc.robot.commands.SetOffset;
import frc.robot.commands.ElevatorHeight0;
import frc.robot.commands.ElevatorHeight1;
import frc.robot.commands.ElevatorHeight2;
import frc.robot.commands.ElevatorHeight3;
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
    //Buttons for the different levels the elevator will travel to. 
    Button operatorX = new JoystickButton(Robot.operator, RobotMap.kButtonX);
    Button operatorA = new JoystickButton(Robot.operator, RobotMap.kButtonA);
    Button operatorB = new JoystickButton(Robot.operator, RobotMap.kButtonB);
    Button operatorY = new JoystickButton(Robot.operator, RobotMap.kButtonY);

    POVButton operatorPOVUp = new POVButton(Robot.operator, 0);
    POVButton operatorPOVDown = new POVButton(Robot.operator, 180);

    operatorPOVUp.whenActive(new ArmAngle0());
    operatorPOVDown.whenActive(new ArmAngle1());

    operatorX.whenPressed(new ElevatorHeight0());
    operatorA.whenPressed(new ElevatorHeight1());
    operatorB.whenPressed(new ElevatorHeight2());
    operatorY.whenPressed(new ElevatorHeight3());

    Button driverLeftBumper = new JoystickButton(Robot.driver, RobotMap.kButtonLeftBumper);
    Button driverRightBumper = new JoystickButton(Robot.driver, RobotMap.kButtonRightBumper);

    driverLeftBumper.whileActive(new ArmDown());
    driverRightBumper.whileActive(new ArmUp());

    //Trigger for the manual operation of the elevator
    Trigger operatorLeftAxisUp = new AxisButton(Robot.operator, RobotMap.kLeftStickY, true);
    Trigger operatorLeftAxisDown = new AxisButton(Robot.operator, RobotMap.kLeftStickY, false);

    operatorLeftAxisUp.whileActive(new ElevatorUp());
    operatorLeftAxisDown.whileActive(new ElevatorDown());

    //Trigger for the manual operation of the intake
    Trigger operatorRightAxisUp = new AxisButton(Robot.operator, RobotMap.kRightStickY, true);
    Trigger operatorRightAxisDown = new AxisButton(Robot.operator, RobotMap.kRightStickY, false);

    operatorRightAxisUp.whileActive(new IntakeOut());
    operatorRightAxisDown.whileActive(new IntakeIn());

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
  }
}
