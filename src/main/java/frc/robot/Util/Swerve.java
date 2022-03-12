package frc.robot.Util;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//MOTORS
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//PID
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
//SENSORS
import edu.wpi.first.math.kinematics.SwerveModuleState;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import frc.robot.Constants;

public class Swerve {
  public TalonFX driveMotor;
  private TalonFX angleMotor;
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
        driveMotor = new TalonFX(motorIdDrive);
        angleMotor = new TalonFX(motorIdAngle);

    }

    public void setSpeed(double driveSpeed, double angleSpeed){
        driveMotor.set(ControlMode.PercentOutput, driveSpeed);
        angleMotor.set(ControlMode.PercentOutput, angleSpeed);
    }

    public void setAngleSpeed(double angleSpeed){
        angleMotor.set(ControlMode.PercentOutput, angleSpeed);
    }

    public void setDriveSpeed(double driveSpeed){
        driveMotor.set(ControlMode.PercentOutput, driveSpeed);
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
    public double getDriveEncoder(){
        return driveMotor.getSelectedSensorPosition();
    }
    public void setDriveModeBrake(){
        driveMotor.setNeutralMode(NeutralMode.Brake);
    }
    public void setDriveModeCoast(){
        driveMotor.setNeutralMode(NeutralMode.Coast);
    }
}
