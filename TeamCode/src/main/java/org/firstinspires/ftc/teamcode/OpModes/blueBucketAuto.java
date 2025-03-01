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
@Autonomous(name = "Blue Bucket", group = "Autonomous")

public class blueBucketAuto extends LinearOpMode {
    // Arm class
    public class Arm {
        private DcMotorEx armMotor;

        public Arm(HardwareMap hardwareMap) {
            armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
            armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        public class armDrop implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                armMotor.setTargetPosition(-2020);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(1);
                return false;
            }
        }
        public Action dropBucket() {
            return new armDrop();
        }

        public class armPickup implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                armMotor.setTargetPosition(-50);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(1);
                return false;
            }
        }
        public Action pickupBucket() {
            return new armPickup();
        }

        public class armIdle implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                armMotor.setTargetPosition(-275);
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
                armMotor.setTargetPosition(-50);
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
        public class wristPickupGround implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0.55);
                return false;
            }
        }
        public Action pickupGround() {
            return new wristPickupGround();
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

        public class wristDropBucket implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0);
                return false;
            }
        }
        public Action dropBucket() {
            return new wristDropBucket();
        }

        public class wristHalfway implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0.35);
                return false;
            }
        }
        public Action halfway() {
            return new wristHalfway();
        }

        public class wristInitialize implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0);
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
            viper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        public class viperInGround implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                viper.setTargetPosition(-125);
                viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viper.setPower(1);
                return false;
            }
        }
        public Action viperInGround() {
            return new viperInGround();
        }

        public class viperOut implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                viper.setTargetPosition(-2820);
                viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viper.setPower(1);
                return false;
            }
        }
        public Action viperOut() {
            return new viperOut();
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
        Pose2d initialPose = new Pose2d(23.88, 61, Math.toRadians(270.00));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Viper viper = new Viper(hardwareMap);
        Wrist wrist = new Wrist(hardwareMap);
        Arm arm = new Arm(hardwareMap);
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .lineToY(55)
                .strafeToSplineHeading(new Vector2d(58.5, 57), Math.toRadians(225))
                .stopAndAdd(wrist.halfway())
                .stopAndAdd(arm.dropBucket()) // BUCKET DROP START
                .waitSeconds(1)
                .stopAndAdd(viper.viperOut())
                .waitSeconds(1.25)
                .stopAndAdd(wrist.dropBucket())
                .waitSeconds(0.5)
                .stopAndAdd(claw.openClaw())
                .waitSeconds(0.25)
                .stopAndAdd(wrist.idle())
                .waitSeconds(0.75)
                .stopAndAdd(viper.viperIn())
                .waitSeconds(1)
                .stopAndAdd(arm.idle())
                .waitSeconds(0.5) // BUCKET DROP END
                .stopAndAdd(wrist.pickupGround())
                .strafeToSplineHeading(new Vector2d(49, 34), Math.toRadians(-90)) // move to ground pickup 1
                .stopAndAdd(viper.viperInGround())// GROUND PICKUP START
                .stopAndAdd(arm.pickupBucket())
                .waitSeconds(0.5)
                .stopAndAdd(claw.closeClaw())
                .waitSeconds(0.3)
                .stopAndAdd(arm.idle()) // GROUND PICKUP END
                .strafeToSplineHeading(new Vector2d(58.5, 57), Math.toRadians(225))
                .stopAndAdd(arm.dropBucket()) // BUCKET DROP START
                .waitSeconds(1)
                .stopAndAdd(viper.viperOut())
                .stopAndAdd(wrist.halfway())
                .waitSeconds(1.25)
                .stopAndAdd(wrist.dropBucket())
                .waitSeconds(0.5)
                .stopAndAdd(claw.openClaw())
                .waitSeconds(0.25)
                .stopAndAdd(wrist.idle())
                .waitSeconds(0.75)
                .stopAndAdd(viper.viperIn())
                .waitSeconds(1)
                .stopAndAdd(arm.idle())
                .waitSeconds(0.5) // BUCKET DROP END
                .stopAndAdd(wrist.pickupGround())
                .strafeToLinearHeading(new Vector2d(60, 34), Math.toRadians(-90)) // move to ground pickup 2
                .stopAndAdd(viper.viperInGround())// GROUND PICKUP START
                .stopAndAdd(arm.pickupBucket())
                .waitSeconds(0.5)
                .stopAndAdd(claw.closeClaw())
                .waitSeconds(0.3)
                .stopAndAdd(arm.idle()) // GROUND PICKUP END
                .strafeToSplineHeading(new Vector2d(58.5, 57), Math.toRadians(225))
                .stopAndAdd(arm.dropBucket()) // BUCKET DROP START
                .waitSeconds(1)
                .stopAndAdd(viper.viperOut())
                .stopAndAdd(wrist.halfway())
                .waitSeconds(1.25)
                .stopAndAdd(wrist.dropBucket())
                .waitSeconds(0.5)
                .stopAndAdd(claw.openClaw())
                .waitSeconds(0.25)
                .stopAndAdd(wrist.idle())
                .waitSeconds(0.75)
                .stopAndAdd(viper.viperIn())
                .waitSeconds(1)
                .stopAndAdd(arm.idle())
                .stopAndAdd(claw.closeClaw())
                .waitSeconds(0.5); // BUCKET DROP END
                //.splineToLinearHeading(new Pose2d(-35, 58, Math.toRadians(-90)), Math.PI*5);

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