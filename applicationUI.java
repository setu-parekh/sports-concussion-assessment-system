/**
* applicationUI class implements user interface for Athletes and Physician. It will display a user menu so that Athletes
* or Physicians can select the desired functionality based on their requirement.
*
* Athletes can enter their health conditions, view their symptoms summary report, risky condition indicator and advice
* provided to them by their Physician.
*
* Physicians can review the symptoms entered by the Athletes, view their risky condition indicator and advice Athletes.
*/

import java.util.Scanner;
import java.util.ArrayList;

public class applicationUI {

  public static assessmentSystem SCAS = new assessmentSystem();
  public static Scanner scan = new Scanner(System.in);
  static int athleteID;
  static athlete athleteRef;
  static int practitionerId;
  static medicalPractitioner practitionerRef;
  static String[] symptomsList;

  // Defining variables for Red, Green and Yellow color which will used to display the risk indicator for an athlete.
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
  public static final String ANSI_CYAN_BACKGROUND = "\u001b[36m";
  public static final String ANSI_BLUE = "\u001b[44m";
  public static final String ANSI_WHITE = "\u001b[47m";

  public static void main(String[] args){
    processDisplayMenu();
  }

  /**
   * This method asks whether the user is an Athlete or a Medical Practitioner. Based on their inputs, this method either logs
   * them into the assessment system so that they can choose from their respective functionalities or create new account for the * user.
   */
  public static void processDisplayMenu(){
    do{
      System.out.println();
      displayMenu();
      System.out.println();
      System.out.print("\t Enter your choice: ");
      int user_choice = scan.nextInt();

      switch(user_choice){
        case 1: // Login for existing athlete
          System.out.println();
          System.out.print("\t Enter your 4 digit Athlete ID: ");
          athleteID = scan.nextInt();
          athleteRef = SCAS.getAthlete(athleteID);
          if (athleteRef != null){
            System.out.println();
            System.out.println("\t ** Login successful **");
            processAthleteMenu();
            break;
          }
          else {
            System.out.println();
            System.out.println("\t Athlete account with given ID does not exist. Enter 2 to create new account.");
          }
          break;

        case 2: // Creating account for a new athlete.
          System.out.println();
          System.out.println("\t Lets create your account. Please enter folowing details - ");
          SCAS.addAthlete();
          break;

        case 3: // Login for existing medical practitioner.
          System.out.println();
          System.out.print("\t Enter your 4 digit Practitioner ID: ");
          practitionerId = scan.nextInt();
          practitionerRef = SCAS.getMedicalPractitioner(practitionerId);
          if (practitionerRef != null){
            System.out.println();
            System.out.println("\t ** Login successful **");
            processMedicalPractitionerMenu();
            break;
          }
          else {
            System.out.println();
            System.out.println("\t Practitioner account for given ID does not exist. Enter 4 to create new account.");
          }
          break;

        case 4: // Creating account for a new medical practitioner.
          System.out.println();
          System.out.println("\t Lets create your account. Please enter folowing details - ");
          SCAS.addMedicalPractitioner();
          break;

        case 5: // Application exit.
          System.out.println();
          System.out.println("\t Goodbye!");
          System.exit(0);
          break;

        default:
          System.out.println();
          System.out.println("\t Invalid user entry ");
      }
    } while(true);
  }

