package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.*;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.Auto.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.lang.Math;

@Config
@Autonomous(name = "BLUE_TEST_AUTO_BUCKET", group = "Autonomous")
public class blueBucketAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(23.88, 61.70, Math.toRadians(270.00));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .splineTo(new Vector2d(52.90, 54.02), Math.toRadians(223.92))
                .waitSeconds(3)
                .splineTo(new Vector2d(48.59, 36.98), Math.toRadians(-89.16))
                .waitSeconds(3)
                .splineToConstantHeading(new Vector2d(52.90, 54.02), Math.toRadians(224))
                .setTangent(Math.toRadians(224))
                .waitSeconds(2);

        if (isStopRequested()) return;
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(tab1.build())
        );
    }
}