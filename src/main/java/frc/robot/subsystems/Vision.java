/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Reads values from NetworkTables written from the Pi
 */
public class Vision {
    private static Vision vision;

    private NetworkTable table;
    String tableKey = "Sensors";
    Solenoid lightRing;
    final int LIGHT_PORT = 3;

    private Vision() {
        table = NetworkTableInstance.getDefault().getTable(tableKey);
        lightRing = new Solenoid(LIGHT_PORT);
    }

    /**
     * Gets the Vision instance
     * @return the single instance of the class
     */
    public static Vision getInstance() {
        if (vision == null)
            vision = new Vision();
        return vision;
    }

    /**
     * Gets the array of x coordinates of cargo ports detected by the rPi
     * @return array of x coordinates of detected cargo ports
     */
    public double[] getXCoordinates() {
        return table.getEntry("ports").getDoubleArray(new double[getPortCount()]);
    }

    public int getPortCount() {
        int ports = (int) table.getEntry("nb_ports").getDouble(0);
        // if (ports > 0) System.out.println(ports);
        // System.out.println((int) table.getEntry("nb_ports").getDouble(0));
        return ports;
    }


    public void setLightOn(boolean status) {
        lightRing.set(status);
    }

    public double[] getDistances() {
        return table.getEntry("distances").getDoubleArray(new double[getDistCount()]);
    }

    public double distToFeet(double pixels) {
        return 0.1135 * pixels + 7.6591;
    }



    public int getDistCount() {
        return table.getEntry("nb_distances").getNumber(-1).intValue();
    }
}
 