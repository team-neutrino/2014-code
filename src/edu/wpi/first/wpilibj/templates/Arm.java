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
    private Victor RollerMotor;
    
    public Arm(int pistonChannel, int rollerChannel)
    {
        //TODO add slot and channel
        
        ArmUp = new Solenoid(ArmConstants.PISTON_SLOT, pistonChannel);
        ArmDown = new Solenoid(ArmConstants.PISTON_SLOT, pistonChannel);
        RollerMotor = new Victor(rollerChannel);
    }
    
    public void armUp(boolean up)
    {
        ArmUp.set(up);
        ArmDown.set(!up);
    }
    
    public void rollerForward()
    {
        RollerMotor.set(1);
    }
    
    public void rollerBackward()
    {
        RollerMotor.set(-1);
    }
    
    public void rollerStop()
    {
        RollerMotor.set(0);
    }
}
