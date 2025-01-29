package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.*;

public interface IState {
    void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2);

}

class AutoMode implements IState {
    private boolean clawToggleState = true;
    private boolean barHangToggleState = false;
    private boolean groundPickupToggleState = false;
    private boolean wallIntakeToggleState = false;
    private boolean subPickupToggleState = false;
    //private boolean bucketDropToggleState = false;
    public String currentWallIntakeState;
    public String currentClawState;
    public String currentBarHangState;
    public String currentGroundPickupState;
    // Sleep Function
    private void sleep(long milliseconds) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < milliseconds) {
            
        }
    }
    
    // Telemetry
    public String getWallIntakeState() {
        if (wallIntakeToggleState) {
            return "Picking Up";
        } else {
            return "NOT picking up";
        }
    }
    public String getClawState() {
        if (clawToggleState) {
            return "Closed";
        } else {
            return "Open";
        }
    }
    public String getBarHangState() {
        if (barHangToggleState) {
            return "Dropping off";
        } else {
            return "NOT dropping off";
        }
    }
    public String getGroundPickupState() {
        if (groundPickupToggleState) {
            return "Picking Up";
        } else {
            return "NOT picking up";
        }
    }
    public void execute(Robot r, GamepadEx gamepadEx1, GamepadEx gamepadEx2) {
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.START)) {
            r.setState(Robot.ERobotState.Manual);
        }

        // Telemetry
        currentWallIntakeState = getWallIntakeState();
        currentClawState = getClawState();
        currentBarHangState = getBarHangState();
        currentGroundPickupState = getGroundPickupState();

        // Rest/Travel
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.BACK)) {
            r.setViperPosition(-25);
            r.setArmPosition(-325);
            r.setIntakeWrist(0.61);
        }

        // Bucket Drop
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON)) {
            r.setArmPosition(-2070);
            sleep(1000);
            r.setViperPosition(-2900);
            r.setIntakeWrist(0.35);
            sleep(1250);
            r.setIntakeWrist(0);
            sleep(500);
            r.setClawPosition(0.38);
            sleep(250);
            r.setIntakeWrist(0.5);
            sleep(750);
            r.setViperPosition(-50);
            sleep(1500);
            r.setArmPosition(-700);
        }

        // Off Wall Intake
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
            wallIntakeToggleState = !wallIntakeToggleState;
            if (wallIntakeToggleState) { // Picking up from wall
                r.setArmPosition(-450);
                r.setIntakeWrist(0.29);
                r.setViperPosition(0);
                r.setClawPosition(0.38);
            } else { // Raise from wall
                r.setArmPosition(-680);
                r.setIntakeWrist(0.31);
            }
        }

        // Claw
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.X)) {
            clawToggleState = !clawToggleState;
            r.setClawPosition(clawToggleState ? 0 /* closed */ : 0.38 /* open */);
        }

        // High Spec. Bar
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.A)) {
            barHangToggleState = !barHangToggleState;
            if (barHangToggleState) { // Clipping onto bar
                r.setViperPosition(-1400);
                r.setArmPosition(-930);
                r.setIntakeWrist(0.13);
            } else { // Let go of bar
                r.setClawPosition(0.38);
                r.setViperPosition(-150);
            }
        }

        // Ground pickup
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
            groundPickupToggleState = !groundPickupToggleState;
            if (groundPickupToggleState) { // Picking up from ground
                r.setArmPosition(-150);
                r.setViperPosition(-200);
                r.setIntakeWrist(0.55);
            } else { // Close claw
                r.setClawPosition(0);
            }
        }

        // Sub. Pickup
        if (gamepadEx2.wasJustPressed(GamepadKeys.Button.Y)) {
            subPickupToggleState = !subPickupToggleState;
            if (subPickupToggleState) { // Picking up from sub
                r.setViperPosition(-1200);
                r.setArmPosition(-250);
                r.setIntakeWrist(0.62);
            } else { // Raise from sub
                r.setArmPosition(-500);
                r.setViperPosition(-200);
                r.setIntakeWrist(0.3);
            }
        }


    }


}

class ManualMode implements IState {
    private boolean clawToggleState = true;
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
            r.setIntakeWrist(r.getWristPosition() - 0.01);
        } else if (gamepadEx2.isDown(GamepadKeys.Button.DPAD_DOWN)) {
            r.setIntakeWrist(r.getWristPosition() + 0.01);
        }

        // Viper Position
        if (gamepadEx2.isDown(GamepadKeys.Button.LEFT_BUMPER) && r.getViperPosition() > -2000) {
            r.setViperPosition(r.getViperPosition() - 200);
        } else if (gamepadEx2.isDown(GamepadKeys.Button.RIGHT_BUMPER) && r.getViperPosition() < 10) {
            r.setViperPosition(r.getViperPosition() + 150);
        }

        // Claw Position
        if (gamepadEx2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5) {
            r.setClawPosition(r.getClawPosition()+0.01);
        } else if (gamepadEx2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5) {
            r.setClawPosition(r.getClawPosition()-0.01);
        } else if (gamepadEx2.wasJustPressed(GamepadKeys.Button.X)) {
            clawToggleState = !clawToggleState;
            r.setClawPosition(clawToggleState ? 0 : 0.38);
        }
    }
}