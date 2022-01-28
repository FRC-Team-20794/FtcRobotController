package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "Omni square", group = "omni drive")
public class omniSquare extends OpMode{


    //CONFIG(inches)

    int wheelbase = 14;
    int diameter = 6;
    int encoderTick = 360;
    //encoder tick should be the amount of ticks it takes for the encoder to wrap back to 0.

    //CONFIG END

    private DcMotor front = null;
    private DcMotor back = null;
    private DcMotor left = null;
    private DcMotor right = null;
    int frontCurrentPosition = 0;
    int frontTotalDegrees = 0;
    int backCurrentPosition = 0;
    int backTotalDegrees = 0;
    double pi = Math.PI;



    public void init(){
        telemetry.addData("starting initialization", "");

        front = hardwareMap.get(DcMotor.class, "front");
        back = hardwareMap.get(DcMotor.class, "back");
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");


        front.setDirection(DcMotor.Direction.FORWARD);
        back.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);

        front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    public void init_loop(){

    }

    public void start(){

        front.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void loop(){

        double xInput = gamepad1.left_stick_x;
        double yInput = gamepad1.left_stick_y;

        double frontPower = xInput;
        double backPower = xInput;
        double leftPower = yInput;
        double rightPower = yInput;

        boolean clockTurn = gamepad1.right_bumper;
        boolean countclockTurn = gamepad1.left_bumper;

        telemetry.addData(String.valueOf(gamepad1.left_stick_y), String.valueOf(gamepad1.left_stick_x));


        if(clockTurn){
            frontPower = 1;
            backPower = -1;
            leftPower = -1;
            rightPower = 1;
        }
        if(countclockTurn){
            frontPower = -1;
            backPower = 1;
            leftPower = 1;
            rightPower = -1;
        }

        front.setPower(Range.clip(frontPower, -1.0, 1.0));
        back.setPower(Range.clip(backPower, 1.0, -1.0));
        left.setPower(Range.clip(leftPower, -1.0, 1.0));
        right.setPower(Range.clip(rightPower, -1.0, 1.0));

        //EXPERIMENTAL
        int frontLastPostition = frontCurrentPosition;
        int frontCurrentPosition = front.getCurrentPosition();
        int backLastPostition = backCurrentPosition;
        int backCurrentPosition = back.getCurrentPosition();


        if((frontLastPostition - frontCurrentPosition)>encoderTick/2){
            //made full rotation
            frontTotalDegrees += frontCurrentPosition - frontLastPostition + encoderTick;//equalize for degree-tick
        }
        else{
            //edit for ticks-degrees
            frontTotalDegrees += frontCurrentPosition - frontLastPostition;
        }

        //back
        if((backLastPostition - backCurrentPosition)>encoderTick/2){
            //made full rotation
            backTotalDegrees += backCurrentPosition - backLastPostition + encoderTick;//equalize for degree-tick
        }
        else{
            //edit for ticks-degrees
            backTotalDegrees += backCurrentPosition - backLastPostition;
        }

        double totalDistance = ((frontTotalDegrees-backTotalDegrees)/encoderTick*360)*pi*diameter;
        double turnedOffset = totalDistance/(wheelbase*pi)*360;

    }

}
