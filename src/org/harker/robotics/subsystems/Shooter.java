package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap;
import org.harker.robotics.commands.ManualShootCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
	private Wheel lowerShooter;
    private Wheel upperShooter;
    
    public Shooter() {
    	lowerShooter = new Wheel(RobotMap.ST_TALON_LOWER_CHANNEL);
    	upperShooter = new Wheel(RobotMap.ST_TALON_UPPER_CHANNEL);
    }
    
    public void shoot(double speed) {
    	lowerShooter.set(speed);
    	upperShooter.set(speed);
    }
    
    public void shoot(double lower, double upper) {
    	lowerShooter.set(lower);
    	upperShooter.set(upper);
    }
    
    public void toSmartDashboard() {
    	SmartDashboard.putNumber("ST Lower", lowerShooter.get());
    	SmartDashboard.putNumber("ST Upper", upperShooter.get());
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ManualShootCommand());
    }
}
