
package com.mycompany.elevatorproject;


import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class TrafficGenerator {
    
    
    
    private ArrayList<ArrayList<Passengers>> floors = new ArrayList<ArrayList<Passengers>>();
    private int start_floor;
    private int end_floor;
    private int gen_elv_num;
    private int med_elv_num;
    private int sec_elv_num;
    private Passengers p = new Passengers();
    private String passenger_type;
    
    private Rectangle b1;
    private Label l1;
    private Label l2;
    
    TrafficGenerator(){
    floors.add(new ArrayList<Passengers>());
    floors.add(new ArrayList<Passengers>());
    floors.add(new ArrayList<Passengers>());
    floors.add(new ArrayList<Passengers>());
    floors.add(new ArrayList<Passengers>());
    floors.add(new ArrayList<Passengers>());
    floors.add(new ArrayList<Passengers>());
    floors.add(new ArrayList<Passengers>());
    start_floor = 0;
    end_floor = 0;
    passenger_type = "";
    
    }
   
    
    
    public Passengers pass_Generator(int pass_id){
      
        Random r = new Random();
        double n = r.nextDouble();
        start_floor = r.nextInt(8)+1;
        end_floor = r.nextInt(8)+1;
        gen_elv_num = 1;//r.nextInt(5)+1;
        sec_elv_num = r.nextInt(8)+1;
        med_elv_num = 6;//r.nextInt(3)+6;
        
        if(n < 0.0){//30% Support Staff
               
                passenger_type = "Support";
                while(start_floor == end_floor){
                    end_floor = r.nextInt(8)+1; 
                }
                b1 = new Rectangle();
                l1 = new Label();
                l2 = new Label();
                
                return new Passengers(passenger_type, pass_id, start_floor, end_floor,  med_elv_num,b1,l1,l2); 
        } 
        else if(n < 0.50){//25% Patient 0.55 //Testing Elevator Logic with one passenger
                passenger_type = "Patient";
                while(start_floor == end_floor){
                    end_floor = r.nextInt(8)+1; 
                }
                b1 = new Rectangle();
                l1 = new Label();
                l2 = new Label();
                floors.get(start_floor-1).add(new Passengers(passenger_type, pass_id, start_floor, end_floor,  gen_elv_num,b1 ,l1,l2));

                return new Passengers(passenger_type, pass_id, start_floor, end_floor,  gen_elv_num,b1 ,l1,l2); 
        } 
       
        else if(n < 0.0){//20% Medical Staff 0.75
               passenger_type = "Medical";
               while(start_floor == end_floor){
                    end_floor = r.nextInt(8)+1; 
                }
               
                b1 = new Rectangle();
                l1 = new Label();
                l2 = new Label();
                floors.get(start_floor-1).add(new Passengers(passenger_type, pass_id, start_floor, end_floor,  med_elv_num,b1 ,l1,l2));

                return new Passengers(passenger_type, pass_id, start_floor, end_floor,  med_elv_num,b1,l1,l2); 
        } 
        else if(n < 0.99){//15% Visitor 0.90
               passenger_type = "Visitor";
               while(start_floor == end_floor){
                    end_floor = r.nextInt(8)+1; 
                }
               
         b1 = new Rectangle();
                l1 = new Label();
                l2 = new Label();
                floors.get(start_floor-1).add(new Passengers(passenger_type, pass_id, start_floor, end_floor,  gen_elv_num,b1 ,l1,l2));

       
                return new Passengers(passenger_type, pass_id, start_floor, end_floor,  gen_elv_num,b1 ,l1,l2); 
        } 
        else{//Security Staff
                passenger_type = "Security";
                while(start_floor == end_floor){
                    end_floor = r.nextInt(8)+1; 
                }
              b1 = new Rectangle();
                l1 = new Label();
                l2 = new Label();
                
                floors.get(start_floor-1).add(new Passengers(passenger_type, pass_id, start_floor, end_floor,  sec_elv_num,b1 ,l1,l2));
                return new Passengers(passenger_type, pass_id, start_floor, end_floor,  sec_elv_num,b1 ,l1,l2); 
        } 
        
    }
  
    public ArrayList<ArrayList<Passengers>> getFloors(){
        return floors;
    }
    
    
}

