package org.harker.robotics.commands;

import org.harker.robotics.OI;
import org.harker.robotics.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ManualIntakeCommand extends Command {
	private static double INTAKE_THRESHOLD = 0.5;
	private static double INTAKE_SPEED = 0.7;
	
    public ManualIntakeCommand() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.gamepad.getLeftTrigger() > INTAKE_THRESHOLD) {
    		Robot.intake.accept(SmartDashboard.getNumber("Intake Speed", 0.8));
    	}
    	else if (OI.gamepad.getButtonBumperLeftState()){
    		Robot.intake.reject(SmartDashboard.getNumber("Intake Speed", 0.8));
    	}
    	else {
    		Robot.intake.accept(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
