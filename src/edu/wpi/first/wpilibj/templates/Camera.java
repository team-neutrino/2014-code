/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;

/**
 *
 * @author Team Neutrino
 */
public class Camera 
{
    private AxisCamera RobotCamera;
    private Solenoid CameraLight;
    private DriverMessages DriverMessages;
    
    public Camera(DriverMessages driverMessages)
    {
        RobotCamera = AxisCamera.getInstance();
        CameraLight = new Solenoid(MainConstants.CAMERA_LIGHT_SLOT, MainConstants.CAMERA_LIGHT_CHANNEL);
        DriverMessages = driverMessages;
    }
    
    public boolean goalIsHot()
    {
        CameraLight.set(true);
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
        int tapes;
        long startTime = System.currentTimeMillis();
        while(!RobotCamera.freshImage() && (System.currentTimeMillis() - startTime < 1000))
        {
            try
            {
                Thread.sleep(1);
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        try 
        {
            ColorImage image = RobotCamera.getImage();
            image.write("color.bmp");
            //red green blue
            //BinaryImage thresholdImage = image.thresholdRGB(0, 64, 128, 255, 0, 64); //green threshold
            BinaryImage thresholdImage = image.thresholdRGB(0, 128, 192, 255, 192, 255); //blue threshold
            thresholdImage.write("threshold.bmp");
            BinaryImage rmvpartImage = thresholdImage.removeSmallObjects(true, 3);
            rmvpartImage.write("rmpart.bmp");
            //System.out.println(rmvpartImage.getNumberParticles()); 
            tapes = rmvpartImage.getNumberParticles();
            image.free();
            thresholdImage.free();
            rmvpartImage.free();
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
            DriverMessages.displayCameraError(true);
            DriverMessages.displayHot(true);
            return true;
        }
        
        DriverMessages.displayCameraError(false);
        DriverMessages.displayHot(false);
        return (tapes == 2);
    }
    
    public void setLight(boolean on)
    {
        CameraLight.set(on);
    }
}
