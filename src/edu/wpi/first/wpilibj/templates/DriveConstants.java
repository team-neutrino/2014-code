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
public class DriveConstants 
{
    private static void real()
    {
        DRIVE_FL_CHANNEL = 4;
        DRIVE_FR_CHANNEL = 1;
        DRIVE_BL_CHANNEL = 7;
        DRIVE_BR_CHANNEL = 10;

        DRIVE_SOLENOID_SLOT = 2;
        DRIVE_SOLENOID_OUT_CHANNEL = 1;
        DRIVE_SOLENOID_IN_CHANNEL = 7;
    }
    
    private static void practice()
    {
        DRIVE_FL_CHANNEL = 4;
        DRIVE_FR_CHANNEL = 8;
        DRIVE_BL_CHANNEL = 2;
        DRIVE_BR_CHANNEL = 5;

        DRIVE_SOLENOID_SLOT = 3;
        DRIVE_SOLENOID_OUT_CHANNEL = 8;
        DRIVE_SOLENOID_IN_CHANNEL = 4;
    }
    
    public static int DRIVE_FL_CHANNEL;
    public static int DRIVE_FR_CHANNEL;
    public static int DRIVE_BL_CHANNEL;
    public static int DRIVE_BR_CHANNEL;
    
    public static int DRIVE_SOLENOID_SLOT;
    public static int DRIVE_SOLENOID_OUT_CHANNEL;
    public static int DRIVE_SOLENOID_IN_CHANNEL;
    
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
