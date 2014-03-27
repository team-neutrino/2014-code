/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author TimothySteward
 */
public class LightManager implements Runnable
{
//    private Solenoid FrontLight;
//    private Solenoid BackLight;
    private boolean Blinking;
    private boolean FrontOn;
    private boolean BackOn;
    
    public LightManager()
    {
//        FrontLight = new Solenoid(LightConstants.FRONT_LIGHT_SLOT, LightConstants.FRONT_LIGHT_CHANNEL);
//        BackLight = new Solenoid(LightConstants.BACK_LIGHT_SLOT, LightConstants.BACK_LIGHT_CHANNEL);
    }
    
    public void setFrontLights(boolean on)
    {
        FrontOn = on;
        if(!Blinking)
        {
//            FrontLight.set(on);
        }
    }
    
    public void setBackLights(boolean on)
    {
        BackOn = on;
        if(!Blinking)
        {
//            BackLight.set(on);
        }
    }
    
    public void blink()
    {
        if(!Blinking)
        {
            Blinking = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    public void run() 
    {
        try 
        {
            for (int i = 0; i < 20; i++) 
            {
//                FrontLight.set(true);
//                BackLight.set(false);
                Thread.sleep(25);
//                FrontLight.set(false);
//                BackLight.set(true);
                Thread.sleep(25);
            }
//            FrontLight.set(FrontOn);
//            BackLight.set(BackOn);
        } 
        catch (InterruptedException ex) 
        {
                ex.printStackTrace();
        }
        
        Blinking = false;
    }
}
