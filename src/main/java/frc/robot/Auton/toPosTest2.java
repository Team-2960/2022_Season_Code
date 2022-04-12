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
        autoPathPoint[] positions1 = new autoPathPoint[] {
                new autoPathPoint(new Pose2d(0.9, 0, Rotation2d.fromDegrees(-90)), true, false),
        };

        autoPathPoint[] positions2 = new autoPathPoint[] {
                new autoPathPoint(new Pose2d(0, 0, Rotation2d.fromDegrees(-225)), false, true),
                new autoPathPoint(new Pose2d(0, 1.5, Rotation2d.fromDegrees(-225)), false, true, 0.1,
                        Constants.thetaToPosTolerance),
                new autoPathPoint(new Pose2d(0, 2.4, Rotation2d.fromDegrees(-180)), false, true, 0.1,
                        Constants.thetaToPosTolerance),
                new autoPathPoint(new Pose2d(0.1, 4.1, Rotation2d.fromDegrees(-90)), false, true, 0.1,
                        Constants.thetaToPosTolerance),
                new autoPathPoint(new Pose2d(-1.3, 3.7, Rotation2d.fromDegrees(-35)), false, true, 0.1,
                        Constants.thetaToPosTolerance),
                new autoPathPoint(new Pose2d(-1.4, 3.7, Rotation2d.fromDegrees(-55)), true, true),
        };

        autoPathPoint[] positions3 = new autoPathPoint[] {
                new autoPathPoint(new Pose2d(0.9, 3.35, Rotation2d.fromDegrees(-215)), false, true),
        };

        autoPathPoint[] positions4 = new autoPathPoint[] {
                new autoPathPoint(new Pose2d(-0.9, -2.55, Rotation2d.fromDegrees(-35)), false, true),
                new autoPathPoint(new Pose2d(-0.9, -3.55, Rotation2d.fromDegrees(-35)), true, true),
        };
        System.out.println("Hello!");
        addCommands(
                new ParallelRaceGroup(
                        new SequentialCommandGroup(
                                new intakeDown(),
                                new setShooterRPM(11500),
                                new toArray(positions1),
                                new shoot(11500),
                                new setShooterRPM(0),
                                new toArray(positions2),
                                new setShooterRPM(11500),
                                new shoot(11500),
                                new waitTilBall(),
                                new setShooterRPM(11500),
                                new shoot(11500),
                                new setShooterRPM(0),
                                new toArray(positions3),
                                new intake(0,0.5),
                                new toArray(positions4),
                                new setShooterRPM(11500),
                                new shoot(11500),
                                new waitTilBall(),
                                new shoot(11500)),
                        new timerCommand())

        );

    }
}