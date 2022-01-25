package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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
        double backSlash;
        double forwardSlash;

        //getting stick inputs
        backSlash = (-1*(gamepad1.left_stick_y)) + gamepad1.left_stick_x;
        forwardSlash = gamepad1.left_stick_y + (-1*(gamepad1.left_stick_x));

        frontLeft.setPower(backSlash);
        frontRight.setPower(forwardSlash);
        backLeft.setPower(forwardSlash);
        backRight.setPower(backSlash);

        telemetry.addData("Startup complete", " ");

        }

    @Override
    public void stop(){

    }
}
