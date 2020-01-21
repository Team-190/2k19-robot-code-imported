/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Collector.Speed;

public class RollIntakeForCargo extends Command {
    Collector collector = Collector.getInstance();
    Speed speed;

    public RollIntakeForCargo(Speed speed) {
        requires(collector);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        collector.setIntakeSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        return collector.hasCargo();
        // return false;
    }

    @Override
    protected void end() {
        collector.setIntakeSpeed(Speed.OFF);
    }
}
