
package com.mycompany.elevatorproject;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author lammi
 */
public class GeneralElevatorTwo extends Elevators{
    
    private TranslateTransition tt;
    private TranslateTransition tt2;
    private ScaleTransition st;
    private ScaleTransition st2;
    private ParallelTransition p; 
    private Rectangle leftDoor;
    private Rectangle rightDoor;
    private int findDoors;
    private ArrayList<Passengers> inGeneralElevator = new ArrayList();
    private boolean requestCheck;
    private int nextFloorRequest;
    private int elevatorCap;
  
   
    
     GeneralElevatorTwo() {
        setMax_Capacity(12); 
        findDoors = 0;
        requestCheck = false;
        nextFloorRequest = 0;
    }
        
    @Override
    public void upRequests(ArrayList<ArrayList<Passengers>> floors,int[] cf,ArrayList<Rectangle> genElev,ArrayList<Rectangle> bases,ArrayList<Rectangle> genOne_DOORS, Label capacityGUI, Label currentFloorGUI,Rectangle lWall, Rectangle rWall){
        requestCheck = false;
        if(inGeneralElevator.size() < getMax_Capacity()){
        for(int i = cf[1]; i < getMax_Floors(); i++){//this for loop checks between current floor and highest floor to get Elevator request
            for(int j = 0; j < floors.get(i).size(); j++){
                Passengers p = floors.get(i).get(j);
                if(p.getElv_Num()==2 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor())&& inGeneralElevator.size()<= getMax_Capacity() ){
                    nextFloorRequest= i;
                    requestCheck = true;
                    break; 
                }
            }
            if(requestCheck){
                break;
            }
            
        }
        }
        if(!inGeneralElevator.isEmpty()){
            int min = nextFloorRequest;
            for(int j = 0; j < inGeneralElevator.size(); j++){
                
                if(inGeneralElevator.get(j).getEndFloor() < min && inGeneralElevator.get(j).getEndFloor() > cf[1]){
                    min = inGeneralElevator.get(j).getEndFloor();   
                }
            }
            if(min < nextFloorRequest){
                 nextFloorRequest = min;
                 requestCheck = true;
            }
        }    
        
       
        if(requestCheck == false){//if next floor request is equal to current floor meaning we didnt get a new floor request
            if(!inGeneralElevator.isEmpty()){
                int departFloor = inGeneralElevator.get(0).getEndFloor();
                for(int i = 1; i < inGeneralElevator.size(); i++){
                    if(inGeneralElevator.get(i).getEndFloor() < departFloor){
                        departFloor = inGeneralElevator.get(i).getEndFloor();
                    }
                }
                
                if(departFloor >= nextFloorRequest){
                    nextFloorRequest = departFloor;  
                    requestCheck = true;
                }
                  
            }
        }
        
        
       if(requestCheck == false){
           elevatorRequestCheck(floors,cf, genElev,bases, genOne_DOORS,capacityGUI,currentFloorGUI,lWall,rWall);
       }else{
        
        
        System.out.println("General Elevator 1 Next Request at Floor: " +  nextFloorRequest);
        
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(genElev.get(1));
        tr.setDuration(Duration.seconds(1));
        tr.setCycleCount(1);
        tr.setByY((nextFloorRequest - cf[1]) *-65);
        tr.setDelay(Duration.seconds(1));
        
        for(int i = 0; i < inGeneralElevator.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(inGeneralElevator.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(1));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(inGeneralElevator.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(1));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(inGeneralElevator.get(i).getStartEndGUI());
        startendIN.setDelay(Duration.seconds(1));
        startendIN.setDuration(Duration.seconds(1));
        startendIN.setByY(tr.getByY());
        startendIN.setCycleCount(1);
                    
                    
        passIN.play();
        passtypeIN.play();
        startendIN.play();
        }
        
        tr.play();
        
        tr.setOnFinished(finish ->{
            cf[1] = nextFloorRequest;
            currentFloorGUI.setText(Integer.toString(cf[1]));
            System.out.println("General Elevator 1 Currently on Floor: " + cf[1]);
            
            
            
            findDoors = cf[1]*2;
            leftDoor = genOne_DOORS.get(findDoors);
            rightDoor = genOne_DOORS.get(findDoors+1);
        
         
            tt = new TranslateTransition(Duration.seconds(1),leftDoor);
            st = new ScaleTransition(Duration.seconds(1),leftDoor);
            tt2 = new TranslateTransition(Duration.seconds(1),rightDoor);
            st2 = new ScaleTransition(Duration.seconds(1),rightDoor);
         
            tt.setByX(-10);
            st.setByX(-1);
        
            tt2.setByX(10);
            st2.setByX(-1);
         
            p = new ParallelTransition(tt,st,tt2,st2);
            p.play();
         
            System.out.println("General Elevator 1 Doors Opening on Floor: " + cf[1]);
            p.setOnFinished(finish2 ->{
             
             
            
             elevatorCap = inGeneralElevator.size();
            for(int i = 0; i < inGeneralElevator.size(); i++){
                if(inGeneralElevator.get(i).getEndFloor() == cf[1]){
                    getDestination_Floors().get(cf[1]).add(inGeneralElevator.get(i));

                    System.out.println("Passenger: " + inGeneralElevator.get(i).toString() + " has exited Medical Elevator 1 on Floor " + cf[1]);
                    elevatorCap--;
                    capacityGUI.setText(Integer.toString(elevatorCap)+"/12");
                    inGeneralElevator.get(i).getPassGUI().setLayoutX(genElev.get(1).getX()+30);
                    inGeneralElevator.get(i).getPassGUI().setLayoutY(genElev.get(1).getY());
                    inGeneralElevator.get(i).getPassTypeGUI().setLayoutX(inGeneralElevator.get(i).getPassGUI().getX()+20);
                    inGeneralElevator.get(i).getPassTypeGUI().setLayoutY(inGeneralElevator.get(i).getPassGUI().getY()-10);
                    inGeneralElevator.get(i).getStartEndGUI().setLayoutX(inGeneralElevator.get(i).getPassGUI().getX()+29);
                    inGeneralElevator.get(i).getStartEndGUI().setLayoutY(inGeneralElevator.get(i).getPassGUI().getY()-20);

                    inGeneralElevator.get(i).getPassGUI().toFront();
                    inGeneralElevator.get(i).getPassTypeGUI().toFront();
                    inGeneralElevator.get(i).getStartEndGUI().toFront();

                    Random ra = new Random();
                    int gen_delay = ra.nextInt(5)+3;

                    TranslateTransition passIN = new TranslateTransition();
                    TranslateTransition passtypeIN = new TranslateTransition();
                    TranslateTransition startendIN = new TranslateTransition();

                    passIN.setNode(inGeneralElevator.get(i).getPassGUI());
                    passIN.setDuration(Duration.seconds(gen_delay));
                    passIN.setCycleCount(1);
                    passIN.setByX(500);


                    passtypeIN.setNode(inGeneralElevator.get(i).getPassTypeGUI());
                    passtypeIN.setDuration(Duration.seconds(gen_delay));
                    passtypeIN.setCycleCount(1);
                    passtypeIN.setByX(passIN.getByX());


                    startendIN.setNode(inGeneralElevator.get(i).getStartEndGUI());
                    startendIN.setDuration(Duration.seconds(gen_delay));
                    startendIN.setCycleCount(1);
                    startendIN.setByX(passIN.getByX());



                    passIN.play();
                    passtypeIN.play();
                    startendIN.play();




                }
                if(elevatorCap < getMax_Capacity()){
                    capacityGUI.setTextFill(Color.web("#00ff0a"));

                }
            }
            inGeneralElevator.removeAll(getDestination_Floors().get(cf[1]));
        
            for(int i = 0; i < floors.get(cf[1]).size(); i++){
                if(inGeneralElevator.size()>=getMax_Capacity()){
                    capacityGUI.setTextFill(Color.web("#ff0000"));
                    break;
                }
                Passengers p = floors.get(cf[1]).get(i);
                if((p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getElv_Num() == 2 && p.getEndFloor() > p.getStartFloor() && inGeneralElevator.size()<= getMax_Capacity()){
                
                    inGeneralElevator.add(p);
                    System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                    p.getPassGUI().setLayoutX(genElev.get(1).getX()-5);
                    p.getPassGUI().setLayoutY(genElev.get(1).getY()-10);
                    p.getPassTypeGUI().setLayoutX(p.getPassGUI().getX()-15);
                    p.getPassTypeGUI().setLayoutY(p.getPassGUI().getY()-23);
                    p.getStartEndGUI().setLayoutX(p.getPassGUI().getX()-6);
                    p.getStartEndGUI().setLayoutY(p.getPassGUI().getY()-31);
                    capacityGUI.setText(Integer.toString(inGeneralElevator.size())+"/12");
                    
                }
                
                
            
            }
            floors.get(cf[1]).removeAll(inGeneralElevator);
        
          lWall.toFront();
          rWall.toFront();
        
        //so the Passengers dont look like they are going through the floors
        for(int i = 0; i < genOne_DOORS.size(); i++){
            genOne_DOORS.get(i).toFront();
        }
        
        for(int i = 0; i < bases.size(); i++){
            bases.get(i).toFront();
        }
       
        
        for(int i = 0; i < floors.size(); i++){
            for(int j = 0; j < floors.get(i).size(); j++){
                Passengers pa = floors.get(i).get(j);
                pa.getPassTypeGUI().toFront();
                pa.getStartEndGUI().toFront();
                pa.getPassGUI().toFront();
            }
            
        }
         tt = new TranslateTransition(Duration.seconds(1),leftDoor);
         st = new ScaleTransition(Duration.seconds(1),leftDoor);
         tt2 = new TranslateTransition(Duration.seconds(1),rightDoor);
         st2 = new ScaleTransition(Duration.seconds(1),rightDoor);
         
         tt.setByX(10);
         st.setByX(1);
        
         tt2.setByX(-10);
         st2.setByX(1);
         
         p = new ParallelTransition(tt,st,tt2,st2);
         p.play();
         
         System.out.println("General Elevator 1 Doors Closing on Floor: " + cf[1]);
         
         p.setOnFinished(finish3 ->{
             System.out.println("CHECKING RECURSION APPROACH");
            upRequests(floors,cf,genElev,bases,genOne_DOORS,capacityGUI,currentFloorGUI,lWall,rWall);
        });
     });
           
            
            
        });
       }
       
    }    
    
