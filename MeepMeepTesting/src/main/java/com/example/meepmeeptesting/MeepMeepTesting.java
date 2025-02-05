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
                .strafeToLinearHeading(new Vector2d(-5, 32.5), Math.toRadians(270)) // Move forward
                .strafeTo(new Vector2d(-5, 40)) // Back up a little
// End Drop
                .strafeToLinearHeading(new Vector2d(-35, 40), Math.toRadians(0)) // Move to line up to traverse
                .strafeToConstantHeading(new Vector2d(-35, 14)) // Traverse
                .strafeToConstantHeading(new Vector2d(-47, 14.02)) // Line up to push first
                .strafeToConstantHeading(new Vector2d(-47, 52)) // Push first sample
                .strafeToConstantHeading(new Vector2d(-40, 20)) // Line up to push second | 1
                .splineToLinearHeading(new Pose2d(-57, 14, Math.toRadians(0)), Math.PI*15) // Line up to push second | 2
                .strafeToConstantHeading(new Vector2d(-57, 52)) // Push second sample
                .strafeToLinearHeading(new Vector2d(-48, 57.5), Math.toRadians(90)) // Line up to pick up from wall
                .strafeToLinearHeading(new Vector2d(0, 50), Math.toRadians(270)) // Move into hooking position
                .strafeToLinearHeading(new Vector2d(0, 32.2), Math.toRadians(270)) // Move forward
                .strafeTo(new Vector2d(0, 40)) // Back up a little
                .strafeToLinearHeading(new Vector2d(-48, 58.3), Math.toRadians(90)) // Move back to wall
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}