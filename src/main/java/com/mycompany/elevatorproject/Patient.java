
package com.mycompany.elevatorproject;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author lammi
 */
public class Patient extends Passengers{
    
    //Default Constructor
    Patient(){
        passProbability =0.25;
        passType="Patient";
        passID = 0;
        startFloor = 0;
        endFloor = 0;
        elv_num = 0;
        passGUI = new Rectangle();
        passtypeGUI = new Label();
        startendGUI = new Label();
    }
    
    
}
