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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-7.4, 62, Math.toRadians(270)))
                .waitSeconds(1.5)
                .strafeToLinearHeading(new Vector2d(-5, 32.5), Math.toRadians(270)) // Move forward
                .waitSeconds(1)
                .strafeTo(new Vector2d(-5, 40)) // Back up a little
                .strafeToLinearHeading(new Vector2d(-35, 40), Math.toRadians(0)) // Move to line up to traverse
                .strafeToConstantHeading(new Vector2d(-35, 14)) // Traverse
                .strafeToConstantHeading(new Vector2d(-47, 14.02)) // Line up to push first
                .strafeToConstantHeading(new Vector2d(-47, 52)) // Push first sample
                .strafeToLinearHeading(new Vector2d(-48, 57.25), Math.toRadians(90)) // Line up to pick up from wall
                .waitSeconds(1)
                .waitSeconds(1) // begin raise after this
                .strafeToLinearHeading(new Vector2d(0, 50), Math.toRadians(270)) // Move into hooking position
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(0, 32.2), Math.toRadians(270)) // Move forward
                .waitSeconds(1.5)
                .strafeTo(new Vector2d(0, 40)) // Back up a little
                .strafeToLinearHeading(new Vector2d(-48, 57.25), Math.toRadians(90)) // Line up to pick up from wall
                .waitSeconds(1)
                .waitSeconds(1) // begin raise after this
                .strafeToLinearHeading(new Vector2d(0, 50), Math.toRadians(270)) // Move into hooking position
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(0, 32.2), Math.toRadians(270)) // Move forward
                .waitSeconds(1.5)
                .strafeTo(new Vector2d(0, 40)) // Back up a little
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}