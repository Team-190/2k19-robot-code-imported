/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber;

public class RetractLifters extends Command {
    Climber climber = Climber.getInstance();

    public RetractLifters() {
        requires(climber);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        System.out.println("Retract Lifters");
        climber.setSolenoid(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return climber.isTrolleyUp();
    }
}
