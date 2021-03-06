package org.harker.robotics.commands;

import org.harker.robotics.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author joelmanning
 * @version 0.1
 */
public class DriveDistanceCommand extends Command {

    public static final double ERROR_MARGIN = 0.2;
    public static final double SLOW_DISTANCE = 1;
    private double targetDistance;
    private int direction;
    private double speed = 1.0;
    
    public DriveDistanceCommand(double speed, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        targetDistance = Math.abs(distance);
        direction = (int) Math.signum(distance);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.getLeftEncoder().reset();
        Robot.drivetrain.getRightEncoder().reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double speeds = direction * Math.min(speed, remainingDistance()/targetDistance);
        Robot.drivetrain.tankDrive(speeds, speeds);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(remainingDistance()) < ERROR_MARGIN;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    protected double currentDistance(){
        return (Robot.drivetrain.getLeftEncoder().getDistance() + Robot.drivetrain.getRightEncoder().getDistance())/2.0;
    }
    
    protected double remainingDistance()
    {
        return targetDistance - currentDistance();
    }
    
    public static double inchesToEncoder(double inches){
        //TODO figure out what units the encoder is in
        return inches;
    }
}
