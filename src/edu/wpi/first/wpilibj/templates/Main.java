/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
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
    Joystick Gamepad; 
    Drive Drive;
    Shooter Shooter;
    DriverMessages DriverMessages;
    DriverStation DriverStation;
    Arm ArmFront;
    Arm ArmBack;
    Camera Camera;
    Compressor Compressor;
    
    int AutoMode;
    
    public void robotInit()
    {
        JoystickRight = new Joystick(MainConstants.RIGHT_JOY_PORT);
        JoystickLeft = new Joystick(MainConstants.LEFT_JOY_PORT);
        Gamepad = new Joystick(MainConstants.GAMEPAD_PORT);
        
        Drive = new Drive();
        Shooter = new Shooter();
        DriverStation = DriverStation.getInstance();
        
        AutoMode = MainConstants.DEFUALT_AUTO_MODE;
        DriverMessages = new DriverMessages(AutoMode);
        ArmFront = new Arm(ArmConstants.PISTON_FRONT_CHANNEL, ArmConstants.ROLLER_FRONT_CHANNEL);
        ArmBack = new Arm(ArmConstants.PISTON_BACK_CHANNEL, ArmConstants.ROLLER_BACK_CHANNEL);
        
        Compressor = new Compressor(MainConstants.COMPRESSOR_SWITCH_CHANNEL, MainConstants.COMPRESSOR_RELAY_CHANNEL);
        Compressor.start();
    }
    
    public void autonomous() 
    {
        try {
            switch(AutoMode)
            {
                case 1:
                    if(Camera.goalIsHot())
                    {
                        auto1Hot();
                    }
                    else
                    {
                        auto1Cold();
                    }
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
        catch (InterruptedException ex) 
        {
                ex.printStackTrace();
        }
    }
    
    public void operatorControl()
    {
        while(DriverStation.isOperatorControl())
        {
            //drive
            Drive.setLeft(-JoystickLeft.getRawAxis(MainConstants.DRIVE_AXIS));
            Drive.setRight(-JoystickRight.getRawAxis(MainConstants.DRIVE_AXIS));
            
            //traction
            Drive.traction(JoystickRight.getRawButton(MainConstants.TRACTION_BUTTON) || JoystickLeft.getRawButton(MainConstants.TRACTION_BUTTON));
            
            //shoot
            if(JoystickRight.getRawButton(MainConstants.SHOOT_BUTTON) || JoystickLeft.getRawButton(MainConstants.SHOOT_BUTTON))
            {
                Shooter.shoot();
            }
            
            //arm
            if(Gamepad.getRawButton(MainConstants.FRONT_PICKUP_BUTTON))
            {
                ArmFront.armUp(false);
                ArmFront.rollerForward();
            }
            else
            {
                ArmFront.rollerStop();
            }
            if(JoystickRight.getRawButton(MainConstants.ALL_ARMS_UP_DRIVER) || JoystickLeft.getRawButton(MainConstants.ALL_ARMS_UP_DRIVER) 
                    || Gamepad.getRawButton(MainConstants.ALL_ARMS_UP_GAMEPAD))
            {
                ArmFront.armUp(true);
                ArmBack.armUp(true);    
            }
            else if(Gamepad.getRawButton(MainConstants.BACK_PICKUP_BUTTON))
            {
                ArmBack.armUp(false);
                ArmBack.rollerBackward();
            }
            else if(Gamepad.getRawButton(MainConstants.ALL_ARMS_DOWN_GAMEPAD))
            {
                ArmFront.armUp(false);
                ArmFront.armUp(false);
            }
        }
    }
    
    public void disabled() 
    {
        while(DriverStation.isDisabled())
        {
            //set auto program
            boolean buttonPressed = false;
            for(int autoMode = 1; 10 <= autoMode && false == buttonPressed; autoMode++)
            {
                if(JoystickLeft.getRawButton(autoMode) || JoystickRight.getRawButton(autoMode))
                {
                    AutoMode = autoMode;
                    DriverMessages.updateAutoMode(autoMode);
                    buttonPressed = true;
                }
            }
        }
    }

    private void auto1Hot() throws InterruptedException 
    {
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(3000);
        Drive.setLeft(0);
        Drive.setRight(0);
        Thread.sleep(200);
        Shooter.shoot();
    }

    private void auto1Cold() throws InterruptedException 
    {
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(3000);
        Drive.setLeft(0);
        Drive.setRight(0);
        Thread.sleep(3200);
        Shooter.shoot();
    }
}