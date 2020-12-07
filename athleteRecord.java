/**
 * record class implements functions to build a symptoms summary report.
 * It stores the symptoms data of each athlete for 5 recent games.
 */
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class athleteRecord {

  public int athleteID;
  public ArrayList<Integer> gameSymptomsArray;
  public int recordId;
  public String recordDateTime;

  // This is a constructor method. It is called whenever a record object is created.
  public athleteRecord(int userID, ArrayList<Integer> userGameSymptomsArray){
      athleteID = userID;
      recordId = 1000 + (int)(Math.random()*9000);
      gameSymptomsArray = userGameSymptomsArray;
      DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
      recordDateTime = LocalDateTime.now().format(myFormatObj);
  }

  /**
   * This method calculates the total of the pain scores entered by the athletes against each symptom for a particular game.
   * @param gameSymptoms - This array consists of all the pain scores entered by the athlete against each of the 22 symptoms
   * prompted by the application.
   * @return - returns total pain score value for a particular game.
   */
  public int computeOverallSeverity(ArrayList<Integer> gameSymptoms){
    int sumSymptoms = 0;

    for(int i = 0; i < gameSymptoms.size(); i++){
        sumSymptoms += gameSymptoms.get(i);
    }
    return sumSymptoms;
  }

  /**
   * This method calculates the total number of symptoms shown by a particular athlete. Maximum possible symptoms any athelete
   * can have is 22.
   * @param gameSymptoms - This array consists of all the pain scores entered by the athlete against each of the 22 symptoms
   * prompted by the application.
   * @return - returns total number of symptoms.
   */
  public int computeTotalSymptoms(ArrayList<Integer> gameSymptoms){
    int totalNumberOfSymptoms = 0;

    for(int i = 0; i < gameSymptoms.size(); i++){
        if (gameSymptoms.get(i) != 0){
            totalNumberOfSymptoms += 1;
        }
    }

    return totalNumberOfSymptoms;
  }

  /**
   * This method finds the risky condition of the athlete based on symptoms comparison of consecutive two games.
   * It considers the difference between total number of symptoms and overall severity of consecutive two games
   * @param gameOneSymptoms,gameTwoSymptoms - These array consists of all the pain scores entered by the athlete against
   * each of the 22 symptoms for 2 games.
   * @return - returns a string value ("Minor", "Moderate", "High", or "Not Enough Data!").
   */
  public String calculateRisk(ArrayList<Integer> gameOneSymptoms, ArrayList<Integer> gameTwoSymptoms){
    int totalSymptomsDiff = computeTotalSymptomsDiff(gameOneSymptoms, gameTwoSymptoms);
    int severityScoreDiff = computeSeverityScoreDiff(gameOneSymptoms, gameTwoSymptoms);

    if (totalSymptomsDiff < 3 && severityScoreDiff < 10){
      return "Minor";
    }
    else if (totalSymptomsDiff < 3 && severityScoreDiff >= 10){
      return "Moderate";
    }
    else if (totalSymptomsDiff >= 3 || severityScoreDiff >= 15){
      return "High";
    }
    return "Not Enough Data!";
  }

  /**
   * This method calculates the difference between the total number of symptoms of two games.
   * @param gameOneSymptoms,gameTwoSymptoms - These array consists of all the pain scores entered by the athlete against
   * each of the 22 symptoms for 2 games.
   * @return - returns the difference value.
   */
  public int computeTotalSymptomsDiff(ArrayList<Integer> gameOneSymptoms, ArrayList<Integer> gameTwoSymptoms){
    int gameOneTotSymptoms = computeTotalSymptoms(gameOneSymptoms);
    int gameTwoTotSymptoms = computeTotalSymptoms(gameTwoSymptoms);

    int totalDiff = Math.abs(gameOneTotSymptoms - gameTwoTotSymptoms);

    return totalDiff;
  }

  /**
   * This method calculates the difference between the overall severity score of two games.
   * @param gameOneSymptoms,gameTwoSymptoms - These array consists of all the pain scores entered by the athlete against
   * each of the 22 symptoms for 2 games.
   * @return - returns the difference value.
   */
  public int computeSeverityScoreDiff(ArrayList<Integer> gameOneSymptoms, ArrayList<Integer> gameTwoSymptoms){
    int gameOneSeverityScore = computeOverallSeverity(gameOneSymptoms);
    int gameTwoSeverityScore = computeOverallSeverity(gameTwoSymptoms);
    int severityDiff = Math.abs(gameOneSeverityScore - gameTwoSeverityScore);

    return severityDiff;
  }
}
