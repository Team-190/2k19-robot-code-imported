/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Elevator;

public class ElevateManual extends Command {
    Elevator.Direction direction;
    Elevator elevator = Elevator.getInstance();

    public ElevateManual(Elevator.Direction direction) {
        requires(elevator);
        this.direction = direction;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        // System.out.println("Manual elevator init");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        elevator.moveElevator(direction.get()*.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        
    }

    // Called once after isFinished returns true
    @Override
    protected void interrupted() {
        // System.out.println("End manual elevator");
    }
}
