package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.*;

public interface IState {
    void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2);

}

class AutoMode implements IState {
    private boolean clawToggleState = false;
    private boolean barHangToggleState = true;
    private boolean groundPickupToggleState = true;
    private boolean wallIntakeToggleState = true;
    public void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2) {
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.START)) {
            r.setState(Robot.ERobotState.Manual);
        }

        // Off Wall Intake
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
            wallIntakeToggleState = !wallIntakeToggleState;
            if (wallIntakeToggleState) {
                r.setArmPosition(-435);
                r.setIntakeWrist(0.3);
                r.setViperPosition(-40);
            } else {
                r.setArmPosition(-680);
                r.setIntakeWrist(0.31);
            }
        }

        // Claw
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.A)) {
            clawToggleState = !clawToggleState;
            r.setClawPosition(clawToggleState ? 0.75 : 0.4);
        }

        // High Spec. Bar
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.A)) {
            if (barHangToggleState) {
                r.setViperPosition(-1200);
                r.setArmPosition(-930);
                r.setIntakeWrist(0.13);
            } else {
                r.setClawPosition(0.75);
                r.setViperPosition(-150);
            }
        }

        // Ground pickup
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
            groundPickupToggleState = !groundPickupToggleState;
            if (groundPickupToggleState) {
                r.setArmPosition(-680);
                r.setViperPosition(-250);
                r.setIntakeWrist(0.31);
            } else {
                r.setArmPosition(-435);
                r.setViperPosition(-40);
                r.setIntakeWrist(0.3);
            }
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
            r.setClawPosition(0.75);
        } else if (gamepadEx2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5) {
            r.setClawPosition(0.4);
        }
    }
}