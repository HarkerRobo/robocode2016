package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap;
import org.harker.robotics.commands.ManualDriveCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A drivetrain that manages four PID wheels
 * 
 * @author joelmanning
 * @version 0.1
 */
public class PIDDriveTrain extends Subsystem
{

    private static Wheel leftBack;
    private static Wheel rightBack;
    private static Wheel leftFront;
    private static Wheel rightFront;
    
    private static Encoder leftEncoder;
    private static Encoder rightEncoder;

    /**
     * Creates a new PIDDriveTrain with wheels at the default ports. 
     */
    public PIDDriveTrain()
    {
    	leftEncoder = new Encoder(RobotMap.DT_ENCODER_L_CHANNEL_A, RobotMap.DT_ENCODER_L_CHANNEL_B);
//    	rightEncoder = new Encoder(RobotMap.DT_ENCODER_R_CHANNEL_A, RobotMap.DT_ENCODER_R_CHANNEL_B);
    	leftBack = new Wheel(RobotMap.DT_TALON_LB_CHANNEL);
        rightBack = new Wheel(RobotMap.DT_TALON_RB_CHANNEL);
        leftFront = new Wheel(RobotMap.DT_TALON_LF_CHANNEL);
        rightFront = new Wheel(RobotMap.DT_TALON_RF_CHANNEL);
    }

    /**
     * Sets the speeds of all four wheels. 
     * 
     * @param leftBack
     *            the speed for the left back wheel
     * @param rightBack
     *            the speed for the right back wheel
     * @param leftFront
     *            the speed for the left front wheel
     * @param rightFront
     *            the speed for the right front wheel
     */
    public void drive(int leftBack, int rightBack, int leftFront, int rightFront)
    {
        PIDDriveTrain.leftBack.set(leftBack);
        PIDDriveTrain.rightBack.set(rightBack);
        PIDDriveTrain.leftFront.set(leftFront);
        PIDDriveTrain.rightFront.set(rightFront);
    }

    /**
     * Sets the speed of the right wheels together and left wheels together. 
     * 
     * @param leftSpeed
     *            the speed for the two right wheels
     * @param rightSpeed
     *            the speed for the two left wheels
     */
    public void tankDrive(double leftSpeed, double rightSpeed)
    {
        setLeft(leftSpeed);
        setRight(rightSpeed);
    }

    /**
     * 
     */
    public void initDefaultCommand()
    {
    	setDefaultCommand(new ManualDriveCommand());
    }

    /**
     * Sets the speed of the two left wheels. 
     * 
     * @param speed
     *            the speed for the wheels
     */
    public void setLeft(double speed)
    {
        leftBack.set(speed);
        leftFront.set(speed);
    }

    /**
     * Sets the speed of the two right wheels. 
     * 
     * @param speed
     *            the speed for the wheels
     */
    public void setRight(double speed)
    {
        rightBack.set(speed);
        rightFront.set(speed);
    }

    /**
     * Sets the speed of the two front wheels. 
     * 
     * @param speed
     *            the speed for the wheels
     */
    public void setFront(double speed)
    {
        leftFront.set(speed);
        rightFront.set(speed);
    }

    /**
     * Sets the speed of the two back wheels. 
     * 
     * @param speed
     *            the speed for the wheels
     */
    public void setBack(double speed)
    {
        leftBack.set(speed);
        rightBack.set(speed);
    }
    
    public Encoder getLeftEncoder(){
        return leftEncoder;
    }
    
    public Encoder getRightEncoder(){
        return rightEncoder;
    }
}
