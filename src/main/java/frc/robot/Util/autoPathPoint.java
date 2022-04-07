package frc.robot.Util;

import edu.wpi.first.math.geometry.Pose2d;

public class autoPathPoint {
    public final Pose2d pose;
    public final boolean isCameraTracking;
    public final boolean isIntakeOn;
    public autoPathPoint(Pose2d pose, boolean isCameraTracking, boolean isIntakeOn){
        this.pose = pose;
        this.isCameraTracking = isCameraTracking;
        this.isIntakeOn = isIntakeOn;
    }
}
