/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Tony Milosch
 */
public class Drive 
{
    Victor DriveFL;
    Victor DriveFR;
    Victor DriveBL;
    Victor DriveBR;
    
    Solenoid DriveFrontIn;
    Solenoid DriveFrontOut;
    Solenoid DriveBackIn;
    Solenoid DriveBackOut;
    
    public Drive()
    {
        DriveFL = new Victor(DriveConstants.DRIVE_FL_CHANNEL);
        DriveFR = new Victor(DriveConstants.DRIVE_FR_CHANNEL);
        DriveBL = new Victor(DriveConstants.DRIVE_BL_CHANNEL);
        DriveBR = new Victor(DriveConstants.DRIVE_BR_CHANNEL);
        
        DriveFrontIn = new Solenoid(DriveConstants.DRIVE_SOLENOID_SLOT, DriveConstants.DRIVE_SOLENOID_FRONT_IN_CHANNEL);
        DriveFrontOut = new Solenoid(DriveConstants.DRIVE_SOLENOID_SLOT, DriveConstants.DRIVE_SOLENOID_FRONT_OUT_CHANNEL);
        DriveBackIn  = new Solenoid(DriveConstants.DRIVE_SOLENOID_SLOT, DriveConstants.DRIVE_SOLENOID_BACK_IN_CHANNEL);
        DriveBackOut = new Solenoid(DriveConstants.DRIVE_SOLENOID_SLOT, DriveConstants.DRIVE_SOLENOID_BACK_OUT_CHANNEL);
        
    }
    
    public void traction(boolean isDown)
    {
        DriveFrontIn.set(!isDown);
        DriveFrontOut.set(isDown);
        DriveBackIn.set(!isDown);
        DriveBackOut.set(isDown);
    }
    
    public void goLeft(double speed)
    {
        DriveFL.set(-Math.abs(speed)*speed);
        DriveBL.set(-Math.abs(speed)*speed);
    }
    
    public void goRight(double speed)
    {
        DriveFR.set(Math.abs(speed)*speed);
        DriveBR.set(Math.abs(speed)*speed);
    }
}
