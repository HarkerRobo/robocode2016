package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap.DT;

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

    /**
     * creates a new PIDDriveTrain with wheels at the default ports
     */
    public PIDDriveTrain()
    {
        leftBack = new Wheel(DT.TALON_LB_CHANNEL, DT.LB_ENCODER_A_CHANNEL,
                DT.LB_ENCODER_B_CHANNEL);
        rightBack = new Wheel(DT.TALON_RB_CHANNEL, DT.RB_ENCODER_A_CHANNEL,
                DT.RB_ENCODER_B_CHANNEL);
        leftFront = new Wheel(DT.TALON_LF_CHANNEL, DT.LF_ENCODER_A_CHANNEL,
                DT.LF_ENCODER_B_CHANNEL);
        rightFront = new Wheel(DT.TALON_RF_CHANNEL, DT.RF_ENCODER_A_CHANNEL,
                DT.RF_ENCODER_B_CHANNEL);
    }

    /**
     * sets the speeds of all four wheels
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
     * sets the speed of the right wheels together and left wheels together
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
     * @deprecated this method is not currently being used
     */
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * sets the speed of the two left wheels
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
     * sets the speed of the two right wheels
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
     * sets the speed of the two front wheels
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
     * sets the speed of the two back wheels
     * 
     * @param speed
     *            the speed for the wheels
     */
    public void setBack(double speed)
    {
        leftBack.set(speed);
        rightBack.set(speed);
    }

    /**
     * finds the amount to scale a wheel to get to the target
     * 
     * @param current
     *            the actual speed of the wheel
     * @param target
     *            the target speed for the wheel to reach
     * @return the amount the speed of the wheel should be changed
     */
    public double scale(double current, double target)
    {
        if (Math.abs(target - current) <= DT.THRESHOLD)
        {
            return target;
        }
        else
            return current
                    + ((DT.MAX_ACCELERATION) * ((target - current) / Math
                            .abs(target - current)));
    }
}
