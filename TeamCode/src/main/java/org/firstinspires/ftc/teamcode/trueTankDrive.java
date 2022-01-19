package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="2 Motor Tank Drive")
public class trueTankDrive extends OpMode{
    //clock
    private ElapsedTime runtime = new ElapsedTime();
    //defines motor variables
    private DcMotor left = null;
    private DcMotor right = null;
    @Override
    public void init() {

        telemetry.addData("Starting robot", "Skynet override success");
        //defines motors as motors
        left = hardwareMap.get(DcMotor.class, "leftFront");
        right = hardwareMap.get(DcMotor.class, "rightBack");

        //defines a front
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);

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
        //get inputs from bumpers
        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;
        //get inputs from stick
        double leftDrive = gamepad1.left_stick_y;
        double rightDrive = gamepad1.right_stick_y;
        //bumpers override the sticks if they are pressed
        if(leftBumper){
            leftDrive = 1;
        }
        if(rightBumper){
            rightDrive = 1;
        }

        //sets power level
        leftPower = Range.clip(leftDrive, -1.0, 1.0);
        rightPower = Range.clip(rightDrive, -1.0, 1.0);

        //gives power to the wheels
        left.setPower(leftPower);
        right.setPower(rightPower);
        //data
        telemetry.addData("Time spent fleeing:", " " + runtime.toString());
        telemetry.addData("Stats", "");
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);

    }

    @Override
    public void stop(){

    }
}
