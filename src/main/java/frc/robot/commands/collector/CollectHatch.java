/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.collector;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.blinkinpark.PlaySongForTime;
import frc.robot.commands.elevator.ElevateToHeight;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.BlinkinPark.Song;

public class CollectHatch extends CommandGroup {
    
    public CollectHatch() {
        addSequential(new CloseCollector());
        addSequential(new ElevateToHeight(Elevator.Position.LoadingHatch));
        addSequential(new HatchOpenLimitSwitch());
        addParallel(new PlaySongForTime(Song.Color1HeartbeatFast, 1));
        addSequential(new HatchClose());
    }
}
