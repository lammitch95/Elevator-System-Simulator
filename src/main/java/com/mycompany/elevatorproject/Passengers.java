
package com.mycompany.elevatorproject;


import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

 public class Passengers {
    
    private String passType;
    private int startFloor;
    private int endFloor;
    private int passID;
    private int elv_num;
    private Rectangle passGUI;
    private Label passtypeGUI;
    private Label startendGUI;
    
    
    Passengers(){
        passType = "Passenger Type";
        passID = 0;
        startFloor = 0;
        endFloor = 0;
        elv_num = 0;  
    }
    
    Passengers(String passType,int passID, int startFloor, int endFloor,int elv_num, Rectangle passGUI, Label passtypeGUI, Label startendGUI){
        
        this.passType = passType;
        this.passID = passID;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.elv_num = elv_num;
        this.passGUI = passGUI;
        this.passtypeGUI = passtypeGUI;
        this.startendGUI = startendGUI;
        
    }
    
    Passengers(Passengers p){
        this.passType = p.passType;
        this.passID = p.passID;
        this.startFloor = p.startFloor;
        this.endFloor = p.endFloor;
        this.passGUI = p.passGUI;
        this.passtypeGUI = p.passtypeGUI;
        this.startendGUI = p.startendGUI;
    }
    public Rectangle getPassGUI(){
        return passGUI;
    }
    
    public void setPassGUI(Rectangle passGUI){
        this.passGUI = passGUI;
    }
    
    public Label getPassTypeGUI(){
        return passtypeGUI;
    }
    
    public void setPassTypeGUI(Label passtypeGUI){
        this.passtypeGUI = passtypeGUI;
    }
    
    public Label getStartEndGUI(){
        return startendGUI;
    }
    
    public void setStartEndGUI(Label startendGUI){
        this.startendGUI = startendGUI;
    }
    public int getElv_Num(){
        return elv_num;
    }
    
    public void setElv_Num(int elv_num){
        this.elv_num = elv_num;
    }
    
    public int getPassID(){
        return passID;
    }
   
    public void setPassID(int passID){
        this.passID = passID;
    }
    /**
     * @return the passType
     * 
     */
    public String getPassType() {
        return passType;
    }

    /**
     * @param passType the passType to set
     */
    public void setPassType(String passType) {
        this.passType = passType;
    }

    /**
     * @return the startFloor
     */
    public int getStartFloor() {
        return startFloor;
    }

    /**
     * @param startFloor the startFloor to set
     */
    public void setStartFloor(int startFloor) {
        this.startFloor = startFloor;
    }

    /**
     * @return the endFloor
     */
    public int getEndFloor() {
        return endFloor;
    }

    /**
     * @param endFloor the endFloor to set
     */
    public void setEndFloor(int endFloor) {
        this.endFloor = endFloor;
    }

   
    @Override
    public String toString(){
        
        return(getPassType() + "(iD:" + getPassID()+ ")" + "[" + getStartFloor() + " => " + getEndFloor() + "]"+"Elevator#:"+getElv_Num());
    }

}

