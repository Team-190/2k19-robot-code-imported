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
 * Opens the collector
 */
public class OpenCollector extends InstantCommand {
    Collector collector = Collector.getInstance();
    /**
     * Requires Collector subsystem
     */
    public OpenCollector() {
        requires(collector);
    }

    /**
     * Sets collector to open
     */
    @Override
    protected void initialize() {
        collector.setCollector(false);
        // System.out.println("Open");
    }
}
