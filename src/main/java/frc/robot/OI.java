package frc.robot;

//Subsystems
import frc.robot.SubSystems.Climb;
import frc.robot.SubSystems.Drive;
import frc.robot.SubSystems.Hood;
import frc.robot.SubSystems.Index;
import frc.robot.SubSystems.Intake;
import frc.robot.SubSystems.Lime;
import frc.robot.SubSystems.MegaShooter2PointO;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI extends SubsystemBase {
    //Classes
    private Climb climb;
    private Intake intake;
    private Drive drive;
    public Hood hood;
    private Index index;
    public Joystick driverControl;
    public Joystick operatorControl;
    public Lime lime;
    public MegaShooter2PointO megashooter2pointo;

    public OI(){
        //camera = Camera.get_Instance();
        drive = Drive.get_Instance();
        driverControl = new Joystick(Constants.driverControl);
        operatorControl = new Joystick(Constants.operatorControl);
        lime = new Lime();

        //COMMENT TEST
        megashooter2pointo = MegaShooter2PointO.get_Instance();
        


    }



    private void driveTest(){
        if(driverControl.getRawButton(1)){
            drive.backLeft.setAngleSpeed(0.3);
        }
        else{
            drive.backLeft.setAngleSpeed(0);
        }
        if(driverControl.getRawButton(2)){
            drive.backLeft.setDriveSpeed(0.3);
        }
        else{
            drive.backLeft.setDriveSpeed(0);

        }
        if(driverControl.getRawButton(3)){
            megashooter2pointo.hood.setSpeed(0.3, 0.1);
        }
        else{
            megashooter2pointo.hood.setSpeed(0, 0);
        }
        if(driverControl.getRawButton(4)){
            megashooter2pointo.climb.setPositionHook(1);
        }
        if(driverControl.getRawButton(5)){
            megashooter2pointo.climb.setPositionHook(0);
        }
        if(driverControl.getRawButton(6)){
            megashooter2pointo.climb.setPositionArm(1);
        }
        if(driverControl.getRawButton(7)){
            megashooter2pointo.climb.setPositionArm(0);
        }
    }
     private void driveTest2(){
        if(driverControl.getRawButton(1)){
            megashooter2pointo.index.setSpeed(0.35);
        }

        else if(driverControl.getRawButton(2)){
            megashooter2pointo.index.setSpeed(-0.35);
        }
        else{
            megashooter2pointo.index.setSpeed(0);

        }
        if(driverControl.getRawButton(3)){
            megashooter2pointo.intake.setSpeed(0.35);
        }

        else if(driverControl.getRawButton(4)){
            megashooter2pointo.intake.setSpeed(-0.35);
        }
        else{
            megashooter2pointo.intake.setSpeed(0);

        }
        if(driverControl.getRawButton(5)){
            megashooter2pointo.intakeDown();
        }
        if(driverControl.getRawButton(6)){
            megashooter2pointo.intakeUp();
        }


        if(driverControl.getRawButton(8)){
            drive.frontLeft.setDriveSpeed(0.1);
        }
        else{
            drive.frontLeft.setDriveSpeed(0);
        }
     }


    public double driveAngle(double x, double y){
        double done = 0;
        y = -y;
        if(x<0){
            if(y>0){
                done = (180/Math.PI)*(3*Math.PI/2 - Math.atan(y/-x));
            }else{
                done = (180/Math.PI)*(3*Math.PI/2 + Math.atan(-y/-x));
            }
        }else if(x>0){
            if(y>0){
                done = (180/Math.PI)*(Math.PI/2 + Math.atan(y/x));
            }else{
                done = (180/Math.PI)*(Math.PI/2 - Math.atan(-y/x));
            }
        }else{
            if(y<0){
                done = 1;
            }else{
                done = 180;
            }

   
        }
        

        done = Math.abs(done -360);
        return done;
    }
    public void oiRun(){
        //PERIODICS
        drive.periodic();
       SmartDashboard.putNumber("distance", lime.calcDistance());
        megashooter2pointo.periodic();
        //Drive
        /*
        if(driverControl.getRawButton(1)){
            drive.setVector(0,0,0);
        }
        else */if(cameraTracking()){
            drive.setVector(driveAngle(driverControl.getRawAxis(0), driverControl.getRawAxis(1)), Math.sqrt(Math.pow(Math.abs(driverControl.getRawAxis(0)), 2)+Math.pow(Math.abs(driverControl.getRawAxis(1)), 2)), -0.1 * lime.getHorOffset());
        }
        else{
            drive.setVector(driveAngle(driverControl.getRawAxis(0), driverControl.getRawAxis(1)), Math.sqrt(Math.pow(Math.abs(driverControl.getRawAxis(0)), 2)+Math.pow(Math.abs(driverControl.getRawAxis(1)), 2)), driverControl.getRawAxis(4)*2);
        }
        
        //System.out.println(lime.getHorOffset());
        
        if(shoot()){
            megashooter2pointo.shoot(Constants.edgeTarmacRPM);
            //megashooter2pointo.shootCamera();
        }else if(shootLow()){
            megashooter2pointo.shoot(Constants.lowGoalRPM);
        }else if(rampUp()){
            megashooter2pointo.setShooterRPM(10500, 10500);
        }
        else{
            megashooter2pointo.shootOff();
        }
        
        //INDEX AND INTAKE
        if(reverseIntake()){
            megashooter2pointo.isIndexReversed(true);
        }else if(intake()){
            megashooter2pointo.isIndexReversed(false);
            megashooter2pointo.intakeOn();
        }else{
            megashooter2pointo.intakeOff();
            megashooter2pointo.isIndexReversed(false);
        }
        
        if(driverControl.getRawButton(5)){
            megashooter2pointo.climb.setPositionArm(1);
        }else if(driverControl.getRawButton(6)){
            megashooter2pointo.climb.setPositionArm(0);
        }
        if(operatorControl.getPOV(0)==0){
            megashooter2pointo.intakeUp();
        }else if(operatorControl.getPOV(0) == 180){
            megashooter2pointo.intakeDown();
        }
        
        //CLIMB
        if(climbToLvl1()){
            megashooter2pointo.enableTravClimblvl1();
        }
        else if(climbToLvl2()){
            megashooter2pointo.enableTravClimblvl2();
        }
        else if(climbToLvl3()){
            megashooter2pointo.enableTravClimblvl3();
        }else if(resetClimb()){
            megashooter2pointo.enableReset();
        }
        //TEST OI

        //GITHUB??????
        //GITHUB 2

    }
    //DRIVER CONTROLS
    public boolean homeSwerve(){
        return driverControl.getRawButton(1);
    }

    public boolean cameraTracking(){
        return driverControl.getRawButton(2);
    }

    public boolean intake(){
        return driverControl.getRawAxis(3) >0.3 || operatorControl.getRawAxis(2) > 0.3;
    }

    //OPERATORS
    public boolean shoot(){
        return operatorControl.getRawAxis(3) > 0.3;
    }

    public boolean climbToLvl1(){
        return operatorControl.getRawButton(1);
    }

    public boolean climbToLvl2(){
        return operatorControl.getRawButton(2);
    }

    public boolean climbToLvl3(){
        return operatorControl.getRawButton(4);
    }

    public boolean resetClimb(){
        return driverControl.getRawButton(8);
    }
    public boolean rampUp(){
        return operatorControl.getRawButton(6);
    }
    public boolean reverseIntake(){
        return operatorControl.getRawButton(5);
    }
    public boolean shootLow(){
        return operatorControl.getRawButton(7);
    }
}