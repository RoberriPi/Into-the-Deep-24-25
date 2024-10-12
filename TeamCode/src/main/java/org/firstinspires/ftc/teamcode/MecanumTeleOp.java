package org.firstinspires.ftc.teamcode;

import android.util.Pair;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="RoberriPi", group="TeleOp")
public class MecanumTeleOp extends OpMode {
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();
    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();
    private RobotHardware robot;
    private IntakeFunctions functions;
    @Override
    public void init() {
        robot = new RobotHardware(hardwareMap);
        functions = new IntakeFunctions(hardwareMap);
    }
    boolean isButton1BACK = false;
    double speedMult = 1;
    double wheel;

    @Override
    public void loop() {
        previousGamepad1.copy(currentGamepad1);
        previousGamepad2.copy(currentGamepad2);

        currentGamepad1.copy(gamepad1);
        currentGamepad2.copy(gamepad2);

        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotation = gamepad1.right_stick_x;
        if (gamepad1.back) {
            if(!isButton1BACK) {
                if (speedMult == 1) {
                    speedMult = 0.4;
                } else {
                    speedMult = 1;
                }

                isButton1BACK = true;
            }
        } else {
            isButton1BACK = false;
        }
        robot.setMotorPowers(forward * speedMult, strafe * speedMult, rotation * speedMult);
        functions.intakeWheelServo(gamepad1.a, gamepad1.left_trigger, gamepad1.right_trigger);
        functions.intakeRotServo(gamepad1.b);
        telemetry.addData("Wheel Rot Servo Position", functions.getWheelRotServoPosition());
        telemetry.update();
    }
}