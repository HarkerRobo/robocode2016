package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author joelmanning
 * @version 0.1
 */
public class AutonomousCommand extends CommandGroup {
    public static final boolean PREFERRED_DIRECTION = SwitchDefenseCommand.LEFT;
    public  AutonomousCommand() {
    	addParallel(new ProcessImageCommand());
    	//addParallel(new ProcessImageCommand());

        int[] defenses = new int[5];
        for(int i = 0; i < 5; i++){
            defenses[i] = PassDefenseCommand.getDefenseID(SmartDashboard.getString(InitializeSmartDashboardCommand.DEFENSE + (int)(i + 1)));
        }
        int position = ((Integer)((SendableChooser)SmartDashboard.getData("Starting Robot Position")).getSelected()) - 1;
        if(position == 0){
            addSequential(new SpyAutonomousCommand());
        } else {
            int targetPos = -1;
            for(int i = 0; i < 5 && targetPos == -1; i++){
                for(int j = 1; j > -2 && targetPos == -1; j -= 2){
                    int target = position + i * j * SwitchDefenseCommand.sign(PREFERRED_DIRECTION);
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
            addSequential(new GoToShootingPositionCommand(targetPos));
            //TODO make TurnToGoal understand what angle the goal is or use processingutil
            //addParallel(new ProcessImageCommand());
            //TODO decide what we do here, how odd of an angle can we shoot from, do you just want to turn to the goal and shoot or go to the center for better chances?
            addSequential(new TurnToGoalCommand());
            addSequential(new HighGoalCommand());
        }
    }
}
