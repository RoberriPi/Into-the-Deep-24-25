package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;
import com.arcrobotics.ftclib.gamepad.*;

public class Robot {



    private DcMotor leftFront, leftRear, rightFront, rightRear;
    private Servo wristServo, clawServo;
    private DcMotorEx armMotor, viper;
    private boolean wasToggleWristPressed, wasClawPressed = false;
    private double wristPosition, clawPosition;
    private boolean isButton1BACK = false;
    private boolean isButton1START = false;
    private double speedMult = 1;
    private IState currentState;

    public Robot(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        wristPosition = wristServo.getPosition();
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        viper = hardwareMap.get(DcMotorEx.class, "viper");
        viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void updateLoop(GamepadEx gamepadEx1, GamepadEx gamepadEx2, boolean prevGP2Start) {

        setMotorPowers(gamepadEx1.getLeftY() * speedMult, gamepadEx1.getLeftX() * speedMult, gamepadEx1.getRightX() * speedMult * 0.6);
        double clawPos = claw(gamepadEx2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER), gamepadEx2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER), gamepadEx2.wasJustPressed(GamepadKeys.Button.A));
        int armMotorPos = armMotor(gamepadEx2.isDown(GamepadKeys.Button.DPAD_LEFT), gamepadEx2.isDown(GamepadKeys.Button.DPAD_RIGHT));
        double viperPos = viperSlide(gamepadEx2.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON), gamepadEx2.wasJustPressed(GamepadKeys.Button.B), gamepadEx2.isDown(GamepadKeys.Button.LEFT_BUMPER), gamepadEx2.isDown(GamepadKeys.Button.RIGHT_BUMPER));
        double wristPos = intakeWrist(gamepadEx2.isDown(GamepadKeys.Button.DPAD_UP), gamepadEx2.isDown(GamepadKeys.Button.DPAD_UP), gamepadEx2.wasJustPressed(GamepadKeys.Button.X));
        currentState.execute(this, gamepadEx1, gamepadEx2, prevGP2Start);
    }

    public void initilize() throws InterruptedException {
        currentState = new ManualMode();
    }

    public void setState(ERobotState state) {
        if (state == ERobotState.Manual) {
            currentState = new ManualMode();
        } else if (state == ERobotState.Auto) {
            currentState = new AutoMode();
        }

    }

    public void setMotorPowers(double forward, double strafe, double rotation) {
        double powerdenom = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotation), 1);

        double frontLeftPower = (forward + strafe + rotation) / powerdenom;
        double backLeftPower = (forward - strafe + rotation) / powerdenom;
        double frontRightPower = (forward - strafe - rotation) / powerdenom;
        double backRightPower = (forward + strafe - rotation) / powerdenom;

        leftFront.setPower(frontLeftPower);
        leftRear.setPower(backLeftPower);
        rightFront.setPower(frontRightPower);
        rightRear.setPower(backRightPower);
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
        } else if (GP2LB) {
            viper.setTargetPosition(viper.getCurrentPosition() - 200);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(1);
        } else if (GP2RB) {
            viper.setTargetPosition(viper.getCurrentPosition() + 150);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(1);
        }
        return viper.getCurrentPosition();
    }

    public double claw(double holdLT, double holdRT, boolean GP2A) {
        if (holdLT > 0.2) {
            clawServo.setPosition(clawServo.getPosition() + 0.01);
        } else if (holdRT > 0.2) {
            clawServo.setPosition(clawServo.getPosition() - 0.01);
        } else if (GP2A && !wasClawPressed) {
            if (clawServo.getPosition() < 0.2) {
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


    public void updateSpeedMultiplier(Gamepad gamepad1) {
        if (gamepad1.back) {
            if (!isButton1BACK) {
                speedMult = (speedMult == 1) ? 0.4 : 1;
                isButton1BACK = true;
            }
        } else {
            isButton1BACK = false;
        }

        if (gamepad1.start) {
            if (!isButton1START) {
                speedMult = 0.6;
                isButton1START = true;
            }
        } else {
            isButton1START = false;
        }
    }

    enum ERobotState {
        Manual,
        Auto
    }
}