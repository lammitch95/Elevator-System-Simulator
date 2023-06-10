
package com.mycompany.elevatorproject;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class TrafficGenerator {
    
    private ArrayList<ArrayList<Passengers>> floors = new ArrayList();
    private int start_floor;
    private int end_floor;
    private int gen_elv_num;
    private int med_elv_num;
    private int sec_elv_num;
    MedicalStaff ms;
    Patient p;
    SupportStaff sp; 
    Visitor v;
    SecurityStaff st;
   
    
     
    TrafficGenerator(){
        floors.add(new ArrayList<>());
        floors.add(new ArrayList<>());
        floors.add(new ArrayList<>());
        floors.add(new ArrayList<>());
        floors.add(new ArrayList<>());
        floors.add(new ArrayList<>());
        floors.add(new ArrayList<>());
        floors.add(new ArrayList<>()); 
    }
   
    
    
    public Passengers pass_Generator(int id_Counter){
     
    
     Passengers passenger = null;  
     ms = new MedicalStaff();
     p = new Patient();
     sp = new SupportStaff();
     v = new Visitor();
     st = new SecurityStaff();
    
        Random r = new Random();
        double n = r.nextDouble();
        start_floor = r.nextInt(8); 
        end_floor = r.nextInt(8);
        gen_elv_num = r.nextInt(5)+1;
        sec_elv_num = r.nextInt(8)+1;
        med_elv_num = r.nextInt(3)+6;
        
        while(start_floor == end_floor){
             end_floor = r.nextInt(8); 
        }
        
        if(n <  v.getPassProbability()){// .15 or 15% Visitor Condition n < 0.15
               
                v.setStartFloor(start_floor);
                v.setEndFloor(end_floor);
                v.setElv_Num(gen_elv_num); 
                v.setPassID(id_Counter);
                
                floors.get(start_floor).add(v);
                
                System.out.println(v.toString());
                
                passenger = v;
        }else if(n < v.getPassProbability() + p.getPassProbability()){// .25 or 25% Patient Condition n < 0.40
               
                p.setStartFloor(start_floor);
                p.setEndFloor(end_floor);
                p.setElv_Num(gen_elv_num);  
                p.setPassID(id_Counter);
                floors.get(start_floor).add(p);
                
                System.out.println(p.toString());
                
                passenger = p;
        
        }else if(n < v.getPassProbability() + p.getPassProbability()+sp.getPassProbability()){//.30 or 30% Support Staff Condition n < .70           
                
                sp.setStartFloor(start_floor);
                sp.setEndFloor(end_floor);
                sp.setElv_Num(med_elv_num);
                sp.setPassID(id_Counter);
                floors.get(start_floor).add(sp);
                
                System.out.println(sp.toString());
                
                passenger = sp; 
                
        }else if(n < v.getPassProbability() + p.getPassProbability()+sp.getPassProbability()  + ms.getPassProbability()){//.20 or 20% Medical Staff  Condition n < 0.90
               
                ms.setStartFloor(start_floor);
                ms.setEndFloor(end_floor);
                ms.setElv_Num(med_elv_num);
                ms.setPassID(id_Counter);
                floors.get(start_floor).add(ms);
                
                System.out.println(ms.toString());
                
                passenger = ms;
       
        }else{//Security Staff
                
                st.setStartFloor(start_floor);
                st.setEndFloor(end_floor);
                st.setElv_Num(sec_elv_num); 
                st.setPassID(id_Counter);
                floors.get(start_floor).add(st);
                
                System.out.println(st.toString());
                
                passenger = st;
                
        } 
        
        return passenger;
        
    }
  
    
    
    public Passengers pass_Generator2(boolean checkRandFloor, boolean checkRandType, String passType, int startFloorInput, int endFloorInput, int idCounter){
        
        System.out.println("pass_Generator2 method called");
        System.out.println("Randomize Floors: " + checkRandFloor);
        System.out.println("Randomize Type: "+ checkRandType);
        System.out.println("Custom Passenger Type: "+ passType);
        System.out.println("Custom StartFloor: " + startFloorInput);
        System.out.println("Custom EndFloor: " + endFloorInput);
        System.out.println("Passenger ID: "+ idCounter);
        
        Passengers passenger = null; 
        ms = new MedicalStaff();
        p = new Patient();
        sp = new SupportStaff();
        v = new Visitor();
        st = new SecurityStaff();
    
        Random r = new Random();
        double n = r.nextDouble();
        start_floor = r.nextInt(8); 
        end_floor = r.nextInt(8);
        gen_elv_num = r.nextInt(5)+1;
        sec_elv_num = r.nextInt(8)+1;
        med_elv_num = r.nextInt(3)+6;
        
        while(start_floor == end_floor){
             end_floor = r.nextInt(8); 
        }
        
        /*
        Conditions:
        if randomize floors and randomize type is true
        if randomize floors true and randmoize type is false
        if randmoize type is true and randmoize floor is false
        if both are false
        
        */
        
        
        if(checkRandFloor && !checkRandType){
            switch(passType){
                case "Security":
                    st.setStartFloor(start_floor);
                    st.setEndFloor(end_floor);
                    st.setElv_Num(sec_elv_num); 
                    st.setPassID(idCounter);
                    floors.get(start_floor).add(st);
                
                    System.out.println(st.toString());
                    
                    passenger = st;
                    break;
                    
                    
                case "Patient":
                    
                    p.setStartFloor(start_floor);
                    p.setEndFloor(end_floor);
                    p.setElv_Num(gen_elv_num);  
                    p.setPassID(idCounter);
                    floors.get(start_floor).add(p);
                
                    System.out.println(p.toString());
                    passenger = p;
                    break;
                    
                case "Visitor":
                    
                    v.setStartFloor(start_floor);
                    v.setEndFloor(end_floor);
                    v.setElv_Num(gen_elv_num); 
                    v.setPassID(idCounter);
                
                    floors.get(start_floor).add(v);
                
                    System.out.println(v.toString());
                    passenger = v;
                    break;
                 
                case "Medical":
                    
                    ms.setStartFloor(start_floor);
                    ms.setEndFloor(end_floor);
                    ms.setElv_Num(med_elv_num);
                    ms.setPassID(idCounter);
                    floors.get(start_floor).add(ms);
                
                    System.out.println(ms.toString());
                    passenger = ms;
                    break;
                    
                
                case "Support":
                    
                    sp.setStartFloor(start_floor);
                    sp.setEndFloor(end_floor);
                    sp.setElv_Num(med_elv_num);
                    sp.setPassID(idCounter);
                    floors.get(start_floor).add(sp);
                
                    System.out.println(sp.toString());
                    passenger = sp;
                    break;
                    
                 
                default:
                    System.out.println("NO PASSENGER TYPE EXIST");
                    break;
            }
        }
        
        if(!checkRandFloor && checkRandType){
            
            if(n <  v.getPassProbability()){// .15 or 15% Visitor Condition n < 0.15
               
                v.setStartFloor(startFloorInput);
                v.setEndFloor(endFloorInput);
                v.setElv_Num(gen_elv_num); 
                v.setPassID(idCounter);
                
                floors.get(startFloorInput).add(v);
                
                System.out.println(v.toString());
                passenger = v;
                
        }else if(n < v.getPassProbability() + p.getPassProbability()){// .25 or 25% Patient Condition n < 0.40
               
                p.setStartFloor(startFloorInput);
                p.setEndFloor(endFloorInput);
                p.setElv_Num(gen_elv_num);  
                p.setPassID(idCounter);
                floors.get(startFloorInput).add(p);
                
                System.out.println(p.toString());
                passenger = p;
                
        
        }else if(n < v.getPassProbability() + p.getPassProbability()+sp.getPassProbability() ){//.30 or 30% Support Staff Condition n < .70           
                
                sp.setStartFloor(startFloorInput);
                sp.setEndFloor(endFloorInput);
                sp.setElv_Num(med_elv_num);
                sp.setPassID(idCounter);
                floors.get(startFloorInput).add(sp);
                
                System.out.println(sp.toString());
                
                passenger = sp; 
                
        }else if(n < v.getPassProbability() + p.getPassProbability()+sp.getPassProbability()  + ms.getPassProbability()){//.20 or 20% Medical Staff  Condition n < 0.90
               
                ms.setStartFloor(startFloorInput);
                ms.setEndFloor(endFloorInput);
                ms.setElv_Num(med_elv_num);
                ms.setPassID(idCounter);
                floors.get(startFloorInput).add(ms);
                
                System.out.println(ms.toString());
                
                passenger = ms;
       
        }else{//Security Staff
                
                st.setStartFloor(startFloorInput);
                st.setEndFloor(endFloorInput);
                st.setElv_Num(sec_elv_num); 
                st.setPassID(idCounter);
                floors.get(startFloorInput).add(st);
                
                System.out.println(st.toString());
                
                passenger = st;
                
            } 
            
        }
        
        if(!checkRandFloor  && !checkRandType){
             switch(passType){
                case "Security":
                    st.setStartFloor(startFloorInput);
                    st.setEndFloor(endFloorInput);
                    st.setElv_Num(sec_elv_num); 
                    st.setPassID(idCounter);
                    floors.get(startFloorInput).add(st);
                
                    System.out.println(st.toString());
                    passenger = st;
                    break;
                    
                    
                case "Patient":
                    
                    p.setStartFloor(startFloorInput);
                    p.setEndFloor(endFloorInput);
                    p.setElv_Num(gen_elv_num);  
                    p.setPassID(idCounter);
                    floors.get(startFloorInput).add(p);
                
                    System.out.println(p.toString());
                    passenger = p;
                    break;
                
                       
                case "Visitor":
                    
                    v.setStartFloor(startFloorInput);
                    v.setEndFloor(endFloorInput);
                    v.setElv_Num(gen_elv_num); 
                    v.setPassID(idCounter);
                
                    floors.get(startFloorInput).add(v);
                
                    System.out.println(v.toString());
                    passenger = v;
                    break;
                 
                case "Medical":
                    
                    ms.setStartFloor(startFloorInput);
                    ms.setEndFloor(endFloorInput);
                    ms.setElv_Num(med_elv_num);
                    ms.setPassID(idCounter);
                    floors.get(startFloorInput).add(ms);
                
                    System.out.println(ms.toString());
                    passenger = ms;
                    break;
                
                case "Support":
                    
                    sp.setStartFloor(startFloorInput);
                    sp.setEndFloor(endFloorInput);
                    sp.setElv_Num(med_elv_num);
                    sp.setPassID(idCounter);
                    floors.get(startFloorInput).add(sp);
                
                    System.out.println(sp.toString());
                    passenger = sp;
                    break;
                    
                 
                default:
                    System.out.println("NO PASSENGER TYPE EXIST");
                    break;
            }
            
        }
        
        return passenger;
    }
    
     /**
     * @return the floors
     */
    public ArrayList<ArrayList<Passengers>> getFloors() {
        return floors;
    }

    /**
     * @param floors the floors to set
     */
    public void setFloors(ArrayList<ArrayList<Passengers>> floors) {
        this.floors = floors;
    }

}

