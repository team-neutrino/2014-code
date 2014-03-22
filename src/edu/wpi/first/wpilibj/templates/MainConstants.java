/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Tony Milosch
 */
public class MainConstants
{
    
    public static final boolean REAL_BOT = false;
    
    //autonomous
    public static final int DEFUALT_AUTO_MODE = 1;
    public static final int FORWARD_WAIT = 1500;
    
    //joysticks
    public static final int LEFT_JOY_PORT = 1;
    public static final int RIGHT_JOY_PORT = 2;
    public static final int GAMEPAD_PORT = 3;
    
    //drive
    public static final int DRIVE_AXIS = 2;
    public static final int TRACTION_BUTTON = 1;
    
    public static final int DRIVE_INVERT = 1;
    
    
    //shoot
    public static final int SHOOT_BUTTON = 2;
    public static final int SHOOT_LOB_BUTTON = 2;
    public static final int SHOOTER_RELEASE_BUTTON = 3;
    
    public static final int BALL_EJECT_BUTTON = 2;
    
    //arms
    public static final int ALL_ARMS_UP_DRIVER = 3;
    public static final int ALL_ARMS_UP_GAMEPAD = 1; //7
    public static final int ALL_ARMS_DOWN_GAMEPAD = 4; //8
    
    public static final int ARM_HOLD = 4;
    
    public static final int DISABLE_AUTO_ROLL = 7;
    public static final int ENABLE_AUTO_ROLL = 8;
    
    public static final int FRONT_ARM_TOGGLE = 4;
    public static final int BACK_ARM_TOGGLE = 1;
    
    public static final int ROLLER_FORWARD = 5;
    public static final int ROLLER_BACKWARD = 6;
    public static final int ROLLER_STOP = 1;
    
    private static void real() 
    {
        //Compressor
        COMPRESSOR_SWITCH_CHANNEL = 4;
        COMPRESSOR_RELAY_CHANNEL = 4;
        
        //Camera
        CAMERA_LIGHT_SLOT = 1;
        CAMERA_LIGHT_CHANNEL = 8;
        
        //Auto Switch
        AUTO_BIT_1_CHANNEL = 0;
        AUTO_BIT_2_CHANNEL = 0;
        AUTO_BIT_4_CHANNEL = 0;
        AUTO_BIT_8_CHANNEL = 0;
    }
    
    private static void practice() 
    {
        //Compressor
        COMPRESSOR_SWITCH_CHANNEL = 1;
        COMPRESSOR_RELAY_CHANNEL = 1;
        
        //Camera
        CAMERA_LIGHT_SLOT = 2;
        CAMERA_LIGHT_CHANNEL = 1;
        
        //Auto Switch
        AUTO_BIT_1_CHANNEL = 4;
        AUTO_BIT_2_CHANNEL = 5;
        AUTO_BIT_4_CHANNEL = 6;
        AUTO_BIT_8_CHANNEL = 7;
    }
    
    //Compressor
    public static int COMPRESSOR_SWITCH_CHANNEL;
    public static int COMPRESSOR_RELAY_CHANNEL;
    
    //Camera
    public static int CAMERA_LIGHT_SLOT;
    public static int CAMERA_LIGHT_CHANNEL;
    
    //Auto Switch
    public static int AUTO_BIT_1_CHANNEL;
    public static int AUTO_BIT_2_CHANNEL;
    public static int AUTO_BIT_4_CHANNEL;
    public static int AUTO_BIT_8_CHANNEL;
    
    public static void init()
    {
        if(REAL_BOT)
        {
            real();
        }
        else
        {
            practice();
        }
    }
}
