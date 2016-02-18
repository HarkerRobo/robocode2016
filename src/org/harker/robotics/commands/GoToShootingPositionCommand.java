package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author joelmanning
 * @version 0.1
 */
public class GoToShootingPositionCommand extends CommandGroup {
    
    public static final double[] angles = { 65.9359, 53.3579, 24.2077, -24.0023, -53.2699};
    public static final double[] distances = { 145.92, 99.696, 65.2366, 65.132, 99.4907};
    
    public  GoToShootingPositionCommand(int positionPassed) {
        addSequential(new TurnToAngleCommand(angles[positionPassed]));
        addSequential(new DriveDistanceCommand(distances[positionPassed]));
        addSequential(new TurnToAngleCommand(0));
    }
}
