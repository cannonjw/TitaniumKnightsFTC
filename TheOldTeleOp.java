/* Copyright (c) 2017 FIRST. All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/*
 Titanium Knights - AAI - Copyright 2018
 -Programmers-
 Xavier Ogomo
 Kayle Flake
 Jake Seely
 Milachi Allen
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


@TeleOp(name="TheOldTeleOp", group="TheOpMode")
//@Disabled
public class TheTeleOp extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor crane = null;
    private DcMotor extendingArm = null;
    private DcMotor wrist = null;
    //private DcMotor swiper = null;
    //private Servo left_Grabber = null;
    //private Servo right_Grabber = null;
    private Servo hand = null;
    




    @Override
    public void runOpMode() {

        // Initialize the hardware variables
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFront_Drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBack_Drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront_Drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack_Drive");
        crane = hardwareMap.get(DcMotor.class, "crane");
        extendingArm = hardwareMap.get(DcMotor.class, "extendingArm");
        wrist = hardwareMap.get(DcMotor.class, "wrist");
        //swiper = hardwareMap.get(DcMotor.class, "swiper");
        
        //left_Grabber = hardwareMap.get(Servo.class, "left_Grabber");
        //right_Grabber = hardwareMap.get(Servo.class, "right_Grabber");
        hand = hardwareMap.get(Servo.class, "hand");
        

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        
        crane.setDirection(DcMotor.Direction.REVERSE);
        wrist.setDirection(DcMotor.Direction.FORWARD);
        

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Status", "Waiting for start");
        telemetry.addData("", "");
        telemetry.addData("", "");
        telemetry.addData("", "");
        telemetry.addData("Message", "GET READY!!!");
        telemetry.update();
         
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftFrontPower;
            double leftBackPower;
            double rightFrontPower; 
            double rightBackPower;


            double drive =  gamepad1.left_stick_y;
            double strafe  =  gamepad1.right_stick_x;
            boolean strafeMode = gamepad1.left_bumper;
            boolean turnMode = gamepad1.right_bumper;
            boolean slowMode = gamepad1.b;
            String strafeActive = "NO";
            String slowActive = "NO";
            //Strafe Driving
            if (strafeMode) {
                leftFrontPower    = Range.clip(strafe, -1.0, 1.0);
                leftBackPower     = Range.clip(-strafe, -1.0, 1.0);
                rightFrontPower   = Range.clip(-strafe, -1.0, 1.0);
                rightBackPower    = Range.clip(strafe, -1.0, 1.0);

                strafeActive = "YES";
                slowActive = "NO";
            }
            else if (slowMode) {
                leftFrontPower    = Range.clip(drive - strafe, -0.25, 0.25);
                leftBackPower     = Range.clip(drive - strafe, -0.25, 0.25);
                rightFrontPower   = Range.clip(drive + strafe, -0.25, 0.25);
                rightBackPower    = Range.clip(drive + strafe, -0.25, 0.25);
                strafeActive = "NO";
                slowActive = "YES";
            }
            //Normal Driving
            else {
                leftFrontPower    = Range.clip(drive - strafe, -0.5, 0.5);
                leftBackPower     = Range.clip(drive - strafe, -0.5, 0.5);
                rightFrontPower   = Range.clip(drive + strafe, -0.5, 0.5);
                rightBackPower    = Range.clip(drive + strafe, -0.5, 0.5);

                strafeActive = "NO";
                slowActive = "NO";
            }



            // Send calculated power to wheels
            leftFrontDrive.setPower(leftFrontPower);
            leftBackDrive.setPower(leftBackPower);
            
            rightFrontDrive.setPower(rightFrontPower);
            rightBackDrive.setPower(rightBackPower);
            
            //                   CRANE
            //Controls Cranes
            double cranePower;
            double armPower;

            double craneMove = gamepad2.left_stick_y;
            double armMove = gamepad2.right_stick_y;
            
            cranePower = Range.clip(craneMove, -0.5, 0.5);
            armPower = Range.clip(armMove,  -0.5, 0.5);

            crane.setPower(cranePower);
            extendingArm.setPower(armPower);
            
            //Controls Wrist
            double wristPower;
            
            double wristMove = gamepad2.right_stick_x;
            
            wristPower = Range.clip(wristMove, -0.25, 0.25);
            
            wrist.setPower(wristPower);
            

            //                   Feet
            //Allows you to lift using the feet


            //double feetPower;


            //boolean feetLift = gamepad2.right_bumper;
            //boolean feetLower = gamepad2.left_bumper;
            //String feetStatus = "STANDBY";
            
            //if(feetLift) {
            //    feetPower = 2;
            //    feetStatus = "LIFTING";
            //}
            //else if (feetLower) {
            //    feetPower = -2;
            //    feetStatus = "LOWERING";
            //}
            //else {
            //    feetPower = 0;
            //    feetStatus = "STANDBY";
            //}
            
            //leftFoot.setPower(feetPower);
            //rightFoot.setPower(feetPower);


            //Controls Hand
            double handPos = 0;
            
            double handOpen = gamepad2.right_trigger;
            
            handPos = handOpen;
            
            hand.setPosition(handPos);
            
            
            
            //                   Swiper
            //Crotrols Sweeping Device
            //double swiperPower = 0;
            
            //boolean sweepMoveIn = gamepad1.left_stick_button;
            //boolean sweepMoveOut = gamepad1.right_stick_button;
            //boolean sweepMovePre = gamepad1.a;
            //if (sweepMoveIn)
            //    swiperPower = -2;
            
            //else if (sweepMoveOut)
            //    swiperPower = 2;
                
            //else if (sweepMovePre)
            //    swiperPower = - 0.2;
             
            //else 
            //    swiperPower = 0;
                
            //swiper.setPower(swiperPower);
            
            //                   Grabbers
            //Controls Grabbers
            //double grabberPower;
            //double grabberPos;

            //double grabberMove = gamepad2.right_stick_x;

                //grabberPower = Range.clip(grabberMove, -1.0, 1.0);
                //grabberPos = grabberPower;
            
            //left_Grabber.setPosition(-grabberPos);
            //right_Grabber.setPosition(grabberPos);

        
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("STRAFE - Activated:", strafeActive);
            telemetry.addData("SLOW - Activated:", slowActive);
            //telemetry.addData("Crazy Crane - Activated:", crazyLift);
            telemetry.addData("Motors", "front left (%.2f), front right (%.2f), back left (%.2f), back right (%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
            telemetry.addData("Crane", "(%.2f)", cranePower);
            telemetry.addData("Arm", "(%.2f)", armPower);
            //telemetry.addData("Feet:", feetStatus);
            //telemetry.addData("Grabbers", "(%.2f)", grabberPos);
            telemetry.addData("Hand", "(%.2f)", handPos);
            telemetry.update();

        }

    }
}
