/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;

/**
 * Add your docs here.
 */
public class InterruptClimb extends InstantCommand {
    /**
     * Add your docs here.
     */
    public InterruptClimb() {
        super();
        requires(Drivetrain.getInstance());
        requires(Climber.getInstance());
        requires(Collector.getInstance());
        requires(Elevator.getInstance());
        
    }

    @Override
    protected void initialize() {
    }

}
