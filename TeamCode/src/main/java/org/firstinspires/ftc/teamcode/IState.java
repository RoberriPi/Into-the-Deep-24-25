package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.*;

public interface IState {
    void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2);

}

class AutoMode implements IState {
    public void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2) {
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.START)) {
            r.setState(Robot.ERobotState.Manual);
        }
    }
}

class ManualMode implements IState {
    public void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2) {
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.START)) {
            r.setState(Robot.ERobotState.Auto);
        }

        // Arm Position
        if (gamepadEx2.isDown(GamepadKeys.Button.DPAD_LEFT)) {
            r.setArmPosition(r.getArmPosition() + 100);
        } else if (gamepadEx2.isDown(GamepadKeys.Button.DPAD_RIGHT)) {
            r.setArmPosition(r.getArmPosition() - 100);
        }


        // Wrist Position
        if (gamepadEx2.isDown(GamepadKeys.Button.DPAD_UP)) {
            r.setIntakeWrist(r.getWristPosition() + 0.01);
        } else if (gamepadEx2.isDown(GamepadKeys.Button.DPAD_DOWN)) {
            r.setIntakeWrist(r.getWristPosition() - 0.01);
        }

        // Viper Position
        if (gamepadEx2.isDown(GamepadKeys.Button.LEFT_BUMPER)) {
            r.setViperPosition(r.getViperPosition() - 200);
        } else if (gamepadEx2.isDown(GamepadKeys.Button.RIGHT_BUMPER)) {
            r.setViperPosition(r.getViperPosition() + 150);
        }

        // Claw Position
        if (gamepadEx2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5) {
            r.setClawPosition(r.getClawPosition() + 0.01);
        } else if (gamepadEx2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5) {
            r.setClawPosition(r.getClawPosition() - 0.01);
        }
    }
}