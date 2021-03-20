package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
    private static Climb climb;
    public static Climb get_Instance(){
    
        if(climb == null){
          climb = new Climb();
        } 
        return climb;
      }
      Climb(){
          
      }
}