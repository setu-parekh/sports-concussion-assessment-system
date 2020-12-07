/**
 * athlete class implements all the functionalities carried out by an athlete.
 * In this class, a 4 digit random athlete ID will be created and notified to them.
 * They can enter their symptoms for each game.
 * They can view symptoms summary which will show them summary for recent 5 games.
 * They can view whether they are at risk of concussion.
 * They can view the doctor's advice if any (This functionality has not been implemented).
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class athlete {
  String athleteName;
  int athleteID;
  int athleteAge;
  String athleteGame;
  String doctorAdvise = "Awaiting medical practitioner's review.";

  // Creating a queue to store athleteRecord objects and symptom summary elements. Only 5 objects will be stored at a time for a particular athlete.
  Queue <athleteRecord> recentFiveGameRecords  = new LinkedList<>();
  Queue <String> recentFiveGameTotalSymptoms  = new LinkedList<>();
  Queue <String> recentFiveGameSeverities  = new LinkedList<>();
  Queue <String> recentFiveGameRiskIndicators  = new LinkedList<>();
  Queue <athleteRecord> allRecords = new LinkedList<>();

  /**
    * This is a constructor method which is called when any athlete object is created.
    * A 4 digit random athlete ID is created here and notified to the athlete.
    */
  public athlete(String userName, int age, String game){
    athleteName = userName;
    athleteAge = age;
    athleteGame = game;
    athleteID = 1000 + (int)(Math.random()*9000);
    System.out.println();
    System.out.println("\t Your login ID is: " + athleteID + ". Kindly keep it safe for all future access to the system.");
  }

  /**
    * This method will set the symptom score for each of the 22 symptoms for a game played by athlete.
    * @param gameSymptomsArray - This array consists of all the pain scores entered by the athlete against each of the 22 symptoms
    * prompted by the application.
    */
  public void setGameSymptoms(ArrayList<Integer> gameSymptomsArray){
    // Creating a new athleteRecord object and obtaining symptoms score array
    athleteRecord r = new athleteRecord(athleteID, gameSymptomsArray);
    allRecords.add(r);
    ArrayList<Integer> rSymptoms = r.gameSymptomsArray;

    int rTotalSymptoms = r.computeTotalSymptoms(rSymptoms);
    int rSeverity = r.computeOverallSeverity(rSymptoms);

    if (recentFiveGameRecords.size() < 5){
      recentFiveGameRecords.add(r);
      recentFiveGameTotalSymptoms.add(Integer.toString(rTotalSymptoms));
      recentFiveGameSeverities.add(Integer.toString(rSeverity));
      String rRisk = getRiskIndication();
      recentFiveGameRiskIndicators.add(rRisk);
    }
    else{
      recentFiveGameRecords.remove();
      recentFiveGameTotalSymptoms.remove();
      recentFiveGameSeverities.remove();
      recentFiveGameRiskIndicators.remove();
      recentFiveGameRecords.add(r);
      recentFiveGameTotalSymptoms.add(Integer.toString(rTotalSymptoms));
      recentFiveGameSeverities.add(Integer.toString(rSeverity));
      String rRisk = getRiskIndication();
      recentFiveGameRiskIndicators.add(rRisk);
    }
  }

  /**
    * This method creates a two-dimensional ArrayList of symptoms summary for each game upto recent 5 games.
    * Outer array represents games and inner array represents summary of each game.
    * @return - returns a two-dimensional ArrayList storing an ArrayList of symptom summary for each game upto recent 5 games.
    */
  public ArrayList<ArrayList<String>> getSymptomSummary(){
    // Converting queue to list
    ArrayList <String> gamesTotSymptomsList = new ArrayList <String> (recentFiveGameTotalSymptoms);
    ArrayList <String> gamesSeverityList = new ArrayList <String> (recentFiveGameSeverities);
    ArrayList <String> gamesRiskList = new ArrayList <String> (recentFiveGameRiskIndicators);

    // Creating a two-dimensional ArrayList storing an ArrayList of symptoms summary elements.
    ArrayList<ArrayList<String>> summaryList = new ArrayList<ArrayList<String>>();

    for (int i=0; i < recentFiveGameRecords.size(); i++){
      ArrayList<String> eachGameSummary = new ArrayList<String>(); //Inner Array

      eachGameSummary.add(gamesTotSymptomsList.get(i));
      eachGameSummary.add(gamesSeverityList.get(i));
      eachGameSummary.add(gamesRiskList.get(i));

      summaryList.add(eachGameSummary);
    }
    return summaryList;
  }

  /**
  * This method compares the total number of symptoms and overall severity of the recent 2 games and returns the category of * risk the particular athlete is in.
  * @return - returns a string value ("No Difference", "Unsure", or "Very Different").
  */
  public String getRiskIndication(){
    ArrayList<athleteRecord> recentTwoGamesRecord = getLastTwoGameRecords();
    boolean checkEmpty = recentTwoGamesRecord.isEmpty();

    if (checkEmpty == false){
      if (recentTwoGamesRecord.size() == 2){
        ArrayList<Integer> lastRecordSymptoms = recentTwoGamesRecord.get(0).gameSymptomsArray;
        ArrayList<Integer> secondLastRecordSymptoms = recentTwoGamesRecord.get(1).gameSymptomsArray;
        String riskIndicator = recentTwoGamesRecord.get(0).calculateRisk(lastRecordSymptoms, secondLastRecordSymptoms);
        return riskIndicator;
      }
    }
    return "Not Enough Data!";
  }

  /**
    * This method retrives the records of last two games entered by an athlete.
    * @return - returns array list of recent two game records.
    */
  public ArrayList<athleteRecord> getLastTwoGameRecords(){
    // Converting recentFiveGameRecords Queue to an ArrayList.
    ArrayList <athleteRecord> recentFiveGamesRecordList = new ArrayList <athleteRecord> (recentFiveGameRecords);
    // Creating new ArrayList consisting of two recent game records.
    ArrayList <athleteRecord> recentTwoGamesRecord = new ArrayList <athleteRecord> ();

    boolean checkEmpty = recentFiveGamesRecordList.isEmpty();

    if (checkEmpty == false){
      athleteRecord lastRecord = recentFiveGamesRecordList.get(recentFiveGamesRecordList.size() - 1);
      if (recentFiveGamesRecordList.size() >= 2){
        recentTwoGamesRecord.add(lastRecord);
        athleteRecord secondLastRecord = recentFiveGamesRecordList.get(recentFiveGamesRecordList.size() - 2);
        recentTwoGamesRecord.add(secondLastRecord);
      }
      else{
        recentTwoGamesRecord.add(lastRecord);
      }

    }
    return recentTwoGamesRecord;
  }
}
