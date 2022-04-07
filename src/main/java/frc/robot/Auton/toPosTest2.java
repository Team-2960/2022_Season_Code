package frc.robot.Auton;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.SubSystems.Lime;
import frc.robot.Util.autoPathPoint;

public class toPosTest2 extends SequentialCommandGroup {
    public toPosTest2() {
        autoPathPoint[] positions1 = new autoPathPoint[]{
            new autoPathPoint(new Pose2d(0.9,0,Rotation2d.fromDegrees(-90)), true, false),
        };

        autoPathPoint[] positions2 = new autoPathPoint[]{
            new autoPathPoint(new Pose2d(0,0,Rotation2d.fromDegrees(-225)), false, false),
            new autoPathPoint(new Pose2d(0,2,Rotation2d.fromDegrees(-225)), false, true),
            new autoPathPoint(new Pose2d(0.1,3.7,Rotation2d.fromDegrees(-90)), false, false),
            new autoPathPoint(new Pose2d(-0.3,4.2,Rotation2d.fromDegrees(-55)), false, true),
            new autoPathPoint(new Pose2d(-1,3.1,Rotation2d.fromDegrees(-55)), true, true),
        };

        addCommands(
            new intakeDown(),
            new setShooterRPM(7000),
            new toArray(positions1),
            new shoot(1, 7000),
            new setShooterRPM(7000),
            new toArray(positions2),
            new shoot(2, 7000)
        );

    }
}