package org.harker.robotics.harkerrobolib.commands;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class InitializeSmartDashboardCommand extends Command
{
    public static List<String> defenses = Arrays.asList("portcullis",
            "cheval de frise", "moat", "ramparts", "drawbridge", "sallyport",
            "rock wall", "rough terrain", "low bar");

    public InitializeSmartDashboardCommand()
    {
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        SendableChooser defense1 = new SendableChooser();
        defense1.addDefault("low bar", "low bar");
        SmartDashboard.putData("Defense 1", defense1);
        for(int i = 2; i < 6; i++){
            SendableChooser defense = new SendableChooser();
            for(String s: defenses){
                defense.addObject(s, s);
            }
            SmartDashboard.putData("Defense " + i, defense);
        }
        SendableChooser position = new SendableChooser();
        for(int i = 1; i < 6; i++){
            position.addObject("" + i, new Integer(i));
        }
        SmartDashboard.putData("Starting Robot Position", position);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return true;
    }

    // Called once after isFinished returns true
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}