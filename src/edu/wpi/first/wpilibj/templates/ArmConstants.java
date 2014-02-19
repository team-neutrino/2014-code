/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Team Neutrino
 */
public class ArmConstants
{
    private static void real()
    {
        SOLENOID_BACK_UP_SLOT = 1;
        SOLENOID_BACK_UP_CHANNEL = 2;
        SOLENOID_BACK_DOWN_SLOT = 2;
        SOLENOID_BACK_DOWN_CHANNEL = 4;
        SOLENOID_FRONT_UP_SLOT = 2;
        SOLENOID_FRONT_UP_CHANNEL = 5;
        SOLENOID_FRONT_DOWN_SLOT = 1;
        SOLENOID_FRONT_DOWN_CHANNEL = 3;

        ROLLER_FRONT_CHANNEL = 5;
        ROLLER_BACK_CHANNEL = 8;
    }
    
    private static void practice()
    {
        SOLENOID_BACK_UP_SLOT = 1;
        SOLENOID_BACK_UP_CHANNEL = 1;
        SOLENOID_BACK_DOWN_SLOT = 1;
        SOLENOID_BACK_DOWN_CHANNEL = 5;
        SOLENOID_FRONT_UP_SLOT = 1;
        SOLENOID_FRONT_UP_CHANNEL = 7;
        SOLENOID_FRONT_DOWN_SLOT = 1;
        SOLENOID_FRONT_DOWN_CHANNEL = 3;

        ROLLER_FRONT_CHANNEL = 10;
        ROLLER_BACK_CHANNEL = 1;
    }
    
    public static int SOLENOID_BACK_UP_SLOT;
    public static int SOLENOID_BACK_UP_CHANNEL;
    public static int SOLENOID_BACK_DOWN_SLOT;
    public static int SOLENOID_BACK_DOWN_CHANNEL;
    public static int SOLENOID_FRONT_UP_SLOT;
    public static int SOLENOID_FRONT_UP_CHANNEL;
    public static int SOLENOID_FRONT_DOWN_SLOT;
    public static int SOLENOID_FRONT_DOWN_CHANNEL;
    
    public static int ROLLER_FRONT_CHANNEL;
    public static int ROLLER_BACK_CHANNEL;
    
    public static void init()
    {
        if(MainConstants.REAL_BOT)
        {
            real();
        }
        else
        {
            practice();
        }
    }
}
