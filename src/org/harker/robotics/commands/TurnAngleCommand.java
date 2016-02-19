package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.harker.robotics.Robot;

/**
 *
 */
public class TurnAngleCommand extends Command {

    private double angle;
    public TurnAngleCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       (new TurnToAngleCommand((Robot.drivetrain.getHeading() + angle) % 360)).start();;
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
