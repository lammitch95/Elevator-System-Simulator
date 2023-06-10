
package com.mycompany.elevatorproject;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Label;



public class StatsKeeperPopUpController{

    @FXML
    private Label travelTime1,travelTime2,travelTime3,travelTime4,travelTime5,travelTime6,travelTime7,travelTime8,travelTime9,travelTime10;
    @FXML
    private BarChart<String, Integer> PassengerAmountChart;
    
    
    public BarChart<String, Integer> getPassengerAmountChart(){
        return PassengerAmountChart;
    }

    public Label[] getLabelList(){
        return new Label[]{travelTime1,travelTime2,travelTime3,travelTime4,travelTime5,travelTime6,travelTime7,travelTime8,travelTime9,travelTime10};
        
    }
    /**
     * @return the travelTime1
     */
    public Label getTravelTime1() {
        return travelTime1;
    }
    
    
    /**
     * @param travelTime1 the travelTime1 to set
     */
    public void setTravelTime1(Label travelTime1) {
        this.travelTime1 = travelTime1;
    }

    /**
     * @return the travelTime2
     */
    public Label getTravelTime2() {
        return travelTime2;
    }

    /**
     * @param travelTime2 the travelTime2 to set
     */
    public void setTravelTime2(Label travelTime2) {
        this.travelTime2 = travelTime2;
    }

    /**
     * @return the travelTime3
     */
    public Label getTravelTime3() {
        return travelTime3;
    }

    /**
     * @param travelTime3 the travelTime3 to set
     */
    public void setTravelTime3(Label travelTime3) {
        this.travelTime3 = travelTime3;
    }

    /**
     * @return the travelTime4
     */
    public Label getTravelTime4() {
        return travelTime4;
    }

    /**
     * @param travelTime4 the travelTime4 to set
     */
    public void setTravelTime4(Label travelTime4) {
        this.travelTime4 = travelTime4;
    }

    /**
     * @return the travelTime5
     */
    public Label getTravelTime5() {
        return travelTime5;
    }

    /**
     * @param travelTime5 the travelTime5 to set
     */
    public void setTravelTime5(Label travelTime5) {
        this.travelTime5 = travelTime5;
    }

    /**
     * @return the travelTime6
     */
    public Label getTravelTime6() {
        return travelTime6;
    }

    /**
     * @param travelTime6 the travelTime6 to set
     */
    public void setTravelTime6(Label travelTime6) {
        this.travelTime6 = travelTime6;
    }

    /**
     * @return the travelTime7
     */
    public Label getTravelTime7() {
        return travelTime7;
    }

    /**
     * @param travelTime7 the travelTime7 to set
     */
    public void setTravelTime7(Label travelTime7) {
        this.travelTime7 = travelTime7;
    }

    /**
     * @return the travelTime8
     */
    public Label getTravelTime8() {
        return travelTime8;
    }

    /**
     * @param travelTime8 the travelTime8 to set
     */
    public void setTravelTime8(Label travelTime8) {
        this.travelTime8 = travelTime8;
    }

    /**
     * @return the travelTime9
     */
    public Label getTravelTime9() {
        return travelTime9;
    }

    /**
     * @param travelTime9 the travelTime9 to set
     */
    public void setTravelTime9(Label travelTime9) {
        this.travelTime9 = travelTime9;
    }

    /**
     * @return the travelTime10
     */
    public Label getTravelTime10() {
        return travelTime10;
    }

    /**
     * @param travelTime10 the travelTime10 to set
     */
    public void setTravelTime10(Label travelTime10) {
        this.travelTime10 = travelTime10;
    }
    
    
    

     
    
}
