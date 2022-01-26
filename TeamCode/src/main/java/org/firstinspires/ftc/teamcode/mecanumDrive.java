package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Mecanum Drive", group = "mecanum drive")

public class mecanumDrive extends OpMode{

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    public void init() {

        telemetry.addData("Initiating startup", " ");

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        telemetry.addData("Motors starting up", " ");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Motors online", " ");

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    @Override
    public void loop(){

        //defined variables
        double frontPort1;
        double frontStarboard1;
        double backPort1;
        double backStarboard1;
        double frontPort2;
        double frontStarboard2;
        double backPort2;
        double backStarboard2;
        double frontPort;
        double frontStarboard;
        double backPort;
        double backStarboard;

        //getting stick inputs + right stick directional drift
        frontPort1 = (-1*(gamepad1.left_stick_y) + gamepad1.left_stick_x);
        frontStarboard1 = (gamepad1.left_stick_y + gamepad1.right_stick_x);
        backPort1 = (gamepad1.left_stick_y + gamepad1.right_stick_x);
        backStarboard1 = (-1*(gamepad1.left_stick_y) + gamepad1.left_stick_x);

        frontPort2 = (-1*(gamepad1.right_stick_x));
        frontStarboard2 = (-1*(gamepad1.right_stick_x));
        backPort2 = gamepad1.right_stick_x;
        backStarboard2 = gamepad1.right_stick_x;

        //setting power max of driving and drift
            frontPort = (Range.clip(frontPort1, -0.8, 0.8) + Range.clip(frontPort2, -0.2, 0.2));
            frontStarboard = (Range.clip(frontStarboard1, -0.8, 0.8) + Range.clip(frontStarboard2, -0.2, 0.2));
            backPort = (Range.clip(backPort1, -0.8, 0.8) + Range.clip(backPort2, -0.2, 0.2));
            backStarboard = (Range.clip(backStarboard1, -0.8, 0.8) + Range.clip(backStarboard2, -0.2, 0.2));

            frontLeft.setPower(frontPort);
            frontRight.setPower(frontStarboard);
            backLeft.setPower(backPort);
            backRight.setPower(backStarboard);

        telemetry.addData("Startup complete", " ");

        }

    @Override
    public void stop(){

    }
}
