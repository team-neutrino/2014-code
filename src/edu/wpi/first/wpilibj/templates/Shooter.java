package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Team Neutrino
 */
public class Shooter implements Runnable
{
    private Solenoid ReleasePistonIn;
    private Solenoid ReleasePistonOut;
    private Victor WinchMotor1;
    private Victor WinchMotor2;
    private boolean Loading;
    private DigitalInput LimitSwitch;
    private DriverStation DriverStation;
    private DriverMessages DriverMessages;
    
    public Shooter(DriverStation driverStation, DriverMessages driverMessages)
    {
        ReleasePistonIn = new Solenoid(ShooterConstants.RELEASE_PISTON_IN_SLOT, ShooterConstants.RELEASE_PISTON_IN_CHANNEL);
        ReleasePistonOut = new Solenoid(ShooterConstants.RELEASE_PISTON_OUT_SLOT, ShooterConstants.RELEASE_PISTON_OUT_CHANNEL);
        WinchMotor1 = new Victor(ShooterConstants.WINCH_MOTOR_1_CHANNEL);
        WinchMotor2 = new Victor(ShooterConstants.WINCH_MOTOR_2_CHANNEL);
        Loading = false;
        LimitSwitch = new DigitalInput(ShooterConstants.LIMIT_SWITCH_CHANNEL);
        DriverStation = driverStation;
        DriverMessages = driverMessages;
        //shooterCock();
    }
   
    private void shooterCock()
    {
        if(!Loading)
        {
            Loading = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }
    
    public void shoot()
    {
        System.out.println(System.currentTimeMillis() + "Shoot");
        if (!Loading)
        {
            ReleasePistonIn.set(false);
            ReleasePistonOut.set(true);
            shooterCock();
        }
    }
    
    public void release()
    {
        if (!Loading)
        {
            ReleasePistonIn.set(false);
            ReleasePistonOut.set(true);
        }
    }
    
    public void run() 
    {
        try 
        {
            while (!DriverStation.isEnabled()) 
            {                
                Thread.sleep(5);
            }
            
            Thread.sleep(500);
            ReleasePistonIn.set(true);
            ReleasePistonOut.set(false);
            long startLoad = System.currentTimeMillis();
            //System.out.println(LimitSwitch.get());
            while(!LimitSwitch.get() && (System.currentTimeMillis() - startLoad < 5000))
            {
                WinchMotor1.set(-.5);
                WinchMotor2.set(-.5);
                Thread.sleep(5);
            }
            WinchMotor1.set(0);
            WinchMotor2.set(0);
            
            DriverMessages.displayShooterTimeout(System.currentTimeMillis() - startLoad > 5000);
            
            Loading = false;
        }
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
    }
    
}
