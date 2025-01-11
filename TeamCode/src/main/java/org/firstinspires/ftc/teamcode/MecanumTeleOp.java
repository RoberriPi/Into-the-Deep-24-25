package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.KeyReader;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="RoberriPi", group="TeleOp")
public class MecanumTeleOp extends OpMode {
    private RobotHardware robot;
    private IntakeFunctions functions;
    GamepadEx gamepadEx1;
    GamepadEx gamepadEx2;

    @Override
    public void init() {
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        robot = new RobotHardware(hardwareMap);
        functions = new IntakeFunctions(hardwareMap);
        try {
            functions.initilize();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    boolean isButton1BACK = false;
    boolean isButton1START = false;

    @Override
    public void loop() {

        double forward = -gamepad1.left_stick_y;

        double strafe = gamepad1.left_stick_x;
        double rotation = gamepad1.right_stick_x;

        /*telemetry.addData("viperPos", viperPos);
        telemetry.addData("leftWheelPos", clawPos);
        telemetry.addData("ArmMotor Pos", armMotorPos);
        telemetry.addData("Wrist Pos", wristPos);
        telemetry.update();*/



    }
}