package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap;
import org.harker.robotics.commands.ManualDriveCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A drivetrain that manages four PID wheels
 * 
 * @author joelmanning
 * @version 0.1
 */
public class PIDDriveTrain extends Subsystem
{
    private static final double DEADZONE = 0.1;
    private static Wheel leftBack;
    private static Wheel rightBack;
    private static Wheel leftFront;
    private static Wheel rightFront;
    private static Encoder leftEncoder;
    private static Encoder rightEncoder;
    private static Gyro gyro;
    private static DoubleSolenoid leftShifter;
    private static DoubleSolenoid rightShifter;
    public static double MIN_PERCENT = 0.50;

    /**
     * Creates a new PIDDriveTrain with wheels at the default ports. 
     */
    public PIDDriveTrain()
    {
        //    	leftEncoder = new Encoder(RobotMap.DT_ENCODER_L_CHANNEL_A, RobotMap.DT_ENCODER_L_CHANNEL_B);
        //    	rightEncoder = new Encoder(RobotMap.DT_ENCODER_R_CHANNEL_A, RobotMap.DT_ENCODER_R_CHANNEL_B);
        leftBack = new Wheel(RobotMap.DT_TALON_LB_CHANNEL, true);
        rightBack = new Wheel(RobotMap.DT_TALON_RB_CHANNEL);
        leftFront = new Wheel(RobotMap.DT_TALON_LF_CHANNEL, true);
        rightFront = new Wheel(RobotMap.DT_TALON_RF_CHANNEL);
//        gyro = new AnalogGyro(RobotMap.DT_GYRO_PORT);
        
        leftShifter = new DoubleSolenoid(RobotMap.DT_SHIFT_LEFT_IN, RobotMap.DT_SHIFT_LEFT_OUT);
        rightShifter = new DoubleSolenoid(RobotMap.DT_SHIFT_RIGHT_IN, RobotMap.DT_SHIFT_RIGHT_OUT);
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
     * A version of tank drive to minimize slippage. If one of the two speeds is less
     * than a certain percent of the other, the smaller is scaled up to match that 
     * percentage of that other. If this percentage (MIN_PERCENT) is set to 1, 
     * this code functions as arcade drive. 
     * @param leftSpeed The intended speed of the left tread
     * @param rightSpeed The intended speed of the right tread
     */
    public void safeTank(double leftSpeed, double rightSpeed){
        double left = (leftSpeed > DEADZONE) ? leftSpeed : 0;
        double right = (rightSpeed > DEADZONE) ? rightSpeed : 0;
        if(!(leftSpeed == 0 || rightSpeed == 0)) { //Remember what this means Joel?
            left = Math.signum(leftSpeed) * Math.max(Math.abs(MIN_PERCENT * rightSpeed), Math.abs(leftSpeed));
            right = Math.signum(rightSpeed) * Math.max(Math.abs(MIN_PERCENT * leftSpeed), Math.abs(rightSpeed));
        } else { //Else preserve the sign of the nonzero value
            double sig = Math.signum(leftSpeed) + Math.signum(rightSpeed);
            left = sig * Math.max(Math.abs(MIN_PERCENT * rightSpeed), Math.abs(leftSpeed));
            right = sig * Math.max(Math.abs(MIN_PERCENT * leftSpeed), Math.abs(rightSpeed));
        }     
        tankDrive(left, right);
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

    public void toSmartDashboard() {
        SmartDashboard.putNumber("DT Left Upper", leftFront.get());
        SmartDashboard.putNumber("DT Left Lower", leftBack.get());
        SmartDashboard.putNumber("DT Right Upper", rightFront.get());
        SmartDashboard.putNumber("DT Right Lower", rightBack.get());
    }

    public Encoder getLeftEncoder() {
        return leftEncoder;
    }

    public Encoder getRightEncoder(){
        return rightEncoder;
    }

    public double getHeading() {
        return gyro.getAngle();
    }

    public double getHeadingWrapped() {
        return gyro.getAngle() % 360;
    }
    
    public void toggleLeftShift() {
    	if(leftShifter.get() == Value.kForward) { 
    		leftShifter.set(Value.kReverse);
    	}
    	else {
    		leftShifter.set(Value.kForward);
    	}
    }
    
    public void toggleRightShift() {
    	if(rightShifter.get() == Value.kForward) { 
    		rightShifter.set(Value.kReverse);
    	}
    	else {
    		rightShifter.set(Value.kForward);
    	}
    }
    
    public void toggleShift() {
    	toggleLeftShift();
    	toggleRightShift();
    }
}
