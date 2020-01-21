/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.blinkinpark.PlaySongForTime;
import frc.robot.commands.climber.Climb;
import frc.robot.commands.climber.DeployLifters;
import frc.robot.commands.climber.InterruptClimb;
import frc.robot.commands.climber.PreClimb;
import frc.robot.commands.climber.RetractLifters;
import frc.robot.commands.climber.RollWhileHeld;
import frc.robot.commands.climber.TimeArmSpin;
import frc.robot.commands.collector.AutoCargoCollect;
import frc.robot.commands.collector.CloseCollector;
import frc.robot.commands.collector.CollectHatch;
import frc.robot.commands.collector.ManualHatch;
import frc.robot.commands.collector.OpenCollector;
import frc.robot.commands.collector.ReleaseHatch;
import frc.robot.commands.collector.RollIntake;
import frc.robot.commands.collector.TimedRollIntake;
import frc.robot.commands.collector.ToggleHatch;
import frc.robot.commands.drivetrain.ApproachTape;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.commands.drivetrain.DriveFeet;
import frc.robot.commands.drivetrain.HoldForward;
import frc.robot.commands.drivetrain.StopDrive;
import frc.robot.commands.drivetrain.TurnToHeading;
import frc.robot.commands.elevator.ElevateManual;
import frc.robot.commands.elevator.ElevateToHeight;
import frc.robot.input.AttackThree;
import frc.robot.input.ButtonBox;
import frc.robot.input.ButtonBox2;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.BlinkinPark.Song;
import frc.robot.subsystems.Collector.Speed;
import frc.robot.subsystems.Elevator.Direction;

/**
 * Add your docs here.
 */
public class OI {
    private static OI oi = null;

    private final int LEFT_STICK = 0, RIGHT_STICK = 1, BUTTON_BOX = 2, BUTTON_BOX_2 = 3;

    public final AttackThree leftStick, rightStick;
    public final ButtonBox buttonBox;
    public final ButtonBox2 buttonBox2;

    private OI() {
        leftStick = new AttackThree(LEFT_STICK);
        rightStick = new AttackThree(RIGHT_STICK);
        buttonBox = new ButtonBox(BUTTON_BOX);
        buttonBox2 = new ButtonBox2(BUTTON_BOX_2);

        rightStick.getButton(1).whenPressed(new ApproachTape());
        rightStick.getButton(1).whenReleased(new DefaultDrive());

        // leftStick.getButton(7).whenPressed(new DriveFeet(6));

        // map button box commands
        // buttonBox2.blueOne.whenPressed(new RightRocketAuto());
        // buttonBox2.blueTwo.whenPressed(new ResetNavX());

        // elevator
        buttonBox.upperRocketCargo.whenPressed(new ElevateToHeight(Elevator.Position.RocketCargoThree));
        buttonBox.middleRocketCargo.whenPressed(new ElevateToHeight(Elevator.Position.RocketCargoTwo));
        buttonBox.lowRocketCargo.whenPressed(new ElevateToHeight(Elevator.Position.RocketCargoOne));

        buttonBox.upperRocketPanel.whenPressed(new ElevateToHeight(Elevator.Position.RocketHatchThree));
        buttonBox.middleRocketPanel.whenPressed(new ElevateToHeight(Elevator.Position.RocketHatchTwo));
        buttonBox.lowRocketPanel.whenPressed(new ElevateToHeight(Elevator.Position.HatchOne));

        buttonBox.cargoShipCargo.whenPressed(new ElevateToHeight(Elevator.Position.CargoShipCargo));
        buttonBox.cargoShipPanel.whenPressed(new ElevateToHeight(Elevator.Position.HatchOne));

        // collector
        buttonBox.collectCargo.whenPressed(new AutoCargoCollect());
        buttonBox.collectHatch.whenPressed(new CollectHatch());
        buttonBox.releaseCargo.whenPressed(new TimedRollIntake(Collector.Speed.OUT, .75));
        buttonBox.releaseHatch.whenPressed(new ReleaseHatch());

        // Climber
        buttonBox2.preClimb.whenPressed(new PreClimb());
        buttonBox.climb.whenPressed(new Climb());

        leftStick.getButton(1).whileHeld(new HoldForward());
        leftStick.getButton(1).whenReleased(new StopDrive());
        // rightStick.getButton(1).whenPressed(new InterruptClimb());

        // Manual
        buttonBox2.manualElevator.getForward().whileHeld(new ElevateManual(Direction.UP));
        buttonBox2.manualElevator.getBackward().whileHeld(new ElevateManual(Direction.DOWN));
        buttonBox2.manualElevator.getForward().whenReleased(new ElevateManual(Direction.OFF));
        buttonBox2.manualElevator.getBackward().whenReleased(new ElevateManual(Direction.OFF));
        buttonBox2.openCollector.whenPressed(new OpenCollector());
        buttonBox.closeCollector.whenPressed(new CloseCollector());
        buttonBox2.manualRoller.getForward().whileHeld(new RollIntake(Speed.OUT));
        buttonBox2.manualRoller.getBackward().whileHeld(new RollIntake(Speed.IN));
        buttonBox2.firePiston.whenPressed(new ToggleHatch());

        buttonBox2.blueOne.whenPressed(new RollWhileHeld(Climber.Direction.FORWARD));
        buttonBox2.blueOne.whenReleased(new RollWhileHeld(Climber.Direction.OFF));
        buttonBox2.blueTwo.whenPressed(new DeployLifters());
        buttonBox2.blueThree.whenPressed(new RetractLifters());
        buttonBox2.greenOne.whenPressed(new TimeArmSpin(4));
        buttonBox2.greenTwo.whenPressed(new ApproachTape());

        buttonBox2.greenTwo.whenReleased(new DriveFeet(3, true));

        // new HatchTrigger().whenActive(new PlaySongForTime(Song.Color1HeartbeatFast, 3));
        // new CargoTrigger().whenActive(new PlaySongForTime(Song.Color2HeartbeatFast, 3));
    }

    /**
     * Gets the Drivetrain instance
     * 
     * @return the single instance of the class
     */
    public static OI getInstance() {
        if (oi == null)
            oi = new OI();
        return oi;
    }
}
