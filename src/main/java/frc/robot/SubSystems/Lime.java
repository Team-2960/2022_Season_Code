package frc.robot.SubSystems;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Lime {
    public Lime(){

    }
    public double getHorOffset(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    }
}
