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
        initConstants();
        
        Compressor = new Compressor(MainConstants.COMPRESSOR_SWITCH_CHANNEL, MainConstants.COMPRESSOR_RELAY_CHANNEL);
        Compressor.start();

        JoystickRight = new Joystick(MainConstants.RIGHT_JOY_PORT);
        JoystickLeft = new Joystick(MainConstants.LEFT_JOY_PORT);
        Gamepad = new Joystick(MainConstants.GAMEPAD_PORT);
        
        AutoMode = MainConstants.DEFUALT_AUTO_MODE;
        DriverMessages = new DriverMessages(AutoMode);
        
        DriverStation = DriverStation.getInstance();
        
        Drive = new Drive();
        Shooter = new Shooter(DriverStation, DriverMessages);
        
        ArmFront = new Arm(true);
        ArmBack = new Arm(false);
        
        Camera = new Camera();
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
        boolean frontToggle;
        boolean backToggle; 
        boolean frontTogglePrevious = Gamepad.getRawButton(MainConstants.FRONT_ARM_TOGGLE);
        boolean backTogglePrevious = Gamepad.getRawButton(MainConstants.BACK_ARM_TOGGLE);
        long frontTimeToggled = 0;
        long backTimeToggled = 0;
        long currentTime;
        
        while(DriverStation.isOperatorControl())
        {
            //drive
            Drive.setLeft(-JoystickLeft.getRawAxis(MainConstants.DRIVE_AXIS));
            Drive.setRight(-JoystickRight.getRawAxis(MainConstants.DRIVE_AXIS));
            
            //traction
            Drive.traction(JoystickRight.getRawButton(MainConstants.TRACTION_BUTTON) || JoystickLeft.getRawButton(MainConstants.TRACTION_BUTTON));
            
            //shoot
            if((JoystickRight.getRawButton(MainConstants.SHOOT_BUTTON) || JoystickLeft.getRawButton(MainConstants.SHOOT_BUTTON)) && !ArmFront.isUp())
            {
                Shooter.shoot();
            }
            
            //arm (up/down)
            if(JoystickRight.getRawButton(MainConstants.ALL_ARMS_UP_DRIVER) || JoystickLeft.getRawButton(MainConstants.ALL_ARMS_UP_DRIVER) 
                    || Gamepad.getRawButton(MainConstants.ALL_ARMS_UP_GAMEPAD))
            {
                ArmFront.armUp(true);
                ArmBack.armUp(true);    
            }
            else if(Gamepad.getRawButton(MainConstants.ALL_ARMS_DOWN_GAMEPAD))
            {
                ArmFront.armUp(false);
                ArmBack.armUp(false);
            }
            else
            {
                frontToggle = Gamepad.getRawButton(MainConstants.FRONT_ARM_TOGGLE);
                backToggle = Gamepad.getRawButton(MainConstants.BACK_ARM_TOGGLE);
                currentTime = System.currentTimeMillis();
                
                if (!frontTogglePrevious && frontToggle && (currentTime - frontTimeToggled) > 50)
                {
                    ArmFront.armUp(!ArmFront.isUp());
                }
                if (!backTogglePrevious && backToggle && (currentTime - backTimeToggled) > 50)
                {
                    ArmBack.armUp(!ArmBack.isUp());
                }
                
                if(frontToggle)
                {
                    frontTimeToggled = currentTime;
                }
                if(backToggle)
                {
                    backTimeToggled = currentTime;
                }
                
                frontTogglePrevious = frontToggle;
                backTogglePrevious = backToggle;
            }
            
            //arm (rollers)
            if(Gamepad.getRawButton(MainConstants.ROLLER_FORWARD))
            {
                ArmFront.rollerForward();
                ArmBack.rollerForward();
            }
            else if(Gamepad.getRawButton(MainConstants.ROLLER_BACKWARD))
            {
                ArmFront.rollerBackward();
                ArmBack.rollerBackward();
            }
            else
            {
                ArmFront.rollerStop();
                ArmBack.rollerStop();
            }
            
            //release shooter at match end
            if(154.9 <= DriverStation.getMatchTime())
            {
                Shooter.release();
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
        ArmFront.armUp(false);
        ArmBack.armUp(false);
        Thread.sleep(1000);
        Shooter.shoot();
        Thread.sleep(1000);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(3000);
        Drive.setLeft(0);
        Drive.setRight(0);
    }

    private void auto1Cold() throws InterruptedException 
    {
        ArmFront.armUp(false);
        ArmBack.armUp(false);
        Thread.sleep(5000);
        Shooter.shoot();
        Thread.sleep(1000);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(3000);
        Drive.setLeft(0);
        Drive.setRight(0);
    }
    
    private void initConstants()
    {
        MainConstants.init();
        ShooterConstants.init();
        DriveConstants.init();
        ArmConstants.init();
    }
}