/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;
import frc.robot.input.AttackThree.AttackThreeAxis;

/**
 * Reads joysticks to drive
 */
public class DefaultDrive extends Command {
    private Drivetrain drive = Drivetrain.getInstance();

    /**
     * Requires drivetrain subsystem
     */
    public DefaultDrive() {
        requires(drive);
    }

    /**
     * Sets talons to coast
     */
    @Override
    protected void initialize() {
        // drive.setCoast();
    }

    /**
     * Reads joysticks, sets speeds
     */
    @Override
    protected void execute() {
        double left = -OI.getInstance().leftStick.getAxis(AttackThreeAxis.Y);
        left *= Math.abs(left);
        double right = -OI.getInstance().rightStick.getAxis(AttackThreeAxis.Y);
        right *= Math.abs(right);
        drive.drive(ControlMode.PercentOutput, left, right);
    }

    /**
     * @return false, it never ends unless interrupted
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /**
     * Sets speed to 0, sets talons to brake
     */
    @Override
    protected void end() {
        drive.drive(ControlMode.PercentOutput, 0, 0);
        drive.setBrake();
    }
}
