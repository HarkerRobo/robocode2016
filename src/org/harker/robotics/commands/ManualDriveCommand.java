package org.harker.robotics.commands;

import org.harker.robotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author joelmanning
 * @version 0.1
 */
public class ManualDriveCommand extends Command {

    public ManualDriveCommand() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        (new TankDriveCommand(Robot.oi.getGamepad().getLeftY(), Robot.oi.getGamepad().getRightY())).start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
