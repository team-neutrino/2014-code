/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
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
    DigitalInput AutoBit1;
    DigitalInput AutoBit2;
    DigitalInput AutoBit4;
    DigitalInput AutoBit8;
    
    Joystick JoystickRight;
    Joystick JoystickLeft;
    Joystick Gamepad; 
    Drive Drive;
    Shooter Shooter;
    LightManager LightManager;
    DriverMessages DriverMessages;
    DriverStation DriverStation;
    //Arm ArmFront;
    Arm ArmBack;
    Camera Camera;
    Compressor Compressor;
    
    int AutoMode;
    
    boolean InvertDrive;
    
    public void robotInit()
    {
        initConstants();
        
        Compressor = new Compressor(MainConstants.COMPRESSOR_SWITCH_CHANNEL, MainConstants.COMPRESSOR_RELAY_CHANNEL);
        Compressor.start();
        System.out.println("Compressor Slot & Channel" + MainConstants.COMPRESSOR_SWITCH_CHANNEL +" "+ MainConstants.COMPRESSOR_RELAY_CHANNEL);
        JoystickRight = new Joystick(MainConstants.RIGHT_JOY_PORT);
        JoystickLeft = new Joystick(MainConstants.LEFT_JOY_PORT);
        Gamepad = new Joystick(MainConstants.GAMEPAD_PORT);
        
        AutoBit1 = new DigitalInput(MainConstants.AUTO_BIT_1_CHANNEL);
        AutoBit2 = new DigitalInput(MainConstants.AUTO_BIT_2_CHANNEL);
        AutoBit4 = new DigitalInput(MainConstants.AUTO_BIT_4_CHANNEL);
        AutoBit8 = new DigitalInput(MainConstants.AUTO_BIT_8_CHANNEL);
        
        LightManager = new LightManager();
        DriverMessages = new DriverMessages(LightManager);
        
        updateAutoMode();
        
        DriverStation = DriverStation.getInstance();
        
        Drive = new Drive();
        Shooter = new Shooter(DriverStation, DriverMessages);
        
        //ArmFront = new Arm(true);
        ArmBack = new Arm(false);
        
        Camera = new Camera(DriverMessages);
        
        InvertDrive = false;
    }
    
    public void autonomous() 
    {
        updateAutoMode();
        LightManager.setFrontLights(false);
        LightManager.setBackLights(false);
        try {
            switch(AutoMode)
            {
                case 0://Do Nothing
                    break;
                case 1://Shoot Hot & Mobility
                    if(Camera.goalIsHot())
                    {
                        auto1Hot();
                    }
                    else
                    {
                        auto1Cold();
                    }
                    break;
                case 2://Shoot Hot
                    if(Camera.goalIsHot())
                    {
                        auto2Hot();
                    }
                    else
                    {
                        auto2Cold();
                    }
                    break;
                case 3://Mobility
                    auto3();
                    break;
                case 4://Shoot 2 & Mobility
                    auto4();
                    break;
                case 5://Shoot 3 & Mobility
                    auto5();
                    break;
                case 6://Low Goal Hot
                    if(Camera.goalIsHot())
                    {
                        auto6Hot();
                    }
                    else
                    {
                        auto6Cold();
                    }
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
            }
        }
        catch (InterruptedException ex) 
        {
                ex.printStackTrace();
        }
        Camera.setLight(false);
    }
    
    public void operatorControl()
    {
//        boolean frontToggle;
//        boolean backToggle; 
//        boolean frontTogglePrevious = Gamepad.getRawButton(MainConstants.FRONT_ARM_TOGGLE);
//        boolean backTogglePrevious = Gamepad.getRawButton(MainConstants.BACK_ARM_TOGGLE);
//        long frontTimeToggled = 0;
//        long backTimeToggled = 0;
//        long currentTime;
        Camera.setLight(false);
        while(DriverStation.isOperatorControl() && DriverStation.isEnabled())
        {
            //drive
            InvertDrive = (JoystickLeft.getRawButton(MainConstants.DRIVE_INVERT));
            
            LightManager.setFrontLights(!InvertDrive);
            LightManager.setBackLights(InvertDrive);
            
            if(InvertDrive)
            {
                Drive.setLeft(-JoystickLeft.getRawAxis(MainConstants.DRIVE_AXIS));
                Drive.setRight(-JoystickRight.getRawAxis(MainConstants.DRIVE_AXIS));
            }
            else
            {
                Drive.setLeft(JoystickRight.getRawAxis(MainConstants.DRIVE_AXIS));
                Drive.setRight(JoystickLeft.getRawAxis(MainConstants.DRIVE_AXIS));
            }
            
            //traction
            Drive.traction(JoystickRight.getRawButton(MainConstants.TRACTION_BUTTON));
            
            //shoot
            if(JoystickRight.getRawButton(MainConstants.SHOOT_BUTTON) || JoystickLeft.getRawButton(MainConstants.SHOOT_LOB_BUTTON))
            {
                Shooter.shootCock();
            }
            
            if(Gamepad.getRawButton(MainConstants.SHOOTER_RELEASE_BUTTON))
            {
                Shooter.release();
            }
            
            Shooter.eject(Gamepad.getRawButton(MainConstants.BALL_EJECT_BUTTON));
            
            //arm (up/down)
            ArmBack.armDown(Gamepad.getRawButton(MainConstants.ARM_HOLD));
//            if(JoystickRight.getRawButton(MainConstants.ALL_ARMS_UP_DRIVER) || JoystickLeft.getRawButton(MainConstants.ALL_ARMS_UP_DRIVER) 
//                    || Gamepad.getRawButton(MainConstants.ALL_ARMS_UP_GAMEPAD))
//            {
//                //ArmFront.armDown(true);
//                ArmBack.armDown(true);    
//            }
//            else if(Gamepad.getRawButton(MainConstants.ALL_ARMS_DOWN_GAMEPAD))
//            {
//                //ArmFront.armDown(false);
//                ArmBack.armDown(false);
//            }
//            else
//            {
////                frontToggle = Gamepad.getRawButton(MainConstants.FRONT_ARM_TOGGLE);
////                backToggle = Gamepad.getRawButton(MainConstants.BACK_ARM_TOGGLE);
////                currentTime = System.currentTimeMillis();
////                
////                if (!frontTogglePrevious && frontToggle && (currentTime - frontTimeToggled) > 50)
////                {
////                    //ArmFront.armDown(!ArmFront.isUp());
////                }
////                if (!backTogglePrevious && backToggle && (currentTime - backTimeToggled) > 50)
////                {
////                    ArmBack.armDown(!ArmBack.isUp());
////                }
////                
////                if(frontToggle)
////                {
////                    frontTimeToggled = currentTime;
////                }
////                if(backToggle)
////                {
////                    backTimeToggled = currentTime;
////                }
////                
////                frontTogglePrevious = frontToggle;
////                backTogglePrevious = backToggle;
//            }
            
            //arm (rollers)
            if(Gamepad.getRawButton(MainConstants.ROLLER_STOP))
            {
                //ArmFront.rollerStop();
                ArmBack.rollerStop();
            }
            else if(Gamepad.getRawButton(MainConstants.ROLLER_BACKWARD) || Gamepad.getRawButton(MainConstants.BALL_EJECT_BUTTON))
            {
                //ArmFront.rollerOut();
                ArmBack.rollerOut();
            }
            else if(Gamepad.getRawButton(MainConstants.ROLLER_FORWARD) || Gamepad.getRawButton(MainConstants.ARM_HOLD))
            {
                //ArmFront.rollerIn();
                ArmBack.rollerIn();
            }
            else
            {
                //ArmFront.rollerStopSlow();
                ArmBack.rollerStopSlow();
            }
            
            //arm (disable/enable autoroll)
            if(Gamepad.getRawButton(MainConstants.ENABLE_AUTO_ROLL))
            {
                //ArmFront.slowRollEnabled(true);
                ArmBack.slowRollEnabled(true);
            }
            else if(Gamepad.getRawButton(MainConstants.DISABLE_AUTO_ROLL))
            {
                //ArmFront.slowRollEnabled(false);
                ArmBack.slowRollEnabled(false);
            }
            
            
            updateAutoMode();
            
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
            updateAutoMode();
        }
    }

    //Shoot Hot & Mobility
    private void auto1Hot() throws InterruptedException 
    {
        Drive.traction(true);
        //ArmFront.armDown(false);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(MainConstants.FORWARD_WAIT);
        Drive.setLeft(0);
        Drive.setRight(0);
        Thread.sleep(1000);
        Shooter.shootCock();
        Drive.traction(false);
    }

    private void auto1Cold() throws InterruptedException 
    {
        Drive.traction(true);
        //ArmFront.armDown(false);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(MainConstants.FORWARD_WAIT);
        Drive.setLeft(0);
        Drive.setRight(0);
        Thread.sleep(6000 - MainConstants.FORWARD_WAIT);
        Shooter.shootCock();
        Drive.traction(false);
    }
    
    //Shoot Hot
    private void auto2Hot() throws InterruptedException 
    {
        //ArmFront.armDown(false);
        ArmBack.armDown(false);
        Thread.sleep(1000);
        Shooter.shootCock();
    }
    
    private void auto2Cold() throws InterruptedException 
    {
        //ArmFront.armDown(false);
        ArmBack.armDown(false);
        Thread.sleep(5000);
        Shooter.shootCock();
    }
    
    //Mobility
    private void auto3() throws InterruptedException 
    {
        Drive.traction(true);
        //ArmFront.armDown(false);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(MainConstants.FORWARD_WAIT);
        Drive.setLeft(0);
        Drive.setRight(0);
        Drive.traction(false);
    }
    
    //Shoot 2 & Mobility
    private void auto4() throws InterruptedException 
    {
        Drive.traction(true);
        //ArmFront.armDown(false);
        ArmBack.armDown(true);
        boolean hot = Camera.goalIsHot();
        ArmBack.rollerIn();
        Thread.sleep(500);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(MainConstants.FORWARD_WAIT);
        Drive.setLeft(0);
        Drive.setRight(0);
        Drive.traction(false);
        ArmBack.rollerStopSlow();
        Thread.sleep(700);
        Shooter.shootCock();
        Thread.sleep(2500);
        ArmBack.armDown(true);
        ArmBack.rollerIn();
        Drive.setLeft(-1);
        Drive.setRight(-1);
        Thread.sleep(250);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(250);
        Drive.setLeft(0);
        Drive.setRight(0);
        ArmBack.armDown(false);
        Thread.sleep(2500);
        Shooter.shootCock();
    }
    
    //Shoot 3 & Mobility
    private void auto5() throws InterruptedException
    {
        Drive.traction(true);
        //ArmFront.armDown(true);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(MainConstants.FORWARD_WAIT);
        Drive.setLeft(0);
        Drive.setRight(0);
        Thread.sleep(500);
        Shooter.shootCock();
        Thread.sleep(1500);
        //ArmFront.rollerForward();
        Thread.sleep(2000);
        Shooter.shootCock();
        ///ArmFront.rollerStopSlow();
        Thread.sleep(500);
        ArmBack.armDown(true);
        //ArmFront.armDown(false);
        ArmBack.rollerIn();
        Drive.setLeft(-1);
        Drive.setRight(-1);
        Thread.sleep(MainConstants.FORWARD_WAIT);
        Drive.traction(false);
        Drive.setLeft(0);
        Drive.setRight(0);
    }
    
    //Low Goal Hot
    private void auto6Hot() throws InterruptedException
    {
        Drive.traction(true);
        //ArmFront.armDown(false);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(2250);
        Drive.setLeft(0);
        Drive.setRight(0);
        Drive.traction(false);
        Thread.sleep(500);
        Shooter.eject(true);
        ArmBack.rollerOut();
        Thread.sleep(2000);
        Shooter.eject(false);
        ArmBack.rollerStopSlow();
    }
    
    private void auto6Cold() throws InterruptedException
    {
        Drive.traction(true);
        //ArmFront.armDown(false);
        ArmBack.armDown(false);
        Drive.setLeft(1);
        Drive.setRight(1);
        Thread.sleep(2250);
        Drive.setLeft(0);
        Drive.setRight(0);
        Drive.traction(false);
        Thread.sleep(2750);
        Shooter.eject(true);
        ArmBack.rollerOut();
        Thread.sleep(2000);
        Shooter.eject(false);
        ArmBack.rollerStopSlow();
    }
            
    private void initConstants()
    {
        MainConstants.init();
        ShooterConstants.init();
        DriveConstants.init();
        ArmConstants.init();
        LightConstants.init();
    }
    
    private void updateAutoMode()
    {
        AutoMode = (AutoBit1.get() ? 1 : 0) + (AutoBit2.get() ? 2 : 0) + (AutoBit4.get() ? 4 : 0) + (AutoBit8.get() ? 8 : 0);
        DriverMessages.updateAutoMode(AutoMode);
    }
}
