package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wheel extends Subsystem {
	private CANTalon wheelTalon;
	private boolean isReversed;
    
    public Wheel(int channel) {
    	wheelTalon = new CANTalon(channel);
    	isReversed = false;
    }
    
    public Wheel(int channel, boolean reversed) {
    	wheelTalon = new CANTalon(channel);
    	isReversed = reversed;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(double speed) {
    	double spd = (isReversed) ? -speed : speed;
    	wheelTalon.set(scale(wheelTalon.get(), spd));
    }
    
    public double get() {
    	return wheelTalon.get();
    }
    
    /**
     * Finds the amount to scale a wheel to get to the target.
     * 
     * @param current
     *            the actual speed of the wheel
     * @param target
     *            the target speed for the wheel to reach
     * @return the the speed the wheel should be set to
     */
    private double scale(double current, double target)
    {
        if (Math.abs(target - current) <= RobotMap.DT_THRESHOLD)
        {
            return target;
        }
        else
            return current + RobotMap.DT_MAX_ACCELERATION
                    * Math.signum(target - current);
    }
}

