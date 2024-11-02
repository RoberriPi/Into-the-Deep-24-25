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
        double wheelServoPower = functions.intakeWheelServo(gamepad2.x, gamepad2.left_trigger, gamepad2.right_trigger);
        double rotServoPos = functions.intakeRotServo(gamepad2.b);
        int armMotorPos = functions.armMotor(gamepad2.dpad_left, gamepad2.dpad_right);
        double viperPos = functions.viperSlide(gamepad2.a, gamepad2.b, gamepad2.left_bumper, gamepad2.right_bumper);
        int lifterPos = functions.lifter(gamepad1.y, gamepad1.left_bumper, gamepad1.x, gamepad1.right_bumper);
        double wristPos = functions.intakeWrist(gamepad2.dpad_up, gamepad2.dpad_down);

        telemetry.addData("viperPos", viperPos);
        telemetry.addData("lifterPos", lifterPos);
        telemetry.addData("WheelServo Power", wheelServoPower);
        telemetry.addData("RotServo Pos", rotServoPos);
        telemetry.addData("ArmMotor Pos", armMotorPos);
        telemetry.addData("Wrist Pos", wristPos);
        telemetry.update();
    }
}