  public static void processMedicalPractitionerMenu() {
    do{
      System.out.println();
      displayPractitionerMenu();
      System.out.println();
      System.out.print("\t Enter your choice: ");
      int mp_choice = scan.nextInt();

      switch(mp_choice){

        case 1: // Get the list of all athletes
          System.out.println();
          System.out.println("\t List of athletes: ");
          System.out.println("\t ----------");
          for (athlete ath: SCAS.athletes) {
            System.out.println("\t ID: " + ath.athleteID + " || Name: " + ath.athleteName + " || Age: " + ath.athleteAge + " || Game: " + ath.athleteGame);
            System.out.println("\t ----------");
          }
          break;

        case 2: // View all symptom reports of an athlete.
          System.out.println();
          System.out.print("\t Enter athlete ID: ");
          athleteID = scan.nextInt();

          athleteRef = SCAS.getAthlete(athleteID);

          if (athleteRef != null) {
            practitionerRef.getAthleteSymptoms(athleteRef);
          }
          else {
            System.out.println();
            System.out.println("\t Athlete account with given ID does not exist.");
          }
          break;

        case 3: // Set advice
          System.out.println();
          System.out.print("\t Enter athlete ID: ");
          athleteID = scan.nextInt();

          athleteRef = SCAS.getAthlete(athleteID);

          if (athleteRef != null) {
            practitionerRef.setAdvice(athleteRef);
          }
          else {
            System.out.println();
            System.out.println("\t Athlete account with given ID does not exist.");
          }
          break;

        case 4: // Return to user type menu.
          System.out.println();
          processDisplayMenu();
          break;

        case 5: // Application exit.
          System.out.println();
          System.out.println("\t Application exiting. Have a good day!");
          System.exit(0);
          break;

        default:
          System.out.println();
          System.out.println("\t Invalid user entry ");
      }
    } while(true);
  }


  /**
   * This method processes functionalities based on the athlete input. Athlete can enter their pain level for the symptoms asked,
   * can view their symptom summary, view risk indication or view medical practitioner's advice.
   */
  public static void processAthleteMenu() {
    do{
      System.out.println();
      displayAthleteMenu();
      System.out.println();
      System.out.print("\t Enter your choice: ");
      int athlete_choice = scan.nextInt();

      switch(athlete_choice){

        case 1: // Asks user to enter their pain level against 22 symptoms prompted by the application.
          ArrayList<Integer> painData = fillSymptomAssessmentForm();
          athleteRef.setGameSymptoms(painData);
          break;

        case 2: // Displays symptom summary for upto 5 recent games played by the athlete.
          System.out.println();
          System.out.println("\t Dislaying Symptoms Summary for the upto 5 recent games you played starting from least recent one:");
          System.out.println("\t ----------");
          ArrayList<ArrayList<String>> recentGamesSummary = athleteRef.getSymptomSummary();
          if (recentGamesSummary.size() > 0){
            for (int i=0; i<recentGamesSummary.size(); i++){
            System.out.println("\t Game " + (i+1) +":");
            System.out.println("\t Total number of Symptoms: " + recentGamesSummary.get(i).get(0));
            System.out.println("\t Symptom Severity Score: " + recentGamesSummary.get(i).get(1));
            System.out.println("\t Risk Indication: " + recentGamesSummary.get(i).get(2));
            System.out.println("\t ----------");
            }
          }
          else{
            System.out.println();
            System.out.println("\t No Data Available yet. Enter your symptoms first.");
          }
          break;

        case 3: //Displaying risk indicator for the recent game.
          String riskCondition = athleteRef.getRiskIndication();
          System.out.println();
          displayColoredText(riskCondition);
          break;

        case 4: //Displaying medical practitioner's advice to the athlete. This functionality has not been implemented.
          System.out.println();
          System.out.println("\t As of today, doctor's advice is: " + athleteRef.doctorAdvise);
          break;

        case 5: // Return to user type menu.
          System.out.println();
          processDisplayMenu();
          break;

        case 6: // Application exit.
          System.out.println();
          System.out.println("\t Application exiting. Have a good day!");
          System.exit(0);
          break;

        default:
          System.out.println();
          System.out.println("\t Invalid user entry ");
      }
    } while(true);
  }

