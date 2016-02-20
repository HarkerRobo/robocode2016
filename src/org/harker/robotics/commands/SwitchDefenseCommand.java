package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author joelmanning
 * @version 0.1
 */
public class SwitchDefenseCommand extends CommandGroup {

    public static final double DEFENSE_WIDTH = DriveDistanceCommand.inchesToEncoder(50);
    public static final boolean RIGHT = true;
    public static final boolean LEFT = false;
    public static final double TURN_SPEED = 0.5;
    
    public SwitchDefenseCommand(boolean direction, int num) {
        addSequential(new TurnToAngleCommand(90 * sign(direction)));
        addSequential(new DriveDistanceCommand(1.0, DEFENSE_WIDTH * num));
        addSequential(new TurnToAngleCommand(0));
    }
    public static int sign(boolean bool){
        if(bool){
            return 1;
        }
        return -1;
    }
}
