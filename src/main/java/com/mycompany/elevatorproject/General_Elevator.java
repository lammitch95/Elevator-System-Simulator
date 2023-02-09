
package com.mycompany.elevatorproject;


import java.util.ArrayList;
import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
public class General_Elevator extends Elevators{
    
    
    
   
  
    
    private TranslateTransition passIN;
    
    
    private TranslateTransition passtypeIN;
   
    
    private TranslateTransition startendIN;
    private TranslateTransition tt;
    private TranslateTransition tt2;
    private ScaleTransition st;
    private ScaleTransition st2;
    private ParallelTransition p;
    private int genOne_CHECK;
    private Rectangle leftDoor;
    private Rectangle rightDoor;
    private int findDoors;
  
   
    
     General_Elevator() {
        setMax_Capacity(10); 
        genOne_CHECK = 0;
        findDoors = 0;
    }
        
   
    
    @Override
    public void elevators(ArrayList<ArrayList<Passengers>> floors, ArrayList<Rectangle> r){
        
        
        
    }
     
    //General Elevator 1 Operations for Up and Down Requests
    public int[] genOne_UP_REQUESTS(ArrayList<ArrayList<Passengers>> floors,int[] currentFloors,ArrayList<Passengers> genElev1){
     
        for(int i = currentFloors[0]+1; i <= 8; i++){
            for(int j = 0; j < floors.get(i-1).size(); j++){
                Passengers p = floors.get(i-1).get(j);
                if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor()) ){
                    currentFloors[1]= i;
                    break; 
                }
            }
            if(currentFloors[1] > currentFloors[0]){
                break;
            }
            
        }
        
        for(int i = currentFloors[0]; i <  currentFloors[1]; i++){
            for(int j = 0; j < genElev1.size(); j++){
                if(genElev1.get(j).getEndFloor() == i){
                    genOne_CHECK = i;
                    break;
                }
            }
            if(genOne_CHECK > currentFloors[0] && genOne_CHECK <  currentFloors[1]){
                 currentFloors[1] = genOne_CHECK;
                break;
            }
        }
        
        
        
        if(currentFloors[1] == currentFloors[0]){
            if(genElev1.isEmpty()==false){
                int a = genElev1.get(0).getEndFloor();
                for(int i = 1; i < genElev1.size(); i++){
                    if(genElev1.get(i).getEndFloor() < a){
                        a = genElev1.get(i).getEndFloor();
                    }
                }
                 currentFloors[1] = a;   
            }
        }
        
        
        return currentFloors;    
    }    
    
    public int[] genOne_DOWN(ArrayList<ArrayList<Passengers>> floors,int[] currentFloors,ArrayList<Passengers> genElev1){
        System.out.println("DOWN METHOD HA");
        System.out.println("Next Floor down: " + currentFloors[1]);
        
        //CHECKS THE FIRST DOWN REQUEST THAT LOWER THAN CURRENTFLOOR
        int a = currentFloors[1];
            for(int i = currentFloors[0]; i >= 1; i--){
                for(int j =0; j < floors.get(i-1).size(); j++){
                    Passengers p = floors.get(i-1).get(j);
                        if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor()) ){
                             currentFloors[1] = i;
                             break;
                    }
                    
                }
                if(currentFloors[1] < a){
                    break;
                }
            }
        //CHECKS ANY DOWNREQUEST ABOVE THE CURRENT FLOOR
        for(int i = currentFloors[0]+1; i <= 8;i++){
            for(int j = 0; j < floors.get(i-1).size(); j++){
                Passengers p = floors.get(i-1).get(j);
                 if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor()) ){
                    currentFloors[1] = i;
                 }
            }
    
        }
        
       
        return currentFloors;
        
        
    }
    
    public int[] genOne_DOWN_REQUESTS(ArrayList<ArrayList<Passengers>> floors,int[] currentFloors,ArrayList<Passengers> genElev1){
         System.out.println("Time for down requests");
         
         for(int i = currentFloors[0]; i >= 1; i--){
             for(int j = 0; j < floors.get(i-1).size(); j++){
                 Passengers p = floors.get(i-1).get(j);
                 if(p.getElv_Num() == 1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getEndFloor() < p.getStartFloor()){
                     currentFloors[1] = i;
                     break;
                 }
             }
             if(currentFloors[1] < currentFloors[0]){
                 break;
             }
         }
         
         for(int i = currentFloors[0]; i > currentFloors[1]; i--){
            for(int j = 0; j < genElev1.size(); j++){
                if(genElev1.get(j).getEndFloor() == i){
                    genOne_CHECK = i;
                    break;
                }
            }
            if(genOne_CHECK < currentFloors[0] && genOne_CHECK > currentFloors[1]){
                currentFloors[1] = genOne_CHECK;
                break;
            }
        }
        
        
        if(currentFloors[1] >= currentFloors[0]){
            if(genElev1.isEmpty()==false){
                int a = genElev1.get(0).getEndFloor();
                for(int i = 1; i < genElev1.size(); i++){
                    if(genElev1.get(i).getEndFloor() > a){
                        a = genElev1.get(i).getEndFloor();
                    }
                }
                currentFloors[1] = a;   
            }else{
                currentFloors[1] = 1;
            }
        }
         
        System.out.println("General Elevator 1 Next Request at Floor: " + currentFloors[1]);
         
        return currentFloors;
    }
    
    //General Elevator 2 Operationss for Up and Down Requests
    public int[] genTwo_UP_REQUESTS(ArrayList<ArrayList<Passengers>> floors,int[] currentFloors,ArrayList<Passengers> genElev1){
     
        for(int i = currentFloors[0]+1; i <= 8; i++){
            for(int j = 0; j < floors.get(i-1).size(); j++){
                Passengers p = floors.get(i-1).get(j);
                if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor()) ){
                    currentFloors[1]= i;
                    break; 
                }
            }
            if(currentFloors[1] > currentFloors[0]){
                break;
            }
            
        }
        
        for(int i = currentFloors[0]; i <  currentFloors[1]; i++){
            for(int j = 0; j < genElev1.size(); j++){
                if(genElev1.get(j).getEndFloor() == i){
                    genOne_CHECK = i;
                    break;
                }
            }
            if(genOne_CHECK > currentFloors[0] && genOne_CHECK <  currentFloors[1]){
                 currentFloors[1] = genOne_CHECK;
                break;
            }
        }
        
        
        
        if(currentFloors[1] == currentFloors[0]){
            if(genElev1.isEmpty()==false){
                int a = genElev1.get(0).getEndFloor();
                for(int i = 1; i < genElev1.size(); i++){
                    if(genElev1.get(i).getEndFloor() < a){
                        a = genElev1.get(i).getEndFloor();
                    }
                }
                 currentFloors[1] = a;   
            }
        }
        
        
        return currentFloors;    
    }    
    
    public int[] genTwo_DOWN(ArrayList<ArrayList<Passengers>> floors,int[] currentFloors,ArrayList<Passengers> genElev1){
        System.out.println("DOWN METHOD HA");
        System.out.println("Next Floor down: " + currentFloors[1]);
        
        //CHECKS THE FIRST DOWN REQUEST THAT LOWER THAN CURRENTFLOOR
        int a = currentFloors[1];
            for(int i = currentFloors[0]; i >= 1; i--){
                for(int j =0; j < floors.get(i-1).size(); j++){
                    Passengers p = floors.get(i-1).get(j);
                        if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor()) ){
                             currentFloors[1] = i;
                             break;
                    }
                    
                }
                if(currentFloors[1] < a){
                    break;
                }
            }
        //CHECKS ANY DOWNREQUEST ABOVE THE CURRENT FLOOR
        for(int i = currentFloors[0]; i <= 8;i++){
            for(int j = 0; j < floors.get(i-1).size(); j++){
                Passengers p = floors.get(i-1).get(j);
                 if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor()) ){
                    currentFloors[1] = i;
                 }
            }
    
        }
        
       
        return currentFloors;
        
        
    }
    
    public int[] genTwo_DOWN_REQUESTS(ArrayList<ArrayList<Passengers>> floors,int[] currentFloors,ArrayList<Passengers> genElev1){
         System.out.println("Time for down requests");
         
         for(int i = currentFloors[0]; i >= 1; i--){
             for(int j = 0; j < floors.get(i-1).size(); j++){
                 Passengers p = floors.get(i-1).get(j);
                 if(p.getElv_Num() == 1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getEndFloor() < p.getStartFloor()){
                     currentFloors[1] = i;
                     break;
                 }
             }
             if(currentFloors[1] < currentFloors[0]){
                 break;
             }
         }
         
         for(int i = currentFloors[0]; i > currentFloors[1]; i--){
            for(int j = 0; j < genElev1.size(); j++){
                if(genElev1.get(j).getEndFloor() == i){
                    genOne_CHECK = i;
                    break;
                }
            }
            if(genOne_CHECK < currentFloors[0] && genOne_CHECK > currentFloors[1]){
                currentFloors[1] = genOne_CHECK;
                break;
            }
        }
        
        
        if(currentFloors[1] >= currentFloors[0]){
            if(genElev1.isEmpty()==false){
                int a = genElev1.get(0).getEndFloor();
                for(int i = 1; i < genElev1.size(); i++){
                    if(genElev1.get(i).getEndFloor() > a){
                        a = genElev1.get(i).getEndFloor();
                    }
                }
                currentFloors[1] = a;   
            }else{
                currentFloors[1] = 1;
            }
        }
         
        System.out.println("General Elevator 1 Next Request at Floor: " + currentFloors[1]);
         
        return currentFloors;
    }
    
    


}