    @Override
    public void elevatorRequestCheck(ArrayList<ArrayList<Passengers>> floors,int[] cf,ArrayList<Rectangle> genElev,ArrayList<Rectangle> bases,ArrayList<Rectangle> genOne_DOORS,Label capacityGUI, Label currentFloorGUI,Rectangle lWall, Rectangle rWall){
        requestCheck = false;
        System.out.println("DOWN METHOD HA");
        System.out.println("Next Floor down: " + nextFloorRequest);
        
        //CHECKS FOR DOWN REQUEST BELOW CURRENT FLOOR
        
            for(int i = cf[1]; i >= 0; i--){
                for(int j =0; j < floors.get(i).size(); j++){
                    Passengers p = floors.get(i).get(j);
                        if(p.getElv_Num()==2 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor()) && inGeneralElevator.size() <= getMax_Capacity()){
                             nextFloorRequest = i;
                             requestCheck = true;
                             break;
                    }
                    
                }
                if(requestCheck){
                    break;
                }
            }
        //CHECKS FOR ANY DOWNREQUEST ABOVE THE CURRENT FLOOR
        for(int i = cf[1]; i < getMax_Floors();i++){
            for(int j = 0; j < floors.get(i).size(); j++){
                Passengers p = floors.get(i).get(j);
                 if(p.getElv_Num()==2 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor()) && inGeneralElevator.size() <= getMax_Capacity()){
                    nextFloorRequest = i;
                 }
            }
            if(nextFloorRequest > cf[1]){
                requestCheck = true;
            }
    
        } 
        
        if(nextFloorRequest == cf[1] && requestCheck == false){
            System.out.println("THERE ARE NO OTHER REQUEST");
            nextFloorRequest = 0;
            
            TranslateTransition tr = new TranslateTransition();
            tr.setNode(genElev.get(1));
            tr.setDuration(Duration.seconds(1));
            tr.setCycleCount(1);
        
            tr.setByY((cf[1]-nextFloorRequest)*65);
        
            tr.setDelay(Duration.seconds(1));
       
        
            tr.play();
            
            tr.setOnFinished(finish4 ->{
               
                cf[1] = 0;
                currentFloorGUI.setText(Integer.toString(cf[1]));
                nextFloorRequest = 0;
                requestCheck = false;
                for(int i = cf[1]; i < 8; i++){
                    
                    for(int j = 0; j < floors.get(i).size(); j++){
                        Passengers p = floors.get(i).get(j);
                        if(p.getElv_Num()==2 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor()) ){
                            requestCheck = true;
                            upRequests(floors,cf,genElev,bases,genOne_DOORS,capacityGUI,currentFloorGUI,lWall,rWall);
                            break; 
                        }
                    }
                    if(requestCheck){
                        break;
                    }
            
                }
                
                if(requestCheck == false){
                    System.out.println("There are no more requests for General Elevator 1");
                    setGeneralElevatorTwoCall(true);
                    cf[1]= -1;
                }
                
            });
            
       }else{
            System.out.println("General Elevator 1 Next Request at Floor: " + nextFloorRequest);
         
            TranslateTransition tr = new TranslateTransition();
            tr.setNode(genElev.get(1));
            tr.setDuration(Duration.seconds(1));
            tr.setCycleCount(1);
        
            tr.setByY((cf[1]-nextFloorRequest)*65);
        
            tr.setDelay(Duration.seconds(1));
       
        
            tr.play();
        
            tr.setOnFinished(finish ->{
            cf[1] = nextFloorRequest;
            currentFloorGUI.setText(Integer.toString(cf[1]));
            System.out.println("General Elevator 1 Currently on Floor: " + cf[1]);
            
            findDoors = cf[1]*2;
            leftDoor = genOne_DOORS.get(findDoors);
            rightDoor = genOne_DOORS.get(findDoors+1);
        
         
            tt = new TranslateTransition(Duration.seconds(1),leftDoor);
            st = new ScaleTransition(Duration.seconds(1),leftDoor);
            tt2 = new TranslateTransition(Duration.seconds(1),rightDoor);
            st2 = new ScaleTransition(Duration.seconds(1),rightDoor);
         
            tt.setByX(-10);
            st.setByX(-1);
        
            tt2.setByX(10);
            st2.setByX(-1);
         
            p = new ParallelTransition(tt,st,tt2,st2);
            p.play();
         
            System.out.println("General Elevator 1 Doors Opening on Floor: " + cf[1]);
            p.setOnFinished(finish2 ->{
        
            if(inGeneralElevator.size()<getMax_Capacity()){
                    capacityGUI.setTextFill(Color.web("#00ff0a"));
             }
        
            for(int i = 0; i < floors.get(cf[1]).size(); i++){
                if(inGeneralElevator.size()>=getMax_Capacity()){
                    capacityGUI.setTextFill(Color.web("#ff0000"));
                    break;
                }
                Passengers p = floors.get(cf[1]).get(i);
                if((p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getElv_Num() == 2 && p.getEndFloor() < p.getStartFloor() && inGeneralElevator.size()<= getMax_Capacity()){
                
                    inGeneralElevator.add(p);
                    System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                    p.getPassGUI().setLayoutX(genElev.get(1).getX()-5);
                    p.getPassGUI().setLayoutY(genElev.get(1).getY()-10);
                    p.getPassTypeGUI().setLayoutX(p.getPassGUI().getX()-15);
                    p.getPassTypeGUI().setLayoutY(p.getPassGUI().getY()-23);
                    p.getStartEndGUI().setLayoutX(p.getPassGUI().getX()-6);
                    p.getStartEndGUI().setLayoutY(p.getPassGUI().getY()-31);
                    capacityGUI.setText(Integer.toString(inGeneralElevator.size())+"/12");
                    
                }
                
                
            
            }
            floors.get(cf[1]).removeAll(inGeneralElevator);
        
        
        
        
        
        for(int i = 0; i < genOne_DOORS.size(); i++){
            genOne_DOORS.get(i).toFront();
        }
        
        for(int i = 0; i < bases.size(); i++){
            bases.get(i).toFront();
        }
        for(int i = 0; i < floors.size(); i++){
            for(int j = 0; j < floors.get(i).size(); j++){
                Passengers pa = floors.get(i).get(j);
                pa.getPassTypeGUI().toFront();
                pa.getStartEndGUI().toFront();
                pa.getPassGUI().toFront();
            }
            
        }
        
       
         tt = new TranslateTransition(Duration.seconds(1),leftDoor);
         st = new ScaleTransition(Duration.seconds(1),leftDoor);
         tt2 = new TranslateTransition(Duration.seconds(1),rightDoor);
         st2 = new ScaleTransition(Duration.seconds(1),rightDoor);
         
         tt.setByX(10);
         st.setByX(1);
        
         tt2.setByX(-10);
         st2.setByX(1);
         
         p = new ParallelTransition(tt,st,tt2,st2);
         p.play();
         
         System.out.println("General Elevator 1 Doors Closing on Floor: " + cf[1]);
         
         p.setOnFinished(finish3 ->{
        
         downRequests(floors,cf,genElev,bases,genOne_DOORS,capacityGUI,currentFloorGUI,lWall,rWall);

        });
        
    });
       
 });
         
 }
        
    }
    
    @Override
    public void downRequests(ArrayList<ArrayList<Passengers>> floors,int[] cf,ArrayList<Rectangle> genElev,ArrayList<Rectangle> bases,ArrayList<Rectangle> genOne_DOORS,Label capacityGUI, Label currentFloorGUI,Rectangle lWall, Rectangle rWall){
         System.out.println("Time for down requests");
         requestCheck = false;
         if(inGeneralElevator.size() < getMax_Capacity()){
         for(int i = cf[1]; i >= 0; i--){
             for(int j = 0; j < floors.get(i).size(); j++){
                 Passengers p = floors.get(i).get(j);
                 if(p.getElv_Num() == 2 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getEndFloor() < p.getStartFloor()&& inGeneralElevator.size() <= getMax_Capacity()){
                     nextFloorRequest = i;
                     requestCheck = true;
                     break;
                 }
             }
             if(requestCheck){
                 break;
             }
         }
         }
          int min = nextFloorRequest;
            for(int j = 0; j < inGeneralElevator.size(); j++){
                
                if(inGeneralElevator.get(j).getEndFloor() > min && inGeneralElevator.get(j).getEndFloor() < cf[1]){
                    min = inGeneralElevator.get(j).getEndFloor();   
                }
            }
            if(min > nextFloorRequest){
                 nextFloorRequest = min;
                 requestCheck = true;
            }
            
        
        if(requestCheck == false){
            if(!inGeneralElevator.isEmpty()){
                int a = inGeneralElevator.get(0).getEndFloor();
                for(int i = 1; i < inGeneralElevator.size(); i++){
                    if(inGeneralElevator.get(i).getEndFloor() > a){
                        a = inGeneralElevator.get(i).getEndFloor();
                    }
                }
                nextFloorRequest = a;   
            }else{
                nextFloorRequest = 0;
            }
        }
       
         
        System.out.println("General Elevator 1 Next Request at Floor: " + nextFloorRequest);
        
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(genElev.get(1));
        tr.setDuration(Duration.seconds(1));
        tr.setCycleCount(1);
        tr.setByY((cf[1]-nextFloorRequest) *65);
        tr.setDelay(Duration.seconds(1));
        
        for(int i = 0; i < inGeneralElevator.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(inGeneralElevator.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(1));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(inGeneralElevator.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(1));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(inGeneralElevator.get(i).getStartEndGUI());
        startendIN.setDelay(Duration.seconds(1));
        startendIN.setDuration(Duration.seconds(1));
        startendIN.setByY(tr.getByY());
        startendIN.setCycleCount(1);
                    
                    
        passIN.play();
        passtypeIN.play();
        startendIN.play();
        }
        
        tr.play();
        
        tr.setOnFinished(finish ->{
            cf[1] = nextFloorRequest;
            currentFloorGUI.setText(Integer.toString(cf[1]));
            System.out.println("General Elevator 1 Currently on Floor: " + cf[1]);
            
            findDoors = cf[1]*2;
            leftDoor = genOne_DOORS.get(findDoors);
            rightDoor = genOne_DOORS.get(findDoors+1);
        
         
            tt = new TranslateTransition(Duration.seconds(1),leftDoor);
            st = new ScaleTransition(Duration.seconds(1),leftDoor);
            tt2 = new TranslateTransition(Duration.seconds(1),rightDoor);
            st2 = new ScaleTransition(Duration.seconds(1),rightDoor);
         
            tt.setByX(-10);
            st.setByX(-1);
        
            tt2.setByX(10);
            st2.setByX(-1);
         
            p = new ParallelTransition(tt,st,tt2,st2);
            p.play();
         
            System.out.println("General Elevator 1 Doors Opening on Floor: " + cf[1]);
            p.setOnFinished(finish2 ->{
        
              elevatorCap = inGeneralElevator.size();
            for(int i = 0; i < inGeneralElevator.size(); i++){
                if(inGeneralElevator.get(i).getEndFloor() == cf[1]){
                    getDestination_Floors().get(cf[1]).add(inGeneralElevator.get(i));

                    System.out.println("Passenger: " + inGeneralElevator.get(i).toString() + " has exited Medical Elevator 1 on Floor " + cf[1]);
                    elevatorCap--;
                    capacityGUI.setText(Integer.toString(elevatorCap)+"/12");
                    inGeneralElevator.get(i).getPassGUI().setLayoutX(genElev.get(1).getX()+30);
                    inGeneralElevator.get(i).getPassGUI().setLayoutY(genElev.get(1).getY());
                    inGeneralElevator.get(i).getPassTypeGUI().setLayoutX(inGeneralElevator.get(i).getPassGUI().getX()+20);
                    inGeneralElevator.get(i).getPassTypeGUI().setLayoutY(inGeneralElevator.get(i).getPassGUI().getY()-10);
                    inGeneralElevator.get(i).getStartEndGUI().setLayoutX(inGeneralElevator.get(i).getPassGUI().getX()+29);
                    inGeneralElevator.get(i).getStartEndGUI().setLayoutY(inGeneralElevator.get(i).getPassGUI().getY()-20);

                    inGeneralElevator.get(i).getPassGUI().toFront();
                    inGeneralElevator.get(i).getPassTypeGUI().toFront();
                    inGeneralElevator.get(i).getStartEndGUI().toFront();

                    Random ra = new Random();
                    int gen_delay = ra.nextInt(5)+3;

                    TranslateTransition passIN = new TranslateTransition();
                    TranslateTransition passtypeIN = new TranslateTransition();
                    TranslateTransition startendIN = new TranslateTransition();

                    passIN.setNode(inGeneralElevator.get(i).getPassGUI());
                    passIN.setDuration(Duration.seconds(gen_delay));
                    passIN.setCycleCount(1);
                    passIN.setByX(500);


                    passtypeIN.setNode(inGeneralElevator.get(i).getPassTypeGUI());
                    passtypeIN.setDuration(Duration.seconds(gen_delay));
                    passtypeIN.setCycleCount(1);
                    passtypeIN.setByX(passIN.getByX());


                    startendIN.setNode(inGeneralElevator.get(i).getStartEndGUI());
                    startendIN.setDuration(Duration.seconds(gen_delay));
                    startendIN.setCycleCount(1);
                    startendIN.setByX(passIN.getByX());



                    passIN.play();
                    passtypeIN.play();
                    startendIN.play();




                }
                if(elevatorCap < getMax_Capacity()){
                    capacityGUI.setTextFill(Color.web("#00ff0a"));

                }
            }
            inGeneralElevator.removeAll(getDestination_Floors().get(cf[1]));
        
            for(int i = 0; i < floors.get(cf[1]).size(); i++){
                
                if(inGeneralElevator.size()>=getMax_Capacity()){
                    capacityGUI.setTextFill(Color.web("#ff0000"));
                    break;
                }
                Passengers p = floors.get(cf[1]).get(i);
                if((p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getElv_Num() == 2 && p.getEndFloor() < p.getStartFloor() && inGeneralElevator.size()<= getMax_Capacity()){
                
                    inGeneralElevator.add(p);
                    System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                    p.getPassGUI().setLayoutX(genElev.get(1).getX()-5);
                    p.getPassGUI().setLayoutY(genElev.get(1).getY()-10);
                    p.getPassTypeGUI().setLayoutX(p.getPassGUI().getX()-15);
                    p.getPassTypeGUI().setLayoutY(p.getPassGUI().getY()-23);
                    p.getStartEndGUI().setLayoutX(p.getPassGUI().getX()-6);
                    p.getStartEndGUI().setLayoutY(p.getPassGUI().getY()-31);
                    capacityGUI.setText(Integer.toString(inGeneralElevator.size())+"/12");
                    
                }
                
                
            
            }
            floors.get(cf[1]).removeAll(inGeneralElevator);
        
         lWall.toFront();
          rWall.toFront();  
        
        //so the Passengers dont look like they are going through the floors
        for(int i = 0; i < genOne_DOORS.size(); i++){
            genOne_DOORS.get(i).toFront();
        }
        
        for(int i = 0; i < bases.size(); i++){
            bases.get(i).toFront();
        }
        
        
         for(int i = 0; i < floors.size(); i++){
            for(int j = 0; j < floors.get(i).size(); j++){
                Passengers pa = floors.get(i).get(j);
                pa.getPassTypeGUI().toFront();
                pa.getStartEndGUI().toFront();
                pa.getPassGUI().toFront();
            }
            
        }
        
         tt = new TranslateTransition(Duration.seconds(1),leftDoor);
         st = new ScaleTransition(Duration.seconds(1),leftDoor);
         tt2 = new TranslateTransition(Duration.seconds(1),rightDoor);
         st2 = new ScaleTransition(Duration.seconds(1),rightDoor);
         
         tt.setByX(10);
         st.setByX(1);
        
         tt2.setByX(-10);
         st2.setByX(1);
         
         p = new ParallelTransition(tt,st,tt2,st2);
         p.play();
         
         System.out.println("General Elevator 1 Doors Closing on Floor: " + cf[1]);
         
         p.setOnFinished(finish3 ->{
             if(cf[1] == 0){
                   requestCheck = false;
                   for(int i = cf[1]; i < getMax_Floors(); i++){//this for loop checks between current floor and highest floor to get Elevator request
                        for(int j = 0; j < floors.get(i).size(); j++){
                            Passengers p = floors.get(i).get(j);
                            if(p.getElv_Num()==2 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor())  ){
                                nextFloorRequest= i;
                                requestCheck = true;
                                upRequests(floors,cf,genElev,bases,genOne_DOORS,capacityGUI,currentFloorGUI,lWall,rWall);
                                break; 
                            }
                        }
                        if(requestCheck){
                            break;
                        }
            
                 }
                 
                 if(requestCheck == false){
                     currentFloorGUI.setText(Integer.toString(cf[1]));
                     System.out.println("DONE!");
                     setGeneralElevatorTwoCall(true);
                     cf[1] = -1;
                 }
             }else{
                downRequests( floors, cf,genElev, bases, genOne_DOORS,capacityGUI,currentFloorGUI,lWall,rWall);      

             }
             
        });
         
      });
         
    });        
        

         
        
    }
    
   
   
}
