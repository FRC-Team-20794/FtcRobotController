package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "encoder test", group = "tests")
public class encoderTest extends OpMode{

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    int maxEncode;

    public void init(){

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class,"backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

//        frontLeft.setMotorType(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("bootup complete", "");

    }

    public void init_loop(){

    }

    public void start(){


        maxEncode = frontLeft.getCurrentPosition();

    }

    public void loop(){
        frontLeft.setPower(0.1);
        int temp = frontLeft.getCurrentPosition();
        if(temp >= maxEncode){
            maxEncode = temp;
            telemetry.clear();
            telemetry.addData("max found encoder", maxEncode + "");
        }


    }

    public void stop(){

    }
}
