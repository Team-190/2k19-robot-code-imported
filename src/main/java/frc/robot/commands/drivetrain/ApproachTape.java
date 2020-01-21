/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

import java.util.Arrays;
import java.util.Collections;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ApproachTape extends PIDCommand {
    Drivetrain drive = Drivetrain.getInstance();
    Vision vision = Vision.getInstance();

    boolean noLines = false;
    double[] distances, ports;

    public ApproachTape() {
        super(1.0, 0, 0);
        requires(drive);
        setInputRange(-1, 1);
        getPIDController().setOutputRange(-1, 1);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        noLines = false;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return noLines;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drive.drive(ControlMode.PercentOutput, 0, 0);
    }

    @Override
    protected double returnPIDInput() {
        distances = vision.getDistances();
        ports = vision.getXCoordinates();

        // find the minimum (the distances are negative for some bizarre reason)
        int minIndex = -1;
        double minValue = 1;

        for (int i = 0; i < distances.length; i++) {
            if (distances[i] < minValue) {
                minIndex = i;
                minValue = distances[i];
            }
        }

        // if (minValue < -40 || minIndex == -1) {
        if (minIndex == -1) {
            noLines = true;
            // System.out.println("We are stopping");
            return 0;
        } else {
            noLines = false;
        }
        // double port = Math.max(ports[minIndex]/80 - 1.5, -1);
        // double port = Math.max(ports[minIndex]/80 - 1, -1);

        return ports[minIndex] / 75 - 1.47;
    }

    @Override
    protected void usePIDOutput(double output) {
        if (noLines) {
            drive.drive(ControlMode.PercentOutput, 0, 0);
        } else {
            drive.arcadeDrive(.5, output);
        }
    }
}
