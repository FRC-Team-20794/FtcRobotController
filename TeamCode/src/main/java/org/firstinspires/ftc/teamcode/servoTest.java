package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "servo test", group = "do not touch")
public class servoTest extends OpMode {
    private Servo leftGrabber = null;
    private Servo rightGrabber = null;

    public static double MAX_POSITION = 1.0;
    public static double MIN_POSITION = 0.0;

    public void init(){
        leftGrabber = hardwareMap.get(Servo.class, "grabberLeft");
        rightGrabber = hardwareMap.get(Servo.class, "grabberRight");

        telemetry.addData("Initial position left", leftGrabber.getPosition());
        telemetry.addData("Initial position right", rightGrabber.getPosition());

    }

    public void init_loop(){

    }

    public void start(){

    }

    public void loop(){

        double servoPos = 0;
        boolean activeServo = gamepad1.x;

        if (activeServo){
            servoPos += 0.01;
        }


        leftGrabber.setPosition(servoPos);
        rightGrabber.setPosition(-servoPos);

        telemetry.addData("Left Servo Position", leftGrabber.getPosition());
        telemetry.addData("Right Servo Position", rightGrabber.getPosition());

    }
}
