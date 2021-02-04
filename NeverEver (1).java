package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "NeverEver (Blocks to Java)", group = "")
public class NeverEver extends LinearOpMode {

  private DcMotor leftFront_Drive;
  private DcMotor leftBack_Drive;
  private DcMotor rightFront_Drive;
  private DcMotor rightBack_Drive;
  private Servo boxHold;
  private DcMotor crane;
  private DcMotor crane1;
  private ElapsedTime runtime = new ElapsedTime();
  
   // This function is executed when this Op Mode is selected from the Driver Station.
   @Override
   public void runOpMode() {
    leftFront_Drive = hardwareMap.get(DcMotor.class, "leftFront_Drive");
    leftBack_Drive = hardwareMap.get(DcMotor.class, "leftBack_Drive");
    rightFront_Drive = hardwareMap.get(DcMotor.class, "rightFront_Drive");
    rightBack_Drive = hardwareMap.get(DcMotor.class, "rightBack_Drive");
    crane = hardwareMap.get(DcMotor.class, "crane");
    crane1 = hardwareMap.get(DcMotor.class, "crane1");
    
    boxHold = hardwareMap.get(Servo.class, "boxHold");
    
    waitForStart();
    leftFront_Drive.setDirection(DcMotorSimple.Direction.REVERSE);
    leftBack_Drive.setDirection(DcMotorSimple.Direction.REVERSE);
    runtime.reset();
    
      // Turn Right
      rightFront_Drive.setPower(0.5);
      leftFront_Drive.setPower(-0.5);
      rightBack_Drive.setPower(-0.5);
      leftBack_Drive.setPower(0.5);
   }
}
