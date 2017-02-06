package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "RedBeacons", group = "Spooky")
public class RedBeacons extends OpMode {

    HardwareSpooky robot = new HardwareSpooky(DcMotor.RunMode.RUN_USING_ENCODER);
    private int stateID;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init()
    {
        robot.init(hardwareMap);
        telemetry.addData("Initialization", "Complete");
    }

    @Override
    public void init_loop() {}

    @Override
    public void start() {
        runtime.reset();
        stateID = 0;
    }

    final double SLOW = .2;
    final double MEDIUM = .3;
    final double FAST = .4;

    int blue;
    int red;

    @Override
    public void loop() {
        telemetry.addData("State", stateID);
        telemetry.addData("Time", runtime.time());
        telemetry.addData("Colors", "R: " + red + "; B: " + blue);

        if(runtime.seconds() < 1)
            robot.stop();
        else
        {
            switch(stateID)
            {
                //RED AUTO: Forward and backward reversed! Robot is backwards!
                case 0: //Move up towards ball
                    robot.setDrive(HoloDir.BACKWARD, MEDIUM);
                    if (robot.getAdist() >= 2036)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 1: //Move closer to beacon
                    robot.setDrive(HoloDir.RIGHT, MEDIUM);
                    if (robot.getAdist() >= 2460)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 2: //Move up into line with beacon
                    robot.setDrive(HoloDir.BACKWARD, MEDIUM);
                    if (robot.getAdist() >= 800)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 3: //Approach beacon
                    robot.setDrive(HoloDir.RIGHT, MEDIUM);
                    if (robot.getAdist() >= 440)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 4: //Check color
                    blue = robot.colorSensor.blue();
                    red = robot.colorSensor.red();
                    if(blue < red)
                        stateID++;
                    else
                        stateID = 8;
                    break;

                //CELL 1 IS RED
                case 5: //Press button 1
                    robot.setDrive(HoloDir.RIGHT, SLOW);
                    if (robot.getAdist() >= 250)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 6: //Release button 1
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 250)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 7: //Move up to clear beacon
                    robot.setDrive(HoloDir.BACKWARD, SLOW);
                    if (robot.getAdist() >= 1230)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 20;
                    }
                    break;

                //CELL 1 IS BLUE
                case 8: //Move to cell 2
                    robot.setDrive(HoloDir.BACKWARD, SLOW);
                    if (robot.getAdist() >= 280)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 9: //Press button 2
                    robot.setDrive(HoloDir.RIGHT, SLOW);
                    if (robot.getAdist() >= 250)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 10: //Release button 2
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 250)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 11: //Move up to clear beacon
                    robot.setDrive(HoloDir.BACKWARD, SLOW);
                    if (robot.getAdist() >= 950)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 20;
                    }
                    break;

                //BEACON 2
                case 20: //Square up to wall
                    robot.setDrive(HoloDir.RIGHT, SLOW);
                    if (robot.getAdist() >= 500)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 21: //Back up from wall
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 200)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 22: //Move up to beacon 2 cell 1
                    robot.setDrive(HoloDir.BACKWARD, MEDIUM);
                    if (robot.getAdist() >= 1850)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 23: //Wait for blur reduction; check cell 1 color
                    if(runtime.milliseconds() > 16500)
                    {
                        blue = robot.colorSensor.blue();
                        red = robot.colorSensor.red();
                        if(blue < red)
                            stateID++;
                        else
                            stateID = 26;
                    }
                    break;

                //CELL 1 IS RED
                case 24: //Press button 1
                    robot.setDrive(HoloDir.RIGHT, SLOW);
                    if (robot.getAdist() >= 300)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 25: //Release button 1
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 250)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 30;
                    }
                    break;

                //CELL 1 IS BLUE
                case 26: //Move to cell 2
                    robot.setDrive(HoloDir.BACKWARD, SLOW);
                    if (robot.getAdist() >= 350)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 27: //Press button 2
                    robot.setDrive(HoloDir.RIGHT, SLOW);
                    if (robot.getAdist() >= 300)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 28: //Release button 2
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 250)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 30;
                    }
                    break;

                case 30: //Back up from wall
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 180)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 31: //Adjust angle to establish path to center
                    robot.setAllDrive(.3);
                    if (robot.getAdist() >= 575)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 32: //Move closer to vortex
                    robot.setDrive(HoloDir.FORWARD, MEDIUM);
                    if (robot.getAdist() >= 850)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 33: //Shoot ball
                    robot.catapult.setPower(1);
                    if(runtime.seconds() > 22)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 34: //Rush to center, knock over cap ball
                    robot.catapult.setPower(0);
                    robot.setDrive(HoloDir.FORWARD, 1);
                    if (robot.getAdist() >= 3150)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 35: //Park on center
                    robot.stop();
                    break;
            }
        }
    }
}
