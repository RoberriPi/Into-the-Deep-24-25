package org.firstinspires.ftc.teamcode.DegradedFiles;
import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.*;

public class IntakeFunctions {
    private Servo wristServo, clawServo;
    private DcMotorEx armMotor, viper, lifter;
    private boolean wasToggleWristPressed, wasClawPressed = false;
    private double wristPosition, clawPosition;

    public IntakeFunctions(HardwareMap hardwareMap) {
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        wristPosition = wristServo.getPosition();
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        viper = hardwareMap.get(DcMotorEx.class, "viper");
        viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifter = hardwareMap.get(DcMotorEx.class, "lifter");
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void initilize() throws InterruptedException {

    }



    public int armMotor(boolean GP2dpadL, boolean GP2dpadR) {
        if (GP2dpadL) {
            armMotor.setTargetPosition(armMotor.getCurrentPosition() + 100);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(1);
        } else if (GP2dpadR) {
            armMotor.setTargetPosition(armMotor.getCurrentPosition() - 100);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(1);
        }
        return armMotor.getCurrentPosition();
    }
    /*public double intakeWrist(boolean toggleWrist) {
        if (toggleWrist && !wasToggleWristPressed) {
            wristPosition = (wristPosition == 0.2) ? 0.5 : 0.2;
            wristServo.setPosition(wristPosition);
            wasToggleWristPressed = true;
        } else if (!toggleWrist) {
            wasToggleWristPressed = false;
        }
        return wheelRotServo.getPosition();
    }*/
    public double intakeWrist(boolean toggleWristUP, boolean toggleWristDOWN, boolean GP2X) {
        if (toggleWristUP) {
            wristServo.setPosition(wristServo.getPosition() + 0.01);
        } else if (toggleWristDOWN) {
            wristServo.setPosition(wristServo.getPosition() - 0.01);
        } else if (GP2X && !wasToggleWristPressed) {
            if (wristServo.getPosition() < 0.7) {
                wristPosition = 0.9;
            } else if (wristServo.getPosition() > 0.4) {
                wristPosition = 0.3;
            }
            wristServo.setPosition(wristPosition);
            wasToggleWristPressed = true;
        } else if (!GP2X) {
            wasToggleWristPressed = false;
        }
        return wristServo.getPosition();
    }

    public double viperSlide(boolean toggleViperA, boolean toggleViperB, boolean GP2LB, boolean GP2RB) {
        if (toggleViperA) {
            viper.setTargetPosition(-50);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(1);
        } else if (toggleViperB) {
            viper.setTargetPosition(-2000);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(1);
        } else if (GP2LB /*&& viper.getCurrentPosition() < -2250*/) {
            viper.setTargetPosition(viper.getCurrentPosition() - 200);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(1);
        } else if (GP2RB /*&& viper.getCurrentPosition() > -10*/) {
            viper.setTargetPosition(viper.getCurrentPosition() + 150);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(1);
        }
        return viper.getCurrentPosition();
    }

    public int lifter(boolean GP1Y, boolean GP1LB, boolean GP1X, boolean GP1RB) {
        if (GP1Y) {
            lifter.setTargetPosition(0);
            lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lifter.setPower(1);
        } else if (GP1LB) {
            lifter.setTargetPosition(lifter.getCurrentPosition() + 100);
            lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lifter.setPower(1);
        } else if (GP1X) {
            lifter.setTargetPosition(3000);
            lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lifter.setPower(1);
        } else if (GP1RB) {
            lifter.setTargetPosition(lifter.getCurrentPosition() - 100);
            lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lifter.setPower(1);
        }
        return lifter.getCurrentPosition();
    }

    public double claw(float holdLT, float holdRT, boolean GP2A) {
        if (holdLT > 0.2) {
            clawServo.setPosition(clawServo.getPosition()+0.01);
        } else if (holdRT > 0.2) {
            clawServo.setPosition(clawServo.getPosition()-0.01);
        } else if (GP2A && !wasClawPressed) {
            if (clawServo.getPosition() < 0.2) { // CLOSE : 0.1 ; OPEN : 0.35
                clawPosition = 0.35;
            } else if (clawServo.getPosition() > 0.2) {
                clawPosition = 0.1;
            }
            clawServo.setPosition(clawPosition);
            wasClawPressed = true;
        } else if (!GP2A) {
            wasClawPressed = false;
        }
        return clawServo.getPosition();
    }
    /*public double rightWheel(float holdLT, float holdRT) {
        if (holdLT > 0.2) {
            rightWheelServo.setPosition(rightWheelServo.getPosition()-0.01);
        } else if (holdRT > 0.2) {
            rightWheelServo.setPosition(rightWheelServo.getPosition()+0.01);
        } else {
            rightWheelServo.setPosition(rightWheelServo.getPosition());
        }
        return rightWheelServo.getPosition();
    }*/
}