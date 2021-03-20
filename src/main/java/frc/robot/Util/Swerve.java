package frc.robot.Util;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//MOTORS
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//PID
import edu.wpi.first.wpilibj.controller.PIDController;
//SENSORS

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.sensors.CANCoder;

import frc.robot.Constants;

public class Swerve {
  private CANSparkMax driveMotor;
  private CANSparkMax angleMotor;
  private PIDController anglePID;
  private PIDController drivePID;
  private CANCoder angleEncoder;
      public Swerve(int motorIdDrive,int motorIdAngle,int encoderID, PIDController pidA, PIDController pidD){
        //TODO ADD OFFSET OF THE MOTOR SO ITS NOT ANNOYING
        angleEncoder = new CANCoder(encoderID);
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
        if(angle == 42069){
          return 0;
        }else{
        double calcAngleSpeed = 0;
        calcAngleSpeed = anglePID.calculate(angleEncoder.getAbsolutePosition(), angle);
        return calcAngleSpeed;
        }
      }
      public void resetEncoder(){
        angleEncoder.setPosition(0);
      }
      public double getEncoder(){
        return angleEncoder.getAbsolutePosition();
      }
}