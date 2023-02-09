
package com.mycompany.elevatorproject;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Medical_Elevator extends Elevators{
    
    private ArrayList<Passengers> medElev1 = new ArrayList();
    private ArrayList<Passengers> medElev2 = new ArrayList();
    private ArrayList<Passengers> medElev3 = new ArrayList();
   
    
    private int med_elvSpeed = 1;
    
    
    private int med_nextFloor1 =0;
    private int med_highestFloor1 =0;
    private int med_exitDelay1 = 1;
    
    
    

    private int med_nextFloor2 =0;
    private int med_highestFloor2 =0;
    
    

    private int med_nextFloor3 =0;
    private int med_highestFloor3 =0;
    
    
    private int[] med_currentFloors = {0,0,0,0,0,0};
     
    /*
     currentFloor[0] is current floor for Medical Elevator 1
     currentFloor[1] is current floor for Medical Elevator 2
     currentFloor[2] is current floor for Medical Elevator 3
    
     currentFloor[3] is Highest/Lowest floor Request for Medical Elevator 1
     currentFloor[4] is Highest/Lowest floor Request for Medical Elevator 2
     currentFloor[5] is Highest/Lowest floor Request for Medical Elevator 3
     */

    @Override
    public void elevators( ArrayList<ArrayList<Passengers>> floors, ArrayList<Rectangle> medElevs){
     
     if(med_currentFloors[3] == 0 && med_currentFloors[4]== 0 && med_currentFloors[5] == 0){
         System.out.println("Medical Elevators Up Request Starting");
      for(int i = 0; i < floors.size(); i++){
            if(floors.get(i).isEmpty() == false){
                for(int j = 0; j < floors.get(i).size(); j++){
                    Passengers p = floors.get(i).get(j);
                    if((p.getPassType().equals("Medical")||p.getPassType().equals("Support"))&& p.getElv_Num()-5 == 1){
                        med_currentFloors[3] = (i+1); 
                        
                       
                    }
                    else if((p.getPassType().equals("Medical")||p.getPassType().equals("Support"))&& p.getElv_Num() == 2){
                        med_currentFloors[4] = (i+1); 
                         
                    }
                    else if((p.getPassType().equals("Medical")||p.getPassType().equals("Support"))&& p.getElv_Num() == 3){
                         med_highestFloor3 = (i+1); 
                        med_currentFloors[5] = floors.size() - i;
                    }
                    
                      
                }
            }
            
        }
      System.out.println("Medical Elevator 1 Highest Request is at Floor: " + med_currentFloors[3]);
      System.out.println("Medical Elevator 2 Highest Request is at Floor: " + med_currentFloors[4]);
      System.out.println("Medical Elevator 3 Highest Request is at Floor: " + med_highestFloor3);
    }
     
     for(int i = 3; i < med_currentFloors.length; i++){
         if(med_currentFloors[i] > 0){
                switch(i){
                 case 3:
                     medicalOne_UP(floors, medElevs, med_currentFloors,medElev1);
                     break;
                 
                 case 4:
                     System.out.println("This would be method call for Medical Elevator 2");
                     break;
                 
                 case 5:
                     System.out.println("This would be method call for Medical Elevator 3");
                     break;
                 
                 default:
                     System.out.println("No Elevator exist");
                     break;
             
                }
         }
         else{
             System.out.println("There are no Request for Medical Elevator " + (i-2));
         }
         
     }
    
  }

    
    
    
    
    public void medicalOne_UP(ArrayList<ArrayList<Passengers>> floors, ArrayList<Rectangle> medElevs, int[] currentFloors, ArrayList<Passengers> medOne){
        
     
    
      if(floors.get(0).isEmpty() == false  && currentFloors[0] == 0){
        for(int i = 0; i < floors.get(0).size(); i++){
            Passengers p = floors.get(0).get(i);
            if(p.getElv_Num()-5 ==1 && (p.getPassType().equals("Medical")||p.getPassType().equals("Support"))&& p.getEndFloor() > p.getStartFloor()){
                currentFloors[0] = 1;
                med_nextFloor1 = 1;
                break;
                
            }else{
                for(int j = currentFloors[3]-1; j >=currentFloors[0] ; j--){
                    if(floors.get(j).isEmpty() == false){
                        for(int k = 0; k < floors.get(j).size(); k++){
                            p = floors.get(j).get(k);
                            if(p.getElv_Num()-5 == 1 && (p.getPassType().equals("Medical")||p.getPassType().equals("Support"))&& p.getEndFloor()> p.getStartFloor()){
                                if(currentFloors[0] == 0){
                                    currentFloors[0] = 1;
                                }
                                med_nextFloor1 = (j+1);
                        
                           }
                            else{
                                if(currentFloors[0] == 0){
                                    currentFloors[0] = 1;
                                }
                                med_nextFloor1 = currentFloors[3];
                            }
                        }
                    }
                }
                
                
               
                
            }
             
        }             
    }else{
           for(int i = currentFloors[3]-1; i >= currentFloors[0] ; i--){
            if(floors.get(i).isEmpty() == false){
                for(int j = 0; j < floors.get(i).size(); j++){
                    Passengers p = floors.get(i).get(j);
                    if(p.getElv_Num()-5 == 1 && (p.getPassType().equals("Medical")||p.getPassType().equals("Support"))&& p.getEndFloor()>p.getStartFloor()){
                        if(currentFloors[0] == 0){
                            currentFloors[0] = 1;
                        }
                        med_nextFloor1 = (i+1);
                        
                    }
                    else{
                        if(currentFloors[0] == 0){
                           currentFloors[0] = 1;
                        }
                        med_nextFloor1 = currentFloors[3];
                    }
                }
            }
        }
         for(int i = med_nextFloor1; i > currentFloors[0]; i--){
                   for(int j = 0; j < medOne.size(); j++){
                       if(medOne.get(j).getEndFloor() == i){
                           med_nextFloor1 = (i);
                           
                           
                       }
                   }
                }
       
      }
      
     
   
   
            
  
   
        
     System.out.println("Medical Elevator 1 Next Request Floor: " + (med_nextFloor1));
             
    
    TranslateTransition tr = new TranslateTransition();
    tr.setNode(medElevs.get(0));
    tr.setDuration(Duration.seconds(med_elvSpeed));
    tr.setByY((med_nextFloor1-currentFloors[0]) * -65);
    tr.setCycleCount(1);
    tr.setDelay(Duration.seconds(1));
    
    if(medOne.size() >= 1){
        for(int i = 0; i < medOne.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(medOne.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(med_elvSpeed));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(medOne.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(med_elvSpeed));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(medOne.get(i).getStartEndGUI());
        startendIN.setDelay(Duration.seconds(1));
        startendIN.setDuration(Duration.seconds(med_elvSpeed));
        startendIN.setByY(tr.getByY());
        startendIN.setCycleCount(1);
                    
                    
        passIN.play();
        passtypeIN.play();
        startendIN.play();
        }
    }
    
    tr.play();
 
    tr.setOnFinished(finish1 ->{
        currentFloors[0] = med_nextFloor1;
        System.out.println("Medical Elevator 1 Currently on Floor: " + currentFloors[0]);
        
        for(int i = 0; i < floors.get(currentFloors[0]-1).size(); i++){
            Passengers p = floors.get(currentFloors[0]-1).get(i);
            if((p.getPassType().equals("Medical")||p.getPassType().equals("Support")) && p.getElv_Num()-5 == 1 && p.getEndFloor() > p.getStartFloor()){
                
                medOne.add(p);
                System.out.println("Passenger: " + p.toString() + " has been added to Medical Elevator 1.");
                
            }
            
        }
        floors.get(currentFloors[0]-1).removeAll(medOne);
        
        for(int i = 0; i < medOne.size(); i++){
                    
                    medOne.get(i).getPassGUI().setLayoutX(medElevs.get(0).getX()+5);
                    medOne.get(i).getPassGUI().setLayoutY(medElevs.get(0).getY()-10);
                    medOne.get(i).getPassTypeGUI().setLayoutX(medOne.get(i).getPassGUI().getX()-15);
                    medOne.get(i).getPassTypeGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-23);
                    medOne.get(i).getStartEndGUI().setLayoutX(medOne.get(i).getPassGUI().getX()+4);
                    medOne.get(i).getStartEndGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-31);  
        }
        
        
        
        for(int i = 0; i < medOne.size(); i++){
            if(medOne.get(i).getEndFloor() == currentFloors[0]){
                getDestination_Floors().get(currentFloors[0]-1).add(medOne.get(i));
                System.out.println("Passenger: " + medOne.get(i).toString() + " has exited Medical Elevator 1 on Floor " + currentFloors[0]);
            
                medOne.get(i).getPassGUI().setLayoutX(medElevs.get(0).getX()+30);
                medOne.get(i).getPassGUI().setLayoutY(medElevs.get(0).getY()+3);
                medOne.get(i).getPassTypeGUI().setLayoutX(medOne.get(i).getPassGUI().getX()+11);
                medOne.get(i).getPassTypeGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-10);
                medOne.get(i).getStartEndGUI().setLayoutX(medOne.get(i).getPassGUI().getX()+27);
                medOne.get(i).getStartEndGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-20);
                
                Random r = new Random();
                med_exitDelay1 = r.nextInt(5)+3;
                        
                TranslateTransition passIN = new TranslateTransition();
                TranslateTransition passtypeIN = new TranslateTransition();
                TranslateTransition startendIN = new TranslateTransition();
                     
                passIN.setNode(medOne.get(i).getPassGUI());
                passIN.setDuration(Duration.seconds(med_exitDelay1));
                passIN.setCycleCount(1);
                passIN.setByX(-425);
                
                        
                passtypeIN.setNode(medOne.get(i).getPassTypeGUI());
                passtypeIN.setDuration(Duration.seconds(med_exitDelay1));
                passtypeIN.setCycleCount(1);
                passtypeIN.setByX(passIN.getByX());
                
                        
                startendIN.setNode(medOne.get(i).getStartEndGUI());
                startendIN.setDuration(Duration.seconds(med_exitDelay1));
                startendIN.setCycleCount(1);
                startendIN.setByX(passIN.getByX());
                
                        
                        
                passIN.play();
                passtypeIN.play();
                startendIN.play();
                
              
                
            
            }
        }
        medOne.removeAll(getDestination_Floors().get(currentFloors[0]-1));
        
        
        if(currentFloors[0] == currentFloors[3]){
            
            medicalOne_CHECK(floors, medElevs,currentFloors,medOne);
            return;
        }else{
           medicalOne_UP(floors, medElevs, currentFloors,medOne); 
        }
        
        
        
    });
    
    
        
    }
   
    public void medicalOne_CHECK( ArrayList<ArrayList<Passengers>> floors, ArrayList<Rectangle> medElevs,int[] currentFloors,ArrayList<Passengers> medOne) {
        System.out.println("Medical Elevator 1 INTERNAL CHECK");
         
         
        if(medOne.isEmpty()){
            for(int i = currentFloors[0]-1; i >= 0; i--){
                    if(floors.get(i).isEmpty()==false){
                        for(int j = 0; j < floors.get(i).size( ); j++){
                        Passengers p = floors.get(i).get(j);
                            if((p.getPassType().equals("Medical")||p.getPassType().equals("Support")) && p.getElv_Num()-5==1){
                            currentFloors[3] = i+1;
                        }
                    }
                    }   
                    
                }
                System.out.println("Medical Elevator 1 Lowest Request on Floor: " + currentFloors[3]);
                System.out.println("Medical Elevator 1 Completed all UP Request");
                medicalOne_DOWN(floors,medElevs,currentFloors,medOne);
                return;
            
        }else{
            if(medOne.size() == 1){
                med_nextFloor1 = medOne.get(0).getEndFloor();
                
            }else{
                int a = medOne.get(0).getEndFloor();
                for(int i = 1; i < medOne.size(); i++){
                    if(medOne.get(i).getEndFloor() < a){
                        a = medOne.get(i).getEndFloor();
                    }
                }
                med_nextFloor1 = a;
            }
            
            
        }
        
        System.out.println("Medical Elevator 1 Next Request Floor: " + (med_nextFloor1));
        
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(medElevs.get(0));
        tr.setDuration(Duration.seconds(med_elvSpeed));
        tr.setByY((med_nextFloor1-currentFloors[0]) * -65);
        tr.setCycleCount(1);
        tr.setDelay(Duration.seconds(1));
        
        if(medOne.size()>=1){
        for(int i = 0; i < medOne.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(medOne.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(med_elvSpeed));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(medOne.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(med_elvSpeed));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(medOne.get(i).getStartEndGUI());
        startendIN.setDelay(Duration.seconds(1));
        startendIN.setDuration(Duration.seconds(med_elvSpeed));
        startendIN.setByY(tr.getByY());
        startendIN.setCycleCount(1);
                    
                    
        passIN.play();
        passtypeIN.play();
        startendIN.play();
    }
    }
        
        tr.play();
        
        tr.setOnFinished(finish ->{
            currentFloors[0] = med_nextFloor1;
            System.out.println("Medical Elevator 1 Currently on Floor: " + currentFloors[0]);
            
            for(int i = 0; i < medOne.size(); i++){
            if(medOne.get(i).getEndFloor() == currentFloors[0]){
                getDestination_Floors().get(currentFloors[0]-1).add(medOne.get(i));
                System.out.println("Passenger: " + medOne.get(i).toString() + " has exited Medical Elevator 1 on Floor " + currentFloors[0]);
            
                medOne.get(i).getPassGUI().setLayoutX(medElevs.get(0).getX()+30);
                medOne.get(i).getPassGUI().setLayoutY(medElevs.get(0).getY()+3);
                medOne.get(i).getPassTypeGUI().setLayoutX(medOne.get(i).getPassGUI().getX()+11);
                medOne.get(i).getPassTypeGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-10);
                medOne.get(i).getStartEndGUI().setLayoutX(medOne.get(i).getPassGUI().getX()+27);
                medOne.get(i).getStartEndGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-20);
                         
                Random r = new Random();
                med_exitDelay1 = r.nextInt(5)+3;
                
                TranslateTransition passIN = new TranslateTransition();
                TranslateTransition passtypeIN = new TranslateTransition();
                TranslateTransition startendIN = new TranslateTransition();
                     
                passIN.setNode(medOne.get(i).getPassGUI());
                passIN.setDuration(Duration.seconds(med_exitDelay1));
                passIN.setCycleCount(1);
                passIN.setByX(-425);
                        
                passtypeIN.setNode(medOne.get(i).getPassTypeGUI());
                passtypeIN.setDuration(Duration.seconds(med_exitDelay1));
                passtypeIN.setCycleCount(1);
                passtypeIN.setByX(passIN.getByX());
                        
                startendIN.setNode(medOne.get(i).getStartEndGUI());
                startendIN.setDuration(Duration.seconds(med_exitDelay1));
                startendIN.setCycleCount(1);
                startendIN.setByX(passIN.getByX());
                        
                        
                passIN.play();
                passtypeIN.play();
                startendIN.play();
            
           }
        }
        medOne.removeAll(getDestination_Floors().get(currentFloors[0]-1));
        
        medicalOne_CHECK(floors,medElevs,currentFloors,medOne);
 
            
        });
        
       
        
        
        
    }
     
    public void medicalOne_DOWN( ArrayList<ArrayList<Passengers>> floors, ArrayList<Rectangle> medElevs,int[] currentFloors,ArrayList<Passengers> medOne) {
       
        System.out.println("Medical Elevator 1 currently at Floor: " + currentFloors[0]);
     
        for(int i = currentFloors[0]; i >= 1; i--){
            if(floors.get(i-1).isEmpty() && medOne.isEmpty()){
                med_nextFloor1 =1;
            }else{
                break;
            }
        }
        
       
        
        if(currentFloors[0] <= currentFloors[3]){
            if(medOne.size()==1){
                med_nextFloor1 = medOne.get(0).getEndFloor();
            }else if(medOne.size() > 1){
                int a = medOne.get(0).getEndFloor();
                for(int i = 1; i < medOne.size(); i++){
                    if(medOne.get(i).getEndFloor() >= a){
                        a = medOne.get(i).getEndFloor();
                    }
                
                }
                med_nextFloor1 = a;
                
            }
            
        }else{
            
             for(int i = currentFloors[3]-1; i <= currentFloors[0]-1; i++){
            if(floors.get(i).isEmpty()==false){
                for(int j = 0; j < floors.get(i).size(); j++){
                    Passengers p = floors.get(i).get(j);
                    if((p.getPassType().equals("Medical")||p.getPassType().equals("Support")) && p.getElv_Num()-5 == 1 && p.getEndFloor() < p.getStartFloor()){
                        med_nextFloor1 = (i+1);
                    }
                }
                
                
            }
        }
        
        for(int i = currentFloors[0]; i > med_nextFloor1; i--){
                   for(int j = 0; j < medOne.size(); j++){
                       if(medOne.get(j).getEndFloor() == i){
                           med_nextFloor1 = (i);
                           break;
                       }
                   }
                }

        }
        
        
        
        
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(medElevs.get(0));
        tr.setDuration(Duration.seconds(med_elvSpeed));
        tr.setByY((currentFloors[0]-med_nextFloor1) * 65);
        tr.setCycleCount(1);
        tr.setDelay(Duration.seconds(1));
        
        
        for(int i = 0; i < medOne.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(medOne.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(med_elvSpeed));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(medOne.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(med_elvSpeed));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(medOne.get(i).getStartEndGUI());
        startendIN.setDelay(Duration.seconds(1));
        startendIN.setDuration(Duration.seconds(med_elvSpeed));
        startendIN.setByY(tr.getByY());
        startendIN.setCycleCount(1);
                    
                    
        passIN.play();
        passtypeIN.play();
        startendIN.play();
        }
        
        
        tr.play();
        
        tr.setOnFinished(finish ->{
            currentFloors[0] = med_nextFloor1;
            System.out.println("Medical Elevator 1 Currently on Floor: " + currentFloors[0]);
        
            for(int i = 0; i < floors.get(currentFloors[0]-1).size(); i++){
                Passengers p = floors.get(currentFloors[0]-1).get(i);
                if((p.getPassType().equals("Medical")||p.getPassType().equals("Support")) && p.getElv_Num()-5 == 1 && p.getEndFloor() < p.getStartFloor()){
                
                    medOne.add(p);
                    System.out.println("Passenger: " + p.toString() + " has been added to Medical Elevator 1.");
                
                }
            
            }
        floors.get(currentFloors[0]-1).removeAll(medOne);
        
        for(int i = 0; i < medOne.size(); i++){
                    
                    medOne.get(i).getPassGUI().setLayoutX(medElevs.get(0).getX()+5);
                    medOne.get(i).getPassGUI().setLayoutY(medElevs.get(0).getY()-10);
                    medOne.get(i).getPassTypeGUI().setLayoutX(medOne.get(i).getPassGUI().getX()-15);
                    medOne.get(i).getPassTypeGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-23);
                    medOne.get(i).getStartEndGUI().setLayoutX(medOne.get(i).getPassGUI().getX()+4);
                    medOne.get(i).getStartEndGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-31);  
        }
        
        for(int i = 0; i < medOne.size(); i++){
            if(medOne.get(i).getEndFloor() == currentFloors[0]){
                getDestination_Floors().get(currentFloors[0]-1).add(medOne.get(i));
                System.out.println("Passenger: " + medOne.get(i).toString() + " has exited Medical Elevator 1 on Floor " + currentFloors[0]);
            
                medOne.get(i).getPassGUI().setLayoutX(medElevs.get(0).getX()+30);
                medOne.get(i).getPassGUI().setLayoutY(medElevs.get(0).getY()+3);
                medOne.get(i).getPassTypeGUI().setLayoutX(medOne.get(i).getPassGUI().getX()+11);
                medOne.get(i).getPassTypeGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-10);
                medOne.get(i).getStartEndGUI().setLayoutX(medOne.get(i).getPassGUI().getX()+27);
                medOne.get(i).getStartEndGUI().setLayoutY(medOne.get(i).getPassGUI().getY()-20);
                
                Random r = new Random();
                med_exitDelay1 = r.nextInt(5)+3;
                        
                TranslateTransition passIN = new TranslateTransition();
                TranslateTransition passtypeIN = new TranslateTransition();
                TranslateTransition startendIN = new TranslateTransition();
                     
                passIN.setNode(medOne.get(i).getPassGUI());
                passIN.setDuration(Duration.seconds(med_exitDelay1));
                passIN.setCycleCount(1);
                passIN.setByX(-425);
                        
                passtypeIN.setNode(medOne.get(i).getPassTypeGUI());
                passtypeIN.setDuration(Duration.seconds(med_exitDelay1));
                passtypeIN.setCycleCount(1);
                passtypeIN.setByX(passIN.getByX());
                        
                startendIN.setNode(medOne.get(i).getStartEndGUI());
                startendIN.setDuration(Duration.seconds(med_exitDelay1));
                startendIN.setCycleCount(1);
                startendIN.setByX(passIN.getByX());
                        
                        
                passIN.play();
                passtypeIN.play();
                startendIN.play();
            
            }
        }
        medOne.removeAll(getDestination_Floors().get(currentFloors[0]-1));
        
        if(currentFloors[0] == 1){
            System.out.println("Done");
            currentFloors[3] = 0;
            elevators(floors,medElevs);
            
        }else{
            medicalOne_DOWN(floors,medElevs,currentFloors,medOne);  
        }
        
            
            
        });
        
        
        
    }
    
    
    
    
    
    
    
    
   
}
