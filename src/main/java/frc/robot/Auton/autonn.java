package frc.robot.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Auton.*;

public class autonn extends SequentialCommandGroup{
    public autonn(){
        addCommands(
            //new DriveAngle(1, 90, 0.75),
            new moveToCoord(1, 1)
        );



    }
}