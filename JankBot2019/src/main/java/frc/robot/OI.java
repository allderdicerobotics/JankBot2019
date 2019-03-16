/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.FrontClimbingElevator.BottomWheelBackward;
import frc.robot.commands.FrontClimbingElevator.BottomWheelForward;
import frc.robot.commands.FrontClimbingElevator.FrontClimbingElevatorDown;
import frc.robot.commands.FrontClimbingElevator.FrontClimbingElevatorUp;
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
  public OI() {
    /*-------------------------------ELEVATOR----------------------------*/
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

    //Buttons for the manual nudging of the elevator 
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
    /*--------------------------END OF ELEVATOR----------------------------*/

    /*-------------------------------INTAKE--------------------------------*/
    //Trigger for the manual operation of the intake
    Trigger operatorRightAxisUp = new AxisButton(Robot.operator, RobotMap.kRightStickY, true);
    Trigger operatorRightAxisDown = new AxisButton(Robot.operator, RobotMap.kRightStickY, false);
    operatorRightAxisUp.whileActive(new IntakeOut());
    operatorRightAxisDown.whileActive(new IntakeIn());
    /*---------------------------END OF INTAKE-----------------------------*/

    /*----------------------FRONT CLIMBING ELEVATOR------------------------*/
    Trigger climberLeftAxisUp = new AxisButton(Robot.climberJoystick, RobotMap.kLeftStickY, true);
    Trigger climberLeftAxisDown = new AxisButton(Robot.climberJoystick, RobotMap.kLeftStickY, false);
    climberLeftAxisUp.whileActive(new FrontClimbingElevatorDown());
    climberLeftAxisDown.whileActive(new FrontClimbingElevatorUp());

    Button climberY = new JoystickButton(Robot.climberJoystick, RobotMap.kButtonY);
    Button climberA = new JoystickButton(Robot.climberJoystick, RobotMap.kButtonA);
    climberY.whileActive(new BottomWheelForward());
    climberA.whileActive(new BottomWheelBackward());
    /*------------------END OF FRONTCLIMBING ELEVATOR---------------------*/
  }
}
