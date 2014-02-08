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
    
    public static final boolean REAL_BOT = true;
    
    //defaults
    public static final int DEFUALT_AUTO_MODE = 1;
    
    //joysticks
    public static final int LEFT_JOY_PORT = 1;
    public static final int RIGHT_JOY_PORT = 2;
    public static final int GAMEPAD_PORT = 3;
    
    //drive
    public static final int DRIVE_AXIS = 2;
    public static final int TRACTION_BUTTON = 1;
    public static final int SHOOT_BUTTON = 2;
    
    public static final int ALL_ARMS_UP_DRIVER = 3;
    public static final int ALL_ARMS_UP_GAMEPAD = 6;
    public static final int ALL_ARMS_DOWN_GAMEPAD = 5;
    
    public static final int FRONT_PICKUP_BUTTON = 1;
    public static final int BACK_PICKUP_BUTTON = 2;
    
    private static void real() 
    {
        //Modules
        TWO_SOLENOID_SOLTS = true;

        //Compressor
        COMPRESSOR_SWITCH_CHANNEL = 1;
        COMPRESSOR_RELAY_CHANNEL = 1;
    }
    
    private static void practice() 
    {
        //Modules
        TWO_SOLENOID_SOLTS = false;

        //Compressor
        COMPRESSOR_SWITCH_CHANNEL = 1;
        COMPRESSOR_RELAY_CHANNEL = 1;
    }
    
    //Modules
    public static boolean TWO_SOLENOID_SOLTS;
    
    //Compressor
    public static int COMPRESSOR_SWITCH_CHANNEL;
    public static int COMPRESSOR_RELAY_CHANNEL;
    
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
