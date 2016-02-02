package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap.DT;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author joelmanning
 * @version 0.1
 */
public class Wheel extends PIDSubsystem
{

    private static Talon wheel;
    private static Encoder encoder;
    private static final int P = 1;
    private static final int I = 1;
    private static final int D = 1;

    /**
     * creates a new wheel with specified channels for the encoders and talons
     * 
     * @param talonChannel
     *            the channel of the wheel's talon
     * @param encoderChannelA
     *            the encoder's A channel
     * @param encoderChannelB
     *            the encoder's B channel
     */
    public Wheel(int talonChannel, int encoderChannelA, int encoderChannelB)
    {
        super(P, I, D);
        this.enable();
        this.setOutputRange(-1.0, 1.0);
        wheel = new Talon(talonChannel);
        encoder = new Encoder(encoderChannelA, encoderChannelB);
    }

    /**
     * @deprecated this command is not currently used
     */
    public void initDefaultCommand()
    {
    }

    /**
     * sets the target speed for the wheel
     * 
     * @param setPoint
     *            the target speed
     */
    public void set(double setPoint)
    {
        this.setSetpoint(setPoint);
    }

    /**
     * @return the current speed of the wheel
     */
    @Override
    protected double returnPIDInput()
    {
        return encoder.getRate();
    }

    /**
     * sets the wheel's speed to the PID output
     */
    @Override
    protected void usePIDOutput(double output)
    {
        wheel.set(encoder.getRate() + scale(encoder.getRate(), output));
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
