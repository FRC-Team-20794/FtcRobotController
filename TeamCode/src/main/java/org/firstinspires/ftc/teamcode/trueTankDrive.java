package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="2 Motor Tank Drive")
public class trueTankDrive extends OpMode{
    //clock
    private ElapsedTime runtime = new ElapsedTime();
    //defines motor variables
    private DcMotor left = null;
    private DcMotor right = null;
    private DcMotor table = null;
    private Servo grabberLeft = null;
    double leftServoInc = 0.0;

    public void init() {

        telemetry.addData("Starting robot", "Skynet override success");
        //defines motors as motors
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        table = hardwareMap.get(DcMotor.class, "turnTable");
        grabberLeft = hardwareMap.get(Servo.class, "grabberLeft");

        //defines a front
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);
        table.setDirection(DcMotor.Direction.REVERSE);
        grabberLeft.setDirection(Servo.Direction.FORWARD);

        telemetry.addData("Skynet Initialized", "May your last moments be unhappy");

    }

    public void init_loop() {

    }

    public void start() {
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void loop() {
        //telemetry.addData("Crash test loop", "");
        //defining a power variable
        double leftPower;
        double rightPower;
        double tablePower;

        //telemetry.addData("Crash test 1", "");

        //get inputs from bumpers
        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;
        boolean yButton = gamepad1.y;
        boolean xButton = gamepad2.x;
        //get inputs from stick
        double leftDrive = gamepad1.left_stick_y;
        double rightDrive = gamepad1.right_stick_y;

        //telemetry.addData("Crash test 2", "");

        //bumpers override the sticks if they are pressed
        if(leftBumper){
            leftDrive = -1.0;
        }
        if(rightBumper){
            rightDrive = -1.0;
        }
        if(gamepad2.right_bumper){
            tablePower = 1.0;
        }
        else if(gamepad2.left_bumper){
            tablePower = -1.0;
        }
        else{
            tablePower = 0.0;
        }
        if(xButton){
            leftServoInc =  0.85;
        }
        else{
            leftServoInc = 0.25;
        }

        //telemetry.addData("Crash test 3", "");

        //sets power level
        leftPower = Range.clip(leftDrive, -1.0, 1.0);
        rightPower = Range.clip(rightDrive, -1.0, 1.0);
        //telemetry.addData("Crash test 4", "");
        //TEST THIS FIRST, UNTESTED AND WILL MOST LIKELY BURN OUT SERVO
        grabberLeft.setPosition(Range.clip(leftServoInc, 0.0, 0.8));
        //telemetry.addData("Crash test 5", "");
        //gives power to the wheels
        left.setPower(leftPower);
        right.setPower(rightPower);
        table.setPower(tablePower);
        //data
        String encoderTest = Integer.toString(left.getCurrentPosition());
        telemetry.addData("Time spent fleeing:", " " + runtime.toString());
        telemetry.addData("Stats", tablePower+"");
        telemetry.addData("testing", table.getCurrentPosition() + "");
        telemetry.addData("gamepad", gamepad1.left_stick_y + "+" + gamepad1.right_stick_y);
        telemetry.addData("left motor", ""+leftDrive);
        telemetry.addData("right motor", rightDrive);
    }


    public void stop(){

    }
}
