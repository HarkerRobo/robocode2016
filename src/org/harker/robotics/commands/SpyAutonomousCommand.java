package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author joelmanning
 * @version
 */
public class SpyAutonomousCommand extends CommandGroup {
    
    public  SpyAutonomousCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        //TODO think about and then code what we will do as a spy
    }
}
