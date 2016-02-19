package org.harker.robotics.commands;

import org.harker.robotics.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author joelmanning
 * @version 0.1
 */
public class TurnToAngleCommand extends Command {

    public static final double ERROR_MARGIN = 0.2;
    private double targetAngle;
    
    public TurnToAngleCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        targetAngle = angle % 360;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double speed = (targetAngle - Robot.gyro.getAngle())/180;
        (new TankDriveCommand(speed, -speed)).start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(targetAngle - Robot.gyro.getAngle()) < ERROR_MARGIN;
    }

    // Called once after isFinished returns true
    protected void end() {
        (new TankDriveCommand(0, 0)).start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
