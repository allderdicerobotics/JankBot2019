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
	//CAN IDENTIFIERS
	public static final int ELEVATOR_1_CAN = 1;
	public static final int ELEVATOR_2_CAN = 5;
	public static final int FRONT_CLIMBING_ELEVATOR_CAN = 4;
	public static final int BACK_CLIMGING_ELEVATOR_CAN = 3;

	//PWM PORTS
	public static final int INTAKE_PWM_LEFT = 4;
	public static final int INTAKE_PWM_RIGHT = 5;
  	public static final int DRIVE_TRAIN_LEFT_1 = 0;
  	public static final int DRIVE_TRAIN_LEFT_2 = 1;
	public static final int DRIVE_TRAIN_RIGHT_1 = 2;
	public static final int DRIVE_TRAIN_RIGHT_2 = 3;
	public static final int CLIMBING_WHEEL = 6;

	//USB PORTS
	public static final int DRIVE_PORT = 0;
	public static final int OPERATOR_PORT = 1;
	public static final int CLIMBER_JOYSTICK_PORT = 2;

	//Drive train:
	public static final double THROTTLE_SCALE = 0.8;
	public static final double STEERING_SCALE = 0.65;
	
	public static final double BOOST_THROTTLE_SCALE = 1.2;
	public static final double BOOST_STEERING_SCALE = 1.2;
	public static final double SLOW_THROTTLE_SCALE = 1.6;
	public static final double SLOW_STEERING_SCALE = 1.34;
	public static final double MAX_SPEED = 0.9;

	//Intake:
	public static final double INTAKE_IN_SPEED = 0.55;
	public static final double INTAKE_OUT_SPEED = -0.88;

	//Elevator:
	public static final double ELEVATOR_MANUAL_CHANGE = 1.6871584;
	public static final double ELEVATOR_NUDGE_CHANGE = 4.0;
	//Position Encoder values
	public static final double BALL_1 = 55.0;
	public static final double BALL_2 = 117.0;
	public static final double BALL_3 = 170.0;
	public static final double GET_BALL = 75.6;
	public static final double HATCH_1 = 15.0;
	public static final double HATCH_2 = 79.0;
	public static final double HATCH_3 = 143.0;

	public static final double CLIMB_AUTO_TOLERANCE = 10.0;

	//Front Climbing Elevator:
	public static final double FRONT_CLIMBING_ELEVATOR_SPEED = 0.346;
	public static final double FRONT_CLIMBING_ELEVATOR_MAX_SPEED = 0.6;
	public static final double CLIMBING_WHEEL_NORMAL_SPEED = 0.35;
	public static final double CLIMBING_WHEEL_MAX_SPEED = 0.5;

	public static final double CLIMB_LEVEL_2 = 90;
	public static final double CLIMB_LEVEL_3 = 30;
	public static final double BACK_OFFSET = 47.5;
	public static final double FRONT_OFFSET = 55;

	//Back Climbing Elevator
	public static final double BACK_CLIMBING_ELEVATOR_SPEED = 0.3;
	public static final double BACK_CLIMBING_ELEVATOR_MAX_SPEED = 0.6;

	public static final double DEADZONE_THRESHOLD = 0.07;

	//Autonomous commands:
	public static final double HATCH_AUTO_DRIVE_TIME = 1.0;
	public static final double HATCH_AUTO_DRIVE_SPEED = -0.4;
	public static final double ELEVATOR_GET_HATCH_CHANGE = 20.0;
	public static final double ELEVATOR_RELEASE_HATCH_CHANGE = 7.0;

	//GamePad Mappings
	//Logitech f310
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
