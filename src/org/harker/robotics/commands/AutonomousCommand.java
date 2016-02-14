package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

    public static final boolean PREFERRED_DIRECTION = SwitchDefenseCommand.LEFT;
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
        int targetPos = -1;
        for(int i = 0; i < 5 && targetPos == -1; i++){
            for(int j = 1; j > -2 && targetPos == -1; j -= 2){
                int target = position + SwitchDefenseCommand.sign(PREFERRED_DIRECTION);
                if(target > -1 && target < 6){
                    if(PassDefenseCommand.passable(defenses[target])){
                        targetPos = target;
                    }
                }
            }
        }
        int move = targetPos - position;
        addSequential(new SwitchDefenseCommand(Math.signum(move) == 1.0, Math.abs(move)));
        addSequential(new PassDefenseCommand(defenses[targetPos]));
        //TODO make TurnToGoal understand what angle the goal is or use processingutil
        addSequential(new TurnToGoalCommand());
    }
}
