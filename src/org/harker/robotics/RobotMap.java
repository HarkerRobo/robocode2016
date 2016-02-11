package org.harker.robotics;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
        public static final int DT_TALON_LF_CHANNEL = 0;
        public static final int DT_TALON_RF_CHANNEL = 3;
        public static final int DT_TALON_LB_CHANNEL = 2;
        public static final int DT_TALON_RB_CHANNEL = 1;
        public static final int DT_ENCODER_L_CHANNEL_A = 1;
        public static final int DT_ENCODER_L_CHANNEL_B = 2;
        public static final int DT_ENCODER_R_CHANNEL_A = 3;
        public static final int DT_ENCODER_R_CHANNEL_B = 4;
        public static final double DT_MAX_ACCELERATION = 0.1;
        public static final double DT_THRESHOLD = 0.1;
        public static final int GAMEPAD_PORT = 0;
}
