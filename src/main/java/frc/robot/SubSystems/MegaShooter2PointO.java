package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.Auton.shoot;
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
    boolean isClimbExtendedlvl1 = false;
    boolean isClimbExtendedlvl2 = false;
    boolean isClimbExtendedlvl3 = false;

    double climbSequencelvl1 = 1;
    double climbSequencelvl2 = 1;
    double climbSequencelvl3 = 1;

    boolean enableTravlvl1 = false;
    boolean enableTravlvl2 = false;
    boolean enableTravlvl3 = false;
    boolean go = false;
    boolean enableReset = false;
    boolean enableArmsUp = false;

    //Intake VARS
    boolean isIntakeOut = false;
    boolean intakeEnabled = false;
    boolean shooting = false;
    boolean isIndexReversedVar = false;
    boolean takeOff = false;

    //Climb Timer
    Timer climbTimer;


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
          climbTimer = new Timer();
      }
      public void indexing(){
        if(index.isInTransit() || (!index.getUpperPhotoeye() && index.getLowerPhotoeye()) || shooting){
          index.setSpeed(-0.75);
        }else{
          index.setSpeed(0);
        }
        if((!index.getUpperPhotoeye() && index.getLowerPhotoeye()) || (intakeEnabled && !(index.getLowerPhotoeye() && index.getUpperPhotoeye()))){//!(index.getLowerPhotoeye() && (index.isInTransit() || index.getUpperPhotoeye()))
          intake.setSpeed(-0.4);
        }else if(isIndexReversedVar){
          intake.setSpeed(0.4);
        }else{
          intake.setSpeed(0);
        }
      }
      public void intakeOn(){
        intakeEnabled = true;
      }
      public void intakeOff(){
        intakeEnabled = false;
      }
      public void isIndexReversed(boolean state){
        isIndexReversedVar = state;
      }
      public void setShooterRPM(double targetUpper, double targetLower){
        hood.setWheelSpeedVel(targetUpper, targetLower);
      }
      public void setSpeed(double speed){
        hood.setSpeed(-speed, -speed);
      }
      public void shootOn(){
        if(hood.isWheelAtVel()){
          shooting = true;
        }else{
          shooting = false;
        }
      }

      public void shoot(double velocity){
        setShooterRPM(velocity, velocity);
        shootOn();
      }
      public void shootCamera(){
        shoot(hood.RPMCalc(lime.calcDistance()));
        shootOn();
      }
      public void RPMCamera(){
        megashooter2pointo.setShooterRPM(hood.RPMCalc(lime.calcDistance()), hood.RPMCalc(lime.calcDistance()));

      }
      

      public void shootOff(){
        shooting = false;
        setShooterRPM(0, 0);
      }

      public void intakeUp(){
        intake.setPosition(1);
      }

      public void intakeDown(){
        intake.setPosition(0);
      }

      public void enableTakeOff(){
        takeOff = true;
      }
      public void enableArmsUp(){
        enableArmsUp = true;
      }
      public void armsTakeOff(){
        if(takeOff){
        if(climb.getWinchPos() < Constants.winchContractPos){
          climb.setWinchSpeed(0.9, 0.9);
        }else{
          climb.setWinchSpeed(0, 0);
          takeOff = false;
        }
      }
      }
      public void armsUp(){
      climb.setPositionArm(0);
      if(enableArmsUp){
        if(climb.getWinchPos() > Constants.winchExtendPos){
          climb.setWinchSpeed(0, 0);
          climb.setPositionArm(1);
          enableArmsUp = false;
        }else{
          climb.setWinchSpeed(1, 1);
        }
      }
      }

      public void prepCLimblvl2(){
          if(climb.getWinchPos() > Constants.winchExtendPos){
            climb.setWinchSpeed(0, 0);
            climbSequencelvl2 =2;
          }else{
            climb.setWinchSpeed(1, 1);
          }
        }
      
      public void extendArmslvl1(){
        climb.setPositionArm(1);
        if(climb.isArmsExtended()){
          climbSequencelvl1 = 2;
          System.out.println("to three");
          climbTimer.start();
        }
      }

      public void extendArmslvl2(){
        climb.setPositionArm(1);
        if(climb.isArmsExtended()){
          climbSequencelvl2 = 3;
          System.out.println("to three");
          climbTimer.start();
        }
      }

      public void extendArmslvl3(){
        climb.setPositionArm(1);
        if(climb.isArmsExtended()){
          climbSequencelvl3 = 2;
          System.out.println("to three");
          climbTimer.start();
        }
      }
      
      public void armsInlvl1(){
        drive.modToAngle(0);
        climb.setPositionHook(0);
        if(isClimbExtendedlvl1 == false){
        if(climbTimer.get() > 1.5){
          climbTimer.stop();
          climbTimer.reset();
          go = true;
        }
        if(go){
        climb.setWinchSpeed(-1, -1);
        if(climb.getWinchPos() < Constants.winchContractPos){
          isClimbExtendedlvl1 = true;
          climb.setWinchSpeed(-0.3, -0.3);
          climb.setPositionArm(0);
        }
      }
    }
        if(isClimbExtendedlvl1 == true){
          if(climb.getLimitSwitch()){
            climb.setWinchSpeed(0, 0);
            climb.setPositionHook(1);
            climbSequencelvl1 = 3;
          }else{
            climb.setWinchSpeed(-0.3, -0.3);
          }
         
      }
    }

    public void armsInlvl2(){
      if(isClimbExtendedlvl2 == false){
      if(climbTimer.get() > 1.5){
        climbTimer.stop();
        climbTimer.reset();
        go = true;
      }
      if(go){
      climb.setWinchSpeed(-1, -1);
      climb.setPositionHook(0);
      if(climb.getWinchPos() < Constants.winchContractPos){
        isClimbExtendedlvl2 = true;
        climb.setWinchSpeed(-0.3, -0.3);
        climb.setPositionArm(0);
      }
    }
  }
      if(isClimbExtendedlvl2 == true){
        if(climb.getLimitSwitch()){
          climb.setWinchSpeed(0, 0);
          climb.setPositionHook(1);
          climbSequencelvl2 = 4;
        }else{
          climb.setWinchSpeed(-0.3, -0.3);
        }
       
    }
  }

  public void armsInlvl3(){
    if(isClimbExtendedlvl3 == false){
    if(climbTimer.get() > 0){
      climbTimer.stop();
      climbTimer.reset();
      go = true;
    }
    if(go){
    climb.setWinchSpeed(-1, -1);
    intakeUp();
    if(climb.getWinchPos() < Constants.winchLvl3Pos){
      isClimbExtendedlvl3 = true;
      climb.setWinchSpeed(-0.3, -0.3);
      climb.setWinchSpeed(0, 0);
      climbSequencelvl3 = 3;
    }
  }
}

}

      public void enableTravClimblvl1(){
        enableTravlvl1 = true;
      }
      public void enableTravClimblvl2(){
        enableTravlvl2 = true;
      }
      public void enableTravClimblvl3(){
        enableTravlvl3 = true;
      }

      public void traversalClimblvl1(){
        if(enableTravlvl1){
          if(climbSequencelvl1 == 1){
            extendArmslvl1();
          }else if(climbSequencelvl1 == 2){
            armsInlvl1();
          }else if(climbSequencelvl1 == 3){
            enableTravlvl1 = false;
            climbSequencelvl1 = 1;
            go = false;
            isClimbExtendedlvl1 = false;
          }
        }
        
      }
      public void traversalClimblvl2(){
        if(enableTravlvl2){
          if(climbSequencelvl2 == 1){
            prepCLimblvl2();
          }else if(climbSequencelvl2 == 2){
            extendArmslvl2();
          }else if(climbSequencelvl2 == 3){
            armsInlvl2();
          }else if(climbSequencelvl2 == 4){
            enableTravlvl2 = false;
            climbSequencelvl2 = 1;
            go = false;
            isClimbExtendedlvl2 = false;

          }
        }
        
      }
      public void traversalClimblvl3(){
        if(enableTravlvl3){
          if(climbSequencelvl3 == 1){
            extendArmslvl3();
          }else if(climbSequencelvl3 == 2){
            armsInlvl3();
          }else if(climbSequencelvl3 == 3){
            enableTravlvl3 = false;
            climbSequencelvl3 = 1;
            isClimbExtendedlvl3 = false;
            go = false;
          }
        }
        
      }
      public void resetClimb(){
        if(enableReset){
          climb.setPositionHook(0);
          climb.setPositionArm(0);

          if(!climb.getLimitSwitch()){
            climb.setWinchSpeed(-0.3, -0.3);
          }
          else{
            climb.setWinchSpeed(0, 0);
            climb.mLeftClimb.setSelectedSensorPosition(0); 
            climb.mRightClimb.setSelectedSensorPosition(0); 
            enableReset = false;
          }
        }
      }
      public void disableClimb(){
        enableTravlvl1 = false;
        enableTravlvl1 = false;
        enableTravlvl1 = false;
        enableReset = false;
        takeOff = false;
        climb.setWinchSpeed(0, 0);
      }
      public void enableReset(){
        enableReset = true;
      }
      public void disableReset(){
        enableReset = false;
      }
      public boolean fallingEdgeUpper(){
        return index.fallingEdgeUpper();
      }

      public void periodic(){

          indexing();
          index.inTransit();
          //Climbing
          if(enableArmsUp){
            armsUp();
          }else if(enableTravlvl1){
            traversalClimblvl1();
          }else if(enableTravlvl2){
            traversalClimblvl2();
          }else if(enableTravlvl3){
            traversalClimblvl3();
          }else if(enableReset){
            resetClimb();
          }else if(takeOff){
            armsTakeOff();
          }
          //if(!(climbSequencelvl3 == 1 || climbSequencelvl3 == 2 || climbSequencelvl3 == 3)){
            climb.resetWinchPos();
          //}
          hood.calcWheelSpeed();
          SmartDashboard.putBoolean("shooting", shooting);
          megashooter2pointo.hood.printRPM();
          SmartDashboard.putNumber("camera", lime.calcDistance());
          SmartDashboard.putNumber("winch", climb.getWinchPos());
          SmartDashboard.putBoolean("limit", climb.getLimitSwitch()
          );
      }
}