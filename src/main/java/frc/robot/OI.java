package frc.robot;

import frc.robot.SubSystems.Climb;
import frc.robot.SubSystems.Drive;
import frc.robot.SubSystems.Hood;
import frc.robot.SubSystems.Index;
import frc.robot.SubSystems.Intake;
import frc.robot.SubSystems.Shooter;
import frc.robot.SubSystems.Lime;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OI extends SubsystemBase {
    private Climb climb;
    private Intake intake;
    private Drive drive;
    private Shooter shooter;
    public Hood hood;
    private Index index;
    public Joystick joy1;
    public Lime lime;

    public OI(){
        //camera = Camera.get_Instance();
        drive = Drive.get_Instance();
        joy1 = new Joystick(Constants.joy1);
        lime = new Lime();


    }
    private void manualDrive(){
        //TODO ADD CONTROLS ONCE I GET ROBOT
        //TODO ADD SCALAR ONCE I GET ROBOT
        //drive.setSwerve(joy1.getRawAxis(1/*TODO CHANGE TO VERT LEFT*/), joy1.getRawAxis(5/*TODO CHANGE TO HORIZ LEFT*/), joy1.getRawAxis(4/*TODO CHANGE TO HORIZ RIGHT*/));
    }
    private void testMotor(){
        if(joy1.getRawButton(1)){
            drive.frontRight.setSpeed(joy1.getRawAxis(1),joy1.getRawAxis(5));
        }else if(joy1.getRawButton(2)){
            drive.frontLeft.setSpeed(joy1.getRawAxis(1),joy1.getRawAxis(5));
        }else if(joy1.getRawButton(3)){
            drive.backRight.setSpeed(joy1.getRawAxis(1),joy1.getRawAxis(5));
        }else {
        drive.backLeft.setSpeed(joy1.getRawAxis(1),joy1.getRawAxis(5));
        }
    }
    private void testCANCODER(){
        System.out.println(drive.frontRight.getEncoder());
    }
    private void driveTest(){
        //drive.setVector(90, Math.sqrt(Math.pow(Math.abs(joy1.getRawAxis(0)), 2)+Math.pow(Math.abs(joy1.getRawAxis(1)), 2)), joy1.getRawAxis(4));
        drive.homeSwerve();
        drive.resetGyro();
        //drive.backLeft.setSpeed(0, 0.2);
        //drive.setVector(driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1)), Math.sqrt(Math.pow(Math.abs(joy1.getRawAxis(0)), 2)+Math.pow(Math.abs(joy1.getRawAxis(1)), 2)), joy1.getRawAxis(4));
        //drive.backLeft.setAngleSpeed(drive.backLeft.anglePIDCalcABS(driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1))));
        //drive.backLeft.setDriveSpeed(Math.sqrt(Math.pow(joy1.getRawAxis(0), 2)+Math.pow(joy1.getRawAxis(1), 2))/4);
        //System.out.println(driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1)));
        //System.out.println(drive.backLeft.getEncoder() + "ENC");  
    }
    public double driveAngle(double x, double y){
        double done = 0;
        y = -y;
        if(x<0){
            if(y>0){
                done = (180/Math.PI)*(3*Math.PI/2 - Math.atan(y/-x));
            }else{
                done = (180/Math.PI)*(3*Math.PI/2 + Math.atan(-y/-x));
            }
        }else if(x>0){
            if(y>0){
                done = (180/Math.PI)*(Math.PI/2 + Math.atan(y/x));
            }else{
                done = (180/Math.PI)*(Math.PI/2 - Math.atan(-y/x));
            }
        }else{
            if(y<0){
                done = 1;
            }else{
                done = 180;
            }

   
        }
        

        done = Math.abs(done -360);
        return done;
    }
    public void periodic(){
        //testCANCODER();
        //manualDrive();
        //testMotor();
        if(joy1.getRawButton(1)){
            driveTest();
        }//else if(Math.sqrt(Math.pow(Math.abs(joy1.getRawAxis(0)), 2)+Math.pow(Math.abs(joy1.getRawAxis(1)), 2)) < 0.05 && Math.abs(joy1.getRawAxis(4))<0.05){
           // drive.setVector(driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1)), 0, 0);
        else if(joy1.getRawButton(2)){
            drive.setVector(driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1)), Math.sqrt(Math.pow(Math.abs(joy1.getRawAxis(0)), 2)+Math.pow(Math.abs(joy1.getRawAxis(1)), 2)), lime.getHorOffset()/40);
        }
        else{
            drive.setVector(driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1)), Math.sqrt(Math.pow(Math.abs(joy1.getRawAxis(0)), 2)+Math.pow(Math.abs(joy1.getRawAxis(1)), 2)), joy1.getRawAxis(4));

        }
        
        drive.periodic();
        drive.coEff = joy1.getRawAxis(1);
        drive.driverIn = driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1));
        //drive.backLeft.setSpeed(0.2, 0.2);
        //drive.backRight.setSpeed(0.2, 0.2);
        //System.out.println(drive.backRight.getEncoder() + "br");
        //System.out.println(drive.backLeft.getEncoder() + "bl");

    }
}