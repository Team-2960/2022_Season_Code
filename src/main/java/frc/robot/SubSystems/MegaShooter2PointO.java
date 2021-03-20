package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MegaShooter2PointO extends SubsystemBase {
    private static MegaShooter2PointO megashooter2pointo;
    public static MegaShooter2PointO get_Instance(){
    
        if(megashooter2pointo == null){
          megashooter2pointo = new MegaShooter2PointO();
        } 
        return megashooter2pointo;
      }
      MegaShooter2PointO(){
          
      }
}