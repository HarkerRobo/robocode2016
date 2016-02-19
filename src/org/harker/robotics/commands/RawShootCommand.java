package org.harker.robotics.commands;

import org.harker.robotics.OI;
import org.harker.robotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RawShootCommand extends Command {
	private static final double SHOOTING_THRESHOLD = 0.5;
	private static final double FIRING_SPEED = 1.0;
    public RawShootCommand() {
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.gamepad.getRightTrigger() >= SHOOTING_THRESHOLD) {
    		Robot.shooter.shoot(FIRING_SPEED);
    	}
    	else {
    		Robot.shooter.shoot(0);
    	}
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
