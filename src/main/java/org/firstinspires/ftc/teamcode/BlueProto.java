package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "BlueDouble", group = "Spooky")
public class BlueProto extends OpMode {

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
                //BLUE AUTO: Normal operations! Robot is facing forward!
                case 0: //Lift-off from wall
                    robot.setDrive(HoloDir.FORWARD, SLOW);
                    if (robot.getAdist() >= 500)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 1: //Activate catapult, shoot a particle
                    robot.catapult.setPower(1);
                    if(runtime.milliseconds() >= 3500) stateID = 200;
                    break;
                case 200: //Amendment 2: prepare load
                    robot.catapult.setPower(.2);
                    if(robot.touchSensor.isPressed()) stateID++;
                    break;
                case 201:
                    robot.catapult.setPower(0);
                    robot.ballBelt.setPower(1);
                    if(runtime.milliseconds() >= 5500) stateID++;
                    break;
                case 202:
                    robot.catapult.setPower(1);
                    if(runtime.milliseconds() >= 8000) stateID = 2;
                    break;
                case 2: //Shut off catapult, move to cap ball
                    robot.catapult.setPower(0);
                    robot.setDrive(HoloDir.FORWARD, MEDIUM);
                    if (robot.getAdist() >= 1700)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 3: //Move right and stop next to ramp to avoid collision
                    robot.setDrive(HoloDir.RIGHT, MEDIUM);
                    if (robot.getAdist() >= 2200)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 100;
                    }
                    break;
                case 100: //Amendment 1.0: Move forward to clear ramp
                    robot.setDrive(HoloDir.FORWARD, MEDIUM);
                    if (robot.getAdist() >= 200)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 101: //Amendment 1.1: Square up to wall
                    robot.setDrive(HoloDir.RIGHT, MEDIUM);
                    if (robot.getAdist() >= 900)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 102: //Amendment 1.2: Back away from wall
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 300)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 5;
                    }
                    break;
                case 5: //Move forward to beacon 1 cell 1
                    robot.setDrive(HoloDir.FORWARD, .1);
                    if (robot.getAdist() >= 750)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 6: //Check cell 1 color
                    blue = robot.colorSensor.blue();
                    red = robot.colorSensor.red() - 100;
                    if(blue > red)
                        stateID++;
                    else
                        stateID = 11;
                    break;

                //CELL 1 IS BLUE
                case 7: //Press button 1
                    robot.setDrive(HoloDir.RIGHT, SLOW);
                    if (robot.getAdist() >= 300)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 8: //Release button 1
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 300)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 9: //Move forward to clear beacon
                    robot.setDrive(HoloDir.FORWARD, SLOW);
                    if (robot.getAdist() >= 800)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 10: //Square up to wall
                    robot.setDrive(HoloDir.RIGHT, MEDIUM);
                    if (robot.getAdist() >= 900)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 20;
                    }
                    break;

                //CELL 1 IS RED
                case 11: //Move to cell 2
                    robot.setDrive(HoloDir.FORWARD, SLOW);
                    if (robot.getAdist() >= 205)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 12: //Press button 2
                    robot.setDrive(HoloDir.RIGHT, SLOW);
                    if (robot.getAdist() >= 300)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 13: //Release button 2
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 300)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 14: //Move forward to clear beacon
                    robot.setDrive(HoloDir.FORWARD, SLOW);
                    if (robot.getAdist() >= 620)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 15: //Square up to wall
                    robot.setDrive(HoloDir.RIGHT, MEDIUM);
                    if (robot.getAdist() >= 900)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 20;
                    }
                    break;

                ////BEACON 2
                case 20: //Back up from wall
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 400)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 21: //Move forward to beacon 2 cell 1
                    robot.setDrive(HoloDir.FORWARD, MEDIUM);
                    if (robot.getAdist() >= 2200)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 22: //Wait for blur reduction; check cell 1 color
                    if(runtime.seconds() >= 20)
                    {
                        blue = robot.colorSensor.blue();
                        red = robot.colorSensor.red() - 100;
                        if(blue > red)
                            stateID++;
                        else
                            stateID = 26;
                    }
                    break;

                //CELL 1 IS BLUE
                case 23: //Press button 1
                    robot.setDrive(HoloDir.RIGHT, SLOW);
                    if (robot.getAdist() >= 350)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 24: //Release button 1
                    robot.setDrive(HoloDir.LEFT, SLOW);
                    if (robot.getAdist() >= 350)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 25: //Rotate to establish path to center
                    robot.setAllDrive(.3);
                    if (robot.getAdist() >= 450)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 30;
                    }
                    break;

                //CELL 1 IS RED
                case 26: //Move to cell 2
                    robot.setDrive(HoloDir.FORWARD, SLOW);
                    if (robot.getAdist() >= 230)
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
                    if (robot.getAdist() >= 300)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 29: //Rotate to establish path to center
                    robot.setAllDrive(.3);
                    if (robot.getAdist() >= 550)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID = 30;
                    }
                    break;

                case 30: //Rush to center, knock over cap ball
                    robot.setDrive(HoloDir.LEFT, 1);
                    if (robot.getAdist() >= 4000)
                    {
                        robot.stop();
                        robot.resetEncoders();
                        stateID++;
                    }
                    break;
                case 31: //Park on center
                    robot.stop();
                    break;
            }
        }
    }
}
