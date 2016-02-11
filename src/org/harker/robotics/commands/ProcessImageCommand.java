package org.harker.robotics.commands;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.harker.robotics.Robot;
import org.harker.robotics.vision.ProcessingUtil;

import boofcv.struct.PointIndex_I32;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ProcessImageCommand extends Command {
	private int session;
	private Image frame;
    public ProcessImageCommand() {
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxStartAcquisition(session);
    } 

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	NIVision.IMAQdxGrab(session, frame, 1);
        NIVision.imaqWriteFile(frame, "output.png", null);
        BufferedImage in = null;
        
        try {
			in = ImageIO.read(new File("output.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        CameraServer.getInstance().setImage(frame);
        
        List<PointIndex_I32> points = ProcessingUtil.process(in);
        System.out.println((points == null) ? 0 : points.size());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ! Robot.robot.isAutonomous();
    }

    // Called once after isFinished returns true
    protected void end() {
    	NIVision.IMAQdxStopAcquisition(session);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
