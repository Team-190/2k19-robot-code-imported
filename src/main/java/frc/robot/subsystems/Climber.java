/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
    private static Climber climber;

    private AnalogInput chassisACS;
    private final int CHASSIS_ACS_PORT = 3;

    private DigitalInput trolleyUp, armsDown;
    private final int TROLLEY_PORT = 7, ARMS_PORT = 6;

    private WPI_TalonSRX trolleyMotor, armReleaseMotor;
    private final int TROLLEY_MOTOR_PORT = 18, ARM_RELEASE_PORT = 16;

    // private Servo servo;
    // private final int SERVO_PORT = 1;

    private DoubleSolenoid solenoid;
    private final int SOLENOID_FORWARD_PORT = 6, SOLENOID_REVERSE_PORT = 5;
    private boolean climbPressed;

    private Climber() {
        climbPressed = false;
        chassisACS = new AnalogInput(CHASSIS_ACS_PORT);

        trolleyUp = new DigitalInput(TROLLEY_PORT);
        armsDown = new DigitalInput(ARMS_PORT);

        trolleyMotor = new WPI_TalonSRX(TROLLEY_MOTOR_PORT);
        armReleaseMotor = new WPI_TalonSRX(ARM_RELEASE_PORT);
        // servo = new Servo(SERVO_PORT);

        solenoid = new DoubleSolenoid(SOLENOID_FORWARD_PORT, SOLENOID_REVERSE_PORT);
    }

    // public Value getSolenoidState() {
    //     DoubleSolenoid.Value value = solenoid.get();
    //     if (value == DoubleSolenoid.Value.kForward)
    //         return true;
    //     else
    //         value = Value.kReverse;
    //     return solenoid.get();
    // }

    public double getChassisACS() {
        return chassisACS.getVoltage();
    }

    /**
     * Value between 0 and 1
     */
    // public void setServo(double value) {
    //     servo.set(value);
    // }

    // public double getServoValue() {
    //     return servo.get();
    // }

    public void setClimbPressed() {
        climbPressed = true;
    }

    public boolean getClimbPressed() {
        return climbPressed;
    }

    public boolean isChassisACSTriggered() {
        return chassisACS.getVoltage() > 2.25;
    }

    public boolean isTrolleyUp() {
        return !trolleyUp.get();
    }

    public boolean isArmsDown() {
        return !armsDown.get();
    }

    public void setTrolleySpeed(ControlMode mode, double speed) {
        trolleyMotor.set(mode, speed);
    }

    public void setTrolleyDirection(Direction direction) {
        setTrolleySpeed(ControlMode.PercentOutput, direction.value);
    }

    public void setArmSpeed(ControlMode mode, double speed) {
        armReleaseMotor.set(mode, speed);
    }

    public void setSolenoid(boolean state) {
        DoubleSolenoid.Value value;
        if (state)
            value = Value.kForward;
        else
            value = Value.kReverse;

        solenoid.set(value);
    }

    @Override
    public void initDefaultCommand() {
    }

    public static Climber getInstance() {
        if (climber == null)
            climber = new Climber();
        return climber;
    }

    public enum Direction {
        FORWARD(1), BACKWARD(-1), OFF(0);

        private final int value;

        private Direction(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }
    }
}
