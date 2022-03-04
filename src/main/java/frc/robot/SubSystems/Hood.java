package frc.robot.SubSystems;



import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.math.controller.PIDController;


import javax.naming.ldap.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.BangBangController;

public class Hood extends SubsystemBase {
    private static final String Controlmode = null;
  private static Hood hood;

    private PIDController upperWheelPID;
    private double upperWheelVel;
    private TalonFX mUpperWheel;


    private PIDController lowerWheelPID;
    private double lowerWheelVel;
    private TalonFX mLowerWheel;

    //bang bang controllers
    private BangBangController bangL;
    private BangBangController bangU;


    private double shooterTolerance = 0;

    public static Hood get_Instance(){
        if(hood == null){
          hood = new Hood();
        }
        return hood;
      }
      Hood(){
          //Motors
          mUpperWheel = new TalonFX(Constants.mUpperShooter);
          mLowerWheel = new TalonFX(Constants.mLowerShooter);
          //BANG BANG
          bangU = new BangBangController();
          bangL = new BangBangController();
          //PID STUFF
          lowerWheelPID = new PIDController(Constants.lWP, Constants.lWI, Constants.lWD);
          upperWheelPID = new PIDController(Constants.uWP, Constants.uWI, Constants.uWD);
      }
      public void setSpeed(double powerL, double powerU){
        mUpperWheel.set(ControlMode.PercentOutput, -powerU);
        mLowerWheel.set(ControlMode.PercentOutput, powerL);
      }

      public void bangBang(){
        setSpeed(bangL.calculate(mLowerWheel.getSelectedSensorVelocity(), lowerWheelVel), bangU.calculate(mUpperWheel.getSelectedSensorVelocity(), upperWheelVel));
      }
      public void setWheelSpeedVel(double upper, double lower){//IN SENSOR UNITS/ 100 MS
        upperWheelVel = upper;
        lowerWheelVel = lower;
      }
      public void calcWheelSpeed(){
        setSpeed(upperWheelPID.calculate(mUpperWheel.getSelectedSensorVelocity(), upperWheelVel), lowerWheelPID.calculate(mLowerWheel.getSelectedSensorVelocity(), lowerWheelVel));
      }

      public boolean isWheelAtVel(){
        if((Math.abs(upperWheelVel - mUpperWheel.getSelectedSensorVelocity()) < shooterTolerance) && (Math.abs(lowerWheelVel - mLowerWheel.getSelectedSensorVelocity()) < shooterTolerance)){
          return true;
        }
        else{
          return false;
        }
      }
}