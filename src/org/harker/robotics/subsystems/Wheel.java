package org.harker.robotics.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wheel extends Subsystem {
    private static Talon wheelTalon;
    
    public Wheel(int channel) {
    	wheelTalon = new Talon(channel); 
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(double speed) {
    	wheelTalon.set(speed);
    }
}

