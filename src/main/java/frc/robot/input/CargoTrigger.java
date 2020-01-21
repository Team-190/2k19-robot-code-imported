/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.input;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.subsystems.Collector;

/**
 * Add your docs here.
 */
public class CargoTrigger extends Trigger {
  @Override
  public boolean get() {
    return Collector.getInstance().hasCargo();
  }
}
