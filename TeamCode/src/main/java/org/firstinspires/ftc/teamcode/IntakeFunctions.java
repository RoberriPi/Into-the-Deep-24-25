// Testing Github Desktop

package org.firstinspires.ftc.teamcode;
// testing github desktop again
import static java.lang.Thread.sleep;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.ServoConfigurationType;

public class IntakeFunctions {
    private CRServo wheelServo;
    private int toggleState = 0; // 0: power 0, 1: power -0.5, 2: power 0, 3: power 0.5

    public IntakeFunctions(HardwareMap hardwareMap) {
        wheelServo = hardwareMap.get(CRServo.class, "wheelServo");
    }


    public void initilize() throws InterruptedException {
        // Add later
    }

    public void intakeWheelServo(boolean toggleWheelServo, float holdLT, float holdRT) {
        if (toggleWheelServo) {
            if (holdLT == 1) {
                wheelServo.setPower(0.5);
                toggleState = 3;
            } else if (holdRT == 1) {
                wheelServo.setPower(-0.5);
                toggleState = 1;
            } else if (toggleWheelServo) {
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
        }
    }
}