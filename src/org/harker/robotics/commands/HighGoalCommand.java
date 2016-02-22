package org.harker.robotics.commands;

import org.harker.robotics.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author joelmanning
 * @version 0.1
 */
public class HighGoalCommand extends Command {

    /**
     * @param distance the current distance from the robot to the goal
     */
    public HighGoalCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooter.shoot(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
