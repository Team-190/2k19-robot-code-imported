/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains elevator buttons, collecting
 */
public class ButtonBox extends Joystick {

    public JoystickButton upperRocketCargo, middleRocketCargo, lowRocketCargo, cargoShipCargo,

            upperRocketPanel, middleRocketPanel, lowRocketPanel, cargoShipPanel,
            // End elevator heights

            collectCargo, collectHatch, releaseCargo, releaseHatch,
            // End collector

            climb,

            // manual
            closeCollector;

    // public Rocker
    // // manualElevator;
    // manualCollector; // pistons
    // manualRoller,
    // manualClimbPistons; // cylinders

    public ButtonBox(int channel) {
        super(channel);
        upperRocketCargo = new JoystickButton(this, 7);
        middleRocketCargo = new JoystickButton(this, 6);
        lowRocketCargo = new JoystickButton(this, 5);
        cargoShipCargo = new JoystickButton(this, 8);

        upperRocketPanel = new JoystickButton(this, 3);
        middleRocketPanel = new JoystickButton(this, 2);
        lowRocketPanel = new JoystickButton(this, 1);
        cargoShipPanel = new JoystickButton(this, 4);
        // End elevator heights

        collectCargo = new JoystickButton(this, 10);
        collectHatch = new JoystickButton(this, 12);
        releaseCargo = new JoystickButton(this, 9);
        releaseHatch = new JoystickButton(this, 13);
        // End collector

        climb = new JoystickButton(this, 11);
        closeCollector = new JoystickButton(this, 15);

    }

}
