package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class HardwareSpooky
{
    //"can you just do the nuts"

    public DcMotor aMotor = null;
    public DcMotor bMotor = null;
    public DcMotor cMotor = null;
    public DcMotor dMotor = null;
    //
    public DcMotor catapult = null;
    public DcMotor ballBelt = null;
    //
    public Servo lifter = null;
    public ColorSensor colorSensor = null;
    public TouchSensor touchSensor = null;

    HardwareMap hwMap = null;
    DcMotor.RunMode selectedMode = null;

    public HardwareSpooky(DcMotor.RunMode mode)
    {
        selectedMode = mode;
    }

    public void init(HardwareMap ahwMap)
    {
        hwMap = ahwMap;

        aMotor = hwMap.dcMotor.get("a_drive");
        bMotor = hwMap.dcMotor.get("b_drive");
        cMotor = hwMap.dcMotor.get("c_drive");
        dMotor = hwMap.dcMotor.get("d_drive");
        catapult = hwMap.dcMotor.get("catapult");
        ballBelt = hwMap.dcMotor.get("ball_belt");
        lifter = hwMap.servo.get("lifter");
        colorSensor = hwMap.colorSensor.get("color");
        touchSensor = hwMap.touchSensor.get("cat_btn");

        aMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapult.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        aMotor.setMode(selectedMode);
        bMotor.setMode(selectedMode);
        cMotor.setMode(selectedMode);
        dMotor.setMode(selectedMode);
        catapult.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ballBelt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        aMotor.setDirection(DcMotor.Direction.REVERSE);
        bMotor.setDirection(DcMotor.Direction.REVERSE);
        cMotor.setDirection(DcMotor.Direction.REVERSE);
        dMotor.setDirection(DcMotor.Direction.REVERSE);
        catapult.setDirection(DcMotor.Direction.REVERSE);
        ballBelt.setDirection(DcMotor.Direction.REVERSE);
        lifter.setPosition(0);

        stop();
    }

    public void setDrive(double aPow, double bPow, double cPow, double dPow) {
        aMotor.setPower(aPow);
        bMotor.setPower(bPow);
        cMotor.setPower(cPow);
        dMotor.setPower(dPow);
    }

    public void setAllDrive(double pow) {
        aMotor.setPower(pow);
        bMotor.setPower(pow);
        cMotor.setPower(pow);
        dMotor.setPower(pow);
    }

    public void setDrive(HoloDir dir, double power)
    {
        setDrive(dir.a * power, dir.b * power, dir.c * power, dir.d * power);
    }

    public void stop()
    {
        setAllDrive(0);
    }

    public void resetEncoders()
    {
        aMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        aMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public int getAdist()
    {
        return Math.abs(aMotor.getCurrentPosition());
    }
}

