package frc.robot.Auton;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.SubSystems.Lime;

public class toPosTest extends SequentialCommandGroup {
    public toPosTest() {
        Lime lime = new Lime();
        addCommands(
                new toPos(1,0,0)
        );

    }
}