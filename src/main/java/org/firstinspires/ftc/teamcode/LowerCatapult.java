package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "LowerCatapult", group = "Spooky")
public class LowerCatapult extends OpMode
{
    HardwareSpooky robot = new HardwareSpooky(DcMotor.RunMode.RUN_USING_ENCODER);

    @Override
    public void init()
    {
        robot.init(hardwareMap);
        telemetry.addData("Initialization", "Complete");
    }

    @Override
    public void loop() {
        if(!robot.touchSensor.isPressed())
            robot.catapult.setPower(.5);
        else
            robot.catapult.setPower(0);
    }
}
