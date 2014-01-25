/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Main extends SimpleRobot 
{
    Joystick JoystickRight;
    Joystick JoystickLeft;
    Drive Drive;
    
    public void robotInit()
    {
        JoystickRight = new Joystick(MainConstants.RIGHT_JOY_PORT);
        JoystickLeft = new Joystick(MainConstants.LEFT_JOY_PORT);
        Drive = new Drive();
    }
    
    public void autonomous() 
    {
    }
    
    public void operatorControl()
    {
        while(true)
        {
            //drive
            Drive.goLeft(-JoystickLeft.getRawAxis(MainConstants.DRIVE_AXIS));
            Drive.goRight(-JoystickRight.getRawAxis(MainConstants.DRIVE_AXIS));
            
            //traction
            Drive.traction(JoystickRight.getRawButton(MainConstants.TRACTION_BUTTON) || JoystickLeft.getRawButton(MainConstants.TRACTION_BUTTON));
        }
    }
    
    public void disabled() 
    {
    }
}