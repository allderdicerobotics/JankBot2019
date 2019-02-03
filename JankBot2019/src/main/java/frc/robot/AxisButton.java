/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.GenericHID;

/**
 * Add your docs here.
 */
public class AxisButton extends Trigger {
    private GenericHID joystick;
    private int buttonNumber;
    private boolean right;

    public AxisButton(GenericHID joystick, int buttonNumber, boolean right) {
        this.buttonNumber = buttonNumber;
        this.joystick = joystick;
        this.right = right;
    }

    public boolean get() {
        boolean move = false;
        double axis = joystick.getRawAxis(buttonNumber);
        if(axis>RobotMap.DEADZONE_THRESHOLD && right){
            move = true;
        }
        else if(axis<-RobotMap.DEADZONE_THRESHOLD && !right){
            move = true;
        }
        return move;
    }
}
