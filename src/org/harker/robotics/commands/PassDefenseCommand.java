package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author joelmanning
 * @version 0.1
 * A commandgroup that will execute the commands needed to get through a given defense
 */
public class PassDefenseCommand extends CommandGroup {
    
    public static final boolean[] passable = { true, false, false, true, true, false, false, true, true };
    public static final double robotLength = 12;
    public static final double platformLength = 48;
    public static final double[] defenseAdditionalLengths = { 0 };
    
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
    
    /**
     * @param defense the name of the defense
     * @return the integer representation of that defense, or -1 if it does not exist
     */
    public static int getDefenseID(String defense){
        return InitializeSmartDashboardCommand.defenses.indexOf(defense);
    }
    
    public static boolean passable(int defense){
        return passable[defense];
    }
}
