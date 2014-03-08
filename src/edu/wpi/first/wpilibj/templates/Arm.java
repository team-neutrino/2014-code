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
        int solenoidUpSlot;
        int solenoidDownSlot;
        int solenoidUpChannel;
        int solenoidDownChannel;
        int rollerChannel;
        Front = front;
        
        if(front)
        {
            solenoidUpSlot = ArmConstants.SOLENOID_FRONT_UP_SLOT;
            solenoidDownSlot = ArmConstants.SOLENOID_FRONT_DOWN_SLOT;
            solenoidUpChannel = ArmConstants.SOLENOID_FRONT_UP_CHANNEL;
            solenoidDownChannel = ArmConstants.SOLENOID_FRONT_DOWN_CHANNEL;
            rollerChannel = ArmConstants.ROLLER_FRONT_CHANNEL;
        }
        else
        {
            solenoidUpSlot = ArmConstants.SOLENOID_BACK_UP_SLOT;
            solenoidDownSlot = ArmConstants.SOLENOID_BACK_DOWN_SLOT;
            solenoidUpChannel = ArmConstants.SOLENOID_BACK_UP_CHANNEL;
            solenoidDownChannel = ArmConstants.SOLENOID_BACK_DOWN_CHANNEL;
            rollerChannel = ArmConstants.ROLLER_BACK_CHANNEL;
        }

        solenoidUp = new Solenoid(solenoidUpSlot, solenoidUpChannel);
        solenoidDown = new Solenoid(solenoidDownSlot, solenoidDownChannel);
        RollerMotor = new Victor(rollerChannel);
    }
    
    public void armDown(boolean up)
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
    
    public void rollerSlow()
    {
        if(Front)
        {
            RollerMotor.set(-.5);
        }
        else
        {
            RollerMotor.set(.5);
        }
    }
    
    public boolean isUp()
    {
        return solenoidUp.get();
    }
}
