package frc.robot.Util;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//MOTORS
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//PID
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
//SENSORS
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.sensors.CANCoder;

import frc.robot.Constants;

public class Swerve {
  private CANSparkMax driveMotor;
  private CANSparkMax angleMotor;
  private PIDController anglePID;
  private PIDController drivePID;
  private CANCoder angleEncoder;
  private Translation2d translation2d;
  private Rotation2d rotation2d;
  private Pose2d pose2d;
    public Swerve(int motorIdDrive,int motorIdAngle,int encoderID, PIDController pidA, PIDController pidD, double offSet){
        //TODO ADD OFFSET OF THE MOTOR SO ITS NOT ANNOYING
        angleEncoder = new CANCoder(encoderID);
        angleEncoder.configMagnetOffset(offSet);
        drivePID = pidA;
        anglePID = pidD;
        driveMotor = new CANSparkMax(motorIdDrive, MotorType.kBrushless);
        angleMotor = new CANSparkMax(motorIdAngle, MotorType.kBrushless);
    }

    public void setSpeed(double driveSpeed, double angleSpeed){
        driveMotor.set(driveSpeed);
        angleMotor.set(angleSpeed);
    }

    public void setAngleSpeed(double angleSpeed){
        angleMotor.set(angleSpeed);
    }

    public void setDriveSpeed(double driveSpeed){
        driveMotor.set(driveSpeed);
    }
    public double drivePIDCalc(double rate){
        double calcDriveSpeed = 0;
        //angle
        return calcDriveSpeed;
    }

    public double angleOffsetAnglePID(double angle, double offSet){
        return anglePIDCalcABS(angle);
    }

    public double angleOffsetDrivePID(double angle, double offSet){
        return anglePIDCalcABS(angle);
    }

    public double anglePIDCalc(double angle){
        double calcAngleSpeed = 0;
        anglePID.calculate(angleEncoder.getPosition(), angle);
        return calcAngleSpeed;
    }

    public double anglePIDCalcABS(double angle){
        double calcAngleSpeed = 0;
        
        if(angle != 42069) {
            double curPos = angleEncoder.getAbsolutePosition();
            double posAngle = angle + 360;
            double negAngle = angle - 360;
            
            double curError = Math.abs(curPos - angle);
            double posError = Math.abs(curPos - posAngle);
            double negError = Math.abs(curPos - negAngle);

            if(curError < posError && curError < negError) {
                calcAngleSpeed = anglePID.calculate(angleEncoder.getAbsolutePosition(), angle);
            } else if(posError < curError && posError < negError) {
                calcAngleSpeed = anglePID.calculate(angleEncoder.getAbsolutePosition(), posAngle);
            } else {
                calcAngleSpeed = anglePID.calculate(angleEncoder.getAbsolutePosition(), negAngle);
            }
        }
        
        return calcAngleSpeed;
    }

    public void resetEncoder(){
        angleEncoder.setPosition(0);
    }

    public double getEncoder(){
        return angleEncoder.getAbsolutePosition();
    }
    public SwerveModuleState getState(){
        return new SwerveModuleState(driveMotor.getEncoder().getVelocity()*0.31918645197/(60*6.86), new Rotation2d(getEncoder()*Math.PI/180));
    }
}
