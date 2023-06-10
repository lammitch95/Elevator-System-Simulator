
package com.mycompany.elevatorproject;

import java.sql.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
public class StatisticsKeeper {
    
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    
    StatisticsKeeper(){
        
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/lammi/OneDrive/Documents/NetBeansProjects/ElevatorProject/PassengerDatabase");
            System.out.println("Connected to Database Success");
            
            
        }catch(Exception e){
            System.out.println("Failed Connection to Database" + e);
            
        }
        
    }
    
    public double calculateTravelTime(double n){
        double currentTimeMillis = System.currentTimeMillis();
        return (currentTimeMillis - n)/1000.0;
        
    }
    
    public void updatTravelTimeData(Passengers p){
        try {

            String sql = "UPDATE PassengersInfo SET \"TravelTime(seconds)\" = ? WHERE PassengerID = ?";
            statement = connection.prepareStatement(sql);


            statement.setDouble(1, p.getTravelTime());
            statement.setInt(2,p.getPassID());
            


            int affectedRows = statement.executeUpdate();


            if (affectedRows > 0) {
                System.out.println("Passenger travel time updated successfully.");
            } else {
                System.out.println("Passenger with ID " + p.getPassID() + " not found.");
            }
            
        } catch (SQLException e) {
           System.out.println("Error occured When Updating Passengers Travel Time in Database");
           e.printStackTrace();
        }
    }
    
     public void updateDatabase(Passengers p) {
        try {

            String sql = "INSERT INTO PassengersInfo (PassengerID, PassengerType, ElevatorNumber, StartingFloor, DestinationFloor, \"TravelTime(seconds)\") VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);


            statement.setInt(1, p.getPassID());
            statement.setString(2, p.getPassType());
            statement.setInt(3, p.getElv_Num());
            statement.setInt(4, p.getStartFloor());
            statement.setInt(5, p.getEndFloor());
            statement.setDouble(6, p.getTravelTime());


            statement.executeUpdate();


            statement.close();
            System.out.println(p.toString()+ " was added to Database");
        } catch (SQLException e) {
           System.out.println("Error occured When Updating Database");
           e.printStackTrace();
        }
}
 
 public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred when closing the database connection");
            e.printStackTrace();
        }
    }
 
  public int getLargestPassengerID() {
        int largestPassengerID = 0;
        try {
            String sql = "SELECT MAX(PassengerID) FROM PassengersInfo";
            PreparedStatement statement = connection.prepareStatement(sql);
             resultSet = statement.executeQuery();
            if (resultSet.next()) {
                largestPassengerID = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error occurred when retrieving the largest Passenger ID");
            e.printStackTrace();
        }
        return largestPassengerID;
    }
  
  
  public void displayTopTenTravelTime(Label[] labelList){
       
        int passengerIdData;
        String passengerTypeData;
        int elevatorNumData;
        int startFloorData;
        int endFloorData;
        double travelTimeData;
        int count = 0;
         try{      
             String sql = "SELECT * FROM PassengersInfo WHERE  \"TravelTime(seconds)\" > 0 ORDER BY \"TravelTime(seconds)\" LIMIT 10";
             PreparedStatement statement2 = connection.prepareStatement(sql);
             ResultSet resultSet2 = statement2.executeQuery();

            while (resultSet2.next()) {
                passengerIdData = resultSet2.getInt("PassengerID");
                passengerTypeData = resultSet2.getString("PassengerType");
                elevatorNumData = resultSet2.getInt("ElevatorNumber");
                startFloorData = resultSet2.getInt("StartingFloor");
                endFloorData = resultSet2.getInt("DestinationFloor");
                travelTimeData = resultSet2.getDouble("TravelTime(seconds)");
                
                
                
                
                labelList[count].setText((count+1)+". ID: "+passengerIdData+"|"+passengerTypeData+"|"+startFloorData+"=>"+endFloorData+"|Elevator#: "+elevatorNumData+"|Time: "+travelTimeData+" sec");
                count++;
                
                
            }
            
           
        } catch (SQLException e) {
            System.out.println("Error Displaying Top 10 Leaderboard");
            e.printStackTrace();
        }

  }
  
  public boolean clearDB(boolean check){
      try{
          String sql = "DELETE FROM PassengersInfo";
          statement = connection.prepareStatement(sql);
          statement.executeUpdate();
          System.out.println("Database is cleared successfully.");
          closeConnection();
          return true;
          
      }catch(Exception e){
          System.out.println("Error clearing Database");
          e.printStackTrace();
          
      }
      
      return false;
  }
  
  public void barChart(BarChart<String, Integer> chart){
      
            try{
                String sql = "SELECT StartingFloor, COUNT(*) as passengerStartDloorCount FROM PassengersInfo GROUP BY StartingFloor";
                String sql2 = "SELECT DestinationFloor, COUNT(*) as passengerEndFloorCount FROM PassengersInfo GROUP BY DestinationFloor";
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                ResultSet resultSet2 = statement2.executeQuery();
                
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
                
                int passengerCount;
                series.setName("Starting Floor");
                while (resultSet.next()) {
                    String startfloor = resultSet.getString("StartingFloor");
                    passengerCount = resultSet.getInt(2); 
                    series.getData().add(new XYChart.Data<>("Floor " + startfloor, passengerCount));
                }
                
                series2.setName("Destination Floor");
                while(resultSet2.next()){
                    String Endfloor = resultSet2.getString("DestinationFloor");
                    passengerCount = resultSet2.getInt(2); 
                    series2.getData().add(new XYChart.Data<>("Floor " + Endfloor, passengerCount));
                }
                
                
                chart.getData().addAll(series,series2);
                
            }catch(Exception e){
                System.out.println("Error bar chart");
                e.printStackTrace();
            }
            
            
           
  }

}
    

