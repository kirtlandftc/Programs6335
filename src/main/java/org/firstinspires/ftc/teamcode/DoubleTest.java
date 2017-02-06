package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "DoubleTest", group = "Spooky")
public class DoubleTest extends OpMode
{
    HardwareSpooky robot = new HardwareSpooky(DcMotor.RunMode.RUN_USING_ENCODER);
    private ElapsedTime runtime = new ElapsedTime();
    private int stateID;

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Initialization", "Complete");
    }

    @Override
    public void start() {
        runtime.reset();
        stateID = 0;
    }

    boolean loading = true;

    @Override
    public void loop() {
        switch(stateID)
        {
            case 0:
                robot.catapult.setPower(1);
                if(runtime.seconds() >= 1)
                    stateID++;
                break;
            case 1:
                robot.catapult.setPower(.2);
                if(robot.touchSensor.isPressed())
                    stateID++;
                break;
            case 2:
                robot.catapult.setPower(0);
                robot.ballBelt.setPower(1);
                if(runtime.seconds() >= 4)
                    stateID++;
                break;
            case 3:
                robot.ballBelt.setPower(0);
                break;
        }
    }
}
