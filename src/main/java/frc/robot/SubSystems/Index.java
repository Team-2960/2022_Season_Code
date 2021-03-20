package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Index extends SubsystemBase {
    private static Index index;
    public static Index get_Instance(){
    
        if(index == null){
          index = new Index();
        } 
        return index;
      }
      Index(){
          
      }
}