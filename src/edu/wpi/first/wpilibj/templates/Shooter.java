package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.templates.ShooterConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author team Neutrino
 */
public class Shooter implements Runnable
{
    private Solenoid ReleasePistonIn;
    private Solenoid ReleasePistonOut;
    private Victor WinchMotor1;
    private Victor WinchMotor2;
    private boolean ThreadRunning;
    private DigitalInput LimitSwitch;
    public Shooter()
   
    {
        ReleasePistonIn = new Solenoid(ShooterConstants.RELEASE_PISTON_SLOT, ShooterConstants.RELEASE_PISTON_IN_CHANNEL);
        ReleasePistonOut = new Solenoid(ShooterConstants.RELEASE_PISTON_SLOT, ShooterConstants.RELEASE_PISTON_OUT_CHANNEL);
        WinchMotor1 = new Victor(ShooterConstants.WINCH_MOTOR_1_CHANNEL);
        WinchMotor2 = new Victor(ShooterConstants.WINCH_MOTOR_2_CHANNEL);
        ThreadRunning = false;
        LimitSwitch = new DigitalInput(ShooterConstants.LIMIT_SWITCH_CHANNEL);
        shooterCock();
    }
   
    private void shooterCock()
    {
        
        if(!ThreadRunning)
        {
            ThreadRunning = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }
    
    public void shoot()
    {
        ReleasePistonIn.set(false);
        ReleasePistonOut.set(true);
        shooterCock();
    }
    
    public void run() 
    {
        try 
        {
            Thread.sleep(500);
            ReleasePistonIn.set(true);
            ReleasePistonOut.set(false);
            while(!LimitSwitch.get())
            {
                WinchMotor1.set(.2);
                WinchMotor2.set(.2);
                Thread.sleep(5);
            }
            WinchMotor1.set(0);
            WinchMotor2.set(0);
            
            ThreadRunning = false;
        }
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
    }
    
}
