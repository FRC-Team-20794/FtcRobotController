package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "Omni square", group = "omni drive")
public class omniSquare extends OpMode{



    private DcMotor front = null;
    private DcMotor back = null;
    private DcMotor left = null;
    private DcMotor right = null;



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

    }

    public void init_loop(){

    }

    public void start(){

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



    }

}
