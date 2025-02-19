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
@Autonomous(name = "Red Bar", group = "Autonomous")
public class redBarAuto extends LinearOpMode {
    // Arm class
    public class Arm {
        private DcMotorEx armMotor;

        public Arm(HardwareMap hardwareMap) {
            armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
            armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        public class armHook implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                armMotor.setTargetPosition(-870);
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
                armMotor.setTargetPosition(-415);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(1);
                return false;
            }
        }
        public Action pickupWall() {
            return new armPickupWall();
        }

        public class armRaiseWall implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                armMotor.setTargetPosition(-640);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(1);
                return false;
            }
        }
        public Action raiseWall() {
            return new armRaiseWall();
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
        public class wristPickupWall implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0.29);
                return false;
            }
        }
        public Action pickupWall() {
            return new wristPickupWall();
        }

        public class wristRaiseWall implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristServo.setPosition(0.31);
                return false;
            }
        }
        public Action raiseWall() {
            return new wristRaiseWall();
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
                wristServo.setPosition(0.15);
                return false;
            }
        }
        public Action dropBar() {
            return new wristDropBar();
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

        public class viperInBar implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                viper.setTargetPosition(-150);
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
        Pose2d initialPose = new Pose2d(7.4, -61.25, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Viper viper = new Viper(hardwareMap);
        Wrist wrist = new Wrist(hardwareMap);
        Arm arm = new Arm(hardwareMap);
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                // Begin drop | Ready for drop
                .stopAndAdd(arm.hookBar())
                .stopAndAdd(viper.viperOutBar())
                .stopAndAdd(wrist.dropBar())
                .waitSeconds(.1)
                .strafeToConstantHeading(new Vector2d(5, -32.5)) // Move forward
                // Hook, let go
                .stopAndAdd(claw.openClaw())
                .stopAndAdd(viper.viperInBar())
                .waitSeconds(0.3)
                .strafeToConstantHeading(new Vector2d(5, -40)) // Back up a little
                .stopAndAdd(viper.initialize())
                .stopAndAdd(arm.idle())
                // End Drop
                .strafeToLinearHeading(new Vector2d(35, -40), Math.toRadians(180)) // Move to line up to traverse
                .strafeToConstantHeading(new Vector2d(35, -14)) // Traverse
                .strafeToConstantHeading(new Vector2d(47, -14.02)) // Line up to push first
                .strafeToConstantHeading(new Vector2d(47, -52)) // Push first sample
                /*.strafeToConstantHeading(new Vector2d(40, -20)) // Line up to push second | 1
                .splineToLinearHeading(new Pose2d(57, -14, Math.toRadians(180)), Math.PI/15) // Line up to push second | 2
                .strafeToConstantHeading(new Vector2d(57, -52)) // Push second sample */
                .strafeToLinearHeading(new Vector2d(48, -57.15), Math.toRadians(270)) // Line up to pick up from wall
                // Begin Wall Pickup
                .stopAndAdd(arm.pickupWall())
                .stopAndAdd(viper.initialize())
                .stopAndAdd(wrist.pickupWall())
                .waitSeconds(0.5)
                .stopAndAdd(claw.closeClaw())
                .waitSeconds(.3) // begin raise after this
                .stopAndAdd(arm.raiseWall())
                .stopAndAdd(wrist.raiseWall())
                // End Wall Pickup
                .strafeToLinearHeading(new Vector2d(0, -50), Math.toRadians(90)) // Move into hooking position
                // Begin drop | Ready for drop
                .stopAndAdd(arm.hookBar())
                .stopAndAdd(viper.viperOutBar())
                .stopAndAdd(wrist.dropBar())
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(0, -32.2), Math.toRadians(90)) // Move forward
                // Hook, let go
                .stopAndAdd(claw.openClaw())
                .stopAndAdd(viper.viperInBar())
                .waitSeconds(0.3)
                .strafeTo(new Vector2d(0, -40)) // Back up a little
                .stopAndAdd(viper.initialize())
                .stopAndAdd(arm.idle())

                .strafeToLinearHeading(new Vector2d(48, -57.5), Math.toRadians(270)) // Line up to pick up from wall
                // Begin Wall Pickup
                .stopAndAdd(arm.pickupWall())
                .stopAndAdd(viper.initialize())
                .stopAndAdd(wrist.pickupWall())
                .waitSeconds(.5)
                .stopAndAdd(claw.closeClaw())
                .waitSeconds(0.3) // begin raise after this
                .stopAndAdd(arm.raiseWall())
                .stopAndAdd(wrist.raiseWall())
                // End Wall Pickup
                .strafeToLinearHeading(new Vector2d(-5, -50), Math.toRadians(90)) // Move into hooking position
                // Begin drop | Ready for drop
                .stopAndAdd(arm.hookBar())
                .stopAndAdd(viper.viperOutBar())
                .stopAndAdd(wrist.dropBar())
                .waitSeconds(.5)
                .strafeToLinearHeading(new Vector2d(-5, -32.2), Math.toRadians(90)) // Move forward
                // Hook, let go
                .stopAndAdd(claw.openClaw())
                .stopAndAdd(viper.viperInBar())
                .waitSeconds(.75)
                .strafeTo(new Vector2d(-5, -40)) // Back up a little
                .stopAndAdd(viper.initialize())
                .stopAndAdd(arm.idle())

                .strafeToConstantHeading(new Vector2d(48, -56.5)) // Move back to wall
                /*// Begin Wall Pickup
                .stopAndAdd(arm.pickupWall())
                .stopAndAdd(wrist.pickupWall())
                .waitSeconds(3)
                .stopAndAdd(claw.closeClaw())
                .waitSeconds(3) // begin raise after this
                .stopAndAdd(arm.raiseWall())
                .stopAndAdd(wrist.raiseWall())
                // End Wall Pickup
                .strafeToLinearHeading(new Vector2d(-5, -50), Math.toRadians(90)) // Move into hooking position
                // Begin drop | Ready for drop
                .stopAndAdd(arm.hookBar())
                .stopAndAdd(viper.viperOutBar())
                .stopAndAdd(wrist.dropBar())
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-5, -32.2), Math.toRadians(90)) // Move forward
                // Hook, let go
                .stopAndAdd(claw.openClaw())
                .stopAndAdd(viper.initialize())
                .waitSeconds(3)
                .strafeTo(new Vector2d(0, -40))*/; // Back up a little
        // End Drop

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