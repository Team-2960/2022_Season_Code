package frc.robot.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class twoBallAuto extends SequentialCommandGroup{
    public twoBallAuto(){
        addCommands(
            new intakeDown(),
            new modAngle(180),
            new moveInDir(36,180,-0.3)
           // new moveInDir(10, 0, 0.3)
            //new toAngle(180),
            //new shoot(1, 3000),
            //new moveInDir(48,180,0.3)

            //new toAngle(90)
            //new DriveAngle(1, 90, 0.75),
            //new moveToCoord(1, 1)
        );

    }
}
