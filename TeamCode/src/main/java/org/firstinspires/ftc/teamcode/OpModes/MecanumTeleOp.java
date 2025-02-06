package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name="RoberriPi", group="TeleOp")
public class MecanumTeleOp extends OpMode {
    private org.firstinspires.ftc.teamcode.Robot Robot;
    GamepadEx gamepadEx1;
    GamepadEx gamepadEx2;

    @Override
    public void init() {
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        Robot = new Robot(hardwareMap);
        telemetry.addData("viperPos", Robot.getViperPosition());
        telemetry.addData("clawPos", Robot.getClawPosition());
        telemetry.addData("ArmMotor Pos", Robot.getArmPosition());
        telemetry.addData("Wrist Pos", Robot.getWristPosition());
        telemetry.update();
        try {
            Robot.initilize();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void loop() {
        gamepadEx1.readButtons();
        gamepadEx2.readButtons();
        Robot.updateLoop(gamepadEx1, gamepadEx2);

        telemetry.addData("viperPos", Robot.getViperPosition());
        telemetry.addData("clawPos", Robot.getClawPosition());
        telemetry.addData("ArmMotor Pos", Robot.getArmPosition());
        telemetry.addData("Wrist Pos", Robot.getWristPosition());
        telemetry.addData("Current State", Robot.getCurrentState());
        telemetry.addData("Speed Multiplier", Robot.getSpeedMultiplier());
        telemetry.addLine("—————————————————————————————");
        telemetry.addData("Wall Intake State", Robot.getCurrentWallIntakeState());
        telemetry.addData("Claw State", Robot.getCurrentClawState());
        telemetry.addData("Bar Hang State", Robot.getCurrentBarHangState());
        telemetry.addData("Ground Pickup State", Robot.getCurrentGroundPickupState());
        telemetry.update();
    }
}