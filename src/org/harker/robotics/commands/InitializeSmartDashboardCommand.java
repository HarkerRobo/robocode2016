package org.harker.robotics.commands;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author joelmanning
 * @verision 0.1
 */
public class InitializeSmartDashboardCommand extends Command
{
    public static List<String> defenses = Arrays.asList("Low Bar", "Portcullis",
            "Cheval de Frise", "Moat", "Ramparts", "Drawbridge", "Sallyport",
            "Rock Wall", "Rough Terrain");
    public static final String DEFENSE = "Dfs";

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
        SendableChooser chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new AutonomousCommand());
        SmartDashboard.putData("Auto mode", chooser);
        SendableChooser defense1 = new SendableChooser();
        defense1.addDefault("Low Bar", "Low Bar");
        SmartDashboard.putData(DEFENSE + " 1", defense1);
        for(int i = 2; i < 6; i++){
            SendableChooser defense = new SendableChooser();
            for(String s: defenses){
                defense.addObject(s, s);
            }
            SmartDashboard.putData(DEFENSE + " " + i, defense);
        }
        SendableChooser position = new SendableChooser();
        position.addObject("Spy", new Integer(0));
        for(int i = 1; i < 6; i++){
            position.addObject("" + i, new Integer(i));
        }
        SmartDashboard.putData("Starting Robot Position", position);
        SmartDashboard.putNumber("Lower Firing Speed", 1.0);
        SmartDashboard.putNumber("Upper Firing Speed", 1.0);
        SmartDashboard.putNumber("Intake Speed", 0.8);
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
