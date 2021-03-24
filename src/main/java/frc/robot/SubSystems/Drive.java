package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//MOTORS
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//UTIL
import frc.robot.Util.Swerve;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.controller.PIDController;
//MATHU
import java.math.*;
import java.util.ArrayList;


public class Drive extends SubsystemBase {
  //TODO ADD TRAJECTORY https://docs.wpilib.org/en/stable/docs/software/examples-tutorials/trajectory-tutorial/index.html
    private static Drive drive;
    public static double frontLeftSwerveSpeed;
    public static double frontLeftSwerveAngle;
    public static double frontRightSwerveSpeed;
    public static double frontRightSwerveAngle;
    public static double backLeftSwerveSpeed;
    public static double backLeftSwerveAngle;
    public static double backRightSwerveSpeed;
    public static double backRightSwerveAngle;
    public static PIDController PIDDFL;
    public static PIDController PIDAFL;
    public static PIDController PIDDFR;
    public static PIDController PIDAFR;
    public static PIDController PIDDBL;
    public static PIDController PIDABL;
    public static PIDController PIDDBR;
    public static PIDController PIDABR;
    public Swerve frontLeft;
    public Swerve frontRight;
    public Swerve backRight;
    public Swerve backLeft;
    public static Drive get_Instance(){
    
        if(drive == null){
          drive = new Drive();
        } 
        return drive;
      }
      public Drive(){
         PIDDFL = new PIDController(Constants.dPFL, Constants.dIFL, Constants.dDFL);
         PIDAFL = new PIDController(Constants.aPFL, Constants.aIFL, Constants.aDFL);
         PIDDFR = new PIDController(Constants.dPFR, Constants.dIFR, Constants.dDFR);
         PIDAFR = new PIDController(Constants.aPFR, Constants.aIFR, Constants.aDFR);
         PIDDBL = new PIDController(Constants.dPBL, Constants.dIBL, Constants.dDBL);
         PIDABL = new PIDController(Constants.aPBL, Constants.aIBL, Constants.aDBL);
         PIDDBR = new PIDController(Constants.dPBR, Constants.dIBR, Constants.dDBR);
         PIDABR = new PIDController(Constants.aPBR, Constants.aIBR, Constants.aDBR);
         frontLeft = new Swerve(Constants.motorIdDriveFrontLeft, Constants.motorIdAngleFrontLeft, Constants.encoderIdFrontLeft, PIDDFL, PIDAFL);
         frontRight = new Swerve(Constants.motorIdDriveFrontRight, Constants.motorIdAngleFrontRight, Constants.encoderIdFrontRight,PIDDFR, PIDAFR);
         backRight = new Swerve(Constants.motorIdDriveBackRight, Constants.motorIdAngleBackRight, Constants.encoderIdBackRight, PIDDBR, PIDABR);
         backLeft = new Swerve(Constants.motorIdDriveBackLeft, Constants.motorIdAngleBackLeft, Constants.encoderIdBackLeft, PIDDBL, PIDABL);
      }
      public void homeSwerve(){
        frontLeft.setSpeed(0, frontLeft.anglePIDCalcABS(Constants.flHome));
        frontRight.setSpeed(0, frontRight.anglePIDCalcABS(Constants.frHome));
        backLeft.setSpeed(0, backLeft.anglePIDCalcABS(Constants.blHome));
        backRight.setSpeed(0, backRight.anglePIDCalcABS(Constants.brHome));
        frontLeft.resetEncoder();
        frontRight.resetEncoder();
        backLeft.resetEncoder();
        backRight.resetEncoder();
      }
      public void setSwerve(double angleVectorX, double angleVectorY, double rotationVectorX){
        double rotationVectorY = rotationVectorX;
        double A = angleVectorX - rotationVectorX;//THE PLUS AND MINUS MAY BE FLIPPED
        double B = angleVectorX + rotationVectorX;
        double C = angleVectorY - rotationVectorY;
        double D = angleVectorY + rotationVectorY;

        frontLeftSwerveSpeed = Math.sqrt(Math.pow(A,2.0) + Math.pow(C,2.0));
        frontLeftSwerveAngle = Math.atan2(A,C)*180/Math.PI;
        frontRightSwerveSpeed =  Math.sqrt(Math.pow(A,2.0) + Math.pow(D,2.0));
        frontRightSwerveAngle = Math.atan2(A,D)*180/Math.PI;
        backLeftSwerveSpeed =  Math.sqrt(Math.pow(B,2.0) + Math.pow(C,2.0));
        backLeftSwerveAngle = Math.atan2(B,C)*180/Math.PI;
        backRightSwerveSpeed =  Math.sqrt(Math.pow(B,2.0) + Math.pow(D,2.0));
        backRightSwerveAngle = Math.atan2(B,D)*180/Math.PI;
        //SET ALL OF THE NUMBERS FOR THE SWERVE VARS
      }
      public void setVector(double angle, double mag, double rotationVectorX){
        double angleVX = Math.cos(angle*Math.PI/180) *180/Math.PI * mag;//TODO CHECK about RAD VS DEG
        double angleVY = Math.sin(angle*Math.PI/180) *180/Math.PI * mag;
        setSwerve(angleVX, angleVY, rotationVectorX*180/Math.PI);
      } 
      public void periodic(){
        //System.out.println(frontLeftSwerveSpeed/300);
        //+180+Constants.flHome
        System.out.println(frontLeft.getEncoder() + "ENC");
        System.out.println(frontLeftSwerveAngle+180+Constants.flHome);
        /*System.out.println(backRight.getEncoder() + "enc");
        System.out.println(backRightSwerveAngle);
        System.out.println(backRightSwerveSpeed);
        System.out.println(backLeftSwerveAngle);
        System.out.println(backLeftSwerveSpeed);
        System.out.println(frontRightSwerveAngle);
        System.out.println(frontRightSwerveSpeed);
        System.out.println(frontLeftSwerveAngle);
        System.out.println(frontLeftSwerveSpeed);*/
        frontLeft.setSpeed(frontLeftSwerveSpeed/300, frontLeft.anglePIDCalcABS(frontLeftSwerveAngle+180+Constants.flHome));
        frontRight.setSpeed(frontRightSwerveSpeed/300, frontRight.anglePIDCalcABS(frontRightSwerveAngle+180+Constants.frHome));
        backLeft.setSpeed(backLeftSwerveSpeed/300, backLeft.anglePIDCalcABS(backLeftSwerveAngle+180+Constants.blHome));
        backRight.setSpeed(backRightSwerveSpeed/300, backRight.anglePIDCalcABS(backRightSwerveAngle+180+Constants.brHome));

      }


}