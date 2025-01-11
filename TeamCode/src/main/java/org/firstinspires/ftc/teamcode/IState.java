package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.*;

public interface IState {
    void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2, boolean prevGP2Start);

}

class AutoMode implements IState {
    public void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2, boolean prevGP2Start) {
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.START)) {
            r.setState(Robot.ERobotState.Manual);
        }
    }
}

class ManualMode implements IState {
    public void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2, boolean prevGP2Start) {
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.START)) {
            r.setState(Robot.ERobotState.Auto);
        }
    }
}