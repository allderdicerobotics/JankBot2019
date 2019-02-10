/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

	//CAN Identifiers
	public static final int ELEVATOR_CAN = 1;

	//PWM Ports
	public static final int INTAKE_PWM_SINGLE = 4;
  	public static final int DRIVE_TRAIN_LEFT_1 = 0;
  	public static final int DRIVE_TRAIN_LEFT_2 = 1;
	public static final int DRIVE_TRAIN_RIGHT_1 = 2;
	public static final int DRIVE_TRAIN_RIGHT_2 = 3;

	//USB Ports
	public static final int DRIVE_PORT = 0;
	public static final int OPERATOR_PORT = 1;

	//Speed Constants
	public static final double ELEVATOR_SPEED = 0.7;
	public static final double INTAKE_SPEED = 0.75;
	public static final double THROTTLE_SCALE = 0.6;
	public static final double STEERING_SCALE = 0.6;
	
	public static final double BOOST_THROTTLE_SCALE = 1.5;
	public static final double BOOST_STEERING_SCALE = 1.2;
	public static final double SLOW_THROTTLE_SCALE = 1.5;
	public static final double SLOW_STEERING_SCALE = 1.2;
	public static final double MAX_SPEED = 0.95;
	
	public static final double DEADZONE_THRESHOLD = 0.07;

	public static final double NUDGE_SPEED = 0.3;
	public static final double NUDGE_TIME = 0.4;

	public static final double AUTO_BACK_SPEED = -0.4;
	public static final double AUTO_BACK_TIME = 0.6;
	public static final double ELEVATOR_DOWN_TIME = 0.15;
	public static final double ELEVATOR_UP_TIME = 0.2;

	public static final double ELEVATOR_ENCODER_CHANGE_VALUE = 2.5;
	public static final double ELEVATOR_NUDGE_CHANGE_VALUE = 1.0;

	//goalPosition Encoder values
	public static final double BALL_1 = 135.0;
	public static final double BALL_2 = 295.0;
	public static final double BALL_3 = 430.0;
	public static final double GET_HATCH_1 = 18.0;
	public static final double HATCH_1 = 25.0;
	public static final double HATCH_2 = 185.0;
	public static final double HATCH_3 = 330.0;

  	//GamePad
	/* axis mappings */
	public static final int kLeftStickX = 0;
	public static final int kLeftStickY = 1;
	public static final int kLeftTrigger = 2;
	public static final int kRightTrigger = 3;
	public static final int kRightStickX = 4;
	public static final int kRightStickY = 5;
	
	/* button mappings */
	public static final int kButtonA = 1;
	public static final int kButtonB = 2;
	public static final int kButtonX = 3;
	public static final int kButtonY = 4;
	public static final int kButtonLeftBumper = 5;
	public static final int kButtonRightBumper = 6;
	public static final int kButtonBack = 7;
	public static final int kButtonStart = 8;
	public static final int kButtonLeftStick = 9;
	public static final int kButtonRightStick = 10;
  	public static final int kPOVDPad = 0;
}
