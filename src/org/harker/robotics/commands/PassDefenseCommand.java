package org.harker.robotics.commands;

import org.harker.robotics.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author joelmanning
 * @version 0.1
 */
public class PassDefenseCommand extends CommandGroup {
    
    public static final boolean[] passable = new boolean[InitializeSmartDashboardCommand.defenses.size()];
    
    public PassDefenseCommand(int defense) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //requires(Robot.drivetrain);
        addSequential(new DriveDistanceCommand(DriveDistanceCommand.inchesToEncoder(48/* the length of a base*/)));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //TODO this will need ways to pass different commands
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
    
    public static int getDefenseID(String defense){
        return InitializeSmartDashboardCommand.defenses.indexOf(defense);
    }
    
    public static boolean passable(int defense){
        return passable[defense];
    }
}
