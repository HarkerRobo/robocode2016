package org.harker.robotics;

import edu.wpi.first.wpilibj.buttons.Button;

import org.harker.robotics.commands.ExampleCommand;
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static GamepadWrapper gamepad = new GamepadWrapper(RobotMap.OI_GAMEPAD_CHANNEL, GamepadWrapper.SETTING_XBOX);
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    public OI() {

    }
}

