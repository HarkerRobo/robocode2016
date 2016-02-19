package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap;
import org.harker.robotics.commands.ManualIntakeCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {
    private Wheel intakeWheel;
    
    public Intake() {
    	intakeWheel = new Wheel(RobotMap.IN_TALON_PICKUP);
    }
    
    public void intakeRaw(double speed) {
    	intakeWheel.set(speed);
    }
    
    public void accept(double speed) {
    	intakeRaw(-speed);
    }
    
    public void reject(double speed) {
    	intakeRaw(speed);
    }
    

    public void initDefaultCommand() {
        setDefaultCommand(new ManualIntakeCommand());
    }
    
    public void toSmartDashboard() {
    	SmartDashboard.putNumber("IN Intake", intakeWheel.get());
    }
}

