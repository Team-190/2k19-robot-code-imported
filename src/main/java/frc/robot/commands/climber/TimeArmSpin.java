/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.subsystems.Climber;

/**
 * Add your docs here.
 */
public class TimeArmSpin extends TimedCommand {
    /**
     * Add your docs here.
     */
    public TimeArmSpin(double timeout) {
        super(timeout);
        requires(Climber.getInstance());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Climber.getInstance().setArmSpeed(ControlMode.PercentOutput, .5);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Called once after timeout
    @Override
    protected void end() {
        Climber.getInstance().setArmSpeed(ControlMode.PercentOutput, 0);
    }
}
