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
    public static double uWP = .3;
    public static double uWI = 0;
    public static double uWD = 0;
    public static double lWP = .3;
    public static double lWI = 0;
    public static double lWD = 0;

    public static double uTarmacRPM = 1;
    public static double lTarmacRPM = 1;


    //Drive Constants
    public static double aRP = .3;
    public static double aRI = 0;
    public static double aRD = 0;
    public static double dPFL = 1;
    public static double dIFL = 1;
    public static double dDFL = 1;
    public static double aPFL = 0.002;
    public static double aIFL = 0;
    public static double aDFL = 0;
    public static double dPFR = 1;
    public static double dIFR = 1;
    public static double dDFR = 1;
    public static double aPFR = 0.002;
    public static double aIFR = 0;
    public static double aDFR = 0;
    public static double dPBL = 1;
    public static double dIBL = 1;
    public static double dDBL = 1;
    public static double aPBL = 0.002;
    public static double aIBL = 0;
    public static double aDBL = 0.000000;
    public static double dPBR = 1;
    public static double dIBR = 1;
    public static double dDBR = 1;
    public static double aPBR = 0.002;
    public static double aIBR = 0;
    public static double aDBR = 0;
    public static double flHome = -7.27;
    public static double frHome = -269.19;
    public static double blHome = -73.81;
    public static double brHome = -157.03;

    public static int motorIdDriveFrontLeft = 5;
    public static int motorIdAngleFrontLeft = 6;
    public static int motorIdDriveFrontRight = 7;
    public static int motorIdAngleFrontRight = 8;
    public static int motorIdDriveBackRight = 1;
    public static int motorIdAngleBackRight = 2;
    public static int motorIdDriveBackLeft = 3;
    public static int motorIdAngleBackLeft = 4;
    public static int encoderIdFrontLeft = 12;
    public static int encoderIdFrontRight = 11;
    public static int encoderIdBackLeft = 9;
    public static int encoderIdBackRight = 10;
    //ADD PIDs FOR SWERVE

    //CAMERA CONSTANTS
    public final static int cameraPort = 0;
    public final static int cWidth = 640;
    public final static int cHeight = 480;
    public final static double horizontalViewAngle = 61;
    public final static double verticalViewAngle = 20.55;
    public final static double deg_per_px = verticalViewAngle / cHeight;

    //Climber Constants
    public final static int climbArmSolenoid1 = 1;
    public final static int climbArmSolenoid2 = 1;
    public final static int climbHookSolenoid1 = 1;
    public final static int climbHookSolenoid2 = 1;
    public final static int mClimb1 = 1;
    public final static int mClimb2 = 1;
    public final static double winchExtendPos = 1;
    public final static double winchContractPos = 1;
    public final static int rHallEffectSensor = 1;
    public final static int lHallEffectSensor = 1;

    //Intake Constants
    public final static int intakeMotor = 1;
    public final static int intakeSolenoid1 = 1;
    public final static int intakeSolendoid2 = 1;

    //Index Constants
    public final static int photoeye1 = 1;
    public final static int photoeye2 = 1;
    public final static int indexMotor = 1;

    //Limelight Constants
    public final static double h1 = 9;//HEIGHT OF LIMELIGHT
    public final static double a1 = 25;//ANGLE FROM HORIZONTAL
    public final static double h2 = 75;//HEIGHT OF TARGET
}
