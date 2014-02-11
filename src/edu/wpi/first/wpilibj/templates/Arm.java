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
 * @author Team Neutrino
 */
public class Arm 
{
    private Solenoid solenoidUp;
    private Solenoid solenoidDown;
    private Victor RollerMotor;
    private boolean Front;
    
    public Arm(boolean front)
    {
        int solenoidUpChannel;
        int solenoidDownChannel;
        int rollerChannel;
        Front = front;
        
        if(front)
        {
            solenoidUpChannel = ArmConstants.SOLENOID_FRONT_UP_CHANNEL;
            solenoidDownChannel = ArmConstants.SOLENOID_FRONT_DOWN_CHANNEL;
            rollerChannel = ArmConstants.ROLLER_FRONT_CHANNEL;
        }
        else
        {
            solenoidUpChannel = ArmConstants.SOLENOID_BACK_UP_CHANNEL;
            solenoidDownChannel = ArmConstants.SOLENOID_BACK_DOWN_CHANNEL;
            rollerChannel = ArmConstants.ROLLER_BACK_CHANNEL;
        }

        if(MainConstants.TWO_SOLENOID_SOLTS)
        {
            solenoidUp = new Solenoid(ArmConstants.PISTON_SLOT, solenoidUpChannel);
            solenoidDown = new Solenoid(ArmConstants.PISTON_SLOT, solenoidDownChannel);
        }
        else
        {
            solenoidUp = new Solenoid(solenoidUpChannel);
            solenoidDown = new Solenoid(solenoidDownChannel);
        }
        RollerMotor = new Victor(rollerChannel);
    }
    
    public void armUp(boolean up)
    {
        solenoidUp.set(up);
        solenoidDown.set(!up);
    }
    
    public void rollerForward()
    {
        if(Front)
        {
            RollerMotor.set(-1);
        }
        else
        {
            RollerMotor.set(1);
        }
    }
    
    public void rollerBackward()
    {
        if(Front)
        {
            RollerMotor.set(1);
        }
        else
        {
            RollerMotor.set(-1);
        }
    }
    
    public void rollerStop()
    {
        RollerMotor.set(0);
    }
    
    public boolean isUp()
    {
        return solenoidUp.get();
    }
}
