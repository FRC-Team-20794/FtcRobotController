package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Real Tank Drive")
public class tankDrive extends OpMode{
    //clock
    private ElapsedTime runtime = new ElapsedTime();
    //defines motor variables
    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;
    private CRServo turnTable = null;
    @Override
    public void init() {

        telemetry.addData("Starting robot", "Skynet override success");
        //defines motors as motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        turnTable = hardwareMap.get(CRServo.class, "turnTable");

        //defines a front
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Skynet Initialized", "May your last moments be unhappy");

    }
    @Override
    public void init_loop() {

    }
    @Override
    public void start() {

    }
    @Override
    public void loop() {
        //defining a power variable
        double leftFrontPower;
        double leftBackPower;
        double rightFrontPower;
        double rightBackPower;
        double tablePower;
        //get inputs from bumpers
        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;
        boolean ducks = gamepad1.y;
        //get inputs from stick
        double leftDrive = gamepad1.left_stick_y;
        double rightDrive = gamepad1.right_stick_y;
        float leftDrift = gamepad1.left_trigger;
        float rightDrift = gamepad1.right_trigger;
        //bumpers override the sticks if they are pressed
        if(leftBumper){
            leftDrive = 1;
        }
        if(rightBumper){
            rightDrive = 1;
        }
        if(ducks){
            tablePower = 1;
        }
        else{
            tablePower = 0;
        }
        //sets power level
        leftFrontPower = Range.clip(leftDrive, -1.0, 1.0);
        leftBackPower = Range.clip(rightDrive, -1.0, 1.0);
        rightFrontPower = Range.clip(rightDrive, -1.0, 1.0);
        rightBackPower = Range.clip(rightDrive, -1.0, 1.0);

        if (leftDrift > 0.1){
            leftBackPower = 0;
        }
        if(rightDrift > 0.1){
            rightBackPower = 0;
        }


        //gives power to the wheels
        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
        turnTable.setPower(tablePower);
        //data
        telemetry.addData("Time spent fleeing:", " " + runtime.toString());
        telemetry.addData("Stats", "");
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftFrontPower, rightFrontPower);

    }

    @Override
    public void stop(){

    }
}
