/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.subsystems.Collector;

public class ManualHatch extends TimedCommand {
  public ManualHatch() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(0.25);
    requires(Collector.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Collector.getInstance().setEjector(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Collector.getInstance().setEjector(false);
  }
}
