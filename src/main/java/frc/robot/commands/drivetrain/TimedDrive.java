/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class TimedDrive extends TimedCommand {
    double speed;
    public TimedDrive(double timeout, double speed) {
        super(timeout);
        requires(Drivetrain.getInstance());
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Drivetrain.getInstance().drive(ControlMode.PercentOutput, speed, speed);
    }

    // Called once after timeout
    @Override
    protected void end() {
        Drivetrain.getInstance().drive(ControlMode.PercentOutput, 0, 0);
    }
}
