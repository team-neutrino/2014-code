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
    public DriverMessages(int autoMode)
    {
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
                autoDescription = "Shoot 3 & Mobility";
                break;
            case 6:
                autoDescription = "Auto 6 Description";
                break;
            case 7:
                autoDescription = "Auto 7 Description";
                break;
            case 8:
                autoDescription = "Auto 8 Description";
                break;
            case 9:
                autoDescription = "Auto 9 Description";
                break;
            case 10:
                autoDescription = "Auto 10 Description";
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
            DriverStation.println(DriverStationLCD.Line.kUser3, 1, "Shooter Timeout                     ");
            DriverStation.println(DriverStationLCD.Line.kUser3, 1, "                     ");
        }
        DriverStation.updateLCD();
    }
}
