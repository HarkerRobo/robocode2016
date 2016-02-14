package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author joelmanning
 * @version 0.1
 */
public class SwitchDefenseCommand extends CommandGroup {

    public static final double DEFENSE_WIDTH = 1;
    public static final boolean RIGHT = true;
    public static final boolean LEFT = false;
    public static final double TURN_SPEED = 0.5;
    
    public SwitchDefenseCommand(boolean direction, int num) {
        addSequential(new TurnToAngleCommand(90 * sign(direction)));
        addSequential(new DriveDistanceCommand(DEFENSE_WIDTH * num));
        addSequential(new TurnToAngleCommand(0));
    }
    public int sign(boolean bool){
        if(bool){
            return 1;
        }
        return -1;
    }
}
