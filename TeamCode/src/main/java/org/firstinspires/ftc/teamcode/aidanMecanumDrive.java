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
        double slowCount = gamepad1.left_trigger;
        double slowClock = gamepad1.right_trigger;

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

            if (Math.abs(frontRightPower) >= Math.abs(backRightPower)) {
                double speed = Math.hypot(x, y) / Math.abs(frontRightPower);
                frontRightPower *= speed;
                backRightPower *= speed;
            } else {
                double speed = Math.hypot(x, y) / Math.abs(backRightPower);
                frontRightPower *= speed;
                backRightPower *= speed;
            }

            backLeftPower = frontRightPower;
            frontLeftPower = backRightPower;

            if(slowClock > 0.01){

                frontRightPower -= slowClock;
                backRightPower += slowClock;
                frontLeftPower -= slowClock;
                backLeftPower += slowClock;

            }else if(slowCount > 0.01){

                frontRightPower += slowCount;
                backRightPower -= slowCount;
                frontLeftPower += slowCount;
                backLeftPower -= slowCount;

            }

        }

        double absfr = Math.abs(frontRightPower);
        double absfl = Math.abs(frontLeftPower);
        double absbr = Math.abs(backRightPower);
        double absbl = Math.abs(backLeftPower);

        if (absfr >= absfl && absfr >= absbl && absfr >= absbr){

            double speed = Math.hypot(x,y)/absfr;
            frontLeftPower /= speed;
            frontRightPower /= speed;
            backLeftPower /= speed;
            backRightPower /= speed;

        }else if(absfl >= absfr && absfl >= absbl && absfl >= absbr){

            double speed = Math.hypot(x,y)/absfl;
            frontLeftPower /= speed;
            frontRightPower /= speed;
            backLeftPower /= speed;
            backRightPower /= speed;

        }else if(absbr >= absfr && absbr >= absfl && absbr >= absbl){

            double speed = Math.hypot(x,y)/absbr;
            frontLeftPower /= speed;
            frontRightPower /= speed;
            backLeftPower /= speed;
            backRightPower /= speed;

        }else{

            double speed = Math.hypot(x,y)/absbl;
            frontLeftPower /= speed;
            frontRightPower /= speed;
            backLeftPower /= speed;
            backRightPower /= speed;

        }

        frontLeft.setPower(Range.clip(frontLeftPower, -1, 1));
        frontRight.setPower(Range.clip(frontRightPower, -1, 1));
        backLeft.setPower(Range.clip(backLeftPower, -1, 1));
        backRight.setPower(Range.clip(backRightPower, -1, 1));

    }
    public void stop(){

    }
}
