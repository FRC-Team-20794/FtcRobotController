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
        double backPower;
        double leftPower = yInput;
        double rightPower;
        double ratio;

        boolean clockTurn = gamepad1.right_bumper;
        boolean countclockTurn = gamepad1.left_bumper;

        telemetry.addData(String.valueOf(gamepad1.left_stick_y), String.valueOf(gamepad1.left_stick_x));


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

        double totalDistance = (frontTotalDegrees-backTotalDegrees)*2*pi*pi*diameter/encoderTick;
        double turnedOffset = totalDistance/(wheelbase*pi)*2*pi;

        double frontLastVal = 0.0;
        double leftLastVal = 00;

        while(frontPower - frontLastVal <= 0.1 && leftPower - leftLastVal <= -0.1) {

            frontLastVal = frontPower;
            leftLastVal = leftPower;

            double xcorrect = leftPower * Math.tan(turnedOffset);

            frontPower += xcorrect;

            double ycorrect = frontPower * Math.tan(turnedOffset);

            leftPower += ycorrect;

            if(frontPower>=leftPower){

                ratio = 1.0/frontPower;
                frontPower = 1.0;
                leftPower = leftPower*ratio;

            }
            else{
                ratio = 1.0/leftPower;

                frontPower = frontPower*ratio;
                leftPower = 1.0;
            }

        }
        rightPower = leftPower;
        backPower = frontPower;

        if(clockTurn){
            frontPower = 1.0;
            backPower = -1.0;
            leftPower = -1.0;
            rightPower = 1.0;
        }
        if(countclockTurn){
            frontPower = -1.0;
            backPower = 1.0;
            leftPower = 1.0;
            rightPower = -1.0;
        }



        //EXPERIMENTAL

        double hyp = Math.sqrt(Math.pow(gamepad1.right_stick_x, 2)+Math.pow(gamepad1.right_stick_y, 2));
        if (hyp >= 0.9){

            double setDegree = Math.asin(gamepad1.right_stick_y/hyp);
            if(setDegree - turnedOffset > 181){

                frontPower -= 0.2;
                leftPower -= 0.2;

            }
            else if (setDegree - turnedOffset < 179){

                backPower -= 0.2;
                rightPower -= 0.2;

            }
        }

        front.setPower(Range.clip(frontPower, -1.0, 1.0));
        back.setPower(Range.clip(backPower, 1.0, -1.0));
        left.setPower(Range.clip(leftPower, -1.0, 1.0));
        right.setPower(Range.clip(rightPower, -1.0, 1.0));
    }

}
