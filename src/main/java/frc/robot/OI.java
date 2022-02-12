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
        /*
        hood = Hood.get_Instance();
        megashooter2pointo = MegaShooter2PointO.get_Instance();
        */


    }


    private void driveTest(){
        //drive.setVector(90, Math.sqrt(Math.pow(Math.abs(joy1.getRawAxis(0)), 2)+Math.pow(Math.abs(joy1.getRawAxis(1)), 2)), joy1.getRawAxis(4));
        
        //drive.backLeft.setSpeed(0, 0.2);
        //drive.setVector(driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1)), Math.sqrt(Math.pow(Math.abs(joy1.getRawAxis(0)), 2)+Math.pow(Math.abs(joy1.getRawAxis(1)), 2)), joy1.getRawAxis(4));
        //drive.backLeft.setAngleSpeed(drive.backLeft.anglePIDCalcABS(driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1))));
        //drive.backLeft.setDriveSpeed(Math.sqrt(Math.pow(joy1.getRawAxis(0), 2)+Math.pow(joy1.getRawAxis(1), 2))/4);
        //System.out.println(driveAngle(joy1.getRawAxis(0), joy1.getRawAxis(1)));
        //System.out.println(drive.backLeft.getEncoder() + "ENC");  
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
    public void periodic(){
        //PERIODICS
        drive.periodic();
        //megashooter2pointo.periodic();
        //Drive
        
        if(driverControl.getRawButton(1)){
            drive.homeSwerve();
        }
        else if(cameraTracking()){
            drive.setVector(driveAngle(driverControl.getRawAxis(0), driverControl.getRawAxis(1)), Math.sqrt(Math.pow(Math.abs(driverControl.getRawAxis(0)), 2)+Math.pow(Math.abs(driverControl.getRawAxis(1)), 2)), lime.getHorOffset()/-100);
        }
        else{
            drive.setVector(driveAngle(driverControl.getRawAxis(0), driverControl.getRawAxis(1)), Math.sqrt(Math.pow(Math.abs(driverControl.getRawAxis(0)), 2)+Math.pow(Math.abs(driverControl.getRawAxis(1)), 2)), driverControl.getRawAxis(4));

        }
        System.out.println(lime.calcDistance());
        
        //INDEX AND INTAKE
        if(shoot()){
            megashooter2pointo.shoot();
        }else if(intake()){
            megashooter2pointo.intakeOn();
        }else{
            megashooter2pointo.intakeOff();
        }

        //CLIMB
        if(prepClimb()){
            megashooter2pointo.prepCLimb();
        }else if(extendArms()){
            megashooter2pointo.extendArms();
        }else if(climbToLvl1()){
            megashooter2pointo.armsIn();
        }else if(traversalClimb()){
            megashooter2pointo.traversalClimb();
        }
        
        

        //COMMENT TEST
        /*
        //INTAKE
        if(flipIntake()){
            megashooter2pointo.flipIntake();
        }

        //INDEX
        if(shoot()){
            megashooter2pointo.setShooterRPM(Constants.uTarmacRPM, Constants.lTarmacRPM);
            megashooter2pointo.shoot();
        }
        if(intake()){
            megashooter2pointo.intakeOn();
        }
        else{
            megashooter2pointo.intakeOff();
        }

        //Climb
        if(climbToLvl1()){
            megashooter2pointo.climbToLvl1();
        }else if(traversalClimb()){
            megashooter2pointo.traversalClimb();
        }
        */
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
        return driverControl.getTrigger();
    }

    //OPERATORS
    public boolean flipIntake(){
        return operatorControl.getRawButton(1);
    }

    public boolean shoot(){
        return operatorControl.getRawButton(2);
    }

    public boolean prepClimb(){
        return operatorControl.getRawButton(3);
    }

    public boolean extendArms(){
        return operatorControl.getRawButton(4);
    }

    public boolean climbToLvl1(){
        return operatorControl.getRawButton(5);
    }

    public boolean traversalClimb(){
        return operatorControl.getRawButton(6);
    }


}