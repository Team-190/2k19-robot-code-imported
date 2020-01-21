/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.collector.CloseCollector;
import frc.robot.commands.elevator.ElevateToHeight;
import frc.robot.subsystems.Elevator;

public class ManualSandstormHatch extends CommandGroup {
    public ManualSandstormHatch() {
        addSequential(new ElevateToHeight(Elevator.Position.HatchOne));
        addSequential(new CloseCollector());
        // addSequential(new TurnToHeading());
    }
}
