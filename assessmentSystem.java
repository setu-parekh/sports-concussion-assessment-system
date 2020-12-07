/**
 * assessmentSystem class role is to add athletes and medical practitioners to the system and retrieve them whenerver required.
 * It maintains an array list of athlete and medical practitioner objects.
 *
 * For this project:
 *
 * we are not implementing medical practitioner side of the interface.
 * addMedicalPractitioner and getMedicalPractitioner methods have not been created.
 *
 * Array list for medical practitioners have not been created.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class assessmentSystem {
  Scanner scan = new Scanner(System.in);

  // Creating Array list to store athlete objects.
  ArrayList <athlete> athletes = new ArrayList <athlete> ();

  // Creating Array list to store medical practitioner objects.
  ArrayList <medicalPractitioner> medicalPractitioners = new ArrayList <medicalPractitioner> ();

  /**
   * This method will prompt medical practitioners to enter their personal information and will create a medicalPractitioners object.
   * This object will be added to the medicalPractitionerss array list.
   * This method does not return anything.
   */
  public void addMedicalPractitioner(){

    // Asking the user to enter their name and verifying whether user entered valid input.
    System.out.println();
    System.out.print("\t Enter your full name: ");
    while (!scan.hasNext("[A-Za-z]+")) {
      System.out.println();
      System.out.println("\t Invalid Entry! Only alphabets allowed.");
      scan.nextLine();
    }
    String pName = scan.nextLine();

    // Asking the user to enter their speciality and verifying whether user entered valid input.
    System.out.println();
    System.out.print("\t Enter your speciality: ");
    while (!scan.hasNext("[A-Za-z]+")) {
      System.out.println();
      System.out.println("\t Invalid Entry! Only alphabets allowed.");
      scan.nextLine();
    }
    String pSpeciality = scan.nextLine();

    // Asking the user to enter their game and verifying whether user entered valid input.
    System.out.println();
    System.out.print("\t Enter your total practice experience in months (rounded of to nearest month): ");
    while (!scan.hasNext("[0-9]+")) {
      System.out.println();
      System.out.println("\t Invalid Entry! Only numbers allowed.");
      scan.nextLine();
    }
    double pExperience = Double.parseDouble(scan.nextLine());

    // Creating and adding new athlete object to the list.
    medicalPractitioner mp = new medicalPractitioner(pName, pSpeciality, pExperience);
    medicalPractitioners.add(mp);
  }

  /**
   * Method to retrieve the medicalPractitioner resource using the medicalPractionId.
   *
   * @param medicalPractionId - Id of a medical practitioner.
   * @return - medicalPractitioner resource.
   */
  public medicalPractitioner getMedicalPractitioner(int medicalPractitionerId){
    medicalPractitioner mp = null;

    for (int i=0; i<medicalPractitioners.size(); i++){
        if (medicalPractitioners.get(i).practitionerId == medicalPractitionerId){
          mp = medicalPractitioners.get(i);
        }
    }

    return mp;
  }

  /**
   * This method will prompt athletes to enter their personal information and will create an athlete object.
   * This object will be added to the athletes array list.
   * This method does not return anything.
   */
  public void addAthlete(){

    // Asking the user to enter their name and verifying whether user entered valid input.
    System.out.println();
    System.out.print("\t Enter your full name: ");
    while (!scan.hasNext("[A-Za-z]+")) {
      System.out.println();
      System.out.println("\t Invalid Entry! Only Alphabets allowed.");
      scan.nextLine();
    }
    String aName = scan.nextLine();

    // Asking the user to enter their age and verifying whether user entered valid input.
    System.out.println();
    System.out.print("\t Enter your age: ");
    while (!scan.hasNext("[0-9]+")) {
      System.out.println();
      System.out.println("\t Invalid Entry! Only numbers allowed.");
      scan.nextLine();
    }
    int age = Integer.parseInt(scan.nextLine());

    // Asking the user to enter their game and verifying whether user entered valid input.
    System.out.println();
    System.out.print("\t Enter the game you play: ");
    while (!scan.hasNext("[A-Za-z]+")) {
      System.out.println();
      System.out.println("\t Invalid Entry! Only Alphabets allowed.");
      scan.nextLine();
    }
    String game = scan.nextLine();

    // Creating and adding new athlete object to the list.
    athlete a = new athlete(aName, age, game);
    athletes.add(a);
  }

  /**
   * This method will retrieve the reference of the object stored in athletes list based on the Athlete ID passed as the
   * argument.
   * @param athleteID - ID of an athlete which is a unique 4-digit value.
   * @return - It return athlete object and if not found, then will return null.
   */
  public athlete getAthlete(int athleteID){
    athlete a = null;

    for (int i=0; i<athletes.size(); i++){
        if (athletes.get(i).athleteID == athleteID){
            a = athletes.get(i);
        }
    }

    return a;
  }
}
