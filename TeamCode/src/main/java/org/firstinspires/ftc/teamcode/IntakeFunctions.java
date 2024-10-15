package org.firstinspires.ftc.teamcode;
import static java.lang.Thread.sleep;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.*;

public class IntakeFunctions {
    private CRServo wheelServo;
    private Servo wheelRotServo;
    private int toggleState = 0;
    private boolean wasToggleWheelServoPressed = false;
    private boolean wasToggleRotServoPressed = false;
    private double rotServoPosition;

    public IntakeFunctions(HardwareMap hardwareMap) {
        wheelServo = hardwareMap.get(CRServo.class, "wheelServo");
        wheelRotServo = hardwareMap.get(Servo.class, "wheelRotServo");
        rotServoPosition = wheelRotServo.getPosition(); // Initialize based on the current position
    }

    public void initilize() throws InterruptedException {
        // Add later
    }

    public void intakeRotServo(boolean toggleRotServo) {
        if (toggleRotServo && !wasToggleRotServoPressed) {
            rotServoPosition = (rotServoPosition == 0.2) ? 0.5 : 0.2;
            wheelRotServo.setPosition(rotServoPosition);
            wasToggleRotServoPressed = true;
        } else if (!toggleRotServo) {
            wasToggleRotServoPressed = false;
        }
    }

    public double getWheelRotServoPosition() {
        return wheelRotServo.getPosition();
    }

    public void intakeWheelServo(boolean toggleWheelServo, float holdLT, float holdRT) {
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
    }
}