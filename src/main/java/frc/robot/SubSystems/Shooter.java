package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private static Shooter shooter;
    public static Shooter get_Instance(){
    
        if(shooter == null){
          shooter = new Shooter();
        } 
        return shooter;
      }
      Shooter(){
          
      }
}