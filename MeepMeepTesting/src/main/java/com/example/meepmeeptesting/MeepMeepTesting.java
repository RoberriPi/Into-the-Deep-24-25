package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-23.6, 61.70, Math.toRadians(270.00)))
                .splineTo(new Vector2d(-46, 56), Math.toRadians(150))
                // Open Claw
                .strafeToLinearHeading(new Vector2d(-35, 40), Math.toRadians(180))
                .strafeToConstantHeading(new Vector2d(-35, 14))
                .strafeToConstantHeading(new Vector2d(-47, 14.02))
                .strafeToConstantHeading(new Vector2d(-47, 52), new TranslationalVelConstraint(20))
                .waitSeconds(1)
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
                .strafeToLinearHeading(new Vector2d(-5, 32.5), Math.toRadians(270))
                // Let go
                        .build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}