
package com.mycompany.elevatorproject;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;



 abstract class Elevators {
    private ArrayList<ArrayList<Passengers>> destination_floors = new ArrayList();
    private int max_capacity;
    private int max_floors;
    private boolean generalElevatorOneCall;
    private boolean generalElevatorTwoCall;
    private boolean generalElevatorThreeCall;
    private boolean generalElevatorFourCall;
    private boolean generalElevatorFiveCall;
    private boolean medicalElevatorOneCall;
    private boolean medicalElevatorTwoCall;
    private boolean medicalElevatorThreeCall;
   
    
    
    
    Elevators(){
       destination_floors.add(new ArrayList<>());
       destination_floors.add(new ArrayList<>());
       destination_floors.add(new ArrayList<>());
       destination_floors.add(new ArrayList<>());
       destination_floors.add(new ArrayList<>());
       destination_floors.add(new ArrayList<>());
       destination_floors.add(new ArrayList<>());
       destination_floors.add(new ArrayList<>());
                     
  
        max_capacity = 0;
        max_floors = 8;
        generalElevatorOneCall = true;
        generalElevatorTwoCall = true;
        generalElevatorThreeCall = true;
        generalElevatorFourCall = true;
        generalElevatorFiveCall = true;
        medicalElevatorOneCall = true;
        medicalElevatorTwoCall = true;
        medicalElevatorThreeCall = true;
       
    }
    
   
    
    
    public ArrayList<ArrayList<Passengers>> getDestination_Floors(){
        return destination_floors;
    }
    
    public int getMax_Floors(){
        return max_floors;
    }
   
    public void setMax_Floors(int max_floors){
        this.max_floors = max_floors;
    }
   
    
    public int getMax_Capacity(){
        return max_capacity;
    }
    
    public void setMax_Capacity(int max_capacity){
        this.max_capacity = max_capacity;
    }

    /**
     * @return the generalElevatorOneCall
     */
    public boolean isGeneralElevatorOneCall() {
        return generalElevatorOneCall;
    }

    /**
     * @param generalElevatorOneCall the generalElevatorOneCall to set
     */
    public void setGeneralElevatorOneCall(boolean generalElevatorOneCall) {
        this.generalElevatorOneCall = generalElevatorOneCall;
    }

    /**
     * @return the generalElevatorTwoCall
     */
    public boolean isGeneralElevatorTwoCall() {
        return generalElevatorTwoCall;
    }

    /**
     * @param generalElevatorTwoCall the generalElevatorTwoCall to set
     */
    public void setGeneralElevatorTwoCall(boolean generalElevatorTwoCall) {
        this.generalElevatorTwoCall = generalElevatorTwoCall;
    }

    /**
     * @return the generalElevatorThreeCall
     */
    public boolean isGeneralElevatorThreeCall() {
        return generalElevatorThreeCall;
    }

    /**
     * @param generalElevatorThreeCall the generalElevatorThreeCall to set
     */
    public void setGeneralElevatorThreeCall(boolean generalElevatorThreeCall) {
        this.generalElevatorThreeCall = generalElevatorThreeCall;
    }

    /**
     * @return the generalElevatorFourCall
     */
    public boolean isGeneralElevatorFourCall() {
        return generalElevatorFourCall;
    }

    /**
     * @param generalElevatorFourCall the generalElevatorFourCall to set
     */
    public void setGeneralElevatorFourCall(boolean generalElevatorFourCall) {
        this.generalElevatorFourCall = generalElevatorFourCall;
    }

    /**
     * @return the generalElevatorFiveCall
     */
    public boolean isGeneralElevatorFiveCall() {
        return generalElevatorFiveCall;
    }

    /**
     * @param generalElevatorFiveCall the generalElevatorFiveCall to set
     */
    public void setGeneralElevatorFiveCall(boolean generalElevatorFiveCall) {
        this.generalElevatorFiveCall = generalElevatorFiveCall;
    }

    /**
     * @return the medicalElevatorOneCall
     */
    public boolean isMedicalElevatorOneCall() {
        return medicalElevatorOneCall;
    }

    /**
     * @param medicalElevatorOneCall the medicalElevatorOneCall to set
     */
    public void setMedicalElevatorOneCall(boolean medicalElevatorOneCall) {
        this.medicalElevatorOneCall = medicalElevatorOneCall;
    }

    /**
     * @return the medicalElevatorTwoCall
     */
    public boolean isMedicalElevatorTwoCall() {
        return medicalElevatorTwoCall;
    }

    /**
     * @param medicalElevatorTwoCall the medicalElevatorTwoCall to set
     */
    public void setMedicalElevatorTwoCall(boolean medicalElevatorTwoCall) {
        this.medicalElevatorTwoCall = medicalElevatorTwoCall;
    }

    /**
     * @return the medicalElevatorThreeCall
     */
    public boolean isMedicalElevatorThreeCall() {
        return medicalElevatorThreeCall;
    }

    /**
     * @param medicalElevatorThreeCall the medicalElevatorThreeCall to set
     */
    public void setMedicalElevatorThreeCall(boolean medicalElevatorThreeCall) {
        this.medicalElevatorThreeCall = medicalElevatorThreeCall;
    }
    
    abstract public void upRequests(ArrayList<ArrayList<Passengers>> floors,int[] cf,ArrayList<Rectangle> genElev,ArrayList<Rectangle> bases,ArrayList<Rectangle> genDOORS, Label capacityGUI, Label currentFloorGUI,Rectangle lWall, Rectangle rWall,Label errorDisplayGUI);
    abstract public void elevatorRequestCheck(ArrayList<ArrayList<Passengers>> floors,int[] cf,ArrayList<Rectangle> genElev,ArrayList<Rectangle> bases,ArrayList<Rectangle> genDOORS, Label capacityGUI, Label currentFloorGUI,Rectangle lWall, Rectangle rWall,Label errorDisplayGUI);
    abstract public void downRequests(ArrayList<ArrayList<Passengers>> floors,int[] cf,ArrayList<Rectangle> genElev,ArrayList<Rectangle> bases,ArrayList<Rectangle> genDOORS, Label capacityGUI, Label currentFloorGUI,Rectangle lWall, Rectangle rWall,Label errorDisplayGUI);
}
