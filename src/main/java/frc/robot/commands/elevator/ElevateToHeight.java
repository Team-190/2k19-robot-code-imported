/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Elevator;

public class ElevateToHeight extends Command {
    Elevator elevator = Elevator.getInstance();
    Elevator.Position position;
    
    public ElevateToHeight(Elevator.Position position) {
        requires(elevator);
        this.position = position;
    }

    @Override
    protected void initialize() {
        elevator.setHeight(position);
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return elevator.inPosition();
    }

    @Override
    protected void end() {
    }
}
