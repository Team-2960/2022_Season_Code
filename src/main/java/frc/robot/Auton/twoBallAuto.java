package frc.robot.Auton;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.SubSystems.Lime;

public class twoBallAuto extends SequentialCommandGroup {
    public twoBallAuto() {
        Lime lime = new Lime();
        addCommands(
                new intakeDown(),
                new modAngle(180),
                new ParallelCommandGroup(new moveInDir(36, 180, -0.3),
                        new intake(0, 3)),
                new turnWithTime(3.5, true),
                //new conTurn(-180),
                new camera(),
                new shoot(2, 11500)
        );

    }
}
