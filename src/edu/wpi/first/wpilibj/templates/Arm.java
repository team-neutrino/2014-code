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
    private Victor RollerMotor1;
    private Victor RollerMotor2;
    private boolean Front;
    private boolean Down;
    private boolean SlowRollEnabled;
    private boolean SlowRollRunning;
    
    public Arm(boolean front)
    {
        int solenoidUpSlot;
        int solenoidDownSlot;
        int solenoidUpChannel;
        int solenoidDownChannel;
        int rollerChannel1;
        int rollerChannel2;
        Front = front;
        
//        if(front)
//        {
//            solenoidUpSlot = ArmConstants.SOLENOID_FRONT_UP_SLOT;
//            solenoidDownSlot = ArmConstants.SOLENOID_FRONT_DOWN_SLOT;
//            solenoidUpChannel = ArmConstants.SOLENOID_FRONT_UP_CHANNEL;
//            solenoidDownChannel = ArmConstants.SOLENOID_FRONT_DOWN_CHANNEL;
//            rollerChannel1 = ArmConstants.ROLLER_FRONT_CHANNEL;
//        }
//        else
//        {
            solenoidUpSlot = ArmConstants.SOLENOID_BACK_UP_SLOT;
            solenoidDownSlot = ArmConstants.SOLENOID_BACK_DOWN_SLOT;
            solenoidUpChannel = ArmConstants.SOLENOID_BACK_UP_CHANNEL;
            solenoidDownChannel = ArmConstants.SOLENOID_BACK_DOWN_CHANNEL;
            rollerChannel1 = ArmConstants.ROLLER_BACK_CHANNEL;
            rollerChannel2 = ArmConstants.ROLLER_FRONT_CHANNEL;
//        }

        solenoidUp = new Solenoid(solenoidUpSlot, solenoidUpChannel);
        solenoidDown = new Solenoid(solenoidDownSlot, solenoidDownChannel);
        RollerMotor1 = new Victor(rollerChannel1);
        RollerMotor2 = new Victor(rollerChannel2);
        Down = false;
        SlowRollEnabled = true;
        SlowRollRunning = true;
        armDown(false);
        rollerStopSlow();
    }
    
    public void armDown(boolean down)
    {
        Down = down;
        solenoidUp.set(down);
        solenoidDown.set(!down);
        if(SlowRollRunning)
        {
            rollerStopSlow();
        }
    }
    
    public void rollerIn()
    {
        SlowRollRunning = false;
        if(Front)
        {
            RollerMotor1.set(-1);
            RollerMotor2.set(1);
        }
        else
        {
            RollerMotor1.set(1);
            RollerMotor2.set(-1);
        }
    }
    
    public void rollerOut()
    {
        SlowRollRunning = false;
        if(Front)
        {
            RollerMotor1.set(1);
            RollerMotor2.set(-1);
        }
        else
        {
            RollerMotor1.set(-1);
            RollerMotor2.set(1);
        }
    }
    
    public void slowRollEnabled(boolean enabled)
    {
        SlowRollEnabled = enabled;
    }
    
    public void rollerStopSlow()
    {
        SlowRollRunning = true;
        if (!Down && SlowRollEnabled)
        {
            if(Front)
            {
                RollerMotor1.set(-.5);
                RollerMotor2.set(.5);
            }
            else
            {
                RollerMotor1.set(.5);
                RollerMotor2.set(-.5);
            }
        }
        else
        {
            RollerMotor1.set(0);
            RollerMotor2.set(0);
        }
    }
    
    public void rollerStop()
    {
        SlowRollRunning = false;
        RollerMotor1.set(0);
        RollerMotor2.set(0);
    }
    
    public boolean isUp()
    {
        return solenoidUp.get();
    }
}
