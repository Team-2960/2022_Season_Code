package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.MathUtil;

public class Constants{
    public double robotWidth = 0;
    public double robotLength = 0;


    //OI Constants
    public static int driverControl = 1;
    public static int operatorControl =2;
  

    //Shooter Constants
    public static double uWP = 0.550585/16;//0.68585
    public static double uWI = 4.7529 * Math.pow(10, -5);
    public static double uWD = 2.1277* Math.pow(10, -6);
    public static double lWP = 0.550587/16;//0.51587
    public static double lWI = 4.7532*Math.pow(10, -5);
    public static double lWD = 2 * Math.pow(10, -6);
    public static double lowGoalRPM = 7500;
    public static double edgeTarmacRPM = 11500;

    public static double uWPP = 0.0001;//0.68585
    public static double uWII = 0;
    public static double uWDD = 0;
    public static double lWPP = 0.0001;//0.51587
    public static double lWII = 0;
    public static double lWDD = 0;

    public static double uTarmacRPM = 1;
    public static double lTarmacRPM = 1;
    public static int mUpperShooter = 16;
    public static int mLowerShooter = 17;



    //Drive Constants
    public static double aRP = .3;
    public static double aRI = 0;
    public static double aRD = 0;
    public static double dPFL = 1;
    public static double dIFL = 1;
    public static double dDFL = 1;
    public static double aPFL = 0.007;
    public static double aIFL = 0;
    public static double aDFL = 0;
    public static double dPFR = 1;
    public static double dIFR = 1;
    public static double dDFR = 1;
    public static double aPFR = 0.007;
    public static double aIFR = 0;
    public static double aDFR = 0;
    public static double dPBL = 1;
    public static double dIBL = 1;
    public static double dDBL = 1;
    public static double aPBL = 0.007;
    public static double aIBL = 0;
    public static double aDBL = 0.000000;
    public static double dPBR = 1;
    public static double dIBR = 1;
    public static double dDBR = 1;
    public static double aPBR = 0.007;
    public static double aIBR = 0;
    public static double aDBR = 0;
    public static double flHome = -6.5;//-7.27;
    public static double frHome = -70;//-269.19;
    public static double blHome = -28;//-73.81;
    public static double brHome = -21;//-157.03;

    public static int motorIdDriveFrontLeft = 5;
    public static int motorIdAngleFrontLeft = 6;
    public static int motorIdDriveFrontRight = 7;
    public static int motorIdAngleFrontRight = 8;
    public static int motorIdDriveBackRight = 0;
    public static int motorIdAngleBackRight = 2;
    public static int motorIdDriveBackLeft = 3;
    public static int motorIdAngleBackLeft = 4;
    public static int encoderIdFrontLeft = 12;
    public static int encoderIdFrontRight = 11;
    public static int encoderIdBackLeft = 9;
    public static int encoderIdBackRight = 10;

    //toAnglePID
    public static double kPTA = 0.013;
    public static double kITA = 0;
    public static double kDTA = 0;
    public static double smallP = 0.1;
    public static double smallI = 0;
    public static double smallD = 0;
    public static double angleTolerance = 1;




    //ADD PIDs FOR SWERVE

    //CAMERA CONSTANTS
    public final static int cameraPort = 0;
    public final static int cWidth = 640;
    public final static int cHeight = 480;
    public final static double horizontalViewAngle = 61;
    public final static double verticalViewAngle = 20.55;
    public final static double deg_per_px = verticalViewAngle / cHeight;

    //Climber Constants
    public final static int climbRArmSolenoid1 = 2;
    public final static int climbRArmSolenoid2 = 3;
    public final static int climbLArmSolenoid1 = 4;
    public final static int climbLArmSolenoid2 = 5;
    public final static int climbHookSolenoid1 = 6;
    public final static int climbHookSolenoid2 = 7;
    public final static int mClimbL = 13;
    public final static int mClimbR = 14;
    public final static double winchExtendPos = 425000;
    public final static double winchExtendLimit = 490000;

    public final static double winchContractPos = 50000;
    public final static double winchLvl3Pos = 150000;

    public final static int rHallEffectSensor = 2;
    public final static int lHallEffectSensor = 3;
    public final static int limitSwitchPort = 4;

    //Intake Constants
    public final static int intakeMotor = 18;
    public final static int intakeSolenoid1 = 0;
    public final static int intakeSolendoid2 = 1;

    //Index Constants
    public final static int photoeye1 = 0;
    public final static int photoeye2 = 1;
    public final static int indexMotor = 15;

    //Limelight Constants
    public final static double h1 = 32;//HEIGHT OF LIMELIGHT
    public final static double a1 = 26;//ANGLE FROM HORIZONTAL
    public final static double h2 = 104;//HEIGHT OF TARGET

    //AUTON CONSTANTS
    public final static double autoSpeed = 0.3;
    public final static double sensorConv = 1;
}
