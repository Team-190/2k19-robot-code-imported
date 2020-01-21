/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Climber.Direction;

public class RollWhileHeld extends InstantCommand {
    Direction direction;
    public RollWhileHeld(Direction direction) {
        super();
        requires(Climber.getInstance());
        this.direction = direction;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Climber.getInstance().setTrolleyDirection(direction);
    }
}
