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
    private DcMotor turnTable = null;

    public void init() {

        telemetry.addData("Initiating startup", " ");

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        turnTable = hardwareMap.get(DcMotor.class, "turnTable");

        telemetry.addData("Motors starting up", " ");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        turnTable.setDirection(DcMotor.Direction.REVERSE);

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
        double frontStarboard;
        double backPort;
        double backStarboard;
        boolean ducks = gamepad2.right_bumper;
        double ducks2;

        //getting stick inputs + right stick directional drift
        frontPort1 = (gamepad1.left_stick_y + gamepad1.left_stick_x);
        frontStarboard1 = ((-1*(gamepad1.left_stick_y)) + gamepad1.right_stick_x);
        backPort1 = ((-1*(gamepad1.left_stick_y)) + gamepad1.right_stick_x);
        backStarboard1 = (gamepad1.left_stick_y + gamepad1.left_stick_x);

        frontPort2 = (gamepad1.right_stick_x);
        frontStarboard2 = (gamepad1.right_stick_x);
        backPort2 = (-1*(gamepad1.right_stick_x));
        backStarboard2 = (-1*(gamepad1.right_stick_x));

        double frontPort3 = frontPort1 + frontPort2;
        double frontStarboard3 = frontStarboard1 + frontStarboard2;
        double backPort3 = backPort1 + backPort2;
        double backStarboard3 = backStarboard1 + backStarboard2;
        double frontPort4;
        double frontStarboard4;
        double backPort4;
        double backStarboard4;
        double fp;
        double fs;
        double bp;
        double bs;

        frontPort4 = frontPort3;
        frontStarboard4 = frontStarboard3;
        backPort4 = backPort3;
        backStarboard4 = backStarboard3;

        if(frontPort3 > 1) {
            fp = frontPort4 / frontPort4;
        }

        if(frontStarboard3 > 1) {
            fs = frontStarboard4 / frontStarboard4;
        }

        if(backPort3 > 1) {
            bp = backPort4 / backPort4;
        }

        if(backStarboard3 > 1) {
            bs = backStarboard4 / backStarboard4;
        }

        //setting power max of driving and drift
        double frontPort = fp;
            frontStarboard = fs;
            backPort = bp;
            backStarboard = bs;

            frontLeft.setPower(frontPort);
            frontRight.setPower(frontStarboard);
            backLeft.setPower(backPort);
            backRight.setPower(backStarboard);

        if(ducks){
            ducks2 = 1.0;
        }

        else{
            ducks2 = 0.0;
        }

            turnTable.setPower(ducks2);

        telemetry.addData("Startup complete", " ");

        }

    @Override
    public void stop(){

    }
}
