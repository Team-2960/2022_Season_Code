package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private static Intake intake;
    public static Intake get_Instance(){
    
        if(intake == null){
          intake = new Intake();
        } 
        return intake;
      }
      Intake(){
          
      }
}