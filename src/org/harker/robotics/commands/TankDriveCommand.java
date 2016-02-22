package org.harker.robotics.commands;

import org.harker.robotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author joelmanning
 * @version 0.1
 */
public class TankDriveCommand extends Command {
	
	private boolean finished = true;
	private double leftSpeed;
	private double rightSpeed;
	
    public TankDriveCommand(double left, double right) {
    	requires(Robot.drivetrain);
    	leftSpeed = left;
    	rightSpeed = right;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
