package com.mycompany.elevatorproject;


import java.util.ArrayList;
import java.util.Arrays;


import java.util.Random;

import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PrimaryController extends TrafficGenerator{
    
    private int passID=0;
    private  TranslateTransition trPass;
    private boolean add_stuff = true;
    
     
    
    

    @FXML
    private AnchorPane stage;
    
    @FXML
    private Button spawnButton;
    @FXML
    private Rectangle genElev1;
    @FXML
    private Rectangle genElev2;
    @FXML
    private Rectangle genElev3;
    @FXML
    private Rectangle genElev4;
    @FXML
    private Rectangle genElev5;
    @FXML
    private Rectangle medElev1;
    @FXML
    private Rectangle medElev2;
    @FXML
    private Rectangle medElev3;
    @FXML
    private TextField passengerAmount;
    
    private ArrayList<ArrayList<Passengers>> destination_floors = new ArrayList();
    private ArrayList<Passengers> genElev_ONE = new ArrayList();
    private ArrayList<Passengers> genElev_TWO = new ArrayList();

    private ArrayList<Rectangle> genElev = new ArrayList();
    private ArrayList<Rectangle> medElev = new ArrayList();
    private int[] gen_currentFloors = {-1,0,1,0,1,0,1,0,1,0};
   
    private int genOne_CHECK = 0;
    
    
    
    
    private String sp;
    
    private int age;
    private Label errorText;
    @FXML
    private Button spawnButton2;
    @FXML
    private VBox back;
    @FXML
    private Label errorDisplay;
    private FadeTransition fade = new FadeTransition();
    @FXML
    private Rectangle base2;
    @FXML
    private Rectangle base4;
    @FXML
    private Rectangle base5;
    @FXML
    private Rectangle base6;
    @FXML
    private Rectangle base7;
    @FXML
    private Rectangle base8;
    @FXML
    private Rectangle base3;
    
    private ArrayList<Rectangle> bases = new ArrayList();
    @FXML
    private Rectangle base1;
    @FXML
    private Rectangle oneLeft;
    @FXML
    private Rectangle oneRight;
    private int county = 0;
    private TranslateTransition tt;
    private TranslateTransition tt2;
    private ScaleTransition st;
    private ScaleTransition st2;
    private ParallelTransition p;
   
    private Rectangle leftDoor;
    private Rectangle rightDoor;
    @FXML
    private Rectangle oneLeft2;
    @FXML
    private Rectangle oneLeft3;
    @FXML
    private Rectangle oneLeft4;
    @FXML
    private Rectangle oneLeft5;
    @FXML
    private Rectangle oneLeft6;
    @FXML
    private Rectangle oneLeft7;
    @FXML
    private Rectangle oneLeft8;
    @FXML
    private Rectangle oneRight2;
    @FXML
    private Rectangle oneRight3;
    @FXML
    private Rectangle oneRight4;
    @FXML
    private Rectangle oneRight5;
    @FXML
    private Rectangle oneRight6;
    @FXML
    private Rectangle oneRight7;
    @FXML
    private Rectangle oneRight8;
    
    private ArrayList<Rectangle> genOne_DOORS = new ArrayList();
    
    private Rectangle space;
    private int findDoors = 0;
    @FXML
    private Rectangle roof;
    private General_Elevator ge = new General_Elevator();
    int[] cf;
    
        
    public void addItems(){
        if(destination_floors.size()!= 8){
                    for(int i = 0; i < 8; i++){
                        destination_floors.add(new ArrayList<Passengers>());
                    }
                }
            
            
            if(genElev.size() != 5){
                genElev.add(genElev1);
                genElev.add(genElev2);
                genElev.add(genElev3);
                genElev.add(genElev4);
                genElev.add(genElev5);    
            }
            
            
            if(medElev.size() != 3){
                medElev.add(medElev1);
                medElev.add(medElev2);
                medElev.add(medElev3);
            }
            
            if(bases.size() != 9){
                bases.add(base1);
                bases.add(base2);
                bases.add(base3);
                bases.add(base4);
                bases.add(base5);
                bases.add(base6);
                bases.add(base7);
                bases.add(base8);
                bases.add(roof);
            }
            
            if(genOne_DOORS.size()!=17){
                
                genOne_DOORS.add(space);
                genOne_DOORS.add(oneLeft);
                genOne_DOORS.add(oneRight);
                genOne_DOORS.add(oneLeft2);
                genOne_DOORS.add(oneRight2);
                genOne_DOORS.add(oneLeft3);
                genOne_DOORS.add(oneRight3);
                genOne_DOORS.add(oneLeft4);
                genOne_DOORS.add(oneRight4);
                genOne_DOORS.add(oneLeft5);
                genOne_DOORS.add(oneRight5);
                genOne_DOORS.add(oneLeft6);
                genOne_DOORS.add(oneRight6);
                genOne_DOORS.add(oneLeft7);
                genOne_DOORS.add(oneRight7);
                genOne_DOORS.add(oneLeft8);
                genOne_DOORS.add(oneRight8);
          
            }
        
    }
        
  
            
    
  
    @FXML
    private void spawnBtn(ActionEvent event){
            buttonEffect();
            addItems();
            
            try{
                if(gen_currentFloors[0] == -1){
                    
                    age = Integer.parseInt(passengerAmount.getText());
                    System.out.println("Passengers Amount created:" + age);
                    errorDisplay.setText("Passengers have been Generated...");
                    errorDisplay.setStyle("-fx-text-fill: #46e38f");
                    fade.setNode(errorDisplay);
                    fade.setDuration(Duration.seconds(2));
                    fade.setCycleCount(1);
                    fade.setInterpolator(Interpolator.LINEAR);
                    fade.setFromValue(1);
                    fade.setToValue(0);
                    fade.play();
            
                    for(int i = 0; i < age; i++){
                
                        passID++;
       
                        Passengers p1 = pass_Generator(passID);
                        passGenerator(p1);
                        
                    }
            
                    trPass.setOnFinished(finish ->{
               
                    System.out.println("AMOUNT OF FLOORS: " + destination_floors.size());
                    elevators();

                    });
                }else{
                    errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                    errorDisplay.setText("Wait for the Elevators to Finish...");
                    fade.setNode(errorDisplay);
                    fade.setDuration(Duration.seconds(2));
                    fade.setCycleCount(1);
                    fade.setInterpolator(Interpolator.LINEAR);
                    fade.setFromValue(1);
                    fade.setToValue(0);
                    fade.play();
                    
                }
                
            
            
            }
            catch(NumberFormatException e){
                errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                errorDisplay.setText("ERROR! Please Enter Only Integers...");
                System.out.println("Please enter only Integers.");
                
                fade.setNode(errorDisplay);
                fade.setDuration(Duration.seconds(2));
                fade.setCycleCount(1);
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();
                
            }  
            
    }
    
    @FXML
    private void spawnBtn2(ActionEvent event) {
        
       addItems();
           
        if(spawnButton2.getText().equals("OFF")){
            if(gen_currentFloors[0] == -1){
                spawnButton2.setText("ON");
                spawnButton2.setStyle("-fx-background-color: #0be31a");
                System.out.println("Auto Passenger Generator is ON.");
                errorDisplay.setText("Auto Passenger Generator is ON");
                errorDisplay.setStyle("-fx-text-fill: #46e38f");
                fade.setNode(errorDisplay);
                fade.setDuration(Duration.seconds(2));
                fade.setCycleCount(1);
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();
                Random r = new Random();
                int amount = r.nextInt(10)+1;
                for(int i = 0; i < amount; i++){
                
                    passID++;
       
                    Passengers p1 = pass_Generator(passID);
                    passGenerator(p1);
                 
                }
            
                trPass.setOnFinished(finish ->{
               
                System.out.println("AMOUNT OF FLOORS: " + destination_floors.size());
                elevators();

                });
            }else{
                errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                errorDisplay.setText("Wait for the Elevators to Finish...");
                fade.setNode(errorDisplay);
                fade.setDuration(Duration.seconds(2));
                fade.setCycleCount(1);
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();
                
            }
            
            
        }
        else{
            
            spawnButton2.setText("OFF");
            spawnButton2.setStyle("-fx-background-color:   #bf1b1b");
            System.out.println("Auto Passenger Generator is OFF.");
            errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                errorDisplay.setText("Auto Passenger Generator is OFF.");
                fade.setNode(errorDisplay);
                fade.setDuration(Duration.seconds(2));
                fade.setCycleCount(1);
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();
        }
        
        
        
        
       
        
        
    }
    
   
    public void passGenerator_LOOP(){
         
           
        if(spawnButton2.getText().equals("OFF")){
            System.out.println("Auto Passenger Generator is OFF.");   
        }else{
            Random r = new Random();
            int amount = r.nextInt(10)+1;
            for(int i = 0; i < amount; i++){
                
                passID++;
       
                Passengers p1 = pass_Generator(passID);
                passGenerator(p1);
                 
            }
            
            trPass.setOnFinished(finish ->{
               
              System.out.println("AMOUNT OF FLOORS: " + destination_floors.size());
               elevators();

            });
            
        }
                 
    }
    
    
    
    
    
    public void elevators(){
        for(int i = 0; i < getFloors().size(); i++){
            for(int j = 0; j < getFloors().get(i).size(); j++){
                Passengers p = getFloors().get(i).get(j);
                switch(p.getElv_Num()){
                    case 1:
                        if(p.getPassType().equals("Patient") || p.getPassType().equals("Visitor") || p.getPassType().equals("Security")){
                            gen_currentFloors[0] = 0; 
                        }
                        break;
                    
                    case 2:
                        if(p.getPassType().equals("Patient") || p.getPassType().equals("Visitor") || p.getPassType().equals("Security")){
                            gen_currentFloors[2] = 0; 
                        }
                        break;
                        
                }

            }
        }
        
        System.out.println("LOOK: "+ Arrays.toString(gen_currentFloors));
        for(int i = 0; i < gen_currentFloors.length; i++){
            if(gen_currentFloors[i] == 0){
                switch(i){
                    case 0:
                        System.out.println("General Elevator 1 has Requests");
                        genOne_OP(getFloors(),gen_currentFloors,genElev_ONE, destination_floors);
                        break;
                    
                    case 2:
                        System.out.println("General Elevator 1 has Requests");
                        genTwo_OP(getFloors(),gen_currentFloors,genElev_TWO, destination_floors);
                        break;
                        
                }
            }else{
                System.out.println("No Request for Elevator number: " + (i+1));
            }
            
        }
            
    }
    
    public void genOne_OP(ArrayList<ArrayList<Passengers>> floors, int[] currentFloors, ArrayList<Passengers> genElev_1, ArrayList<ArrayList<Passengers>> dest_floors){
        
        cf = ge.genOne_UP_REQUESTS(floors,  currentFloors, genElev_1);
        
        if(cf[0] == 0){
            cf[0] = 1;
        }
        
        if(cf[1] == 0){
          genOne_OP2(floors,cf,genElev_1,dest_floors);
        }else{
        
        
        System.out.println("General Elevator 1 Next Request at Floor: " +  cf[1]);
        
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(genElev.get(0));
        tr.setDuration(Duration.seconds(1));
        tr.setCycleCount(1);
        tr.setByY(( cf[1] - cf[0]) *-65);
        tr.setDelay(Duration.seconds(1));
        
        for(int i = 0; i < genElev_1.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(genElev_1.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(1));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(genElev_1.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(1));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(genElev_1.get(i).getStartEndGUI());
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
         
            System.out.println("General Elevator 1 Doors Opening on Floor: " + currentFloors[0]);
            p.setOnFinished(finish2 ->{
             
        
        
            for(int i = 0; i < floors.get(cf[0]-1).size(); i++){
                Passengers p = floors.get(cf[0]-1).get(i);
                if((p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getElv_Num() == 1 && p.getEndFloor() > p.getStartFloor()){
                
                    genElev_1.add(p);
                    System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                
                }
            
            }
            floors.get(cf[0]-1).removeAll(genElev_1);
        
            for(int i = 0; i < genElev_1.size(); i++){
                    
                    
                    
                    genElev_1.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()-20);
                    genElev_1.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()-10);
                    genElev_1.get(i).getPassTypeGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()-30);
                    genElev_1.get(i).getPassTypeGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-23);
                    genElev_1.get(i).getStartEndGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()-21);
                    genElev_1.get(i).getStartEndGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-31);  
                    
                    
        }
        
        //so the Passengers dont look like they are going through the floors
        for(int i = 1; i < genOne_DOORS.size(); i++){
            genOne_DOORS.get(i).toFront();
        }
        
        for(int i = 0; i < bases.size(); i++){
            bases.get(i).toFront();
        }
        
        
        
        
        for(int i = 0; i < genElev_1.size(); i++){
            if(genElev_1.get(i).getEndFloor() == cf[0]){
                destination_floors.get(cf[0]-1).add(genElev_1.get(i));
                System.out.println("Passenger: " + genElev_1.get(i).toString() + " has exited Medical Elevator 1 on Floor " + cf[0]);
            
                genElev_1.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()+30);
                genElev_1.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()+3);
                genElev_1.get(i).getPassTypeGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()+20);
                genElev_1.get(i).getPassTypeGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-10);
                genElev_1.get(i).getStartEndGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()+29);
                genElev_1.get(i).getStartEndGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-20);
                
                genElev_1.get(i).getPassGUI().toFront();
                genElev_1.get(i).getPassTypeGUI().toFront();
                genElev_1.get(i).getStartEndGUI().toFront();
                
                Random ra = new Random();
                int gen_delay = ra.nextInt(5)+3;
                        
                TranslateTransition passIN = new TranslateTransition();
                TranslateTransition passtypeIN = new TranslateTransition();
                TranslateTransition startendIN = new TranslateTransition();
                     
                passIN.setNode(genElev_1.get(i).getPassGUI());
                passIN.setDuration(Duration.seconds(gen_delay));
                passIN.setCycleCount(1);
                passIN.setByX(425);
                
                        
                passtypeIN.setNode(genElev_1.get(i).getPassTypeGUI());
                passtypeIN.setDuration(Duration.seconds(gen_delay));
                passtypeIN.setCycleCount(1);
                passtypeIN.setByX(passIN.getByX());
                
                        
                startendIN.setNode(genElev_1.get(i).getStartEndGUI());
                startendIN.setDuration(Duration.seconds(gen_delay));
                startendIN.setCycleCount(1);
                startendIN.setByX(passIN.getByX());
                
                        
                        
                passIN.play();
                passtypeIN.play();
                startendIN.play();
                
              
                
            
            }
        }
        genElev_1.removeAll(dest_floors.get(cf[0]-1));
        
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
            if(currentFloors[0] == 8){
            System.out.println("You have finished all Up REQUESTS");
           genOne_OP2(floors,cf,genElev_1,dest_floors);
           
        }else{
            if(genElev_1.isEmpty()==false){
               genOne_OP(floors,cf,genElev_1,dest_floors);
               
            }else{
                int a = 0;
                for(int i = cf[0]; i <= 8; i++){
                    for(int j = 0; j < floors.get(i-1).size(); j++){
                        Passengers p = floors.get(i-1).get(j);
                        if(p.getElv_Num()==1 &&(p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor())){
                            a = 1;
                            break;      
                        }
                    }
                    if(a == 1){
                        genOne_OP(floors,cf,genElev_1,dest_floors);
                        break;
                    }
                
            
                }
                if(a == 0){
                    System.out.println("You have finished all Up REQUESTS");
                    genOne_OP2(floors,cf,genElev_1,dest_floors);
                }
            }
                
               
            }
        });
     });
           
            
            
        });
        }
    
    
    }
    
    public void genOne_OP2(ArrayList<ArrayList<Passengers>> floors, int[] currentFloors, ArrayList<Passengers> genElev_1,ArrayList<ArrayList<Passengers>> dest_floors){
      
        cf = ge.genOne_DOWN(floors, currentFloors, genElev_1);
        
        if(cf[1] == cf[0] && floors.get(cf[1]-1).isEmpty()){
            System.out.println("THERE ARE NO OTHER REQUEST");
            currentFloors[1] = 1;
            
            TranslateTransition tr = new TranslateTransition();
            tr.setNode(genElev.get(0));
            tr.setDuration(Duration.seconds(1));
            tr.setCycleCount(1);
        
            tr.setByY((currentFloors[0]-currentFloors[1])*65);
        
            tr.setDelay(Duration.seconds(1));
       
        
            tr.play();
            
            tr.setOnFinished(finish4 ->{
                System.out.println("DONE");
                cf[0] = -1;
                passGenerator_LOOP();  
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
        
            for(int i = 0; i < floors.get(currentFloors[0]-1).size(); i++){
                Passengers p = floors.get(currentFloors[0]-1).get(i);
                if((p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getElv_Num() == 1 && p.getEndFloor() < p.getStartFloor()){
                
                genElev_1.add(p);
                System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                
            }
            
        }
        floors.get(cf[0]-1).removeAll(genElev_1);
        
        for(int i = 0; i < genElev_1.size(); i++){
                    
                    genElev_1.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()-20);
                    genElev_1.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()-10);
                    genElev_1.get(i).getPassTypeGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()-30);
                    genElev_1.get(i).getPassTypeGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-23);
                    genElev_1.get(i).getStartEndGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()-21);
                    genElev_1.get(i).getStartEndGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-31);   
                    
                    
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
        
         genOne_OP3(floors,currentFloors,genElev_1,dest_floors);

        });
        
    });
       
 });
         
 }
    }
     
    public void genOne_OP3(ArrayList<ArrayList<Passengers>> floors, int[] currentFloors, ArrayList<Passengers> genElev_1,ArrayList<ArrayList<Passengers>> dest_floors){
       
       cf = ge.genOne_DOWN_REQUESTS(floors, currentFloors, genElev_1);
       
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(genElev.get(0));
        tr.setDuration(Duration.seconds(1));
        tr.setCycleCount(1);
        tr.setByY((currentFloors[0]-currentFloors[1]) *65);
        tr.setDelay(Duration.seconds(1));
        
        for(int i = 0; i < genElev_1.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(genElev_1.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(1));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(genElev_1.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(1));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(genElev_1.get(i).getStartEndGUI());
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
                
                genElev_1.add(p);
                System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                
            }
            
        }
        floors.get(cf[0]-1).removeAll(genElev_1);
        
        for(int i = 0; i < genElev_1.size(); i++){
                    
                    genElev_1.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()-20);
                    genElev_1.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()-10);
                    genElev_1.get(i).getPassTypeGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()-30);
                    genElev_1.get(i).getPassTypeGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-23);
                    genElev_1.get(i).getStartEndGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()-21);
                    genElev_1.get(i).getStartEndGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-31);   
        }
        
       for(int i = 1; i < genOne_DOORS.size(); i++){
            genOne_DOORS.get(i).toFront();
        }
        
        for(int i = 0; i < bases.size(); i++){
            bases.get(i).toFront();
        }
        
        
        for(int i = 0; i < genElev_1.size(); i++){
            if(genElev_1.get(i).getEndFloor() == currentFloors[0]){
                destination_floors.get(currentFloors[0]-1).add(genElev_1.get(i));
                System.out.println("Passenger: " + genElev_1.get(i).toString() + " has exited Medical Elevator 1 on Floor " + currentFloors[0]);
            
                genElev_1.get(i).getPassGUI().setLayoutX(genElev.get(0).getX()+30);
                genElev_1.get(i).getPassGUI().setLayoutY(genElev.get(0).getY()+3);
                genElev_1.get(i).getPassTypeGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()+20);
                genElev_1.get(i).getPassTypeGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-10);
                genElev_1.get(i).getStartEndGUI().setLayoutX(genElev_1.get(i).getPassGUI().getX()+29);
                genElev_1.get(i).getStartEndGUI().setLayoutY(genElev_1.get(i).getPassGUI().getY()-20);
                
                genElev_1.get(i).getPassGUI().toFront();
                genElev_1.get(i).getPassTypeGUI().toFront();
                genElev_1.get(i).getStartEndGUI().toFront();
                
                Random ra = new Random();
                int gen_delay = ra.nextInt(5)+3;
                        
                TranslateTransition passIN = new TranslateTransition();
                TranslateTransition passtypeIN = new TranslateTransition();
                TranslateTransition startendIN = new TranslateTransition();
                     
                passIN.setNode(genElev_1.get(i).getPassGUI());
                passIN.setDuration(Duration.seconds(gen_delay));
                passIN.setCycleCount(1);
                passIN.setByX(425);
                
                        
                passtypeIN.setNode(genElev_1.get(i).getPassTypeGUI());
                passtypeIN.setDuration(Duration.seconds(gen_delay));
                passtypeIN.setCycleCount(1);
                passtypeIN.setByX(passIN.getByX());
                
                        
                startendIN.setNode(genElev_1.get(i).getStartEndGUI());
                startendIN.setDuration(Duration.seconds(gen_delay));
                startendIN.setCycleCount(1);
                startendIN.setByX(passIN.getByX());
                
                        
                        
                passIN.play();
                passtypeIN.play();
                startendIN.play();
                
              
                
            
            }
        }
        genElev_1.removeAll(dest_floors.get(cf[0]-1));
        
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
            System.out.println("DONE");
            gen_currentFloors[0] = -1;
            passGenerator_LOOP();
         
        }
        else{
            if(genElev_1.isEmpty()==false){
                   genOne_OP3(floors,currentFloors,genElev_1,dest_floors);
            }else{
                int a = 0;
                for(int i = cf[0]; i >= 1; i--){
                    for(int j = 0; j < floors.get(i-1).size(); j++){
                        Passengers p = floors.get(i-1).get(j);
                        if(p.getElv_Num()==1 && (p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() < p.getStartFloor())){
                            a = 1;
                            break;      
                        }
                    }
                    if(a == 1){
                      genOne_OP3(floors,cf,genElev_1,dest_floors);
                      break;
                    }
            
                }
                 
                if(a == 0){
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
                        System.out.println("DONE");
                        gen_currentFloors[0] = -1;
                        passGenerator_LOOP();
                    });

                }
            }
              
        }
        
        });
         
      });
         
    });

         
       
         


   }

    public void genTwo_OP(ArrayList<ArrayList<Passengers>> floors, int[] currentFloors, ArrayList<Passengers> genElev_2, ArrayList<ArrayList<Passengers>> dest_floors){
        
        cf = ge.genOne_UP_REQUESTS(floors,  currentFloors, genElev_2);
        
        if(cf[2] == 0){
            cf[2] = 1;
        }
        
        if(cf[3] == 0){
          genOne_OP2(floors,cf,genElev_2,dest_floors);
        }else{
        
        
        System.out.println("General Elevator 1 Next Request at Floor: " +  cf[3]);
        
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(genElev.get(1));
        tr.setDuration(Duration.seconds(1));
        tr.setCycleCount(1);
        tr.setByY(( cf[2] - cf[3]) *-65);
        tr.setDelay(Duration.seconds(1));
        
        for(int i = 0; i < genElev_2.size(); i++){
                    
        TranslateTransition passIN = new TranslateTransition();
        TranslateTransition  passtypeIN = new TranslateTransition();
        TranslateTransition  startendIN = new TranslateTransition();
                    
        passIN.setNode(genElev_2.get(i).getPassGUI());
        passIN.setDelay(Duration.seconds(1));
        passIN.setDuration(Duration.seconds(1));
        passIN.setByY(tr.getByY());
        passIN.setCycleCount(1);
                    
        passtypeIN.setNode(genElev_2.get(i).getPassTypeGUI());
        passtypeIN.setDelay(Duration.seconds(1));
        passtypeIN.setDuration(Duration.seconds(1));
        passtypeIN.setByY(tr.getByY());
        passtypeIN.setCycleCount(1);
                    
        startendIN.setNode(genElev_2.get(i).getStartEndGUI());
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
            cf[2] = cf[3];
            
            System.out.println("General Elevator 1 Currently on Floor: " + cf[2]);
            
            findDoors = cf[2]+ (cf[2]-1);
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
         
            System.out.println("General Elevator 1 Doors Opening on Floor: " + currentFloors[2]);
            p.setOnFinished(finish2 ->{
             
        
        
            for(int i = 0; i < floors.get(cf[2]-1).size(); i++){
                Passengers p = floors.get(cf[2]-1).get(i);
                if((p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && p.getElv_Num() == 2 && p.getEndFloor() > p.getStartFloor()){
                
                    genElev_2.add(p);
                    System.out.println("Passenger: " + p.toString() + " has been added to General Elevator 1.");
                
                }
            
            }
            floors.get(cf[0]-1).removeAll(genElev_2);
        
            for(int i = 0; i < genElev_2.size(); i++){
                    
                    
                    
                    genElev_2.get(i).getPassGUI().setLayoutX(genElev.get(1).getX()-20);
                    genElev_2.get(i).getPassGUI().setLayoutY(genElev.get(1).getY()-10);
                    genElev_2.get(i).getPassTypeGUI().setLayoutX(genElev_2.get(i).getPassGUI().getX()-30);
                    genElev_2.get(i).getPassTypeGUI().setLayoutY(genElev_2.get(i).getPassGUI().getY()-23);
                    genElev_2.get(i).getStartEndGUI().setLayoutX(genElev_2.get(i).getPassGUI().getX()-21);
                    genElev_2.get(i).getStartEndGUI().setLayoutY(genElev_2.get(i).getPassGUI().getY()-31);  
                    
                    
        }
        
        //so the Passengers dont look like they are going through the floors
        for(int i = 1; i < genOne_DOORS.size(); i++){
            genOne_DOORS.get(i).toFront();
        }
        
        for(int i = 0; i < bases.size(); i++){
            bases.get(i).toFront();
        }
        
        
        
        
        for(int i = 0; i < genElev_2.size(); i++){
            if(genElev_2.get(i).getEndFloor() == cf[2]){
                destination_floors.get(cf[2]-1).add(genElev_2.get(i));
                System.out.println("Passenger: " + genElev_2.get(i).toString() + " has exited Medical Elevator 1 on Floor " + cf[2]);
            
                genElev_2.get(i).getPassGUI().setLayoutX(genElev.get(1).getX()+30);
                genElev_2.get(i).getPassGUI().setLayoutY(genElev.get(1).getY()+3);
                genElev_2.get(i).getPassTypeGUI().setLayoutX(genElev_2.get(i).getPassGUI().getX()+20);
                genElev_2.get(i).getPassTypeGUI().setLayoutY(genElev_2.get(i).getPassGUI().getY()-10);
                genElev_2.get(i).getStartEndGUI().setLayoutX(genElev_2.get(i).getPassGUI().getX()+29);
                genElev_2.get(i).getStartEndGUI().setLayoutY(genElev_2.get(i).getPassGUI().getY()-20);
                
                genElev_2.get(i).getPassGUI().toFront();
                genElev_2.get(i).getPassTypeGUI().toFront();
                genElev_2.get(i).getStartEndGUI().toFront();
                
                Random ra = new Random();
                int gen_delay = ra.nextInt(5)+3;
                        
                TranslateTransition passIN = new TranslateTransition();
                TranslateTransition passtypeIN = new TranslateTransition();
                TranslateTransition startendIN = new TranslateTransition();
                     
                passIN.setNode(genElev_2.get(i).getPassGUI());
                passIN.setDuration(Duration.seconds(gen_delay));
                passIN.setCycleCount(1);
                passIN.setByX(425);
                
                        
                passtypeIN.setNode(genElev_2.get(i).getPassTypeGUI());
                passtypeIN.setDuration(Duration.seconds(gen_delay));
                passtypeIN.setCycleCount(1);
                passtypeIN.setByX(passIN.getByX());
                
                        
                startendIN.setNode(genElev_2.get(i).getStartEndGUI());
                startendIN.setDuration(Duration.seconds(gen_delay));
                startendIN.setCycleCount(1);
                startendIN.setByX(passIN.getByX());
                
                        
                        
                passIN.play();
                passtypeIN.play();
                startendIN.play();
                
              
                
            
            }
        }
        genElev_2.removeAll(dest_floors.get(cf[2]-1));
        
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
            if(currentFloors[0] == 8){
            System.out.println("You have finished all Up REQUESTS");
           //genTwo_OP2(floors,cf,genElev_2,dest_floors);
           
        }else{
            if(genElev_2.isEmpty()==false){
               //genOne_OP(floors,cf,genElev_2,dest_floors);
               
            }else{
                int a = 0;
                for(int i = cf[2]; i <= 8; i++){
                    for(int j = 0; j < floors.get(i-1).size(); j++){
                        Passengers p = floors.get(i-1).get(j);
                        if(p.getElv_Num()==2 &&(p.getPassType().equals("Patient")||p.getPassType().equals("Visitor")||p.getPassType().equals("Security")) && (p.getEndFloor() > p.getStartFloor())){
                            a = 1;
                            break;      
                        }
                    }
                    if(a == 1){
                       // genOne_OP(floors,cf,genElev_2,dest_floors);
                        break;
                    }
                
            
                }
                if(a == 0){
                    System.out.println("You have finished all Up REQUESTS");
                    //genOne_OP2(floors,cf,genElev_2,dest_floors);
                }
            }
                
               
            }
        });
     });
           
            
            
        });
        }
    
    
    }
    
    
    public void passGenerator(Passengers newp){
        Random r = new Random();
        System.out.println(newp.toString());
        
        Rectangle box;
        Label passType;
        Label startEnd;

         box = newp.getPassGUI();
         passType = newp.getPassTypeGUI();
         startEnd = newp.getStartEndGUI();
         
        
        
          box.setHeight(25);
          box.setWidth(25);
       
        
        
       
        passType.setTextFill(Color.web("#000000"));
        passType.setFont(Font.font("Arial",FontWeight.BOLD, 10));
        passType.setTextAlignment(TextAlignment.CENTER);
        //passType.setStyle("-fx-background-color: #000000;");
        
        
        
        startEnd.setTextFill(Color.web("#000000"));
        startEnd.setFont(Font.font("Arial",FontWeight.BOLD, 10));
        startEnd.setTextAlignment(TextAlignment.CENTER);
        
       
        
        
        box.setX(-25);
        String passT = newp.getPassType();
        if(passT.equals("Visitor")||passT.equals("Patient")||passT.equals("Security")||passT.equals("Medical")||passT.equals("Support")){
            
             switch(newp.getStartFloor()){
                 case 1: 
                    box.setY(488);
                    break;
                 case 2:
                     box.setY(423);
                     break;
                 case 3:
                     box.setY(358);
                     break;
                 case 4:
                     box.setY(293);
                     break;
                 case 5:
                     box.setY(228);
                     break;
                 case 6:
                     box.setY(163);
                     break;
                 case 7:
                     box.setY(98);
                     break;
                 case 8:
                     box.setY(33);
                     break;
                 default:
                     System.out.println("Error finding spawn");
                     break;
                 
             }
        }
        switch(passT){
            case "Patient":
                box.setFill(Color.web("#ff7d4a")); 
                break;
            case "Visitor":
                box.setFill(Color.web("#7cd660"));
                break;
            case "Support":
                box.setFill(Color.web("#3abffc"));
                break;
            case "Security":
                box.setFill(Color.web("#000000"));
                break;
            case "Medical":
                box.setFill(Color.web("#fcfafa"));
                break;
            
            default:
                System.out.println("Error finding Passenger Type...");
                break;      
        }
        
        
        
        
        passType.setLayoutX(box.getX()-10);
        passType.setLayoutY(box.getY()-12);
        
        startEnd.setLayoutX(box.getX());
        startEnd.setLayoutY(box.getY()-20);
        
        passType.setText(Integer.toString(newp.getPassID())+"||"+newp.getPassType());
        startEnd.setText(newp.getStartFloor()+ " => " + newp.getEndFloor());
        
        
        
        stage.getChildren().addAll(box,passType, startEnd);
        
        
        
         trPass = new TranslateTransition();
         TranslateTransition trPtype = new TranslateTransition();
         TranslateTransition trPoints = new TranslateTransition();
        
        trPass.setNode(box);
        
        switch(newp.getElv_Num()){
            case 1:
                
                if((passT.equals("Visitor") || passT.equals("Patient") || passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(r.nextInt(13)+78);
                }
                
                break;
            case 2:
                 if((passT.equals("Visitor") || passT.equals("Patient") || passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(135);
                }
                
                break;
            case 3:
                if((passT.equals("Visitor") || passT.equals("Patient") || passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(205);
                }
                
                break;
            case 4:
                if((passT.equals("Visitor") || passT.equals("Patient") || passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(275);
                }
                
                break;
            case 5:
                if((passT.equals("Visitor") || passT.equals("Patient") || passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(310);
                }
                
                break;
            
            case 6:
                if((passT.equals("Medical") || passT.equals("Support")|| passT.equals("Security"))&& box.getX() > 0){
                    trPass.setToX(-180);
                }
                else if((passT.equals("Medical") || passT.equals("Support")|| passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(420);
                }
                break;
            
            case 7:
                if((passT.equals("Medical") || passT.equals("Support")|| passT.equals("Security"))&& box.getX() > 0){
                    trPass.setToX(-125);
                }
                else if((passT.equals("Medical") || passT.equals("Support")|| passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(505);
                }
                break;
            
            case 8 :
                if((passT.equals("Medical") || passT.equals("Support")|| passT.equals("Security"))&& box.getX() > 0){
                    trPass.setToX(-55);
                }
                else if((passT.equals("Medical") || passT.equals("Support")|| passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(575);
                }
                break;
                
              
            default:
                System.out.println("Couldnt find elevator number");
                break;
        }
        
        Random e = new Random();
        int dur = 1;
        
        trPass.setCycleCount(1);
        trPass.setDuration(Duration.seconds(dur));
        
        trPtype.setNode(passType);
        trPtype.setDuration(Duration.seconds(dur));
        trPtype.setToX(trPass.getToX());
        trPtype.setCycleCount(1);
        
        trPoints.setNode(startEnd);
        trPoints.setDuration(Duration.seconds(dur));
        trPoints.setToX(trPass.getToX());
        trPoints.setCycleCount(1);
        
        trPtype.play();
        trPoints.play();
        trPass.play();
       
    }
   
    public void buttonEffect(){//button effect
        
        spawnButton.setOnMouseReleased(event ->{
           spawnButton.setStyle("-fx-background-color: #0088ff;");
          
             spawnButton.setScaleX(1);
            spawnButton.setScaleY(1); 
        });
        
         spawnButton.setOnMousePressed(event ->{
            spawnButton.setStyle("-fx-background-color: #2e97f2;");
            
            spawnButton.setScaleX(0.90);
            spawnButton.setScaleY(0.90);
            
        });
    }

}
    
    
    
      
   


