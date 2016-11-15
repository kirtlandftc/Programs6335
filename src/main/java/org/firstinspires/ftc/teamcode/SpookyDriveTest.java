/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

//Prototype Tele-Op Program
@TeleOp(name = "SpookyDriveTest", group = "Spooky")
public class SpookyDriveTest extends OpMode {

    HardwareSpooky robot = new HardwareSpooky(DcMotor.RunMode.RUN_USING_ENCODER);
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init()
    {
        robot.init(hardwareMap);
        telemetry.addData("Initialization", "Complete");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    double vert;
    double hori;
    //
    double rotate = 0;
    //
    double aPow;
    double bPow;
    double cPow;
    double dPow;
    //
    double scale;


    @Override
    public void loop() {
        telemetry.addData("Status", "Run Time: " + runtime.toString());

        //Get vertical and horizontal thrust from driver
        vert = -gamepad1.left_stick_y;
        hori = gamepad1.left_stick_x;

        //Catapult
        if(gamepad2.right_trigger > 0)
        {
            robot.catapult.setPower(1);
        }
        else if(gamepad2.left_trigger > 0)
        {
            robot.catapult.setPower(-1);
        }
        else
        {
            robot.catapult.setPower(0);
        }

        //Ball Belt
        if(gamepad2.right_bumper)
        {
            robot.ballBelt.setPower(1);
        }
        else if(gamepad2.left_bumper)
        {
            robot.ballBelt.setPower(-1);
        }
        else
        {
            robot.ballBelt.setPower(0);
        }


        //Speed control
        if (gamepad1.right_trigger > 0) {
            scale = 1.0;
            telemetry.addData("Speed", "Full");
        } else if (gamepad1.left_trigger > 0) {
            scale = 0.25;
            telemetry.addData("Speed", "Quarter");
        } else {
            scale = 0.75;
            telemetry.addData("Speed", "Normal");
        }

        //Drive
        if(gamepad1.right_bumper) //RIGHT ROTATION
        {
            rotate = -scale;
        }
        else if(gamepad1.left_bumper) //LEFT ROTATION
        {
            rotate = scale;
        }
        else
        {
            rotate = -gamepad1.right_stick_x;
        }

        if (rotate != 0) {
            robot.setAllDrive(rotate);
        }
        else if (gamepad1.dpad_up) { //D-PAD MOVEMENTS
            robot.setDrive(HoloDir.FORWARD, 0.2);
        }
        else if (gamepad1.dpad_down) {
            robot.setDrive(HoloDir.BACKWARD, 0.2);
        }
        else if (gamepad1.dpad_left) {
            robot.setDrive(HoloDir.LEFT, 0.2);
        }
        else if (gamepad1.dpad_right) {
            robot.setDrive(HoloDir.RIGHT, 0.2);
        }
        else
        {
            aPow = -vert - hori;
            bPow = vert - hori;
            cPow = vert + hori;
            dPow = -vert + hori;
            robot.setDrive(aPow*scale, bPow*scale, cPow*scale, dPow*scale);
        }

    }
}
