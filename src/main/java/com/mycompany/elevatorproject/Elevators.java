
package com.mycompany.elevatorproject;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;


 abstract class Elevators {
    private ArrayList<ArrayList<Passengers>> destination_floors = new ArrayList();
    private int max_capacity;
    private int max_floors;
    
    
    
    Elevators(){
       destination_floors.add(new ArrayList<Passengers>());
       destination_floors.add(new ArrayList<Passengers>());
       destination_floors.add(new ArrayList<Passengers>());
       destination_floors.add(new ArrayList<Passengers>());
       destination_floors.add(new ArrayList<Passengers>());
       destination_floors.add(new ArrayList<Passengers>());
       destination_floors.add(new ArrayList<Passengers>());
       destination_floors.add(new ArrayList<Passengers>());
                     
  
        max_capacity = 0;
        max_floors = 8;
       
    }
    
    
    Elevators(int max_capacity, int max_floors){
        this.max_capacity = max_capacity;
        this.max_floors = max_floors;
        
       
    }
    
    Elevators(Elevators e){
        
        this.max_capacity = e.max_capacity;
        this.max_floors = e.max_floors;
        
        
    }
    
    abstract public void elevators(ArrayList<ArrayList<Passengers>> fl, ArrayList<Rectangle> r);
    
    public ArrayList<ArrayList<Passengers>> getDestination_Floors(){
        return destination_floors;
    }
    
    public int getMax_Floors(){
        return max_floors;
    }
   
    public void setMax_Floors(int max_floors){
        this.max_floors = max_floors;
    }
   
    
    public int getMax_Capactiy(){
        return max_capacity;
    }
    
    public void setMax_Capacity(int max_capacity){
        this.max_capacity = max_capacity;
    }
}
