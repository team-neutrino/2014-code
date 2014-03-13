/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author TimothySteward
 */
public class LightConstants 
{
    private static void real()
    {
        BACK_LIGHT_SLOT = 0;
        FRONT_LIGHT_SLOT = 0;
        BACK_LIGHT_CHANNEL = 0;
        FRONT_LIGHT_CHANNEL = 0;
    }
    
    private static void practice()
    {
        BACK_LIGHT_SLOT = 0;
        FRONT_LIGHT_SLOT = 0;
        BACK_LIGHT_CHANNEL = 0;
        FRONT_LIGHT_CHANNEL = 0;
    }
    
    public static int BACK_LIGHT_SLOT;
    public static int FRONT_LIGHT_SLOT;
    public static int BACK_LIGHT_CHANNEL;
    public static int FRONT_LIGHT_CHANNEL;
    
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
