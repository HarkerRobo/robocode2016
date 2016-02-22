package org.harker.robotics;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
        public static final int DT_TALON_LF_CHANNEL = 3;
        public static final int DT_TALON_RF_CHANNEL = 1;
        public static final int DT_TALON_LB_CHANNEL = 4;
        public static final int DT_TALON_RB_CHANNEL = 2;
        public static final int DT_ENCODER_L_CHANNEL_A = 0;
        public static final int DT_ENCODER_L_CHANNEL_B = 1;
        public static final int DT_ENCODER_R_CHANNEL_A = 3;
        public static final int DT_ENCODER_R_CHANNEL_B = 4;
        public static final double DT_MAX_ACCELERATION = 0.1;
        public static final double DT_THRESHOLD = 0.1;
        
        public static final int DT_SHIFT_LEFT_IN = 2;
        public static final int DT_SHIFT_LEFT_OUT = 5;
        public static final int DT_SHIFT_RIGHT_IN = 3;
        public static final int DT_SHIFT_RIGHT_OUT = 4;
        
        public static final int ST_TALON_UPPER_CHANNEL = 5;
        public static final int ST_TALON_LOWER_CHANNEL = 6;
        
        public static final int IN_TALON_PICKUP = 7;
        
        public static final int OI_GAMEPAD_CHANNEL = 0;
}
