/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climber.DeployLifters;
import frc.robot.commands.climber.RetractLifters;
import frc.robot.commands.climber.RollToHAB;
import frc.robot.commands.collector.OpenCollector;
import frc.robot.commands.drivetrain.TimedDrive;


public class Climb extends CommandGroup {
    /**
     * Triggered by Climb Button
     */
    public Climb() {
        addSequential(new OpenCollector());
        // trigger lifting pneumatics
        addSequential(new DeployLifters());
        // drive BAG motor on until chassis anti-cliff triggered
        addSequential(new RollToHAB());
        //      Retract pneumatics
        addSequential(new RetractLifters());
        addSequential(new TimedDrive(.55, .25));
    }
}
