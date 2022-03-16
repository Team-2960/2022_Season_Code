package frc.robot.Auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SubSystems.Drive;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.*;


public class toAngle extends CommandBase{
    //shoot the ball
    
    
    private boolean isFinish = false;
    private double theta;
    private Drive drive;
    private TrapezoidProfile trapProfile;

    Timer timer;
    public toAngle(double theta){
        
        drive = Drive.get_Instance();
        this.theta = theta + drive.navX.getFusedHeading();
        while(this.theta > 360){
          this.theta = this.theta - 360;
        }
        while(this.theta < 0){
          this.theta = this.theta + 360;
        }
        timer = new Timer();
        timer.start();
        trapProfile = new TrapezoidProfile(new TrapezoidProfile.Constraints(1, 1),
                                           new TrapezoidProfile.State(theta, 0),
                                           new TrapezoidProfile.State(drive.navX.getFusedHeading(), 0));


    }

    @Override
    public void initialize() {
    }

    /**
     * Returns true if all the commands in this group have been started and have
     * finished.
     * <p>
     * <p>
     * Teams may override this method, although they should probably reference
     * super.isFinished() if they do.
     * </p>
     *
     * @return whether this {@link CommandGroup} is finished
     */
    @Override
    public boolean isFinished() {
        return Math.abs(theta - drive.gyroAngle) < Constants.angleTolerance; //|| trapProfile.totalTime() < timer.get();
    }

    @Override
    public void execute() {
        var setpoint = trapProfile.calculate(timer.get());
        drive.setVector(0, 0, drive.anglePID(theta));
        drive.periodic();
    }

    
    /** 
     * @param interrupte
     */
    @Override
    public void end(boolean interrupte) {
        drive.setVector(0, 0, 0);
        drive.frontLeftSwerveSpeed = 0;
        drive.frontRightSwerveSpeed = 0;
        drive.backLeftSwerveSpeed = 0;
        drive.backRightSwerveSpeed = 0;
        drive.periodic();

    }
}