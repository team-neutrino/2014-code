/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 *
 * @author Team Neutrino
 */
public class DriverMessages
{
    private int AutoMode;
    private DriverStationLCD DriverStation;
    private LightManager LightManager;
    
    public DriverMessages(int autoMode, LightManager lightManager)
    {
        LightManager = lightManager;
        DriverStation = DriverStationLCD.getInstance();
        AutoMode = autoMode;
        updateLCD();
    }
    
    public void updateAutoMode(int autoMode)
    {
        AutoMode = autoMode;
        updateLCD();
    }
    
    private void updateLCD()
    {
        //autonomous display
        String autoDescription;
        switch(AutoMode)
        {
            case 1:
                autoDescription = "Shoot Hot & Mobility";
                break;
            case 2:
                autoDescription = "Shoot Hot";
                break;
            case 3:
                autoDescription = "Mobility";
                break;
            case 4:
                autoDescription = "Shoot 2 & Mobility";
                break;
            case 5:
                autoDescription = "(don't use) Shoot 3 & Mobility";
                break;
            case 6:
                autoDescription = "Do Nothing";
                break;
            case 7:
                autoDescription = "(don't use) Low Goal Hot";
                break;
            case 8:
                autoDescription = "Not Created";
                break;
            case 9:
                autoDescription = "Not Created";
                break;
            case 10:
                autoDescription = "Not Created";
                break;
            default:
                autoDescription = "Auto Error, Check the code";
                break;
        }
        DriverStation.println(DriverStationLCD.Line.kUser1, 1, "Auto " + AutoMode + "                     ");
        DriverStation.println(DriverStationLCD.Line.kUser2, 1, autoDescription+ "                     ");
        DriverStation.updateLCD();
    }
    
    public void displayHot(boolean hot)
    {
        LightManager.setFrontLights(hot);
        LightManager.setBackLights(!hot);
        if(hot)
        {
            DriverStation.println(DriverStationLCD.Line.kUser1, 1, "Auto " + AutoMode + " Hot                     ");
        }
        else
        {
            DriverStation.println(DriverStationLCD.Line.kUser1, 1, "Auto " + AutoMode + " Cold                     ");
        }
        DriverStation.updateLCD();
    }
    
    public void displayShooterTimeout(boolean display)
    {
        if(display)
        {
            DriverStation.println(DriverStationLCD.Line.kUser4, 1, "Shooter Timedout                     ");
            LightManager.blink();
        }
        else
        {
            DriverStation.println(DriverStationLCD.Line.kUser4, 1, "                     ");
        }
        DriverStation.updateLCD();
    }
    
    public void displayCameraError(boolean display)
    {
        if(display)
        {
            DriverStation.println(DriverStationLCD.Line.kUser3, 1, "Camera Error                     ");
        }
        else 
        {
            DriverStation.println(DriverStationLCD.Line.kUser3, 1, "Camera Success                     ");
        }
        DriverStation.updateLCD();
    }
}
