package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;
import com.arcrobotics.ftclib.gamepad.*;

public class Robot {



    private DcMotor leftFront, leftRear, rightFront, rightRear;
    private Servo wristServo, clawServo;
    private DcMotorEx armMotor, viper;
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
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        viper = hardwareMap.get(DcMotorEx.class, "viper");
        viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void updateLoop(GamepadEx gamepadEx1, GamepadEx gamepadEx2) {
        updateSpeedMultiplier(gamepadEx1.wasJustPressed(GamepadKeys.Button.BACK));
        setMotorPowers(gamepadEx1.getLeftY() * speedMult, gamepadEx1.getLeftX() * speedMult, gamepadEx1.getRightX() * speedMult * 0.6);
        currentState.execute(this, gamepadEx1, gamepadEx2);
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
    public String getCurrentState() {
        if (currentState instanceof ManualMode) {
            return "Manual";
        } else if (currentState instanceof AutoMode) {
            return "Auto";
        } else {
            return "Unknown";
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

    // Arm
    public int getArmPosition() {
        return armMotor.getCurrentPosition();
    }
    public void setArmPosition(int armTarget) {
        armMotor.setTargetPosition(armTarget);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(1);
    }

    // Wrist
    public double getWristPosition() {
        return wristServo.getPosition();
    }
    public void setIntakeWrist(double targetWristPosition) { // .01
        wristServo.setPosition(targetWristPosition);
    }

    // Viper
    /*public double viperSlide(boolean toggleViperA, boolean toggleViperB, boolean GP2LB, boolean GP2RB) {
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
    }*/
    public int getViperPosition() {
        return viper.getCurrentPosition();
    }
    public void setViperPosition(int viperTargetPos) {
        viper.setTargetPosition(viperTargetPos);
        viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viper.setPower(1);
    }

    // Claw
    public double getClawPosition() {
        return clawServo.getPosition();
    }
    public void setClawPosition(double targetClawPosition) {
        clawServo.setPosition(targetClawPosition);
    }
    /*public void claw(double holdLT, double holdRT, boolean GP2A) {
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
    }*/


    // Speed Multiplier
    public void updateSpeedMultiplier(boolean GPex1BACK) {
        double[] speedMultipliers = {1, 0.6, 0.4};
        int currentIndex = 0;
        if (GPex1BACK) {
                currentIndex = (currentIndex + 1) % speedMultipliers.length;
                speedMult = speedMultipliers[currentIndex];
        }
    }

    enum ERobotState {
        Manual,
        Auto
    }
}