  /**
   * This method displays the user type menu. User can select whether they are existing/new athlete or existing/new medical
   * practitioner.
   */
  public static void displayMenu(){
    System.out.println("\t Welcome to the Sports Concussion Assessment System ");
    System.out.println("\t ---------------------------------------------------");
    System.out.println("\t Select from the following options (1-5): ");
    System.out.println();
    System.out.println("\t 1. Existing Athlete Login");
    System.out.println("\t 2. New Athlete? Create account here");
    System.out.println("\t 3. Existing Medical Practitioner Login");
    System.out.println("\t 4. New Medical Practitioner? Create account here");
    System.out.println("\t 5. Exit");
  }

  /**
   * This method displays the functionalities available for an Athlete.
   */
  public static void displayAthleteMenu() {
    System.out.println("\t Select from the following options (1-6): ");
    System.out.println();
    System.out.println("\t 1. Enter symptoms for a new game");
    System.out.println("\t 2. View symptoms summary");
    System.out.println("\t 3. Am I at Risk?");
    System.out.println("\t 4. View medical practioner's advice");
    System.out.println("\t 5. Back to user selection menu");
    System.out.println("\t 6. Exit");
  }

  /**
   * This method displays the functionalities available for a medical practitioner.
   */
  public static void displayPractitionerMenu() {
    System.out.println("\t Select from the following options (1-5): ");
    System.out.println();
    System.out.println("\t 1. Get a list of athletes");
    System.out.println("\t 2. View all symptom reports of an athlete");
    System.out.println("\t 3. Give advice to an athlete");
    System.out.println("\t 4. Back to user selection menu");
    System.out.println("\t 5. Exit");
  }

  /**
   * This method will prompt the user to enter their pain score for each of the 22 symptoms.
   * It will store the pain scores entered by an athlete in the form of ArrayList.
   * @return - returns an array list of pain score between 0-6 for each symptom.
   */
  public static ArrayList<Integer> fillSymptomAssessmentForm(){
    ArrayList<Integer> symptomScoreList = new ArrayList<Integer>();

    // symptomsList = new String[] {"Headache", "Pressure in Head", "Neck Pain", "Nausea or Vomiting", "Dizziness",
    //                                       "Blurred vision", "Balance problems", "Sensitivity to light", "Sensitivity to noise",
    //                                       "Feeling slowed down", "Feeling like 'in a fog'", "Don't feel right",
    //                                       "Difficulty concentrating", "Difficulty remembering", "Fatigue or low energy",
    //                                       "Confusion", "Drowsiness", "Trouble falling asleep", "More emotional",
    //                                       "Irritability", "Sadness", "Nervous or Anxious"};

    symptomsList = new String[] {"Headache", "Pressure in Head", "Neck Pain"};
    System.out.println();
    System.out.println("\t Enter your pain score from 0 - 6: none(0), mild(1-2), moderate(3-4), severe(5-6)");

    for (String s:symptomsList){
      System.out.println();
      System.out.print("\t " + s + ": ");
      int userScoreEntry = scan.nextInt();
      if (userScoreEntry >= 0 && userScoreEntry <= 6){
        symptomScoreList.add(userScoreEntry);
      }
      else{
        System.out.println();
        System.out.println("\t Invalid Input. Enter pain score between 0 - 6");
        System.out.print("\t " + s + ": ");
        userScoreEntry = scan.nextInt();
        symptomScoreList.add(userScoreEntry);
      }
    }

    return symptomScoreList;
  }

  /**
   * This method is used to display the colored text on the console.
   * @param text - Any string value
   */
  public static void displayColoredText(String text){
    if (text == "Minor"){
      System.out.println("\t " + ANSI_GREEN_BACKGROUND + ANSI_BLUE + text + ANSI_RESET);
    }
    else if (text == "Moderate"){
      System.out.println("\t " + ANSI_YELLOW_BACKGROUND + ANSI_BLUE + text + ANSI_RESET);
    }
    else if (text == "High"){
      System.out.println("\t " + ANSI_RED_BACKGROUND + ANSI_BLUE + text + ANSI_RESET);
    }
    else {
      System.out.println("\t " + ANSI_CYAN_BACKGROUND + ANSI_WHITE + text + ANSI_RESET);
    }
  }
}


