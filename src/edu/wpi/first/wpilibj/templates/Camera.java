/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;

/**
 *
 * @author Team Neutrino
 */
public class Camera 
{
    AxisCamera RobotCamera;
    
    public Camera()
    {
        RobotCamera = AxisCamera.getInstance();
    }
    
    public boolean goalIsHot()
    {
        int tapes;
        long startTime = System.currentTimeMillis();
        while(!RobotCamera.freshImage() && (System.currentTimeMillis() > startTime + 1000))
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
            //image.write("color.bmp");
            BinaryImage thresholdImage = image.thresholdRGB(0, 64, 128, 255, 0, 64);
            //thresholdImage.write("threshold.bmp");
            BinaryImage rmvpartImage = thresholdImage.removeSmallObjects(true, 3);
            //rmvpartImage.write("rmpart.bmp");
            //System.out.println(rmvpartImage.getNumberParticles()); 
            tapes = rmvpartImage.getNumberParticles();
            image.free();
            thresholdImage.free();
            rmvpartImage.free();
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
            return true;
        }
        return (tapes == 2);
    }
}
