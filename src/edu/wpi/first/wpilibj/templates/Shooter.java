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
    private Solenoid EjectPistonOut;
    private Solenoid EjectPistonIn;
    private Victor WinchMotor1;
    private Victor WinchMotor2;
    private boolean Loading;
    
    private DigitalInput LimitSwitch;
    
    private DigitalInput BeamBreak;
    private Solenoid BeamBreakPower;
    
    private DriverStation DriverStation;
    private DriverMessages DriverMessages;
    private boolean Loaded;
    private boolean Lob;
    
    public Shooter(DriverStation driverStation, DriverMessages driverMessages)
    {
        ReleasePistonIn = new Solenoid(ShooterConstants.RELEASE_PISTON_IN_SLOT, ShooterConstants.RELEASE_PISTON_IN_CHANNEL);
        ReleasePistonOut = new Solenoid(ShooterConstants.RELEASE_PISTON_OUT_SLOT, ShooterConstants.RELEASE_PISTON_OUT_CHANNEL);
        
        EjectPistonIn = new  Solenoid(ShooterConstants.EJECT_PISTON_IN_SLOT, ShooterConstants.EJECT_PISTON_IN_CHANNEL);
        EjectPistonOut = new Solenoid(ShooterConstants.EJECT_PISTON_OUT_SLOT, ShooterConstants.EJECT_PISTON_OUT_CHANNEL);
        
        WinchMotor1 = new Victor(ShooterConstants.WINCH_MOTOR_1_CHANNEL);
        WinchMotor2 = new Victor(ShooterConstants.WINCH_MOTOR_2_CHANNEL);
        Loading = false;
        
        LimitSwitch = new DigitalInput(ShooterConstants.LIMIT_SWITCH_CHANNEL);
        
        BeamBreak = new DigitalInput(ShooterConstants.BEAM_BREAK_CHANNEL);
        BeamBreakPower = new Solenoid(ShooterConstants.BEAM_BREAK_POWER_SLOT, ShooterConstants.BEAM_BREAK_POWER_CHANNEL);
        
        BeamBreakPower.set(true);
        
        DriverStation = driverStation;
        DriverMessages = driverMessages;
        
        //check if shooter loaded based on limit switch
        Loaded = LimitSwitch.get();
        
        //shooterCock();
        
        Lob = false;
    }
    
    public void eject(boolean out)
    {
        if(!Lob)
        {
            EjectPistonIn.set(!out);
            EjectPistonOut.set(out);
        }
    }
    
    public void shootCock()
    {
        //shoot and automatically recock the shooter
        if (!Loading)
        {
            Lob = false;
            Loading = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }
    
    public void shootLobCock()
    {
        //shoot and automatically recock the shooter
        if (!Loading)
        {
            Lob = true;
            Loading = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }
    
    public void release()
    {
        //release the shooter without recocking
        if (!Loading)
        {
            ReleasePistonIn.set(false);
            ReleasePistonOut.set(true);
        }
        
        Loaded = false;
    }
    
    public void manualCock(boolean cock)
    {
        if(!Loading)
        {
            if(cock)
            {
                ReleasePistonIn.set(true);
                ReleasePistonOut.set(false);
                WinchMotor1.set(1);
                WinchMotor2.set(1);
            }
            else
            {
                WinchMotor1.set(0);
                WinchMotor2.set(0);
            }
        }
    }
    
    public void run() 
    {
        try 
        {
            
            //dont winch
            WinchMotor1.set(0);
            WinchMotor2.set(0);
            
            //shoot
            if(Loaded)
            {
            //release shooter
                ReleasePistonIn.set(false);
                ReleasePistonOut.set(true);
                
                if(Lob)
                {
                    EjectPistonOut.set(true);
                    EjectPistonIn.set(false);
                }
                
                Thread.sleep(1000);
            }
            
            
            ReleasePistonIn.set(true);
            ReleasePistonOut.set(false);
            
            //don't start load timeout timer when robot diabled
            while (!DriverStation.isEnabled()) 
            {
                Thread.sleep(5);
            }
            
            //cock
            long startLoad = System.currentTimeMillis();
            while(!LimitSwitch.get() && !BeamBreak.get() && (System.currentTimeMillis() - startLoad < 3000))
            {
                if(DriverStation.isAutonomous())
                {
                    WinchMotor1.set(1); 
                    WinchMotor2.set(1);
                }
                else
                {
                    WinchMotor1.set(.7); 
                    WinchMotor2.set(.7);
                }
                Thread.sleep(5);
                //System.out.println("Cocking: " + (System.currentTimeMillis() - startLoad));
            }
            
            while(!LimitSwitch.get() && (System.currentTimeMillis() - startLoad < 10000))
            {
                WinchMotor1.set(.5);
                WinchMotor2.set(.5);
                Thread.sleep(1);
                //System.out.println("Cocking: " + (System.currentTimeMillis() - startLoad));
            }
            
            WinchMotor1.set(0);
            WinchMotor2.set(0);
            
            EjectPistonOut.set(false);
            EjectPistonIn.set(true);
            Lob = false;
            
            DriverMessages.displayShooterTimeout(System.currentTimeMillis() - startLoad > 10000);
            
            Loaded = true;
            Loading = false;
        }
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
    }
    
}
