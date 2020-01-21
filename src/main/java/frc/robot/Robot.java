/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.Center;
import frc.robot.commands.autonomous.LeftRocketAuto;
import frc.robot.commands.autonomous.ManualSandstormHatch;
import frc.robot.commands.autonomous.RightRocketAuto;
import frc.robot.commands.drivetrain.ResetNavX;
import frc.robot.commands.drivetrain.TurnToHeading;
import frc.robot.subsystems.BlinkinPark;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Vision;

public class Robot extends TimedRobot {
    private static final String MANUAL = "Teleop";
    private static final String LEFT_ROCKET = "Left rocket";
    private static final String RIGHT_ROCKET = "Right rocket";
    private static final String CENTER = "Center";
    private static Command autoCommand = null;
    private String autoSelected;
    private final SendableChooser<String> autoChooser = new SendableChooser<>();
    ShuffleboardTab tab;
    NetworkTableEntry elevHeight, zeroSwitch, elevSetpoint, elevMotorSetpoint;
    NetworkTableEntry hasHatch, hasCargo, angle;
    NetworkTableEntry encoderLeft, encoderRight;
    NetworkTableEntry chassisACS, servo, trolleyUp, armsDown;
    NetworkTableEntry portCount;

    @Override
    public void robotInit() {
        CameraServer.getInstance().startAutomaticCapture();
        OI.getInstance();
        Collector.getInstance();
        Drivetrain.getInstance();
        Elevator.getInstance();
        BlinkinPark.getInstance();
        // Drivetrain.getInstance().resetNavX();
        Climber.getInstance();
        Vision.getInstance();

        autoChooser.setDefaultOption(MANUAL, MANUAL);
        autoChooser.addOption(LEFT_ROCKET, LEFT_ROCKET);
        autoChooser.addOption(RIGHT_ROCKET, RIGHT_ROCKET);
        autoChooser.addOption(CENTER, CENTER);
        // SmartDashboard.putData("Autos", autoChooser);

        tab = Shuffleboard.getTab("Sensors");
        tab.add(new ResetNavX());
        // tab.add(new TurnToHeading(90));

        SimpleWidget elevHeightWidget = tab.add("Elevator Height", 0);
        elevHeightWidget.withSize(1, 1).withPosition(5, 2);
        elevHeight = elevHeightWidget.getEntry();

        SimpleWidget zeroSwitchWidget = tab.add("Elevator Switch", false);
        zeroSwitchWidget.withSize(1, 1).withPosition(6, 3);
        zeroSwitch = zeroSwitchWidget.getEntry();

        SimpleWidget elevSetpointWidget = tab.add("Elevator Setpoint", Elevator.Position.Ground.name());
        elevSetpointWidget.withSize(1, 1).withPosition(5, 1);
        elevSetpoint = elevSetpointWidget.getEntry();

        SimpleWidget elevMotorSetpointWidget = tab.add("Elevator Motor Setpoint", 0);
        elevMotorSetpointWidget.withSize(1, 1).withPosition(6, 2);
        elevMotorSetpoint = elevMotorSetpointWidget.getEntry();

        SimpleWidget hasCargoWidget = tab.add("Has Cargo", false);
        hasCargoWidget.withSize(1, 1).withPosition(5, 3);
        hasCargo = hasCargoWidget.getEntry();

        SimpleWidget angleWidget = tab.add("Drivetrain Angle", 0);
        angleWidget.withSize(2, 1).withPosition(2, 3);
        angle = angleWidget.getEntry();

        SimpleWidget encoderLeftWidget = tab.add("Left encoder", 0);
        encoderLeftWidget.withSize(1, 1).withPosition(2, 2);
        encoderLeft = encoderLeftWidget.getEntry();

        SimpleWidget encoderRightWidget = tab.add("Right encoder", 0);
        encoderRightWidget.withSize(1, 1).withPosition(3, 2);
        encoderRight = encoderRightWidget.getEntry();

        SimpleWidget chassisACSWidget = tab.add("Chassis ACS", 0);
        chassisACSWidget.withSize(1, 1).withPosition(9, 1);
        chassisACS = chassisACSWidget.getEntry();

        SimpleWidget servoWidget = tab.add("Climber Servo", 0);
        servoWidget.withSize(1, 1).withPosition(9, 2);
        servo = servoWidget.getEntry();

        SimpleWidget hasHatchWidget = tab.add("Have Hatch", false);
        hasHatchWidget.withSize(1, 1).withPosition(8, 0);
        hasHatch = hasHatchWidget.getEntry();

        SimpleWidget trolleyUpWidget = tab.add("Trolley Up", false);
        trolleyUpWidget.withSize(1, 1).withPosition(8, 1);
        trolleyUp = trolleyUpWidget.getEntry();

        SimpleWidget armsDownWidget = tab.add("Arms Down", false);
        armsDownWidget.withSize(1, 1).withPosition(8, 2);
        armsDown = armsDownWidget.getEntry();

        SimpleWidget portCountWidget = tab.add("Port Count", 0);
        portCountWidget.withSize(1, 1).withPosition(6, 1);
        portCount = portCountWidget.getEntry();

        tab.add("Auto chooser", autoChooser).withSize(2, 1).withPosition(2, 1);
    }

    @Override
    public void robotPeriodic() {
        // tab.add("Elevator subsystem", Elevator.getInstance());
        elevHeight.setDouble(Elevator.getInstance().getPosition());
        zeroSwitch.setBoolean(Elevator.getInstance().getSwitch());
        elevSetpoint.setString(Elevator.getInstance().getSetpoint().name());
        elevMotorSetpoint.setDouble(Elevator.getInstance().getMotorSetpoint());
        hasHatch.setBoolean(Collector.getInstance().hasHatch());
        hasCargo.setBoolean(Collector.getInstance().hasCargo());
        encoderLeft.setNumber(Drivetrain.getInstance().getLeftPosition());
        encoderRight.setNumber(Drivetrain.getInstance().getRightPosition());
        chassisACS.setNumber(Climber.getInstance().getChassisACS());
        // servo.setNumber(Climber.getInstance().getServoValue());
        trolleyUp.setBoolean(Climber.getInstance().isTrolleyUp());
        armsDown.setBoolean(Climber.getInstance().isArmsDown());
        portCount.setNumber(Vision.getInstance().getPortCount());
    }

    @Override
    public void autonomousInit() {
        Vision.getInstance().setLightOn(true);
        autoSelected = autoChooser.getSelected();
        switch (autoSelected) {
        case LEFT_ROCKET:
            autoCommand = new LeftRocketAuto();
            break;
        case RIGHT_ROCKET:
            autoCommand = new RightRocketAuto();
            break;
        case CENTER:
            autoCommand = new Center();
            break;
        default:
            autoCommand = new ManualSandstormHatch();
            break;
        }
        autoCommand.start();
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // Drivetrain.getInstance().resetNavX();
        Vision.getInstance().setLightOn(true);
        if (autoCommand != null)
            autoCommand.cancel();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
        Scheduler.getInstance().run();
    }
}
