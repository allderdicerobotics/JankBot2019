/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.ElevatorStop;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

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
    Trigger leftAxisUp = new AxisButton(Robot.operator, RobotMap.kLeftStickY, true);
    Trigger leftAxisDown = new AxisButton(Robot.operator, RobotMap.kLeftStickY, false);

    leftAxisUp.whileActive(new ElevatorUp());
    leftAxisDown.whileActive(new ElevatorDown());
    
    Button buttonY = new JoystickButton(Robot.operator, RobotMap.kButtonY);
    Button buttonA = new JoystickButton(Robot.operator, RobotMap.kButtonA);

    buttonY.whileHeld(new ElevatorUp());
    //buttonY.whenReleased(new ElevatorStop());

    buttonA.whileHeld(new ElevatorDown());
    //buttonA.whenReleased(new ElevatorStop());

    //new JoystickButton(Robot.operator, RobotMap.kButtonY).whileHeld(new ElevatorUp());
    //new JoystickButton(Robot.operator, RobotMap.kButtonA).whileHeld(new ElevatorDown());
    Button buttonB = new JoystickButton(Robot.operator, RobotMap.kButtonB);
    Button buttonX = new JoystickButton(Robot.operator, RobotMap.kButtonX);

    buttonB.whileHeld(new IntakeOut());
    buttonX.whileHeld(new IntakeIn());

  }
}
