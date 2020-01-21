/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class StopDrive extends InstantCommand {
  /**
   * Add your docs here.
   */
  public StopDrive() {
    super();
    requires(Drivetrain.getInstance());
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Drivetrain.getInstance().drive(ControlMode.PercentOutput, 0, 0);
  }

}
