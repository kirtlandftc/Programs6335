package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRGyro;

public class HardwareSpooky
{
    //"can you just do the nuts"

    public DcMotor aMotor = null;
    public DcMotor bMotor = null;
    public DcMotor cMotor = null;
    public DcMotor dMotor = null;

    HardwareMap hwMap = null;
    private DcMotor.RunMode runMode = null;

    public HardwareSpooky(DcMotor.RunMode rMode)
    {
        runMode = rMode;
    }

    public void init(HardwareMap ahwMap)
    {
        hwMap = ahwMap;

        aMotor = hwMap.dcMotor.get("a_drive");
        bMotor = hwMap.dcMotor.get("b_drive");
        cMotor = hwMap.dcMotor.get("c_drive");
        dMotor = hwMap.dcMotor.get("d_drive");
        aMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        aMotor.setMode(runMode);
        bMotor.setMode(runMode);
        cMotor.setMode(runMode);
        dMotor.setMode(runMode);
        aMotor.setDirection(DcMotor.Direction.FORWARD);
        bMotor.setDirection(DcMotor.Direction.FORWARD);
        cMotor.setDirection(DcMotor.Direction.FORWARD);
        dMotor.setDirection(DcMotor.Direction.FORWARD);

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

    public void setMode(DcMotor.RunMode runMode) {
        aMotor.setMode(runMode);
        bMotor.setMode(runMode);
        cMotor.setMode(runMode);
        dMotor.setMode(runMode);
    }

    public void stop()
    {
        setAllDrive(0);
    }
}

