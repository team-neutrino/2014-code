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
        System.out.println("Compressor Slot & Channel" + MainConstants.COMPRESSOR_SWITCH_CHANNEL +" "+ MainConstants.COMPRESSOR_RELAY_CHANNEL);
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
        
        Camera = new Camera(DriverMessages);
    }
    
    public void autonomous() 
    {
        try {
            switch(AutoMode)
            {
                case 1://Check Hot, Shoot, & Move
                    if(Camera.goalIsHot())
                    {
                        auto1Hot();
                    }
                    else
                    {
                        auto1Cold();
                    }
                    break;
                case 2:// Check and Shoot Hot
                    if(Camera.goalIsHot())
                    {
                        auto2Hot();
                    }
                    else
                    {
                        auto2Cold();
                    }
                    break;
                case 3://Move
                    auto3();
                    break;
                case 4://Shoot 2 balls and Move
                    auto4();
                    break;
                case 5:
                    //Shoot 2 balls and Move
                    auto5();
                    break;
                case 6:
                    //Do Nothing
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
        
        while(DriverStation.isOperatorControl() && DriverStation.isEnabled())
        {
            //drive
            Drive.setLeft(-JoystickLeft.getRawAxis(MainConstants.DRIVE_AXIS));
            Drive.setRight(-JoystickRight.getRawAxis(MainConstants.DRIVE_AXIS));
            
            //traction
            Drive.traction(JoystickRight.getRawButton(MainConstants.TRACTION_BUTTON) || JoystickLeft.getRawButton(MainConstants.TRACTION_BUTTON));
            
            //shoot
            if((JoystickRight.getRawButton(MainConstants.SHOOT_BUTTON) || JoystickLeft.getRawButton(MainConstants.SHOOT_BUTTON)))
            {
                Shooter.shootCock();
            }
            if(Gamepad.getRawButton(MainConstants.SHOOTER_RELEASE_BUTTON))
            {
                Shooter.release();
            }
            
            //arm (up/down)
            if(JoystickRight.getRawButton(MainConstants.ALL_ARMS_UP_DRIVER) || JoystickLeft.getRawButton(MainConstants.ALL_ARMS_UP_DRIVER) 
                    || Gamepad.getRawButton(MainConstants.ALL_ARMS_UP_GAMEPAD))
            {
                ArmFront.armDown(true);
                ArmBack.armDown(true);    
            }
            else if(Gamepad.getRawButton(MainConstants.ALL_ARMS_DOWN_GAMEPAD))
            {
                ArmFront.armDown(false);
                ArmBack.armDown(false);
            }
            else
            {
                frontToggle = Gamepad.getRawButton(MainConstants.FRONT_ARM_TOGGLE);
                backToggle = Gamepad.getRawButton(MainConstants.BACK_ARM_TOGGLE);
                currentTime = System.currentTimeMillis();
                
                if (!frontTogglePrevious && frontToggle && (currentTime - frontTimeToggled) > 50)
                {
                    ArmFront.armDown(!ArmFront.isUp());
                }
                if (!backTogglePrevious && backToggle && (currentTime - backTimeToggled) > 50)
                {
                    ArmBack.armDown(!ArmBack.isUp());
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
//            if(154.9 <= DriverStation.getMatchTime())
//            {
//                Shooter.release();
//            }
        } 
    }
    
    public void disabled() 
    {
        while(DriverStation.isDisabled())
        {
            //set auto program
            boolean buttonPressed = false;
            for(int autoMode = 1; autoMode <= 10 && false == buttonPressed; autoMode++)
            {
                if(JoystickLeft.getRawButton(autoMode) || JoystickRight.getRawButton(autoMode))
                {
                    AutoMode = autoMode;
                    DriverMessages.updateAutoMode(autoMode);
                    buttonPressed = true;
                    System.out.println("Switching to Auto " + autoMode);
                }
            }
        }
    }

    //Shoot Hot & Mobility
    private void auto1Hot() throws InterruptedException 
    {
        Drive.traction(true);
        ArmFront.armDown(false);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(1500);
        Drive.setLeft(0);
        Drive.setRight(0);
        ArmBack.armDown(true);
        Thread.sleep(1000);
        Shooter.shootCock();
        Drive.traction(false);
    }

    private void auto1Cold() throws InterruptedException 
    {
        Drive.traction(true);
        Thread.sleep(2500);
        ArmFront.armDown(false);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(1500);
        Drive.setLeft(0);
        Drive.setRight(0);
        ArmBack.armDown(true);
        Thread.sleep(1000);
        Shooter.shootCock();
        Drive.traction(false);
    }
    
    //Shoot Hot
    private void auto2Hot() throws InterruptedException 
    {
        ArmFront.armDown(false);
        ArmBack.armDown(true);
        Thread.sleep(1000);
        Shooter.shootCock();
    }
    
    private void auto2Cold() throws InterruptedException 
    {
        ArmFront.armDown(false);
        ArmBack.armDown(true);
        Thread.sleep(4000);
        ArmBack.armDown(true);
        Thread.sleep(1000);
        Shooter.shootCock();
    }
    
    //Mobility
    private void auto3() throws InterruptedException 
    {
        Drive.traction(true);
        ArmFront.armDown(false);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(1500);
        Drive.setLeft(0);
        Drive.setRight(0);
        Drive.traction(false);
    }
    
    //Shoot 2 & Mobility
    private void auto4() throws InterruptedException 
    {
        Drive.traction(true);
        ArmFront.armDown(true);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(1500);
        Drive.setLeft(0);
        Drive.setRight(0);
        Thread.sleep(500);
        Shooter.shootCock();
        Thread.sleep(1500);
        ArmFront.rollerForward();
        Thread.sleep(3000);
        Shooter.shootCock();
        ArmFront.rollerStop();
        Drive.traction(false);
    }
    
    //Shoot 3 & Mobility
    private void auto5() throws InterruptedException
    {
        Drive.traction(true);
        ArmFront.armDown(true);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(1500);
        Drive.setLeft(0);
        Drive.setRight(0);
        Thread.sleep(500);
        Shooter.shootCock();
        Thread.sleep(1500);
        ArmFront.rollerForward();
        Thread.sleep(2000);
        Shooter.shootCock();
        ArmFront.rollerStop();
        Thread.sleep(500);
        ArmBack.armDown(true);
        ArmFront.armDown(false);
        ArmBack.rollerForward();
        Drive.setLeft(-1);
        Drive.setRight(-1);
        Thread.sleep(1500);
        Drive.traction(false);
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
