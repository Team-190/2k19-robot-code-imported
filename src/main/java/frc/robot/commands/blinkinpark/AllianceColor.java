/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.blinkinpark;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.BlinkinPark;
import frc.robot.subsystems.BlinkinPark.Song;

public class AllianceColor extends Command {

  private Alliance alliance;
  private BlinkinPark blinkinPark;

  public AllianceColor() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    blinkinPark = BlinkinPark.getInstance();
    requires(blinkinPark);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (alliance != null) {
      setColor();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Alliance newAlliance = DriverStation.getInstance().getAlliance();
    if (newAlliance != alliance) {
      alliance = newAlliance;
      setColor();
    }
  }

  private void setColor() {
    if (alliance == Alliance.Blue) {
      blinkinPark.playSong(Song.LightChaseBlue);
    } else if (alliance == Alliance.Red) {
      blinkinPark.playSong(Song.LightChaseRed);
    } else {
      blinkinPark.playSong(Song.LightChaseGray);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
}
