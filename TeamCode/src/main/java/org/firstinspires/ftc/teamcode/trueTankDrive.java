package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "2 motor tank drive", group = "two motor")
public class trueTankDrive extends OpMode {
    //clock
    private ElapsedTime runtime = new ElapsedTime();
    //defines motor variables
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor turnTable = null;
    private Servo leftGrabber = null;
    private Servo rightGrabber = null;

    public static double MAX_POSITION = 1.0;
    public static double MIN_POSITION = 0.0;
    @Override
    public void init() {

        telemetry.addData("Starting robot", "Skynet override success");
        //defines motors as motors
        leftFront = hardwareMap.get(DcMotor.class, "left");
        rightFront = hardwareMap.get(DcMotor.class, "right");
        turnTable = hardwareMap.get(DcMotor.class, "turnTable");

        //defines a front
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        turnTable.setDirection(DcMotor.Direction.REVERSE);

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
        double leftPower;
        double rightPower;
        double tablePower;
        double grabberPower;
        //get inputs from bumpers
        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;
        boolean ducks = gamepad2.y;
        boolean grabber = gamepad2.x;
        //get inputs from stick
        double leftDrive = gamepad1.left_stick_y;
        double rightDrive = gamepad1.right_stick_y;

        //bumpers override the sticks if they are pressed
        if(leftBumper){
            leftDrive = -1;
        }
        if(rightBumper){
            rightDrive = -1;
        }
        if(ducks){
            tablePower = 1;
        }
        else{
            tablePower = 0;
        }

        if (grabber){
            grabberPower = 0.5;
        }
        else{
            grabberPower = 0;
        }

        //sets power level
        leftPower = Range.clip(leftDrive, -1.0, 1.0);
        rightPower = Range.clip(rightDrive, -1.0, 1.0);

        //gives power to the wheels
        leftFront.setPower(leftPower);
        rightFront.setPower(rightPower);
        turnTable.setPower(tablePower);
        leftGrabber.setPosition(Range.clip(grabberPower, MIN_POSITION, MAX_POSITION));
        //data
        telemetry.addData("Time spent fleeing:", " " + runtime.toString());
        telemetry.addData("Stats", "");
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);

    }

    @Override
    public void stop(){




    }
}
