package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Intake extends SubsystemBase {
    private static Intake intake;
    private TalonFX iMotor;
    private DoubleSolenoid sIntake;
    public static Intake get_Instance(){
        if(intake == null){
          intake = new Intake();
        } 
        return intake;
      }
      Intake(){
        iMotor = new TalonFX(Constants.intakeMotor);
        iMotor.setNeutralMode(NeutralMode.Brake);
        sIntake = new DoubleSolenoid(20, PneumaticsModuleType.CTREPCM, 
                                     Constants.intakeSolenoid1, 
                                     Constants.intakeSolendoid2);
      }
      public void setPosition(int state){
        if(state == 0){
          sIntake.set(Value.kForward);
        }
        else if(state == 1){
          sIntake.set(Value.kReverse);
        }
      }
      public void setSpeed(double speed){
        iMotor.set(ControlMode.PercentOutput, speed);
      }

}