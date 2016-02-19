package org.harker.robotics.commands;

import org.harker.robotics.OI;
import org.harker.robotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author joelmanning
 * @version 0.1
 */
public class ManualDriveCommand extends Command {
	
	private static double DEADZONE = .15;
	
	public ManualDriveCommand() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	protected void execute() {
		double leftSpeed = OI.gamepad.getLeftY();
		double rightSpeed = OI.gamepad.getRightY();
		leftSpeed = (Math.abs(leftSpeed) > DEADZONE) ? leftSpeed : 0;
		rightSpeed = (Math.abs(rightSpeed) > DEADZONE) ? rightSpeed : 0;
		Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
//		Robot.drivetrain.tankDrive(OI.gamepad.getLeftY(), OI.gamepad.getRightY());
	}
	
	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	protected void end() {
		// TODO Auto-generated method stub
	}
}
