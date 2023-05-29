
package com.mycompany.elevatorproject;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

abstract public class Passengers {
    
    protected String passType;
    protected double passProbability;
    protected int startFloor;
    protected int endFloor;
    protected int passID;
    protected int elv_num;
    protected Rectangle passGUI;
    protected Label passtypeGUI;
    protected Label startendGUI;
    private double travelTime;
    
    
    Passengers(){
        
        passType = "Passenger Type";
        startFloor = -1;
        endFloor = -1;
        passID = -1;
        elv_num = 0;
        passGUI = new Rectangle();
         passtypeGUI = new Label();
         startendGUI = new Label(); 
         passProbability = 0.0;
         travelTime = 0;
    }
    
    Passengers(String passType,int passID, int startFloor, int endFloor,int elv_num, Rectangle passGUI, Label passtypeGUI, Label startendGUI, double passProbability,double travelTime){
        
        this.passType = passType;
        this.passID = passID;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.elv_num = elv_num;
        this.passGUI = passGUI;
        this.passtypeGUI = passtypeGUI;
        this.startendGUI = startendGUI;
        this.passProbability = passProbability;
        this.travelTime = travelTime;
        
    }
    
    Passengers(Passengers p){
        this.passType = p.passType;
        this.passID = p.passID;
        this.startFloor = p.startFloor;
        this.endFloor = p.endFloor;
        this.passGUI = p.passGUI;
        this.passtypeGUI = p.passtypeGUI;
        this.startendGUI = p.startendGUI;
        this.passProbability = p.passProbability;
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
        
        return(passType + "(iD:" + passID+ ")" + "[" + startFloor + " => " + endFloor + "]"+"Elevator#:"+elv_num);
    }

    /**
     * @return the passProbability
     */
    public double getPassProbability() {
        return passProbability;
    }

    /**
     * @param passProbability the passProbability to set
     */
    public void setPassProbability(double passProbability) {
        this.passProbability = passProbability;
    }

    /**
     * @return the travelTime
     */
    public double getTravelTime() {
        return travelTime;
    }

    /**
     * @param travelTime the travelTime to set
     */
    public void setTravelTime(double travelTime) {
        this.travelTime = travelTime;
    }

   
}

