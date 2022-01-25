package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


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

        double frontPower;
        double backPower;
        double leftPower;
        double rightPower;




    }

}
