/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ClimbingElevator;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DriveTrain driveTrain = new DriveTrain();
  public static Elevator elevator = new Elevator();
  public static Intake intake = new Intake();
  public static Arm arm = new Arm();
  public static ClimbingElevator climbingElevator = new ClimbingElevator();
  public static Joystick driver = new Joystick(RobotMap.DRIVE_PORT);
  public static Joystick operator = new Joystick(RobotMap.OPERATOR_PORT); 
  public static Joystick climberJoystick = new Joystick(RobotMap.CLIMBER_JOYSTICK_PORT);

  // public static UsbCamera driveCamera;
  // public static UsbCamera alignCamera;
  // public static VideoSink cameraServer;

  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();

    TeleopDrive teleopDrive = new TeleopDrive(driveTrain, false);

    m_chooser.setDefaultOption("TeleopDrive", teleopDrive);
    SmartDashboard.putData("Drive Train", driveTrain);
    SmartDashboard.putData("Elevator", elevator);
    SmartDashboard.putData("Intake", intake);
    SmartDashboard.putData("Arm", arm);
    //SmartDashboard.putData("Climbing Elevator", climbingElevator);
    SmartDashboard.putData(Scheduler.getInstance());


    driveTrain.setDefaultCommand(teleopDrive);

    // driveCamera = CameraServer.getInstance().startAutomaticCapture();
    // alignCamera = CameraServer.getInstance().startAutomaticCapture();
    // cameraServer = CameraServer.getInstance().getServer();

    // driveCamera.setResolution(213, 160);
    // driveCamera.setFPS(25);
    // driveCamera.setBrightness(50);

    // alignCamera.setResolution(213, 160);
    // alignCamera.setFPS(5);
    // alignCamera.setBrightness(50);

    //Setup Camera Server for Smart Dashboard
		new Thread(() -> {
			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture();
			UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture();

			camera1.setResolution(213, 160);
			camera2.setResolution(213, 160);
			camera1.setFPS(30);
			camera2.setFPS(5);
			camera1.setBrightness(50);
			camera2.setBrightness(50);

			
			CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 213, 160);
			
			 Mat source = new Mat();
			 Mat output = new Mat();
			
			 while(!Thread.interrupted()) {
			 	cvSink.grabFrame(source);
		  	Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
			 	outputStream.putFrame(output);
			}
		}).start();
	}

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    //cameraStream();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //cameraStream();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  // public void cameraStream() {
  //   if(Robot.elevator.getCurrentPosition() > 100 || driver.getRawButton(RobotMap.kButtonLeftBumper)) {
  //     System.out.println("Switch to aligning camera");
  //     cameraServer.setSource(alignCamera);
  //   }
  //   else {
  //     cameraServer.setSource(driveCamera);
  //   }
  //   Mat source = new Mat();
  //   Mat output = new Mat();
  //   CvSource outputStream = CameraServer.getInstance().putVideo("Matias", 213, 160);
  //   outputStream.putFrame(output);
  // }
}
