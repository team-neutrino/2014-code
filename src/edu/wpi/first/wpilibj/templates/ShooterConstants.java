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
public class ShooterConstants 
{
    private static void real()
    {
        RELEASE_PISTON_SLOT = 2;
        RELEASE_PISTON_IN_CHANNEL = 8;
        RELEASE_PISTON_OUT_CHANNEL = 2;
        WINCH_MOTOR_1_CHANNEL = 2;
        WINCH_MOTOR_2_CHANNEL = 6;
        LIMIT_SWITCH_CHANNEL = 2;
    }
    
    private static void practice() 
    {
        RELEASE_PISTON_SLOT = 3;
        RELEASE_PISTON_IN_CHANNEL = 2;
        RELEASE_PISTON_OUT_CHANNEL = 6;
        WINCH_MOTOR_1_CHANNEL = 9;
        WINCH_MOTOR_2_CHANNEL = 7;
        LIMIT_SWITCH_CHANNEL = 2;
    }
    
    public static int RELEASE_PISTON_SLOT;
    public static int RELEASE_PISTON_IN_CHANNEL;
    public static int RELEASE_PISTON_OUT_CHANNEL;
    public static int WINCH_MOTOR_1_CHANNEL;
    public static int WINCH_MOTOR_2_CHANNEL;
    public static int LIMIT_SWITCH_CHANNEL;
    
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
