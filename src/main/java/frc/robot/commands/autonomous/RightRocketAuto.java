/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.collector.CollectHatch;
import frc.robot.commands.collector.ReleaseHatch;
import frc.robot.commands.drivetrain.DriveSequence;
import frc.robot.commands.drivetrain.ResetNavX;
import frc.robot.commands.drivetrain.TurnToHeading;
import frc.robot.commands.elevator.ElevateToHeight;
import frc.robot.models.PathfinderSequence;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.drivetrain.ApproachTape;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.drivetrain.DriveFeet;

public class RightRocketAuto extends CommandGroup {
    /**
     * Right start auto
     */
    public RightRocketAuto() {

        addSequential(new ResetNavX());
        addSequential(new DriveFeet(6, .5));
        addParallel(new ManualSandstormHatch());
        addSequential(new TurnToHeading(45));
        addSequential(new DriveFeet(8.5));
        //VISION
        addSequential(new WaitCommand(.3));
        addSequential(new ApproachTape());
        addSequential(new DriveFeet(.5));
        //HATCH
        addSequential(new ReleaseHatch());
        //BACK UP
        addSequential(new DriveFeet(1, true));
        addParallel(new ElevateToHeight(Elevator.Position.LoadingHatch));

        addSequential(new TurnToHeading(172));
        addParallel(new CollectHatch());
        addSequential(new DriveFeet(13));
        addSequential(new WaitCommand(.3));
        // //VISION
        addSequential(new ApproachTape());

        //BACK UP
        addSequential(new DriveFeet(2, true));

        addSequential(new TurnToHeading(-7));
        addSequential(new DriveFeet(13.4));
        addSequential(new TurnToHeading(30));
        addSequential(new ElevateToHeight(Elevator.Position.RocketHatchThree));
        addSequential(new ApproachTape());
        addSequential(new DriveFeet(.5));
        // HATCH
        addSequential(new ReleaseHatch());
        addSequential(new DriveFeet(1, true));

    }
}
