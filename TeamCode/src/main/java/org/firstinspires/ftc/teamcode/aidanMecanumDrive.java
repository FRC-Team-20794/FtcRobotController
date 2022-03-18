package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Mecanum Drive", group = "mecanum drive")

public class aidanMecanumDrive extends OpMode{

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    public void init(){

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class,"backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("bootup complete", "");

    }

    public void init_loop(){

    }

    public void start(){

    }

    public void loop() {
        double frontLeftPower;
        double frontRightPower;
        double backLeftPower;
        double backRightPower;

        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        boolean quickCount = gamepad1.left_bumper;
        boolean quickClock = gamepad1.right_bumper;
        //motor 1 is x+y, motor 2 is x-y. both should value 50.

        //for quickturns
        if (quickClock || quickCount) {
            if(quickClock){

                frontRightPower = 1;
                backRightPower = -1;
                frontLeftPower = -1;
                backLeftPower = 1;

            }else{

                frontRightPower = -1;
                backRightPower = 1;
                frontLeftPower = 1;
                backLeftPower = -1;

            }
// actual code below
        } else {

            double difference = x - y;
            frontRightPower = y;
            frontRightPower += difference / 2;
            backRightPower = difference / 2;

            if (frontRightPower >= backRightPower) {
                double speed = Math.hypot(x, y) / frontRightPower;
                frontRightPower *= speed;
                backRightPower *= speed;
            } else {
                double speed = Math.hypot(x, y) / backRightPower;
                frontRightPower *= speed;
                backRightPower *= speed;
            }

            backLeftPower = frontRightPower;
            frontLeftPower = backRightPower;

        }

        frontLeft.setPower(Range.clip(frontLeftPower, -1, 1));
        frontRight.setPower(Range.clip(frontRightPower, -1, 1));
        backLeft.setPower(Range.clip(backLeftPower, -1, 1));
        backRight.setPower(Range.clip(backRightPower, -1, 1));

    }
    public void stop(){

    }
}
