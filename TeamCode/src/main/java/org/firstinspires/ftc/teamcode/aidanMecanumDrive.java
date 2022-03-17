package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

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

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("bootup complete", "");

    }

    public void init_loop(){

    }

    public void start(){

    }

    public void loop(){
        double frontLeftPower;
        double frontRightPower;
        double backLeftPower;
        double backRightPower;

        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;

        //motor 1 is x+y, motor 2 is x-y. both should value 50.

        double difference = x-y;
        if (difference >= 0){

        }

    }

    public void stop(){

    }
}
