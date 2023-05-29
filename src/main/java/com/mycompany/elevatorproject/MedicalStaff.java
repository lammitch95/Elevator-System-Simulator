
package com.mycompany.elevatorproject;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author lammi
 */
public class MedicalStaff extends Passengers {
    
    MedicalStaff(){
        passProbability = 0.3;
        passType = "Medical";
        passID = 0;
        startFloor = 0;
        endFloor = 0;
        elv_num = 0;
        passGUI = new Rectangle();
        passtypeGUI = new Label();
        startendGUI = new Label();
    }
    
    
    
    
    
}
