package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.KeyReader;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="RoberriPi", group="TeleOp")
public class MecanumTeleOp extends OpMode {
    private Robot Robot;
    GamepadEx gamepadEx1;
    GamepadEx gamepadEx2;

    @Override
    public void init() {
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        Robot = new Robot(hardwareMap);
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
        telemetry.update();



    }
}