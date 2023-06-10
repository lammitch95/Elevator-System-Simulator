package com.mycompany.elevatorproject;

import java.util.*;
import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrimaryController extends TrafficGenerator{
    
    @FXML
    private AnchorPane stage;
    @FXML
    private Button spawnButton,spawnButton2;
    
    private ArrayList<Rectangle> elevatorGUI = new ArrayList();
    
    private int[] elevatorCurrentFloors = {-1,-1,-1,-1,-1,-1,-1,-1};
    
    private StatisticsKeeper sk = new StatisticsKeeper();
    private StatsKeeperPopUpController skGUI = new StatsKeeperPopUpController();
    @FXML
    private Label errorDisplay;
    private FadeTransition fade = new FadeTransition();
    @FXML
    private Rectangle genElev1,genElev2,genElev3,genElev4,genElev5,medElev1,medElev2,medElev3;
    @FXML
    private Rectangle base1,base2,base3,base4,base5,base6,base7,base8;
    private ArrayList<Rectangle> bases = new ArrayList();
    
    @FXML
    private Rectangle oneLeft,oneLeft2,oneLeft3,oneLeft4,oneLeft5,oneLeft6,oneLeft7,oneLeft8;
    @FXML
    private Rectangle oneRight,oneRight2,oneRight3,oneRight4,oneRight5,oneRight6,oneRight7,oneRight8;
    private ArrayList<Rectangle> genOne_DOORS = new ArrayList();
    @FXML
    private TextField autoPassAmount,intervalbox,startFloorGUI,endFloorGUI,passengerAmount;
    @FXML
    private CheckBox sec1,vis1,pat1,med1,sup1;
    
    
    private int amount,autoPass,interval,startFloorTextBox,endFloorTextBox,idCounter = sk.getLargestPassengerID();
    @FXML
    private RadioButton randFloors,randType;
    
    @FXML
    private Rectangle leftWall,rightWall,roof;
    
    @FXML
    private Label randFloorsLabel,randTypeLabel;
    
    
    
    private TranslateTransition trPass;
    @FXML
    private Rectangle twoLeft1,twoLeft2,twoLeft3,twoLeft4,twoLeft5,twoLeft6,twoLeft7,twoLeft8;
    @FXML
    private Rectangle twoRight1,twoRight2,twoRight3,twoRight4,twoRight5,twoRight6,twoRight7,twoRight8;
    private ArrayList<Rectangle> genTwo_DOORS = new ArrayList();
    @FXML
    private Button statsButton;
    @FXML
    private Label genOneCapGui,genTwoCapGui,genThreeCapGui,genFourCapGui,genFiveCapGui,medOneCapGui,medTwoCapGui,medThreeCapGui;
    @FXML
    private Label genOneCurrentFloorGui,genTwoCurrentFloorGui,genThreeCurrentFloorGui,genFourCurrentFloorGui,genFiveCurrentFloorGui,medOneCurrentFloorGui,medTwoCurrentFloorGui,medThreeCurrentFloorGui;
    @FXML
    private Rectangle threeRight1,threeRight2,threeRight3,threeRight4,threeRight5,threeRight6,threeRight7,threeRight8;
    @FXML
    private Rectangle threeLeft1,threeLeft2,threeLeft3,threeLeft4,threeLeft5,threeLeft6,threeLeft7,threeLeft8;
    private ArrayList<Rectangle> genThree_DOORS = new ArrayList();
    @FXML
    private Label countdownGUI;
    @FXML
    private Button clearDataButton;
    @FXML
    private Rectangle fourLeft1,fourLeft2,fourLeft3,fourLeft4,fourLeft5,fourLeft6,fourLeft7,fourLeft8;
    @FXML
    private Rectangle fourRight,fourRight2,fourRight3,fourRight4,fourRight5,fourRight6,fourRight7,fourRight8;
    @FXML
    private Rectangle fiveLeft,fiveLeft2,fiveLeft3,fiveLeft4,fiveLeft5,fiveLeft6,fiveLeft7,fiveLeft8;
     @FXML
    private Rectangle fiveRight,fiveRight2,fiveRight3,fiveRight4,fiveRight5,fiveRight6,fiveRight7,fiveRight8;
    @FXML
    private Rectangle sixLeft,sixLeft2,sixLeft3,sixLeft4,sixLeft5,sixLeft6,sixLeft7,sixLeft8;
    @FXML
    private Rectangle sixRight,sixRight2,sixRight3,sixRight4,sixRight5,sixRight6,sixRight7,sixRight8;
    @FXML
    private Rectangle sevenLeft,sevenLeft2,sevenLeft3,sevenLeft4,sevenLeft5,sevenLeft6,sevenLeft7,sevenLeft8;
    @FXML
    private Rectangle sevenRight,sevenRight2,sevenRight3,sevenRight4,sevenRight5,sevenRight6,sevenRight7,sevenRight8;
    @FXML
    private Rectangle eightLeft,eightLeft2,eightLeft3,eightLeft4,eightLeft5,eightLeft6,eightLeft7,eightLeft8;
    @FXML
    private Rectangle eightRight,eightRight2,eightRight3,eightRight4,eightRight5,eightRight6,eightRight7,eightRight8;
    private ArrayList<Rectangle> genFour_DOORS = new ArrayList();
    private ArrayList<Rectangle> genFive_DOORS = new ArrayList();
    private ArrayList<Rectangle> genSix_DOORS = new ArrayList();
    private ArrayList<Rectangle> genSeven_DOORS = new ArrayList();
    private ArrayList<Rectangle> genEight_DOORS = new ArrayList();
    
    
    private GeneralElevatorOne ge = new GeneralElevatorOne();
    private GeneralElevatorTwo ge2 = new GeneralElevatorTwo();
    private GeneralElevatorThree ge3 = new GeneralElevatorThree();
    private GeneralElevatorFour ge4 = new GeneralElevatorFour();
    private GeneralElevatorFive ge5 = new GeneralElevatorFive();
    private MedicalElevatorOne me = new MedicalElevatorOne();
    private MedicalElevatorTwo me2 = new MedicalElevatorTwo();
    private MedicalElevatorThree me3 = new MedicalElevatorThree();
    
    
    
    
    public void addItems(){
        
          errorDisplay.toFront();  
            
            if(elevatorGUI.size() != 8){
                elevatorGUI.add(genElev1);
                elevatorGUI.add(genElev2);
                elevatorGUI.add(genElev3);
                elevatorGUI.add(genElev4);
                elevatorGUI.add(genElev5); 
                elevatorGUI.add(medElev1);
                elevatorGUI.add(medElev2);
                elevatorGUI.add(medElev3);
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
            
            if(genOne_DOORS.size()!=16){
                
                
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
            
             if(genTwo_DOORS.size()!=16){
                
                
                genTwo_DOORS.add(twoLeft1);
                genTwo_DOORS.add(twoRight1);
                genTwo_DOORS.add(twoLeft2);
                genTwo_DOORS.add(twoRight2);
                genTwo_DOORS.add(twoLeft3);
                genTwo_DOORS.add(twoRight3);
                genTwo_DOORS.add(twoLeft4);
                genTwo_DOORS.add(twoRight4);
                genTwo_DOORS.add(twoLeft5);
                genTwo_DOORS.add(twoRight5);
                genTwo_DOORS.add(twoLeft6);
                genTwo_DOORS.add(twoRight6);
                genTwo_DOORS.add(twoLeft7);
                genTwo_DOORS.add(twoRight7);
                genTwo_DOORS.add(twoLeft8);
                genTwo_DOORS.add(twoRight8);
          
            }
             
             if(genThree_DOORS.size()!=16){
                
                
                genThree_DOORS.add(threeLeft1);
                genThree_DOORS.add(threeRight1);
                genThree_DOORS.add(threeLeft2);
                genThree_DOORS.add(threeRight2);
                genThree_DOORS.add(threeLeft3);
                genThree_DOORS.add(threeRight3);
                genThree_DOORS.add(threeLeft4);
                genThree_DOORS.add(threeRight4);
                genThree_DOORS.add(threeLeft5);
                genThree_DOORS.add(threeRight5);
                genThree_DOORS.add(threeLeft6);
                genThree_DOORS.add(threeRight6);
                genThree_DOORS.add(threeLeft7);
                genThree_DOORS.add(threeRight7);
                genThree_DOORS.add(threeLeft8);
                genThree_DOORS.add(threeRight8);
          
            }
             
             if(genFour_DOORS.size()!=16){
                
                
                genFour_DOORS.add(fourLeft1);
                genFour_DOORS.add(fourRight);
                genFour_DOORS.add(fourLeft2);
                genFour_DOORS.add(fourRight2);
                genFour_DOORS.add(fourLeft3);
                genFour_DOORS.add(fourRight3);
                genFour_DOORS.add(fourLeft4);
                genFour_DOORS.add(fourRight4);
                genFour_DOORS.add(fourLeft5);
                genFour_DOORS.add(fourRight5);
                genFour_DOORS.add(fourLeft6);
                genFour_DOORS.add(fourRight6);
                genFour_DOORS.add(fourLeft7);
                genFour_DOORS.add(fourRight7);
                genFour_DOORS.add(fourLeft8);
                genFour_DOORS.add(fourRight8);
          
            }
             
              if(genFive_DOORS.size()!=16){
                
                
                genFive_DOORS.add(fiveLeft);
                genFive_DOORS.add(fiveRight);
                genFive_DOORS.add(fiveLeft2);
                genFive_DOORS.add(fiveRight2);
                genFive_DOORS.add(fiveLeft3);
                genFive_DOORS.add(fiveRight3);
                genFive_DOORS.add(fiveLeft4);
                genFive_DOORS.add(fiveRight4);
                genFive_DOORS.add(fiveLeft5);
                genFive_DOORS.add(fiveRight5);
                genFive_DOORS.add(fiveLeft6);
                genFive_DOORS.add(fiveRight6);
                genFive_DOORS.add(fiveLeft7);
                genFive_DOORS.add(fiveRight7);
                genFive_DOORS.add(fiveLeft8);
                genFive_DOORS.add(fiveRight8);
          
            }
              
               if(genSix_DOORS.size()!=16){
                
                
                genSix_DOORS.add(sixLeft);
                genSix_DOORS.add(sixRight);
                genSix_DOORS.add(sixLeft2);
                genSix_DOORS.add(sixRight2);
                genSix_DOORS.add(sixLeft3);
                genSix_DOORS.add(sixRight3);
                genSix_DOORS.add(sixLeft4);
                genSix_DOORS.add(sixRight4);
                genSix_DOORS.add(sixLeft5);
                genSix_DOORS.add(sixRight5);
                genSix_DOORS.add(sixLeft6);
                genSix_DOORS.add(sixRight6);
                genSix_DOORS.add(sixLeft7);
                genSix_DOORS.add(sixRight7);
                genSix_DOORS.add(sixLeft8);
                genSix_DOORS.add(sixRight8);
          
            }
               
                if(genSeven_DOORS.size()!=16){
                
                
                genSeven_DOORS.add(sevenLeft);
                genSeven_DOORS.add(sevenRight);
                genSeven_DOORS.add(sevenLeft2);
                genSeven_DOORS.add(sevenRight2);
                genSeven_DOORS.add(sevenLeft3);
                genSeven_DOORS.add(sevenRight3);
                genSeven_DOORS.add(sevenLeft4);
                genSeven_DOORS.add(sevenRight4);
                genSeven_DOORS.add(sevenLeft5);
                genSeven_DOORS.add(sevenRight5);
                genSeven_DOORS.add(sevenLeft6);
                genSeven_DOORS.add(sevenRight6);
                genSeven_DOORS.add(sevenLeft7);
                genSeven_DOORS.add(sevenRight7);
                genSeven_DOORS.add(sevenLeft8);
                genSeven_DOORS.add(sevenRight8);
          
            }
                
                 if(genEight_DOORS.size()!=16){
                
                
                genEight_DOORS.add(eightLeft);
                genEight_DOORS.add(eightRight);
                genEight_DOORS.add(eightLeft2);
                genEight_DOORS.add(eightRight2);
                genEight_DOORS.add(eightLeft3);
                genEight_DOORS.add(eightRight3);
                genEight_DOORS.add(eightLeft4);
                genEight_DOORS.add(eightRight4);
                genEight_DOORS.add(eightLeft5);
                genEight_DOORS.add(eightRight5);
                genEight_DOORS.add(eightLeft6);
                genEight_DOORS.add(eightRight6);
                genEight_DOORS.add(eightLeft7);
                genEight_DOORS.add(eightRight7);
                genEight_DOORS.add(eightLeft8);
                genEight_DOORS.add(eightRight8);
          
            }
                 
             
        
    }
  
    @FXML
    private void spawnBtn(ActionEvent event){
          
            //buttonEffect();
            addItems();
            Passengers p1;
            CheckBox[] passengerCheckBox  = {sec1,vis1,pat1,med1,sup1}; 
            fade.setNode(errorDisplay);
            
            try{
                    amount = Integer.parseInt(passengerAmount.getText());
                    System.out.println("Passengers Amount created:" + amount);
                    errorDisplay.setText("Passengers have been Generated...");
                    errorDisplay.setStyle("-fx-text-fill: #46e38f");
                    
                    fade.setDuration(Duration.seconds(2));
                    fade.setCycleCount(1);
                    fade.setInterpolator(Interpolator.LINEAR);
                    fade.setFromValue(1);
                    fade.setToValue(0);
                    fade.play();
                     /*
                    for(int i = 0; i < amount; i++){
                        idCounter++;
                        Passengers p1 = pass_Generator(idCounter);
                        passGenerator(p1);
                        
                    }
                    */
                    while(amount > 0 ){
                        
                        if(randType.isSelected()){
                            
                            if(randFloors.isSelected()){
                                idCounter++;
                                 p1 = pass_Generator(idCounter);
                                passGenerator(p1);
                                
                            }else{
                                try{
                                    startFloorTextBox = Integer.parseInt(startFloorGUI.getText());
                                    endFloorTextBox = Integer.parseInt(endFloorGUI.getText());
                                    
                                    if(startFloorTextBox == endFloorTextBox|| (startFloorTextBox < 0 && startFloorTextBox > 7) || (endFloorTextBox < 0 && endFloorTextBox > 7)){
                                        errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                                        errorDisplay.setText("ERROR! StartFloor or EndFloor Input...");
                                        System.out.println("ERROR! StartFloor or EndFloor Input...");
                                        fade.setDuration(Duration.seconds(2));
                                        fade.setCycleCount(1);
                                        fade.setInterpolator(Interpolator.LINEAR);
                                        fade.setFromValue(1);
                                        fade.setToValue(0);
                                        fade.play();
                                    
                                    }else{
                                        idCounter++;
                                         p1 = pass_Generator2(false,true,null,startFloorTextBox,endFloorTextBox,idCounter);
                                         passGenerator(p1);
                                         
                                        }
                                }catch(NumberFormatException e){
                                        errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                                        errorDisplay.setText("ERROR! Custom Floors Not Selected...");
                                        System.out.println("ERROR! Custom Floors Not Selected...");
                                        fade.setDuration(Duration.seconds(2));
                                        fade.setCycleCount(1);
                                        fade.setInterpolator(Interpolator.LINEAR);
                                        fade.setFromValue(1);
                                        fade.setToValue(0);
                                        fade.play();
                                    
                                    
                                    
                                }
                                
                            }
                            
                        }else{
                            boolean check = false;
                            for(int i = 0; i < passengerCheckBox.length; i++){
                            
                                if(passengerCheckBox[i].isSelected()){
                                    check = true;
                                    
                                     if(randFloors.isSelected()){
                                         switch(i){
                                             case 0:
                                                 idCounter++;
                                                 p1 = pass_Generator2(true,false,"Security",-1,-1,idCounter);
                                                 passGenerator(p1);
                                                 
                                                 break;
                                             case 1:
                                                 idCounter++;
                                                  p1 =pass_Generator2(true,false,"Visitor",-1,-1,idCounter);
                                                  passGenerator(p1);
                                                  
                                                 break;
                                             case 2:
                                                 idCounter++;
                                                  p1 = pass_Generator2(true,false,"Patient",-1,-1,idCounter);
                                                  passGenerator(p1);
                                                  
                                                  break;
                                             case 3:
                                                 idCounter++;
                                                  p1 = pass_Generator2(true,false,"Medical",-1,-1,idCounter);
                                                  passGenerator(p1);
                                                  
                                                  break;
                                             case 4:
                                                 idCounter++;
                                                  p1 = pass_Generator2(true,false,"Support",-1,-1,idCounter);
                                                  passGenerator(p1);
                                                  
                                                  break;
                                             default:
                                                 System.out.println("Error finding passenger Type");
                                                 break;
                                                 
                                         }
                                        
                                    }else{
                                         try{
                                              startFloorTextBox = Integer.parseInt(startFloorGUI.getText());
                                              endFloorTextBox = Integer.parseInt(endFloorGUI.getText());
                                              
                                              if(startFloorTextBox == endFloorTextBox|| (startFloorTextBox < 0 && startFloorTextBox > 7) || (endFloorTextBox < 0 && endFloorTextBox > 7)){
                                                    errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                                                    errorDisplay.setText("ERROR! StartFloor or EndFloor Input...");
                                                    System.out.println("ERROR! StartFloor or EndFloor Input...");
                                                    fade.setDuration(Duration.seconds(2));
                                                    fade.setCycleCount(1);
                                                    fade.setInterpolator(Interpolator.LINEAR);
                                                    fade.setFromValue(1);
                                                    fade.setToValue(0);
                                                    fade.play();
                                    
                                           }else{
                                             switch(i){
                                             case 0:
                                                 idCounter++;
                                                 p1 = pass_Generator2(false,false,"Security",startFloorTextBox,endFloorTextBox,idCounter);
                                                 passGenerator(p1);
                                                
                                                break;
                                             case 1:
                                                 idCounter++;
                                                 p1 =  pass_Generator2(false,false,"Visitor",startFloorTextBox,endFloorTextBox,idCounter);
                                                 passGenerator(p1);
                                               
                                                break;
                                             case 2:
                                                 idCounter++;
                                                 p1 =  pass_Generator2(false,false,"Patient",startFloorTextBox,endFloorTextBox,idCounter);
                                                 passGenerator(p1);
                                            
                                                break;
                                             case 3:
                                                 idCounter++;
                                                 p1 = pass_Generator2(false,false,"Medical",startFloorTextBox,endFloorTextBox,idCounter);
                                                 passGenerator(p1);
                                               
                                                break;
                                             case 4:
                                                 idCounter++;
                                                 p1 = pass_Generator2(false,false,"Support",startFloorTextBox,endFloorTextBox,idCounter);
                                                 passGenerator(p1);
                                               
                                                break;
                                             default:
                                                 System.out.println("Error finding passenger Type");
                                                 break;
                                                 
                                            }
                                        }
                                              
                                         }catch(NumberFormatException e){
                                             
                                                    errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                                                    errorDisplay.setText("ERROR! StartFloor or EndFloor Input...");
                                                    System.out.println("ERROR! StartFloor or EndFloor Input...");
                                                    fade.setDuration(Duration.seconds(2));
                                                    fade.setCycleCount(1);
                                                    fade.setInterpolator(Interpolator.LINEAR);
                                                    fade.setFromValue(1);
                                                    fade.setToValue(0);
                                                    fade.play();
                                             
                                         }
                                        
                                    }
                                
                                }
                            }
                            
                            if(check == false){
                                errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                                errorDisplay.setText("ERROR! No Passenger Type Selected...");
                                System.out.println("ERROR! No Passenger Type Selected...");
                                fade.setDuration(Duration.seconds(2));
                                fade.setCycleCount(1);
                                fade.setInterpolator(Interpolator.LINEAR);
                                fade.setFromValue(1);
                                fade.setToValue(0);
                                fade.play();
                                
                            }
                            
                        }
                        
                        amount--;
                    }
                    
                    
                    
            
            }catch(NumberFormatException e){
                System.out.println(amount);
                errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                errorDisplay.setText("ERROR! Passenger Amount Input...");
                System.out.println("Please enter only Integers.");
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
         fade.setNode(errorDisplay);  
        if(spawnButton2.getText().equals("OFF")){
            
                spawnButton2.setText("ON");
                spawnButton2.setStyle("-fx-background-color: #0be31a");
                System.out.println("Auto Passenger Generator is ON.");
                errorDisplay.setText("Auto Passenger Generator is ON");
                errorDisplay.setStyle("-fx-text-fill: #46e38f");
                
                
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
            countdownGUI.setText("");
        }else{
              try{
                    autoPass = Integer.parseInt(autoPassAmount.getText());
                    System.out.println("Auto Generate Passenger Amount: " + autoPass);
                    interval = Integer.parseInt(intervalbox.getText());
                    System.out.println("Interval Input: " + interval);
                    Passengers passenger = null;
                    if((autoPass > 0 && autoPass <= 20) && (interval > 0 && interval <= 5)){
                         Timer timer = new Timer();
                         timer.scheduleAtFixedRate(new TimerTask(){
                            int count = interval+1;
                            
                            public void run(){
                        
                            System.out.println(count);
                            
                            Platform.runLater(() -> {
                                countdownGUI.setText(count+"...");
                    
                            });
                            
                            count--;
                            if(spawnButton2.getText().equals("OFF")){
                                
                                System.out.println("Auto Passenger Generator is OFF.");  
                                Platform.runLater(() -> {
                                countdownGUI.setText("");
                    
                                });
                                timer.cancel();
                            }else if(count <= 0){
                           
                            timer.cancel(); 
                            System.out.println("GENERATING PASSENGERS");
                            Platform.runLater(() -> {
                            //Random r = new Random();
                            //int amount = autoPass;//r.nextInt(10)+1;
                            for(int i = 0; i < autoPass; i++){
                                idCounter++;
                                Passengers p1 = pass_Generator(idCounter);
                                
                                passGenerator(p1);
                                
                                
                            }
                            trPass.setOnFinished(eh ->{
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
                    
            
                    
            }catch(NumberFormatException e){
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
    
    public void passGenerator(Passengers newp){
        leftWall.toFront();
        rightWall.toFront();
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
        
            
             switch(newp.getStartFloor()){
                 case 0: 
                    box.setY(485);
                    break;
                 case 1:
                     box.setY(420);
                     break;
                 case 2:
                     box.setY(355);
                     break;
                 case 3:
                     box.setY(290);
                     break;
                 case 4:
                     box.setY(225);
                     break;
                 case 5:
                     box.setY(160);
                     break;
                 case 6:
                     box.setY(95);
                     break;
                 case 7:
                     box.setY(30);
                     break;
                 default:
                     System.out.println("Error finding spawn");
                     break;    
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
                    trPass.setToX(r.nextInt(3)+132);
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
                if((passT.equals("Medical") || passT.equals("Support")|| passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(420);
                }
                
                break;
            
            case 7:
                 if((passT.equals("Medical") || passT.equals("Support")|| passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(505);
                }
                break;
            
            case 8 :
                 if((passT.equals("Medical") || passT.equals("Support")|| passT.equals("Security"))&& box.getX() < 0){
                    trPass.setToX(575);
                }
                break;
                
              
            default:
                System.out.println("Couldnt find elevator number");
                break;
        }
        
        
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
       
        leftWall.toFront();
        rightWall.toFront();
        
        
        
        trPtype.setOnFinished(eh ->{
            
            sk.updateDatabase(newp);
            
            System.out.println("TEST");
            switch(newp.elv_num){
                case 1:
                    if(ge.isGeneralElevatorOneCall()){
                        
                        elevatorCurrentFloors[0] = 0;
                        genOneCapGui.setText("0/12");
                        genOneCurrentFloorGui.setText("0");
                        ge.upRequests(getFloors(),elevatorCurrentFloors,elevatorGUI,bases,genOne_DOORS,genOneCapGui,genOneCurrentFloorGui,leftWall,rightWall,errorDisplay);
                        ge.setGeneralElevatorOneCall(false);
                    }
                    break;
                case 2:
                    if(ge2.isGeneralElevatorTwoCall()){
                        
                        elevatorCurrentFloors[1] = 0;
                        genTwoCapGui.setText("0/12");
                        genTwoCurrentFloorGui.setText("0");
                        ge2.upRequests(getFloors(),elevatorCurrentFloors,elevatorGUI,bases,genTwo_DOORS,genTwoCapGui,genTwoCurrentFloorGui,leftWall,rightWall,errorDisplay);
                        ge2.setGeneralElevatorTwoCall(false);
                    }
                    break;
                case 3:
                    if(ge3.isGeneralElevatorThreeCall()){
                        
                        elevatorCurrentFloors[2] = 0;
                        genThreeCapGui.setText("0/12");
                        genThreeCurrentFloorGui.setText("0");
                        ge3.upRequests(getFloors(),elevatorCurrentFloors,elevatorGUI,bases,genThree_DOORS,genThreeCapGui,genThreeCurrentFloorGui,leftWall,rightWall,errorDisplay);
                        ge3.setGeneralElevatorThreeCall(false);
                    }
                    break;
                    
                case 4:
                    if(ge4.isGeneralElevatorFourCall()){
                        
                        elevatorCurrentFloors[3] = 0;
                        genFourCapGui.setText("0/12");
                        genFourCurrentFloorGui.setText("0");
                        ge4.upRequests(getFloors(),elevatorCurrentFloors,elevatorGUI,bases,genFour_DOORS,genFourCapGui,genFourCurrentFloorGui,leftWall,rightWall,errorDisplay);
                        ge4.setGeneralElevatorFourCall(false);
                    }
                    break;
                case 5:
                    if(ge5.isGeneralElevatorFiveCall()){
                        
                        elevatorCurrentFloors[4] = 0;
                        genFiveCapGui.setText("0/12");
                        genFiveCurrentFloorGui.setText("0");
                        ge5.upRequests(getFloors(),elevatorCurrentFloors,elevatorGUI,bases,genFive_DOORS,genFiveCapGui,genFiveCurrentFloorGui,leftWall,rightWall,errorDisplay);
                        ge5.setGeneralElevatorFiveCall(false);
                    }
                    break;
                case 6:
                     if(me.isMedicalElevatorOneCall()){
                        
                        elevatorCurrentFloors[5] = 0;
                        medOneCapGui.setText("0/4");
                        medOneCurrentFloorGui.setText("0");
                        me.upRequests(getFloors(),elevatorCurrentFloors,elevatorGUI,bases,genSix_DOORS,medOneCapGui,medOneCurrentFloorGui,leftWall,rightWall,errorDisplay);
                        me.setMedicalElevatorOneCall(false);
                    }
                    break;
                case 7:
                    if(me2.isMedicalElevatorTwoCall()){
                        
                        elevatorCurrentFloors[6] = 0;
                        medTwoCapGui.setText("0/4");
                        medTwoCurrentFloorGui.setText("0");
                        me2.upRequests(getFloors(),elevatorCurrentFloors,elevatorGUI,bases,genSeven_DOORS,medTwoCapGui,medTwoCurrentFloorGui,leftWall,rightWall,errorDisplay);
                        me2.setMedicalElevatorTwoCall(false);
                    }
                    break;
                case 8:
                    if(me3.isMedicalElevatorThreeCall()){
                        
                        elevatorCurrentFloors[7] = 0;
                        medThreeCapGui.setText("0/4");
                        medThreeCurrentFloorGui.setText("0");
                        me3.upRequests(getFloors(),elevatorCurrentFloors,elevatorGUI,bases,genEight_DOORS,medThreeCapGui,medThreeCurrentFloorGui,leftWall,rightWall,errorDisplay);
                        me3.setMedicalElevatorThreeCall(false);
                    }
                    break;
                default:
                    System.out.println("No Elevator Exist");
                    break;

            }
            
            
        });
            
        
        
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
        
       
        if(randType.isSelected()&&sec1.isSelected()){
            randTypeLabel.setText("");
            randType.setSelected(false);
        }
        
    }

    @FXML
    private void visitorBOX(ActionEvent event) {
        
        if(vis1.isSelected()){
            vis1.setSelected(true);
            
        }else{
            vis1.setSelected(false);
        }
        
       
        if(randType.isSelected()&&vis1.isSelected()){
            randTypeLabel.setText("");
            randType.setSelected(false);
        }
        
        
    }

    @FXML
    private void patientBOX(ActionEvent event) {
        if(pat1.isSelected()){
            pat1.setSelected(true);
            
        }else{
            pat1.setSelected(false);
        }
        
       
        if(randType.isSelected()&&pat1.isSelected()){
            randTypeLabel.setText("");
            randType.setSelected(false);
        }
    }

    @FXML
    private void medicalBOX(ActionEvent event) {
        if(med1.isSelected()){
            med1.setSelected(true);
            
        }else{
            med1.setSelected(false);
        }
        
       
        if(randType.isSelected()&&med1.isSelected()){
            randTypeLabel.setText("");
            randType.setSelected(false);
        }
    }

    @FXML
    private void supportBOX(ActionEvent event) {
        if(sup1.isSelected()){
            sup1.setSelected(true);
            
        }else{
            sup1.setSelected(false);
        }
        
       
        if(randType.isSelected()&&sup1.isSelected()){
            randTypeLabel.setText("");
            randType.setSelected(false);
        }
    }

    @FXML
    private void randFloors_BOX(ActionEvent event) {
       
        if(randFloors.isSelected()){
            randFloorsLabel.setText("Randomize Floors (Active)");
            
        }else{
            randFloorsLabel.setText("");
        }
       
    }

    @FXML
    private void randType_BOX(ActionEvent event) {
        
         CheckBox[] passengerCheckBox  = {sec1,vis1,pat1,med1,sup1}; 
        if(randType.isSelected()){
            randType.setSelected(true);
             for(int i = 0; i < passengerCheckBox.length; i++){
                if(passengerCheckBox[i].isSelected()){
                    passengerCheckBox[i].setSelected(false);
                    
                }
            }
           randTypeLabel.setText("Randomize Type (Active)");
        }else{
            randTypeLabel.setText("");
            randType.setSelected(false);
        }
    }

    @FXML
    private void statsBtn(ActionEvent event) {
       
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatsKeeperPopUp.fxml"));
            Parent popupContent;
            popupContent = loader.load();
            Stage popupStage = new Stage();

            popupStage.setScene(new Scene(popupContent));
            popupStage.initModality(Modality.APPLICATION_MODAL);
            double popupWidth = 620; 
            double popupHeight = 500; 

            popupStage.setWidth(popupWidth);
            popupStage.setHeight(popupHeight);
            popupStage.setResizable(false);
            popupStage.show();
            
            skGUI = loader.getController();
            sk.displayTopTenTravelTime(skGUI.getLabelList());
            
            System.out.println("Displaying StatisticKeeper");
            
            
        
            sk.barChart(skGUI.getPassengerAmountChart());
            
        } catch (Exception e) {
            System.out.println("NO fxml file exist");
            e.printStackTrace();
        }

    

    }

    @FXML
    private void clearDataBtn(ActionEvent event) {
        
        boolean databaseCheck = sk.clearDB(false);
        fade.setNode(errorDisplay);
        errorDisplay.toFront();
        if(databaseCheck){
            
                    errorDisplay.setText("Data has been Cleared...");
                    errorDisplay.setStyle("-fx-text-fill: #46e38f");
                    idCounter= 0;
                    fade.setDuration(Duration.seconds(2));
                    fade.setCycleCount(1);
                    fade.setInterpolator(Interpolator.LINEAR);
                    fade.setFromValue(1);
                    fade.setToValue(0);
                    fade.play();
            
        }else{
            
                    errorDisplay.setText("Error Deleteing Data...");
                    errorDisplay.setStyle("-fx-text-fill: #fc3d03");
                    
                    fade.setDuration(Duration.seconds(2));
                    fade.setCycleCount(1);
                    fade.setInterpolator(Interpolator.LINEAR);
                    fade.setFromValue(1);
                    fade.setToValue(0);
                    fade.play();
            
        }
        
        
        
    }
    
    


}
    
    
    
      
   


