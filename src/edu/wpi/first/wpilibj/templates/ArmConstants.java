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
        SOLENOID_BACK_UP_CHANNEL = 0;
        SOLENOID_FRONT_UP_CHANNEL = 1;
        SOLENOID_BACK_DOWN_CHANNEL = 0;
        SOLENOID_FRONT_DOWN_CHANNEL = 5;
        PISTON_SLOT = 3;

        ROLLER_FRONT_CHANNEL = 0;
        ROLLER_BACK_CHANNEL = 0;
    }
    
    private static void practice()
    {
        SOLENOID_BACK_UP_CHANNEL = 0;
        SOLENOID_FRONT_UP_CHANNEL = 1;
        SOLENOID_BACK_DOWN_CHANNEL = 0;
        SOLENOID_FRONT_DOWN_CHANNEL = 5;
        PISTON_SLOT = 3;

        ROLLER_FRONT_CHANNEL = 0;
        ROLLER_BACK_CHANNEL = 0;
    }
    
    public static int SOLENOID_BACK_UP_CHANNEL;
    public static int SOLENOID_FRONT_UP_CHANNEL;
    public static int SOLENOID_BACK_DOWN_CHANNEL;
    public static int SOLENOID_FRONT_DOWN_CHANNEL;
    public static int PISTON_SLOT;
    
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
