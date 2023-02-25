
package com.mycompany.elevatorproject;


import java.util.ArrayList;
import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
public class General_Elevator extends Elevators{
    
    private TranslateTransition tt;
    private TranslateTransition tt2;
    private ScaleTransition st;
    private ScaleTransition st2;
    private ParallelTransition p;
    private int genOne_CHECK;
    private Rectangle leftDoor;
    private Rectangle rightDoor;
    private int findDoors;
    private ArrayList<Passengers> genElev_ONE = new ArrayList();
  
   
    
     General_Elevator() {
        setMax_Capacity(10); 
        genOne_CHECK = 0;
        findDoors = 0;
    }
        
   
    
    @Override
    public void elevators(ArrayList<ArrayList<Passengers>> floors, ArrayList<Rectangle> r){
        
        
        
    }
     
    //General Elevator 1 Operations for Up and Down Requests
    public void genOne_UP_REQUESTS(ArrayList<ArrayList<Passengers>> floors,int[] cf,ArrayList<Rectangle> genElev,ArrayList<Rectangle> bases,ArrayList<Rectangle> genOne_DOORS){
     
        for(int i = cf[0]+1; i <= 8; i++){
            for(int j = 0; j < floors.get(i-1).size(); j++){
                Passengers p = floors.get(i-1).get(j);
                if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor()) ){
                    cf[1]= i;
                    break; 
                }
            }
            if(cf[1] > cf[0]){
                break;
            }
            
        }
        
        for(int i = cf[0]; i <  cf[1]; i++){
            for(int j = 0; j < genElev_ONE.size(); j++){
                if(genElev_ONE.get(j).getEndFloor() == i){
                    genOne_CHECK = i;
                    break;
                }
            }
            if(genOne_CHECK > cf[0] && genOne_CHECK <  cf[1]){
                 cf[1] = genOne_CHECK;
                break;
            }
        }
        
        
        
        if(cf[1] == cf[0]){
            if(genElev_ONE.isEmpty()==false){
                int a = genElev_ONE.get(0).getEndFloor();
                for(int i = 1; i < genElev_ONE.size(); i++){
                    if(genElev_ONE.get(i).getEndFloor() < a){
                        a = genElev_ONE.get(i).getEndFloor();
                    }
                }
                 cf[1] = a;   
            }
        }
        
        
         if(cf[0] == 0){
            cf[0] = 1;
        }
        
        if(cf[1] == 0){
          genOne_DOWN(floors,cf,genElev,bases,genOne_DOORS);
        }else{
        
        
        System.out.println("General Elevator 1 Next Request at Floor: " +  cf[1]);
        
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(genElev.get(0));
        tr.setDuration(Duration.seconds(1));
        tr.setCycleCount(1);
        tr.setByY(( cf[1] - cf[0]) *-65);
        tr.setDelay(Duration.seconds(1));
        
        for(int i = 0; i < genElev_ONE.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(genElev_ONE.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(1));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(genElev_ONE.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(1));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(genElev_ONE.get(i).getStartEndGUI());
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
            cf[0] = cf[1];
            
            System.out.println("General Elevator 1 Currently on Floor: " + cf[0]);
            
            findDoors = cf[0]+ (cf[0]-1);
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
         
            System.out.println("General Elevator 1 Doors Opening on Floor: " + cf[0]);
            p.setOnFinished(finish2 ->{
             
        
        
            for(int i = 0; i < floors.get(cf[0]-1).size(); i++){
                Passengers p = floors.get(cf[0]-1).get(i);
                if((p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getElv_Num() == 1 && p.getEndFloor() > p.getStartFloor()){
                
                    genElev_ONE.add(p);
                    System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                
                }
            
            }
            floors.get(cf[0]-1).removeAll(genElev_ONE);
        
            for(int i = 0; i < genElev_ONE.size(); i++){
                    
                    
                    
                    genElev_ONE.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()-20);
                    genElev_ONE.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()-10);
                    genElev_ONE.get(i).getPassTypeGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()-30);
                    genElev_ONE.get(i).getPassTypeGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-23);
                    genElev_ONE.get(i).getStartEndGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()-21);
                    genElev_ONE.get(i).getStartEndGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-31);  
                    
                    
        }
        
        //so the Passengers dont look like they are going through the floors
        for(int i = 1; i < genOne_DOORS.size(); i++){
            genOne_DOORS.get(i).toFront();
        }
        
        for(int i = 0; i < bases.size(); i++){
            bases.get(i).toFront();
        }
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < getDestination_Floors().get(i).size(); j++){
                
              getDestination_Floors().get(i).get(j).getPassGUI().toFront();
              getDestination_Floors().get(i).get(j).getPassTypeGUI().toFront();
              getDestination_Floors().get(i).get(j).getStartEndGUI().toFront();

            }
        }
            
        
        
        
        
        for(int i = 0; i < genElev_ONE.size(); i++){
            if(genElev_ONE.get(i).getEndFloor() == cf[0]){
                getDestination_Floors().get(cf[0]-1).add(genElev_ONE.get(i));
                System.out.println("Passenger: " + genElev_ONE.get(i).toString() + " has exited Medical Elevator 1 on Floor " + cf[0]);
            
                genElev_ONE.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()+30);
                genElev_ONE.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()+3);
                genElev_ONE.get(i).getPassTypeGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()+20);
                genElev_ONE.get(i).getPassTypeGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-10);
                genElev_ONE.get(i).getStartEndGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()+29);
                genElev_ONE.get(i).getStartEndGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-20);
                
                genElev_ONE.get(i).getPassGUI().toFront();
                genElev_ONE.get(i).getPassTypeGUI().toFront();
                genElev_ONE.get(i).getStartEndGUI().toFront();
                
                Random ra = new Random();
                int gen_delay = ra.nextInt(5)+3;
                        
                TranslateTransition passIN = new TranslateTransition();
                TranslateTransition passtypeIN = new TranslateTransition();
                TranslateTransition startendIN = new TranslateTransition();
                     
                passIN.setNode(genElev_ONE.get(i).getPassGUI());
                passIN.setDuration(Duration.seconds(gen_delay));
                passIN.setCycleCount(1);
                passIN.setByX(425);
                
                        
                passtypeIN.setNode(genElev_ONE.get(i).getPassTypeGUI());
                passtypeIN.setDuration(Duration.seconds(gen_delay));
                passtypeIN.setCycleCount(1);
                passtypeIN.setByX(passIN.getByX());
                
                        
                startendIN.setNode(genElev_ONE.get(i).getStartEndGUI());
                startendIN.setDuration(Duration.seconds(gen_delay));
                startendIN.setCycleCount(1);
                startendIN.setByX(passIN.getByX());
                
                        
                        
                passIN.play();
                passtypeIN.play();
                startendIN.play();
                
              
                
            
            }
        }
        genElev_ONE.removeAll(getDestination_Floors().get(cf[0]-1));
        
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
         
         System.out.println("General Elevator 1 Doors Closing on Floor: " + cf[0]);
         
         p.setOnFinished(finish3 ->{
            if(cf[0] == 8){
            System.out.println("You have finished all Up REQUESTS");
           genOne_DOWN(floors,cf,genElev,bases,genOne_DOORS);
           
        }else{
            if(genElev_ONE.isEmpty()==false){
               genOne_UP_REQUESTS(floors,cf,genElev,bases,genOne_DOORS);
               
            }else{
                boolean a = true;
                for(int i = cf[0]; i <= 8; i++){
                    for(int j = 0; j < floors.get(i-1).size(); j++){
                        Passengers p = floors.get(i-1).get(j);
                        if(p.getElv_Num()==1 &&(p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor())){
                            a = false;
                            break;      
                        }
                    }
                    if(a == false){
                        genOne_UP_REQUESTS(floors,cf,genElev,bases,genOne_DOORS);
                        break;
                    }
                
            
                }
                if(a == true){
                    System.out.println("You have finished all Up REQUESTS");
                    genOne_DOWN(floors,cf,genElev,bases,genOne_DOORS);
                }
            }
                
               
            }
        });
     });
           
            
            
        });
        }
       
    }    
    
    public void genOne_DOWN(ArrayList<ArrayList<Passengers>> floors,int[] cf,ArrayList<Rectangle> genElev,ArrayList<Rectangle> bases,ArrayList<Rectangle> genOne_DOORS){
        System.out.println("DOWN METHOD HA");
        System.out.println("Next Floor down: " + cf[1]);
        
        //CHECKS THE FIRST DOWN REQUEST THAT LOWER THAN CURRENTFLOOR
        boolean nextreq = false;
            for(int i = cf[0]; i >= 1; i--){
                for(int j =0; j < floors.get(i-1).size(); j++){
                    Passengers p = floors.get(i-1).get(j);
                        if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor()) ){
                             cf[1] = i;
                             nextreq = true;
                             break;
                    }
                    
                }
                if(nextreq == true){
                    break;
                }
            }
        //CHECKS ANY DOWNREQUEST ABOVE THE CURRENT FLOOR
        for(int i = cf[0]; i <= 8;i++){
            for(int j = 0; j < floors.get(i-1).size(); j++){
                Passengers p = floors.get(i-1).get(j);
                 if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor()) ){
                    cf[1] = i;
                 }
            }
    
        } 
        
        if(cf[1] == cf[0] && floors.get(cf[1]-1).isEmpty()){
            System.out.println("THERE ARE NO OTHER REQUEST");
            cf[1] = 1;
            
            TranslateTransition tr = new TranslateTransition();
            tr.setNode(genElev.get(0));
            tr.setDuration(Duration.seconds(1));
            tr.setCycleCount(1);
        
            tr.setByY((cf[0]-cf[1])*65);
        
            tr.setDelay(Duration.seconds(1));
       
        
            tr.play();
            
            tr.setOnFinished(finish4 ->{
               
                cf[0] = 0;
                cf[1] = 0;
                boolean checkPas = false;
                for(int i = cf[0]; i < 8; i++){
                    
                    for(int j = 0; j < floors.get(i).size(); j++){
                        Passengers p = floors.get(i).get(j);
                        if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor()) ){
                            checkPas = true;
                            genOne_UP_REQUESTS(floors,cf,genElev,bases,genOne_DOORS);
                            break; 
                        }
                    }
                    if(checkPas == true){
                        break;
                    }
            
                }
                
                if(checkPas == false){
                    System.out.println("There are no more requests for General Elevator 1");
                    setIsGenerator(true);
                }
                
            });
            
       }else{
            System.out.println("General Elevator 1 Next Request at Floor: " + cf[1]);
         
            TranslateTransition tr = new TranslateTransition();
            tr.setNode(genElev.get(0));
            tr.setDuration(Duration.seconds(1));
            tr.setCycleCount(1);
        
            tr.setByY((cf[0]-cf[1])*65);
        
            tr.setDelay(Duration.seconds(1));
       
        
            tr.play();
        
            tr.setOnFinished(finish ->{
            cf[0] = cf[1];
            System.out.println("General Elevator 1 Currently on Floor: " + cf[0]);
            
            findDoors = cf[0]+ (cf[0]-1);
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
         
            System.out.println("General Elevator 1 Doors Opening on Floor: " + cf[0]);
            p.setOnFinished(finish2 ->{
        
            for(int i = 0; i < floors.get(cf[0]-1).size(); i++){
                Passengers p = floors.get(cf[0]-1).get(i);
                if((p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getElv_Num() == 1 && p.getEndFloor() < p.getStartFloor()){
                
                genElev_ONE.add(p);
                System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                
            }
            
        }
        floors.get(cf[0]-1).removeAll(genElev_ONE);
        
        for(int i = 0; i < genElev_ONE.size(); i++){
                    
                    genElev_ONE.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()-20);
                    genElev_ONE.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()-10);
                    genElev_ONE.get(i).getPassTypeGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()-30);
                    genElev_ONE.get(i).getPassTypeGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-23);
                    genElev_ONE.get(i).getStartEndGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()-21);
                    genElev_ONE.get(i).getStartEndGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-31);   
                    
                    
        }
        
        
        
        for(int i = 1; i < genOne_DOORS.size(); i++){
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
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < getDestination_Floors().get(i).size(); j++){
                
              getDestination_Floors().get(i).get(j).getPassGUI().toFront();
              getDestination_Floors().get(i).get(j).getPassTypeGUI().toFront();
              getDestination_Floors().get(i).get(j).getStartEndGUI().toFront();

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
         
         System.out.println("General Elevator 1 Doors Closing on Floor: " + cf[0]);
         
         p.setOnFinished(finish3 ->{
        
         genOne_DOWN_REQUESTS(floors,cf,genElev,bases,genOne_DOORS);

        });
        
    });
       
 });
         
 }
        
    }
    
    public void genOne_DOWN_REQUESTS(ArrayList<ArrayList<Passengers>> floors,int[] cf,ArrayList<Rectangle> genElev,ArrayList<Rectangle> bases,ArrayList<Rectangle> genOne_DOORS){
         System.out.println("Time for down requests");
         
         for(int i = cf[0]; i >= 1; i--){
             for(int j = 0; j < floors.get(i-1).size(); j++){
                 Passengers p = floors.get(i-1).get(j);
                 if(p.getElv_Num() == 1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getEndFloor() < p.getStartFloor()){
                     cf[1] = i;
                     break;
                 }
             }
             if(cf[1] < cf[0]){
                 break;
             }
         }
         
         for(int i = cf[0]; i > cf[1]; i--){
            for(int j = 0; j < genElev_ONE.size(); j++){
                if(genElev_ONE.get(j).getEndFloor() == i){
                    genOne_CHECK = i;
                    break;
                }
            }
            if(genOne_CHECK < cf[0] && genOne_CHECK > cf[1]){
                cf[1] = genOne_CHECK;
                break;
            }
        }
        
        
        if(cf[1] >= cf[0]){
            if(genElev_ONE.isEmpty()==false){
                int a = genElev_ONE.get(0).getEndFloor();
                for(int i = 1; i < genElev_ONE.size(); i++){
                    if(genElev_ONE.get(i).getEndFloor() > a){
                        a = genElev_ONE.get(i).getEndFloor();
                    }
                }
                cf[1] = a;   
            }else{
                cf[1] = 1;
            }
        }
         
        System.out.println("General Elevator 1 Next Request at Floor: " + cf[1]);
        
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(genElev.get(0));
        tr.setDuration(Duration.seconds(1));
        tr.setCycleCount(1);
        tr.setByY((cf[0]-cf[1]) *65);
        tr.setDelay(Duration.seconds(1));
        
        for(int i = 0; i < genElev_ONE.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(genElev_ONE.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(1));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(genElev_ONE.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(1));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(genElev_ONE.get(i).getStartEndGUI());
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
            cf[0] = cf[1];
            System.out.println("General Elevator 1 Currently on Floor: " + cf[0]);
            
            findDoors = cf[0]+ (cf[0]-1);
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
         
            System.out.println("General Elevator 1 Doors Opening on Floor: " + cf[0]);
            p.setOnFinished(finish2 ->{
        
            for(int i = 0; i < floors.get(cf[0]-1).size(); i++){
                Passengers p = floors.get(cf[0]-1).get(i);
                if((p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getElv_Num() == 1 && p.getEndFloor() < p.getStartFloor()){
                
                genElev_ONE.add(p);
                System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                
            }
            
        }
        floors.get(cf[0]-1).removeAll(genElev_ONE);
        
        for(int i = 0; i < genElev_ONE.size(); i++){
                    
                    genElev_ONE.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()-20);
                    genElev_ONE.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()-10);
                    genElev_ONE.get(i).getPassTypeGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()-30);
                    genElev_ONE.get(i).getPassTypeGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-23);
                    genElev_ONE.get(i).getStartEndGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()-21);
                    genElev_ONE.get(i).getStartEndGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-31);   
        }
        
       for(int i = 1; i < genOne_DOORS.size(); i++){
            genOne_DOORS.get(i).toFront();
        }
        
        for(int i = 0; i < bases.size(); i++){
            bases.get(i).toFront();
        }
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < getDestination_Floors().get(i).size(); j++){
                
              getDestination_Floors().get(i).get(j).getPassGUI().toFront();
              getDestination_Floors().get(i).get(j).getPassTypeGUI().toFront();
              getDestination_Floors().get(i).get(j).getStartEndGUI().toFront();

            }
        }
        for(int i = 0; i < genElev_ONE.size(); i++){
            if(genElev_ONE.get(i).getEndFloor() == cf[0]){
                getDestination_Floors().get(cf[0]-1).add(genElev_ONE.get(i));
                System.out.println("Passenger: " + genElev_ONE.get(i).toString() + " has exited Medical Elevator 1 on Floor " + cf[0]);
            
                genElev_ONE.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()+30);
                genElev_ONE.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()+3);
                genElev_ONE.get(i).getPassTypeGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()+20);
                genElev_ONE.get(i).getPassTypeGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-10);
                genElev_ONE.get(i).getStartEndGUI().setLayoutX(genElev_ONE.get(i).getPassGUI().getX()+29);
                genElev_ONE.get(i).getStartEndGUI().setLayoutY(genElev_ONE.get(i).getPassGUI().getY()-20);
                
                genElev_ONE.get(i).getPassGUI().toFront();
                genElev_ONE.get(i).getPassTypeGUI().toFront();
                genElev_ONE.get(i).getStartEndGUI().toFront();
                
                Random ra = new Random();
                int gen_delay = ra.nextInt(5)+3;
                        
                TranslateTransition passIN = new TranslateTransition();
                TranslateTransition passtypeIN = new TranslateTransition();
                TranslateTransition startendIN = new TranslateTransition();
                     
                passIN.setNode(genElev_ONE.get(i).getPassGUI());
                passIN.setDuration(Duration.seconds(gen_delay));
                passIN.setCycleCount(1);
                passIN.setByX(425);
                
                        
                passtypeIN.setNode(genElev_ONE.get(i).getPassTypeGUI());
                passtypeIN.setDuration(Duration.seconds(gen_delay));
                passtypeIN.setCycleCount(1);
                passtypeIN.setByX(passIN.getByX());
                
                        
                startendIN.setNode(genElev_ONE.get(i).getStartEndGUI());
                startendIN.setDuration(Duration.seconds(gen_delay));
                startendIN.setCycleCount(1);
                startendIN.setByX(passIN.getByX());
                
                        
                        
                passIN.play();
                passtypeIN.play();
                startendIN.play();
                
              
                
            
            }
        }
        genElev_ONE.removeAll(getDestination_Floors().get(cf[0]-1));
        
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
         
         System.out.println("General Elevator 1 Doors Closing on Floor: " + cf[0]);
         
         p.setOnFinished(finish3 ->{
        
        if(cf[0] == 1){
            
            cf[0] = 0;
            cf[1] = 0;
            System.out.println("floors: " + floors.toString());
            boolean checkPas = false;
                for(int i = cf[0]; i < 8; i++){
                    
                    for(int j = 0; j < floors.get(i).size(); j++){
                        Passengers p = floors.get(i).get(j);
                        if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor()) ){
                         checkPas = true;
                            genOne_UP_REQUESTS(floors,cf,genElev,bases,genOne_DOORS);
                            break; 
                        }
                    }
                    
                    if(checkPas == true){
                        break;
                    }
            
                }
                System.out.println(checkPas);
                if(checkPas == false){
                    System.out.println("There are no more requests for General Elevator 1");
                    setIsGenerator(true);
                }
         
        }
        else{
            if(genElev_ONE.isEmpty()==false){
                   genOne_DOWN_REQUESTS(floors,cf,genElev,bases,genOne_DOORS);
            }else{
                boolean a = false;
                for(int i = cf[0]; i >= 1; i--){
                    for(int j = 0; j < floors.get(i-1).size(); j++){
                        Passengers p = floors.get(i-1).get(j);
                        if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor())){
                            a = true;
                            break;      
                        }
                    }
                    if(a == true){
                      genOne_DOWN_REQUESTS(floors,cf,genElev,bases,genOne_DOORS);
                      break;
                    }
            
                }
                 
                if(a == false){
                    System.out.println("THERE ARE NO OTHER REQUEST");
                    cf[1] = 1;
            
                    TranslateTransition tr2 = new TranslateTransition();
                    tr2.setNode(genElev.get(0));
                    tr2.setDuration(Duration.seconds(1));
                    tr2.setCycleCount(1);
        
                    tr2.setByY((cf[0]-cf[1])*65);
        
                    tr2.setDelay(Duration.seconds(1));
       
        
                    tr2.play();
            
                    tr2.setOnFinished(finish4 ->{
                        
                        cf[0] = 0;
                        cf[1] = 0;
                         System.out.println("floors: " + floors.toString());
                        boolean checkPas = false;
                        for(int i = cf[0]; i <8; i++){
                            
                            for(int j = 0; j < floors.get(i).size(); j++){
                                Passengers p = floors.get(i).get(j);
                                if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor()) ){
                                    checkPas = true;
                                    genOne_UP_REQUESTS(floors,cf,genElev,bases,genOne_DOORS);
                                    
                                     break; 
                                }
                            }
                            
                            if(checkPas == true){
                                break;
                            }
            
                        }
                        System.out.println(checkPas);
                        if(checkPas == false){
                            System.out.println("There are no more requests for General Elevator 1");
                            setIsGenerator(true);
                        }
                        
                    });

                }
            }
              
        }
        
        });
         
      });
         
    });        
        

         
        
    }
    
   


}