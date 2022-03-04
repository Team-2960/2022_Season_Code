package frc.robot.Auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SubSystems.Drive;
import frc.robot.SubSystems.MegaShooter2PointO;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.*;


public class shoot extends CommandBase{

    public  MegaShooter2PointO megashooter2pointo;
    private boolean isFinish = false;
    private int numBalls = 0;
    private int totalBalls;
    public shoot(int balls, double speed){
        megashooter2pointo = MegaShooter2PointO.get_Instance();
        megashooter2pointo.setShooterRPM(speed, speed);
        totalBalls = balls;
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
        return (numBalls > totalBalls);
    }

    @Override
    public void execute() {
        megashooter2pointo.shootOn();
        if(megashooter2pointo.fallingEdgeUpper()){
            numBalls++;
        }
    }

    
    /** 
     * @param interrupte
     */
    @Override
    public void end(boolean interrupte) {
        megashooter2pointo.setShooterRPM(0, 0);

    }
}