import java.util.Queue;
import java.util.Scanner;

public class medicalPractitioner {
  String practitionerName;
  int practitionerId;
  String practitionerSpeciality;
  Double practitionerExperience;
  Scanner scan = new Scanner(System.in);

  /**
  * This is a constructor method which is called when any athlete object is created.
  * A 4 digit random athlete ID is created here and notified to the athlete.
  */
  public medicalPractitioner(String pName, String pSpeciality, Double pExperience){
    practitionerName = pName;
    practitionerSpeciality = pSpeciality;
    practitionerExperience = pExperience;
    practitionerId = 1000 + (int)(Math.random()*9000);
    System.out.println("\t " +  practitionerName + ", you are successfully enrolled in the system.");
    System.out.println("\t Your login ID is: " + practitionerId + ". Kindly keep it safe for all future access to the system.");
  }

  public void getAthleteSymptoms(athlete athleteRef) {
    if (athleteRef.allRecords.size() > 0) {
      System.out.println();
      System.out.println("\t List of all symptom reports of this athlete: ");

      for (athleteRecord ar: athleteRef.allRecords) {
        System.out.println();
        System.out.println("\t ----------");
        System.out.println("\t Record ID: " + ar.recordId);
        System.out.println("\t Record Entered At: " + ar.recordDateTime);
        System.out.println("\t Symptoms (range 0-6): ");

        for (int i=0; i<ar.gameSymptomsArray.size(); i++) {
          System.out.println("\t " + applicationUI.symptomsList[i] + ": " + ar.gameSymptomsArray.get(i));
        }
      }
    }
    else {
      System.out.println("\t No symptom records exist for athlete account with given ID.");
    }
  }

  public void setAdvice(athlete athleteRef) {
    Queue <athleteRecord> recentFiveGameRecords = athleteRef.recentFiveGameRecords;

    if (recentFiveGameRecords.size() <= 0) {
      // No symptom records exist for this athlete
      System.out.println("\t No recent medical records exist for this athlete.");
      athleteRef.doctorAdvise = "Athlete is healthy, no recent medical records exist.";
    }
    else {
      System.out.println();
      System.out.println("\t Recent medical records of this athlete for your reference: ");

      for (athleteRecord ar: recentFiveGameRecords) {
        System.out.println("\t ----------");
        System.out.println();
        System.out.println("\t Record ID: " + ar.recordId);
        System.out.println("\t Record Entered At: " + ar.recordDateTime);
        System.out.println("\t Total Symptoms With Severity Between 1-6: " + ar.computeTotalSymptoms(ar.gameSymptomsArray));
        System.out.println("\t Total Severity Score: " + ar.computeOverallSeverity(ar.gameSymptomsArray));
      }
      System.out.println("\t ----------");
      System.out.println();
      System.out.print("\t Enter your advice for this athlete: ");
      athleteRef.doctorAdvise = scan.nextLine();
    }
  }
}
