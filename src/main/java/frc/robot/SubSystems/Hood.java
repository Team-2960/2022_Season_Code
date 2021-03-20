package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase {
    private static Hood hood;
    public static Hood get_Instance(){
    
        if(hood == null){
          hood = new Hood();
        } 
        return hood;
      }
      Hood(){
          
      }
}