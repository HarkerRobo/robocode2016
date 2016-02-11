package org.harker.robotics.commands;

import org.harker.robotics.OI;
import org.harker.robotics.Robot;
import org.harker.robotics.subsystems.PIDDriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class ManualDriveCommand extends Command {
	
	public ManualDriveCommand() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	protected void execute() {
		Robot.drivetrain.tankDrive(OI.gamepad.getLeftY(), OI.gamepad.getRightY());
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
