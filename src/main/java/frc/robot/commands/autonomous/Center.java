/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.DriveSequence;
import frc.robot.models.PathfinderSequence;

public class Center extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Center() {
    addSequential(new ManualSandstormHatch());
    addSequential(new DriveSequence(PathfinderSequence.Center, true));
  }
}
