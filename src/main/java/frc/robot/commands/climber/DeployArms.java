/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Climber;

/**
 * Add your docs here.
 */
public class DeployArms extends InstantCommand {
    Climber climber;
    /**
     * Add your docs here.
     */
    public DeployArms() {
        super();
        climber = Climber.getInstance();
        requires(climber);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        climber.setClimbPressed();
        // climber.setServo(.5);
    }

}
