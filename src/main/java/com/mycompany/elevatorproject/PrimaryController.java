package com.mycompany.elevatorproject;

import java.util.*;
import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
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
    
    private int age;
    private Label errorText;
    @FXML
    private Button spawnButton2;
    
    @FXML
    private Label errorDisplay;
    private FadeTransition fade;
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
    @FXML
    private TextField autoPassAmount;
    @FXML
    private CheckBox sec1;
    @FXML
    private CheckBox vis1;
    @FXML
    private CheckBox pat1;
    @FXML
    private CheckBox med1;
    @FXML
    private CheckBox sup1;
    @FXML
    private TextField intervalbox;
    private int autoPass;
    private int interval;
    @FXML
    private RadioButton randFloors;
    
    
        
    public void addItems(){
        
            
            
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
                    if(ge.getIsGenerator()==true){
                        elevators();
                        ge.setIsGenerator(false);
                        
                    }
                    });
            }
            catch(NumberFormatException e){
                errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                errorDisplay.setText("ERROR! Please Enter Only Integers...");
                System.out.println("Please enter only Integers.");
                fade = new FadeTransition();
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
            
                spawnButton2.setText("ON");
                spawnButton2.setStyle("-fx-background-color: #0be31a");
                System.out.println("Auto Passenger Generator is ON.");
                errorDisplay.setText("Auto Passenger Generator is ON");
                errorDisplay.setStyle("-fx-text-fill: #46e38f");
                fade = new FadeTransition();
                fade.setNode(errorDisplay);
                fade.setDuration(Duration.seconds(2));
                fade.setCycleCount(1);
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();
                passGenerator_LOOP();      
        }
        else{
            
            spawnButton2.setText("OFF");
            spawnButton2.setStyle("-fx-background-color:   #bf1b1b");
            System.out.println("Auto Passenger Generator is OFF");
            errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                errorDisplay.setText("Auto Passenger Generator is OFF.");
                fade = new FadeTransition();
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
              try{
                    autoPass = Integer.parseInt(autoPassAmount.getText());
                    System.out.println("Auto Generate Passenger Amount: " + autoPass);
                    interval = Integer.parseInt(intervalbox.getText());
                    System.out.println("Interval Input: " + interval);
                    
                    if((autoPass > 0 && autoPass <= 20) && (interval > 0 && interval <= 5)){
                         Timer timer = new Timer();
                         timer.scheduleAtFixedRate(new TimerTask(){
                            int count = interval;
                            public void run(){
                        
                            System.out.println(count);
                            count--;
                       
                       
                            if(count <= 0){
                           
                            timer.cancel(); 
                            System.out.println("GENERATING PASSENGERS");
                            Platform.runLater(() -> {
                            //Random r = new Random();
                            int amount = autoPass;//r.nextInt(10)+1;
                            for(int i = 0; i < amount; i++){
                
                                passID++;
       
                                Passengers p1 = pass_Generator(passID);
                                passGenerator(p1);
                 
                            }
                            trPass.setOnFinished(finish ->{
                                if(ge.getIsGenerator() == true){ 
                                    elevators();
                                    ge.setIsGenerator(false);
              
                                }
                                passGenerator_LOOP();
                            });
                          });
                            
                       }
                    }
                },0,1000);
                    }else{
                        spawnButton2.setText("OFF");
                        spawnButton2.setStyle("-fx-background-color:   #bf1b1b");
                        System.out.println("Auto Passenger Generator is OFF");
                        errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                        errorDisplay.setText("ERROR! One or More Textfield Input is Invalid");
                        System.out.println("Please enter only Integers.");
                        fade = new FadeTransition();
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
                spawnButton2.setText("OFF");
                spawnButton2.setStyle("-fx-background-color:   #bf1b1b");
                System.out.println("Auto Passenger Generator is OFF");
                errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                errorDisplay.setText("ERROR! One or More Textfield Input is Invalid");
                System.out.println("Please enter only Integers.");
                fade = new FadeTransition();
                fade.setNode(errorDisplay);
                fade.setDuration(Duration.seconds(2));
                fade.setCycleCount(1);
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();
                
            }  
           
            
               
           
              
              

         
            
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
                        ge.genOne_UP_REQUESTS(getFloors(),gen_currentFloors,genElev,bases,genOne_DOORS);
                        break;
                    
                    case 2:
                        System.out.println("General Elevator 1 has Requests");
                        
                        break;
                        
                }
            }else{
                System.out.println("No Request for Elevator number: " + (i+1));
            }
            
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

    @FXML
    private void securityBOX(ActionEvent event) {
         if(sec1.isSelected()){
            sec1.setSelected(true);
            
        }else{
            sec1.setSelected(false);
        }
        
       
        if(randFloors.isSelected()&&sec1.isSelected()){
            randFloors.setSelected(false);
        }
        
    }

    @FXML
    private void visitorBOX(ActionEvent event) {
        
        if(vis1.isSelected()){
            vis1.setSelected(true);
            
        }else{
            vis1.setSelected(false);
        }
        
       
        if(randFloors.isSelected()&&vis1.isSelected()){
            randFloors.setSelected(false);
        }
        
        
    }

    @FXML
    private void patientBOX(ActionEvent event) {
        if(pat1.isSelected()){
            pat1.setSelected(true);
            
        }else{
            pat1.setSelected(false);
        }
        
       
        if(randFloors.isSelected()&&pat1.isSelected()){
            randFloors.setSelected(false);
        }
    }

    @FXML
    private void medicalBOX(ActionEvent event) {
        if(med1.isSelected()){
            med1.setSelected(true);
            
        }else{
            med1.setSelected(false);
        }
        
       
        if(randFloors.isSelected()&&med1.isSelected()){
            randFloors.setSelected(false);
        }
    }

    @FXML
    private void supportBOX(ActionEvent event) {
        if(sup1.isSelected()){
            sup1.setSelected(true);
            
        }else{
            sup1.setSelected(false);
        }
        
       
        if(randFloors.isSelected()&&sup1.isSelected()){
            randFloors.setSelected(false);
        }
    }

    @FXML
    private void randFloors_BOX(ActionEvent event) {
        CheckBox[] chkBox = {sec1,vis1,pat1,med1,sup1};
        if(randFloors.isSelected()){
            randFloors.setSelected(true);
             for(int i = 0; i < chkBox.length; i++){
                if(chkBox[i].isSelected()){
                    chkBox[i].setSelected(false);
                    
                }
            }
            
        }else{
            randFloors.setSelected(false);
        }
        
       
    }


}
    
    
    
      
   


