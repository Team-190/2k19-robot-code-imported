/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.blinkinpark;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.subsystems.BlinkinPark;
import frc.robot.subsystems.BlinkinPark.Song;

public class PlaySongForTime extends TimedCommand {
    Song song;

    public PlaySongForTime(Song song, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(timeout);
        this.song = song;
        requires(BlinkinPark.getInstance());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        BlinkinPark.getInstance().playSong(song);
    }
}
