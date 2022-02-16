package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;




//IMPORT ALL OF THE SUBSYSTEMS
import frc.robot.SubSystems.*;

public class MegaShooter2PointO extends SubsystemBase {
    private static MegaShooter2PointO megashooter2pointo;

    //SUBSYSTEMS
    public Climb climb;
    public Drive drive;
    public Hood hood;
    public Index index;
    public Intake intake;
    public Lime lime;

    //Climbing VARS
    boolean isClimbExtended = false;
    double climbSequence = 1;
    boolean enableTrav = false;
    //Intake VARS
    boolean isIntakeOut = false;


    public static MegaShooter2PointO get_Instance(){
    
        if(megashooter2pointo == null){
          megashooter2pointo = new MegaShooter2PointO();
        } 
        return megashooter2pointo;
      }
      MegaShooter2PointO(){
          climb = Climb.get_Instance();
          drive = Drive.get_Instance();
          hood = Hood.get_Instance();
          index = Index.get_Instance();
          intake = Intake.get_Instance();
          lime = Lime.get_Instance();
      }
      public void intakeOn(){
        if(index.isInTransit() || (!index.getUpperPhotoeye() && index.getLowerPhotoeye())){
          index.setSpeed(1);
        }else{
          index.setSpeed(0);
        }
        if(!(index.getLowerPhotoeye() && (index.isInTransit() || index.getUpperPhotoeye()))){
          intake.setSpeed(1);
        }else{
          index.setSpeed(0);
        }
      }
      public void intakeOff(){
        intake.setSpeed(0);
        index.setSpeed(0);
      }


      public void setShooterRPM(double targetUpper, double targetLower){
        hood.setWheelSpeedVel(targetUpper, targetLower);
      }

      public void shoot(){
        if(hood.isWheelAtVel()){
          if(index.getUpperPhotoeye() == true){
            index.setSpeed(1);
          }else{
            intake.setSpeed(1);
            index.setSpeed(1);
          }
        }else{
          intake.setSpeed(0);
          index.setSpeed(0);
        }
      }

      private void intakeUp(){
        intake.setPosition(1);
      }

      private void intakeDown(){
        intake.setPosition(0);
      }

      private void intakeControl(){
        if(isIntakeOut){
          intakeDown();
        }else{
          intakeUp();
        }
      }

      public void flipIntake(){
        isIntakeOut = !isIntakeOut;
      }
      
      public void prepCLimb(){
      climb.setPositionHook(0);
        if(climb.getWinchPos() > Constants.winchExtendPos){
          climb.setWinchSpeed(0, 0);
          climbSequence =2;
        }else{
          climb.setWinchSpeed(0.1, 0.1);
        }
      }

      public void extendArms(){
        climb.setPositionArm(1);
        if(climb.isArmsExtended()){
          climbSequence = 3;
        }
      }
      
      public void armsIn(){
        climb.setWinchSpeed(-0.1, -0.1);
        if(climb.isAttached()){
          isClimbExtended = true;
          climb.setWinchSpeed(0, 0);
        }
        if(isClimbExtended == true){
          climb.setPositionArm(0);
          climb.setWinchSpeed(-0.1, -0.1);
          if(climb.getWinchPos()< Constants.winchContractPos){
            climb.setWinchSpeed(0, 0);
            climb.setPositionHook(1);
            isClimbExtended = false;
            climbSequence = 4;
          }
        }
      }

      public void enableTravClimb(){
        enableTrav = true;
      }

      public void traversalClimb(){
        if(enableTrav){
          if(climbSequence == 1){
            prepCLimb();
          }else if(climbSequence == 2){
            extendArms();
          }else if(climbSequence == 3){
            armsIn();
          }else if(climbSequence == 4){
            enableTrav = false;
            climbSequence = 1;
          }
        }
        
      }

      public void periodic(){
          intakeControl();
          index.inTransit();
          traversalClimb();
      }
}