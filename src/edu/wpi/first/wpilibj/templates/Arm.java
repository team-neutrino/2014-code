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
    private Solenoid ArmUp;
    private Solenoid ArmDown;
    //private Victor RollerMotor;
    
    public Arm(boolean front)
    {
        if(front)
        {
            int pistoUpChannel = ArmConstants.PISTON_FRONT_UP_CHANNEL;
            int pistonDownChannel = ArmConstants.PISTON_FRONT_DOWN_CHANNEL;
            int rollerChannel = ArmConstants.ROLLER_FRONT_CHANNEL;
        }
        else
        {
            int pistoUpChannel = ArmConstants.PISTON_BACK_UP_CHANNEL;
            int pistonDownChannel = ArmConstants.PISTON_BACK_DOWN_CHANNEL;
            int rollerChannel = ArmConstants.ROLLER_BACK_CHANNEL;
        }

        if(MainConstants.TWO_SOLENOID_SOLTS)
        {
            ArmUp = new Solenoid(ArmConstants.PISTON_SLOT, ArmConstants.PISTON_FRONT_UP_CHANNEL);
            ArmDown = new Solenoid(ArmConstants.PISTON_SLOT, ArmConstants.PISTON_FRONT_DOWN_CHANNEL);
            //RollerMotor = new Victor(rollerChannel);
        }
        else
        {
            ArmUp = new Solenoid(ArmConstants.PISTON_FRONT_UP_CHANNEL);
            ArmDown = new Solenoid(ArmConstants.PISTON_FRONT_DOWN_CHANNEL);
            //RollerMotor = new Victor(rollerChannel);
        }
    }
    
    public void armUp(boolean up)
    {
        ArmUp.set(up);
        ArmDown.set(!up);
    }
    
    public void rollerForward()
    {
        //RollerMotor.set(1);
    }
    
    public void rollerBackward()
    {
        //RollerMotor.set(-1);
    }
    
    public void rollerStop()
    {
        //RollerMotor.set(0);
    }
}
