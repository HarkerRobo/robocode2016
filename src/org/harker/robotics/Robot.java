import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.harker.robotics.OI;
import org.harker.robotics.commands.AutonomousCommand;
import org.harker.robotics.commands.ExampleCommand;
import org.harker.robotics.commands.InitializeSmartDashboardCommand;
import org.harker.robotics.subsystems.PIDDriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import georegression.struct.point.Point2D_I32;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final PIDDriveTrain drivetrain = new PIDDriveTrain();
	public static OI oi;
	private InitializeSmartDashboardCommand initSD;
	public static Robot robot;
    private Command autonomousCommand;
    public static Gyro gyro;
    private List<Point2D_I32> currentGoal = new ArrayList<Point2D_I32>();
    private Object lock = new Object();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	robot = this;
		oi = new OI();
        initSD = new InitializeSmartDashboardCommand();
        gyro = new ADXRS450_Gyro();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	robot = this;
        initSD.start();
    	autonomousCommand = (Command) SmartDashboard.getData("Auto mode");
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	robot = this;
        if (autonomousCommand != null) autonomousCommand.cancel();
        initSD.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void setGoal(List<Point2D_I32> goal){
        synchronized(lock){
            currentGoal = goal;
        }
    }
    
    public List<Point2D_I32> getGoal(){
        synchronized(lock){
            return currentGoal;
        }
    }
}
