/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.collector;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Collector;

/**
 * Add your docs here.
 */
public class ToggleHatch extends InstantCommand {
    Collector collector = Collector.getInstance();
    public ToggleHatch() {
        super();
        requires(collector);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        collector.setEjector(!collector.getEjector());
    }

}
