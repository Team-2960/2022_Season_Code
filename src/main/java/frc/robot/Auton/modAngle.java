package frc.robot.Auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SubSystems.Drive;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.*;


public class modAngle extends CommandBase{
    //shoot the ball
    
    
    private boolean isFinish = false;
    private double theta;
    private Drive drive;
    Timer timer = new Timer();
    public modAngle(double theta){
        this.theta = theta;
        drive = drive.get_Instance();
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
        return timer.get() > 0.3;
    }

    @Override
    public void execute() {
        drive.setVector(theta, 0 ,0);
        drive.periodic();
    }

    
    /** 
     * @param interrupte
     */
    @Override
    public void end(boolean interrupte) {
    }
}