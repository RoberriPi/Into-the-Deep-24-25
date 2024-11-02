package org.firstinspires.ftc.teamcode;
import static java.lang.Thread.sleep;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.*;

public class IntakeFunctions {
    private CRServo wheelServo;
    private Servo wheelRotServo, wristServo;
    private DcMotorEx armMotor, viper, lifter;
    private int toggleState = 0;
    private boolean wasToggleWheelServoPressed = false;
    private boolean wasToggleRotServoPressed = false;
    private boolean wasToggleArmMotorPressed = false;
    private boolean wasToggleWristPressed = false;
    private double rotServoPosition;
    private double wristPosition;

    public IntakeFunctions(HardwareMap hardwareMap) {
        wheelServo = hardwareMap.get(CRServo.class, "wheelServo");
        wheelRotServo = hardwareMap.get(Servo.class, "wheelRotServo");
        rotServoPosition = wheelRotServo.getPosition();
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
        // Add later
    }

    public double intakeRotServo(boolean toggleRotServo) {
        if (toggleRotServo && !wasToggleRotServoPressed) {
            rotServoPosition = (rotServoPosition == 0.2) ? 0.5 : 0.2;
            wheelRotServo.setPosition(rotServoPosition);
            wasToggleRotServoPressed = true;
        } else if (!toggleRotServo) {
            wasToggleRotServoPressed = false;
        }
        return wheelRotServo.getPosition();
    }

    /*public int armMotor(boolean toggleArmMotor) {
        if (toggleArmMotor && !wasToggleArmMotorPressed) {
            if (armMotor.getTargetPosition() < 500) {
                armMotor.setTargetPosition(30);
            } else {
                armMotor.setTargetPosition(10);
            }
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(0.1);
            wasToggleArmMotorPressed = true;
        } else if (!toggleArmMotor) {
            wasToggleArmMotorPressed = false;
        }
        return armMotor.getCurrentPosition();
    }*/
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
    public double intakeWrist(boolean toggleWristUP, boolean toggleWristDOWN) {
        if (toggleWristUP) {
            wristServo.setPosition(wristServo.getPosition()+0.05);
        } else if (toggleWristDOWN) {
            wristServo.setPosition(wristServo.getPosition()-0.05);
        }
        return wheelRotServo.getPosition();
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
        } else if (GP2LB) {
            viper.setTargetPosition(viper.getCurrentPosition() - 100);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(1);
        } else if (GP2RB) {
            viper.setTargetPosition(viper.getCurrentPosition() + 100);
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

    public double intakeWheelServo(boolean toggleWheelServo, float holdLT, float holdRT) {
        if (holdLT > 0.5) {
            wheelServo.setPower(0.5);
            toggleState = 3;
        } else if (holdRT > 0.5) {
            wheelServo.setPower(-0.5);
            toggleState = 1;
        } else if (toggleWheelServo && !wasToggleWheelServoPressed) {
            toggleState = (toggleState + 1) % 4;
            switch (toggleState) {
                case 0:
                case 2:
                    wheelServo.setPower(0);
                    break;
                case 1:
                    wheelServo.setPower(-0.5);
                    break;
                case 3:
                    wheelServo.setPower(0.5);
                    break;
            }
        }
        wasToggleWheelServoPressed = toggleWheelServo;
        return wheelServo.getPower();
    }
}