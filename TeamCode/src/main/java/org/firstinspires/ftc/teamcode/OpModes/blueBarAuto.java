package org.firstinspires.ftc.teamcode.OpModes;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.*;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.Auto.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.lang.Math;




@Config
@Autonomous(name = "Blue Bar", group = "Autonomous")

public class blueBarAuto extends LinearOpMode {
    // Arm class
    public class Arm {
        private DcMotorEx armMotor;

        public Arm(HardwareMap hardwareMap) {
            armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
            armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        public class armHook implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                armMotor.setTargetPosition(-930);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(1);
                return false;
            }
        }
        public Action hookBar() {
            return new armHook();
        }

        public class armPickupWall implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                armMotor.setTargetPosition(-450);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(1);
                return false;
            }
        }
        public Action pickupWall() {
            return new armPickupWall();
        }

        public class armIdle implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                armMotor.setTargetPosition(-325);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(1);
                return false;
            }
        }
        public Action idle() {
            return new armIdle();
        }

        public class armInitialize implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                armMotor.setTargetPosition(-100);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(1);
                return false;
            }
        }
        public Action initialize() {
            return new armInitialize();
        }
    }

    // Wrist class
    public class Wrist {
        private Servo wristServo;

        public Wrist(HardwareMap hardwareMap) {
            wristServo = hardwareMap.get(Servo.class, "wristServo");
        }
        public class wristPickupWall implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0.29);
                return false;
            }
        }
        public Action pickupGround() {
            return new wristPickupWall();
        }

        public class wristIdle implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0.61);
                return false;
            }
        }
        public Action idle() {
            return new wristIdle();
        }

        public class wristDropBar implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0.13);
                return false;
            }
        }
        public Action dropBar() {
            return new wristDropBar();
        }


        public class wristInitialize implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0.58);
                return false;
            }
        }
        public Action initialize() {
            return new wristInitialize();
        }
    }

    // Viper class
    public class Viper {
        private DcMotorEx viper;

        public Viper(HardwareMap hardwareMap) {
            viper = hardwareMap.get(DcMotorEx.class, "viper");
            viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            viper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        public class viperIn implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                viper.setTargetPosition(-10);
                viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viper.setPower(1);
                return false;
            }
        }
        public Action viperIn() {
            return new viperIn();
        }

        public class viperInBar implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                viper.setTargetPosition(-140);
                viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viper.setPower(1);
                return false;
            }
        }
        public Action viperInBar() {
            return new viperInBar();
        }

        public class viperOutBar implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                viper.setTargetPosition(-1400);
                viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viper.setPower(1);
                return false;
            }
        }
        public Action viperOutBar() {
            return new viperOutBar();
        }

        public class viperInitialize implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                viper.setTargetPosition(0);
                viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viper.setPower(1);
                return false;
            }
        }
        public Action initialize() {
            return new viperInitialize();
        }
    }

    // Claw class
    public class Claw {
        private Servo clawServo;

        public Claw(HardwareMap hardwareMap) {
            clawServo = hardwareMap.get(Servo.class, "clawServo");
        }

        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                clawServo.setPosition(0.05);
                return false;
            }
        }
        public Action closeClaw() {
            return new CloseClaw();
        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                clawServo.setPosition(0.38);
                return false;
            }
        }
        public Action openClaw() {
            return new OpenClaw();
        }
    }
    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(23.88, 61.70, Math.toRadians(270.00));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Viper viper = new Viper(hardwareMap);
        Wrist wrist = new Wrist(hardwareMap);
        Arm arm = new Arm(hardwareMap);
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .splineTo(new Vector2d(-46, 56), Math.toRadians(150))
                .stopAndAdd(claw.openClaw())
                .strafeToLinearHeading(new Vector2d(-35, 40), Math.toRadians(180))
                .strafeToConstantHeading(new Vector2d(-35, 14))
                .strafeToConstantHeading(new Vector2d(-47, 14.02))
                .strafeToConstantHeading(new Vector2d(-47, 52), new TranslationalVelConstraint(20))
                .strafeToConstantHeading(new Vector2d(-40, 20))
                .splineToLinearHeading(new Pose2d(-57, 14, Math.toRadians(180)), Math.PI*15)
                .strafeToConstantHeading(new Vector2d(-57, 52), new TranslationalVelConstraint(20))
                .strafeToLinearHeading(new Vector2d(-46, 51), Math.toRadians(90))
                // Pickup from wall
                .strafeToLinearHeading(new Vector2d(0, 50), Math.toRadians(270))
                // Ready for drop
                .strafeToLinearHeading(new Vector2d(0, 32.5), Math.toRadians(270))
                // Let go
                .strafeTo(new Vector2d(0, 40))
                .strafeToLinearHeading(new Vector2d(-46, 51), Math.toRadians(90))
                // Pickup from wall
                .strafeToLinearHeading(new Vector2d(-5, 50), Math.toRadians(270))
                // Ready for drop
                .strafeToLinearHeading(new Vector2d(-5, 32.5), Math.toRadians(270));
                // Let go

        Actions.runBlocking(arm.initialize());
        Actions.runBlocking(wrist.initialize());
        Actions.runBlocking(viper.initialize());
        Actions.runBlocking(claw.closeClaw());

        if (isStopRequested()) return;

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(tab1.build())
        );
    }
}