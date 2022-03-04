package frc.robot.Auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SubSystems.Drive;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.*;


public class moveInDir extends CommandBase{

    
    private boolean isFinish = false;
    private double distance;
    private double theta;
    private Drive drive;
    public moveInDir(double distance, double theta){//Distannce in feet
        this.distance = distance;
        this.theta = theta;
    }

    @Override
    public void initialize() {
        super.initialize();
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
        return drive.frontRight.getDriveEncoder() * Constants.sensorConv > distance;
    }

    @Override
    public void execute() {
        drive.setVector(theta, Constants.autoSpeed, 0);
        drive.periodic();
    }

    
    /** 
     * @param interrupte
     */
    @Override
    public void end(boolean interrupte) {
    }
}