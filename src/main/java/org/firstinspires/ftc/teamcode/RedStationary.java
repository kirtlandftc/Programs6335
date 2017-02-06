package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "RedShoot", group = "Spooky")
public class RedStationary extends OpMode {
    HardwareSpooky robot = new HardwareSpooky(DcMotor.RunMode.RUN_USING_ENCODER);
    private int stateID;
    private ElapsedTime runtime = new ElapsedTime();
    final double SLOW = .2;
    final double MEDIUM = .3;
    final double FAST = .4;

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Initialization", "Complete");
    }

    @Override
    public void loop() {
        switch (stateID)
        {
            case 0: //Lift-off from wall
                robot.setDrive(HoloDir.FORWARD, MEDIUM);
                if (robot.getAdist() >= 1500)
                {
                    robot.stop();
                    robot.resetEncoders();
                    stateID++;
                }
                break;
            case 1: //Activate catapult, shoot a particle
                robot.catapult.setPower(1);
                if(runtime.milliseconds() >= 3000) stateID++;
                break;
            case 2: //Amendment 2: prepare load
                robot.catapult.setPower(.2);
                if(robot.touchSensor.isPressed()) stateID++;
                break;
            case 3:
                robot.catapult.setPower(0);
                robot.ballBelt.setPower(1);
                if(runtime.milliseconds() >= 7000) stateID++;
                break;
            case 4:
                robot.catapult.setPower(1);
                robot.ballBelt.setPower(0);
                if(runtime.milliseconds() >= 10000) stateID++;
                break;
            case 5:
                robot.catapult.setPower(0);
                robot.setDrive(HoloDir.FORWARD, 1);
                if (robot.getAdist() >= 3000)
                {
                    robot.stop();
                    robot.resetEncoders();
                    stateID++;
                }
                break;
            case 6:
                robot.stop();
                break;

        }
    }
}
