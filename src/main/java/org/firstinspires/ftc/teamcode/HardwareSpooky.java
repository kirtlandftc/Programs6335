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

    DcMotor.RunMode runMode = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public HardwareSpooky(DcMotor.RunMode runMode)
    {
        this.runMode = runMode;
    }

    public void init(HardwareMap ahwMap)
    {
        hwMap = ahwMap;

        aMotor = hwMap.dcMotor.get("a_drive");
        bMotor = hwMap.dcMotor.get("b_drive");
        cMotor = hwMap.dcMotor.get("c_drive");
        dMotor = hwMap.dcMotor.get("d_drive");
        aMotor.setMode(runMode);
        bMotor.setMode(runMode);
        cMotor.setMode(runMode);
        dMotor.setMode(runMode);
        aMotor.setDirection(DcMotor.Direction.FORWARD);
        bMotor.setDirection(DcMotor.Direction.FORWARD);
        cMotor.setDirection(DcMotor.Direction.FORWARD);
        dMotor.setDirection(DcMotor.Direction.FORWARD);

        setAllDrive(0);
    }

    public void setDrive(double aPow, double bPow, double cPow, double dPow)
    {
        aMotor.setPower(aPow);
        bMotor.setPower(bPow);
        cMotor.setPower(cPow);
        dMotor.setPower(dPow);
    }

    public void setDrive(HoloDir dir, double power)
    {
        setDrive(dir.a*power, dir.b*power, dir.c*power, dir.d*power);
    }

    public void setAllDrive(double power)
    {
        setDrive(power, power, power, power);
    }

    public void stop()
    {
        setAllDrive(0);
    }
    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException
    {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

