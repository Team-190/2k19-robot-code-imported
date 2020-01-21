/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.models.PairedTalonSRX;

/**
 * Subsystem controls the elevator
 */
public class Elevator extends Subsystem {
    private static Elevator elevator;
    private WPI_TalonSRX motor;
    private DigitalInput zeroSwitch;

    private static final int LEFT = 15, RIGHT = 16;
    private static final int SWITCH = 9;

    private final static double ENC_BOTTOM = 0; // Encoder Value
    private final static double ENC_TOP_OFFSET = 9800; // Encoder Value
    private final static double ERROR_TOLERANCE = 200;
    private final static int DEFAULT_TIMEOUT_MS = 0;

    private Position setpoint;

    private double motorSetpoint;

    // Encoder config values
    private final int PID_X = 0, TIMEOUT_MS = 0;

    /**
     * Gets the singular instance of the Elevator class
     * 
     * @return the Elevator instance
     */
    public static Elevator getInstance() {
        if (elevator == null)
            elevator = new Elevator();
        return elevator;
    }

    /**
     * Constructs the Paired Talon, configures it
     */
    private Elevator() {
        motor = new WPI_TalonSRX(LEFT);
        motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_X, TIMEOUT_MS);
        motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
                DEFAULT_TIMEOUT_MS);
        motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
                DEFAULT_TIMEOUT_MS);
        motor.config_kP(PID_X, .75);
        motor.setInverted(InvertType.InvertMotorOutput);
        motor.setSensorPhase(true);
        motor.setNeutralMode(NeutralMode.Brake);
        motorSetpoint = Position.Ground.getPosition();
        zeroSwitch = new DigitalInput(SWITCH);
        setpoint = Position.Ground;
    }

    /**
     * Resets the encoder if the sensor is triggered
     */
    public void resetTrigger() {
        if (Elevator.getInstance().getSwitch())
            resetEncoder();
    }

    public double getMotorSetpoint() {
        return motorSetpoint;
    }

    public Position getSetpoint() {
        return setpoint;
    }

    /**
     * Gets the encoder value of the elevator
     * 
     * @return the encoder value
     */
    public int getPosition() {
        return motor.getSelectedSensorPosition();
    }

    public boolean getSwitch() {
        return !zeroSwitch.get();
    }

    public void resetEncoder() {
        motor.setSelectedSensorPosition(0, PID_X, TIMEOUT_MS);
    }

    /**
     * Converts inches to encoder values
     * 
     * @param inches the amount of inches to convert
     * @return the encoder values in inches
     */
    private double heightToEnc(double inches) {
        double heightScale = inches / Position.MaximumHeight.getPosition();
        // System.out.println(heightScale);
        return (ENC_TOP_OFFSET * heightScale) + ENC_BOTTOM;
    }

    /**
     * Changes the setpoint of Elevator subsystem to position
     * 
     * @param position Position to move the elevator
     */
    public void setHeight(Position position) {
        resetTrigger();
        setpoint = position;
        motorSetpoint = heightToEnc(position.getPosition());
        motor.set(ControlMode.Position, motorSetpoint);
    }

    public void stop() {
        resetTrigger();
        motor.set(ControlMode.PercentOutput, 0);
    }

    public void moveElevator(double speed) {
        resetTrigger();
        motor.set(ControlMode.PercentOutput, speed);
    }

    public boolean inPosition() {
        double thisError = motorSetpoint - getPosition();
        return Math.abs(thisError) <= ERROR_TOLERANCE;
    }

    public enum Position {
        Ground(0), // collector cargo too
        LoadingHatch(5), HatchOne(7), // cargo ship, rocket, and loading
        CargoShipCargo(20), RocketHatchTwo(37), RocketHatchThree(66), RocketCargoOne(22), RocketCargoTwo(51),
        RocketCargoThree(81), MaximumHeight(80);

        private int inches;

        private Position(int inches) {
            this.inches = inches;
        }

        public int getPosition() {
            return inches;
        }
    }

    public enum Direction {
        UP(1), DOWN(-1), OFF(0);
        private final int value;

        private Direction(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }
    }

    @Override
    protected void initDefaultCommand() {
        // Nothing on purpose
    }
}
