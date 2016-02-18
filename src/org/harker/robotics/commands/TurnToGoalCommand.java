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
import georegression.struct.point.Point2D_I32;

/**
 * @author joelmanning
 * @version 0.1
 */
public class TurnToGoalCommand extends Command {
	private int session;
	private Image frame;
	private boolean finished = false;
	public static final double ERROR_MARGIN = 1;
    public TurnToGoalCommand() {
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
        
        List<Point2D_I32> points = ProcessingUtil.process(in);
        Robot.robot.setGoal(points);
        double pixelHeightU = points.get(0).y + points.get(1).y - points.get(2).y - points.get(3).y;
        double cameraAngleWidth = 69.73;
        double realHeightU = 14;
        double totalPixelHeight = in.getHeight();
        //System.out.println((points == null) ? 0 : points.size());
        //(distance to goal) = ((real height of U)(pixel height of the total image))/(2(pixel height of U)(tan((camera angle width)/2)))
        double inchesToGoal = (realHeightU * totalPixelHeight)/(2 * pixelHeightU * Math.tan(cameraAngleWidth/2));
        //angle to turn to goal = tan^-1((inches distance between center of image and center of U)/d)
        double centerWidth = (points.get(0).x + points.get(1).x + points.get(2).x + points.get(3).x)/4;
        double inchesFromCenter = realHeightU / pixelHeightU * (in.getWidth() / 2 - centerWidth);
        double angleToGoal = Math.atan(inchesFromCenter/inchesToGoal);
        (new TurnAngleCommand(angleToGoal)).start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
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
