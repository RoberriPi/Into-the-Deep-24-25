package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name="RoberriPi", group="TeleOp")
public class MecanumTeleOp extends OpMode {
    private org.firstinspires.ftc.teamcode.Robot Robot;
    GamepadEx gamepadEx1;
    GamepadEx gamepadEx2;
    FtcDashboard dashboard;

    double viperPos;
    double armPos;
    double clawPos;
    double wristPos;


    @Override
    public void init() {
        dashboard = FtcDashboard.getInstance();

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        Robot = new Robot(hardwareMap);
        viperPos = Robot.getViperPosition();
        armPos = Robot.getArmPosition();
        clawPos = Robot.getClawPosition();
        wristPos = Robot.getWristPosition();
        try {
            Robot.initilize();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        telemetry.addData("viperPos", viperPos);
        telemetry.addData("clawPos", clawPos);
        telemetry.addData("ArmMotor Pos", armPos);
        telemetry.addData("Wrist Pos", wristPos);
        telemetry.update();
        dashboard.getTelemetry();

    }

    @Override
    public void loop() {
        gamepadEx1.readButtons();
        gamepadEx2.readButtons();
        Robot.updateLoop(gamepadEx1, gamepadEx2);

        viperPos = Robot.getViperPosition();
        armPos = Robot.getArmPosition();
        clawPos = Robot.getClawPosition();
        wristPos = Robot.getWristPosition();

        telemetry.addData("viperPos", viperPos);
        telemetry.addData("clawPos", clawPos);
        telemetry.addData("ArmMotor Pos", armPos);
        telemetry.addData("Wrist Pos", wristPos);
        telemetry.addData("Current State", Robot.getCurrentState());
        telemetry.addData("Speed Multiplier", Robot.getSpeedMultiplier());
        telemetry.addLine("—————————————————————————————");
        telemetry.addData("Wall Intake State", Robot.getCurrentWallIntakeState());
        telemetry.addData("Claw State", Robot.getCurrentClawState());
        telemetry.addData("Bar Hang State", Robot.getCurrentBarHangState());
        telemetry.addData("Ground Pickup State", Robot.getCurrentGroundPickupState());
        telemetry.update();
        dashboard.getTelemetry();

    }
}