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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(23.88, 61.70, Math.toRadians(270.00)))
                .lineToY(55)
                .strafeToSplineHeading(new Vector2d(58.5, 57), Math.toRadians(225))
                .waitSeconds(1)
                .waitSeconds(1.25)
                .waitSeconds(0.5)
                .waitSeconds(0.25)
                .waitSeconds(0.75)
                .waitSeconds(1)
                .waitSeconds(0.5)
                .strafeToSplineHeading(new Vector2d(49, 34), Math.toRadians(-90)) // move to ground pickup 1
                .waitSeconds(0.5)
                .waitSeconds(0.3)
                .strafeToSplineHeading(new Vector2d(58.5, 57), Math.toRadians(225))
                .waitSeconds(1)
                .waitSeconds(1.25)
                .waitSeconds(0.5)
                .waitSeconds(0.25)
                .waitSeconds(0.75)
                .waitSeconds(1)
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(60, 34), Math.toRadians(-90)) // move to ground pickup 2
                .waitSeconds(0.5)
                .waitSeconds(0.3)
                .strafeToSplineHeading(new Vector2d(58.5, 57), Math.toRadians(225))
                .waitSeconds(1)
                .waitSeconds(1.25)
                .waitSeconds(0.5)
                .waitSeconds(0.25)
                .waitSeconds(0.75)
                .waitSeconds(1)
                .waitSeconds(0.5)
                .build());


        RoadRunnerBotEntity myBot1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot1.runAction(myBot1.getDrive().actionBuilder(new Pose2d(-7.4, 61.25, Math.toRadians(270.00)))
                        .waitSeconds(.5)
                        .strafeToLinearHeading(new Vector2d(-5, 32.5), Math.toRadians(270))
                        .waitSeconds(.5)
                        .strafeTo(new Vector2d(-5, 40))
                        .strafeToLinearHeading(new Vector2d(-35, 40), Math.toRadians(0))
                        .strafeToConstantHeading(new Vector2d(-35, 14))
                        .strafeToConstantHeading(new Vector2d(-47, 14.02))
                        .strafeToConstantHeading(new Vector2d(-47, 52))
                        .strafeToLinearHeading(new Vector2d(-48, 57.25), Math.toRadians(90))
                        .waitSeconds(.5)
                        .strafeToLinearHeading(new Vector2d(0, 50), Math.toRadians(270))
                        .waitSeconds(.5)
                        .strafeToLinearHeading(new Vector2d(0, 32.2), Math.toRadians(270))
                        .waitSeconds(.3)
                        .strafeTo(new Vector2d(0, 40))
                        .strafeToLinearHeading(new Vector2d(-48, 57.4), Math.toRadians(90))
                        .waitSeconds(.5)
                        .strafeToLinearHeading(new Vector2d(5, 50), Math.toRadians(270))
                        .waitSeconds(.5)
                        .strafeToLinearHeading(new Vector2d(5, 32.2), Math.toRadians(270))
                        .waitSeconds(.3)
                        .strafeTo(new Vector2d(5, 40))
                        .strafeToConstantHeading(new Vector2d(-48, 56.5))
                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .addEntity(myBot1)
                .start();
    }
}