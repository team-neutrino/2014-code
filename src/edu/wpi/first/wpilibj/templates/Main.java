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
    DriverMessages DriverMessages;
    
    int AutoMode;
    
    public void robotInit()
    {
        JoystickRight = new Joystick(MainConstants.RIGHT_JOY_PORT);
        JoystickLeft = new Joystick(MainConstants.LEFT_JOY_PORT);
        Drive = new Drive();
        
        AutoMode = MainConstants.DEFUALT_AUTO_MODE;
        DriverMessages = new DriverMessages(AutoMode);
    }
    
    public void autonomous() 
    {
        switch(AutoMode)
        {
            case 1:
                //code for auto 1
                break;
            case 2:
                //code for auto 2
                break;
            case 3:
                //code for auto 3
                break;
            case 4:
                //code for auto 4
                break;
            case 5:
                //code for auto 5
                break;
            case 6:
                //code for auto 6
                break;
            case 7:
                //code for auto 7
                break;
            case 8:
                //code for auto 8
                break;
            case 9:
                //code for auto 9
                break;
            case 10:
                //code for auto 10
                break;
        }
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
        while(true)
        {
            //set auto program
            if(JoystickLeft.getRawButton(1) || JoystickRight.getRawButton(1))
            {
                AutoMode = 1;
                DriverMessages.updateAutoMode(AutoMode);
            } 
            else if (JoystickLeft.getRawButton(2) || JoystickRight.getRawButton(2))
            {
                AutoMode = 2;
                DriverMessages.updateAutoMode(AutoMode);
            }
            else if (JoystickLeft.getRawButton(3) || JoystickRight.getRawButton(3))
            {
                AutoMode = 3;
                DriverMessages.updateAutoMode(AutoMode);
            }
            else if (JoystickLeft.getRawButton(4) || JoystickRight.getRawButton(4))
            {
                AutoMode = 4;
                DriverMessages.updateAutoMode(AutoMode);
            }
            else if (JoystickLeft.getRawButton(5) || JoystickRight.getRawButton(5))
            {
                AutoMode = 5;
                DriverMessages.updateAutoMode(AutoMode);
            }
            else if (JoystickLeft.getRawButton(6) || JoystickRight.getRawButton(6))
            {
                AutoMode = 6;
                DriverMessages.updateAutoMode(AutoMode);
            }
            else if (JoystickLeft.getRawButton(7) || JoystickRight.getRawButton(7))
            {
                AutoMode = 7;
                DriverMessages.updateAutoMode(AutoMode);
            }
            else if (JoystickLeft.getRawButton(8) || JoystickRight.getRawButton(8))
            {
                AutoMode = 8;
                DriverMessages.updateAutoMode(AutoMode);
            }
            else if (JoystickLeft.getRawButton(9) || JoystickRight.getRawButton(9))
            {
                AutoMode = 9;
                DriverMessages.updateAutoMode(AutoMode);
            }
            else if (JoystickLeft.getRawButton(10) || JoystickRight.getRawButton(10))
            {
                AutoMode = 10;
                DriverMessages.updateAutoMode(AutoMode);
            }
        }
    }
}