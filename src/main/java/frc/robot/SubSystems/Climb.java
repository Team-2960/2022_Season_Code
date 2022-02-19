package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Climb extends SubsystemBase {
    private static Climb climb;

    //Solenoids
    private DoubleSolenoid sClimbArm;
    private DoubleSolenoid sClimbHook;

    //Motors
    private TalonFX mLeftClimb;
    private TalonFX mRightClimb;


    //Hall Effect Sensors
    private DigitalInput rHallEffect;
    private DigitalInput lHallEffect;



    public static Climb get_Instance(){
    
        if(climb == null){
          climb = new Climb();
        } 
        return climb;
      }

      Climb(){
        new DoubleSolenoid(20, PneumaticsModuleType.REVPH, Constants.climbArmSolenoid1, Constants.climbArmSolenoid2);
        sClimbArm = new DoubleSolenoid(20, PneumaticsModuleType.REVPH, Constants.climbArmSolenoid1, Constants.climbArmSolenoid2);
        sClimbHook = new DoubleSolenoid(20, PneumaticsModuleType.REVPH, Constants.climbHookSolenoid1, Constants.climbHookSolenoid2);
        mLeftClimb = new TalonFX(Constants.mClimb1);
        mRightClimb = new TalonFX(Constants.mClimb2);
        rHallEffect = new DigitalInput(Constants.rHallEffectSensor);
        lHallEffect = new DigitalInput(Constants.lHallEffectSensor);
      }

      public void setPositionArm(int state){//0 = down 1 = up
        if(state == 0){
          sClimbArm.set(Value.kForward);
        }
        else if(state == 1){
          sClimbArm.set(Value.kReverse);
        }
      }

      public void setPositionHook(int state){//0 = down 1 = up
        if(state == 0){
          sClimbHook.set(Value.kForward);
        }
        else if(state == 1){
          sClimbHook.set(Value.kReverse);
        }
      }

      public void setWinchSpeed(double left, double right){
        mLeftClimb.set(ControlMode.PercentOutput, left);
        mRightClimb.set(ControlMode.PercentOutput, right);
      }

      public double getWinchPos(){
        return mLeftClimb.getSelectedSensorPosition();
      }

      public boolean isAttached(){
        return (rHallEffect.get() && lHallEffect.get());
      }

      public boolean isArmsExtended(){
        return (sClimbArm.get() == Value.kForward);
      }
}