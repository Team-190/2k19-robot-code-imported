/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

public class TurnToHeading extends PIDCommand {
    Drivetrain drive = Drivetrain.getInstance();
    PIDController controller;
    double heading;
    int countOnTarget = 0;

    public TurnToHeading(double heading) {
        super(.03, 0, 0);
        requires(drive);
        this.heading = heading;
        controller = getPIDController();
        controller.setPercentTolerance(3);
        setInputRange(-180, 180);
        controller.setContinuous();
        setSetpoint(heading);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }


    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (controller.onTarget()) {
            if (countOnTarget == 10)
                return true;
            countOnTarget++;
        } else
            countOnTarget = 0;
        return false;
        // return controller.onTarget();
        // return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drive.drive(ControlMode.PercentOutput, 0, 0);
    }

    @Override
    protected double returnPIDInput() {
        return 1.0; //drive.getYaw();
    }

    @Override
    protected void usePIDOutput(double output) {
        drive.drive(ControlMode.PercentOutput, output, -output);
    }
}
