package org.harker.robotics;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    public class DT {
        public static final int TALON_LF_CHANNEL = 0;
        public static final int TALON_RF_CHANNEL = 3;
        public static final int TALON_LB_CHANNEL = 2;
        public static final int TALON_RB_CHANNEL = 1;
        public static final int LF_ENCODER_A_CHANNEL = 1;
        public static final int LF_ENCODER_B_CHANNEL = 1;
        public static final int RF_ENCODER_A_CHANNEL = 1;
        public static final int RF_ENCODER_B_CHANNEL = 1;
        public static final int LB_ENCODER_A_CHANNEL = 1;
        public static final int LB_ENCODER_B_CHANNEL = 1;
        public static final int RB_ENCODER_A_CHANNEL = 1;
        public static final int RB_ENCODER_B_CHANNEL = 1;
        public static final double MAX_ACCELERATION = 0.1;
        public static final double THRESHOLD = 0.1;
    }
}
