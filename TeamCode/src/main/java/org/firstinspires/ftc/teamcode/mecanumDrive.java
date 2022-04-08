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
    //private DcMotor turnTable = null;

    public void init() {

        telemetry.addData("Initiating startup", " ");

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        //turnTable = hardwareMap.get(DcMotor.class, "turnTable");

        telemetry.addData("Motors starting up", " ");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        //turnTable.setDirection(DcMotor.Direction.REVERSE);

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
        boolean ducks = gamepad2.right_bumper;
        double ducks2;
        boolean LB;
        boolean RB;

        //getting stick inputs + right stick directional drift
        frontPort1 = (gamepad1.left_stick_y);
        frontStarboard1 = (-1*(gamepad1.left_stick_y));
        backPort1 = (-1*(gamepad1.left_stick_y));
        backStarboard1 = (gamepad1.left_stick_y);

        LB = gamepad1.left_bumper;
        RB = gamepad1.right_bumper;

        //setting power max of driving and drift

            if(LB){
                frontPort1 = -1.0;
                frontStarboard1 = 1.0;
                backPort1 = -1.0;
                backStarboard1 = 1.0;}

            if(RB){
                frontPort1 = -1.0;
                frontStarboard1 = -11.0;
                backPort1 = 1.0;
                backStarboard1 = 1.0;
            }

            frontPort = frontPort1;
            frontStarboard = frontStarboard1;
            backPort = backPort1;
            backStarboard = backStarboard1;

            frontLeft.setPower(frontPort);
            frontRight.setPower(frontStarboard);
            backLeft.setPower(backPort);
            backRight.setPower(backStarboard);

        /*if(ducks){
            ducks2 = 1.0;
        }

        else{
            ducks2 = 0.0;
        }

            turnTable.setPower(ducks2);*/

        telemetry.addData("Startup complete", " ");

        }

    @Override
    public void stop(){

    }
}
