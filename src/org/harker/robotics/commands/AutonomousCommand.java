package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
    
    public  AutonomousCommand() {
    	//addParallel(new ProcessImageCommand());
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
        int[] defenses = new int[5];
        for(int i = 0; i < 5; i++){
            defenses[i] = PassDefenseCommand.getDefenseID(SmartDashboard.getString(InitializeSmartDashboardCommand.DEFENSE + (int)(i + 1)));
        }
        int position = Integer.parseInt(SmartDashboard.getString("Starting Robot Position"));
        //TODO choose a defense
        addSequential(new SwitchDefenseCommand(false, 0));
        //TODO find the defense you are in front of
        addSequential(new PassDefenseCommand(0));
        //TODO make TurnToGoal understand what angle the goal is or use processingutil
        addSequential(new TurnToGoalCommand());
    }
}
