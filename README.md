# sports-concussion-assessment-system
Console application to help sport medical practitioners monitor athletes’ conditions for symptoms of concussion.

## Summary
* [Introduction](#introduction)
* [Objectives](#objectives)
* [Application Design](#application-design)
* [Run Locally](#run-locally)
* [Supported Functionality](#supported-functionality)

## Introduction
- As of today, one of the most commonly used tool for assessing concussions in sports is a paper based diagnostic tool.
- When there are thousands of athletes and equally high number of sport medical practitioners involved, this paper based tool can be cumbersome and quite slow.
- Using a computerized tool for both the athletes as well as medical practitioners can help speed up and simplify this proces.

## Objectives
- Design and build a console based application for athletes and medical practitioners.
- Describe the functionality and supported workflows using UML diagrams such as Use Case diagram, and Class diagrams.

## Application Design
### Use Case Diagram
![Use Case Diagram](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/use_case_diagram.png)

### Class Diagram
![Class Diagram](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/class_diagram.png)

## Run Locally
* Make sure Java is installed. To verify, run: `java -version`
* Clone the project: `git clone https://github.com/setu-parekh/sports-concussion-assessment-system.git`
* Route to the cloned project: `cd sports-concussion-assessment-system`
* Compile the project: `javac applicationUI.java`
* Run the project: `java applicationUI`

## Supported Functionality
### Athlete
#### New Athlete Signup
![New Athlete Signup](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/athlete_account_creation.png)

#### Existing Athlete Signin
![Existing Athlete Signin](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/existing_athlete_login.png)

#### Create a New Symptom Report
![Create a New Symptom Report](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/athlete_enter_symptoms.png)

#### View Symptoms Summary
![View Symptoms Summary](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/athlete_view_symptoms_summary.png)

#### View Current Risk Indicator
![View Current Risk Indicator](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/athlete_risk_indicator.png)

#### Check Medical Practitioner Advice
![Check Medical Practitioner Advice](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/athlete_view_medical_practitioner_advice.png)

### Medical Practitioner
#### New Medical Practitioner Signup
![New Medical Practitioner Signup](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/medical_practitioner_account_creation.png)

#### Existing Medical Practitioner Signin
![Existing Medical Practitioner Signin](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/existing_medical_practitioner_login.png)

#### View a List of All Athletes
![View a List of All Athletes](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/medical_practitioner_view_list_of_athletes.png)

#### View all Reports of an Athlete
![View all Reports of an Athlete](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/medical_practitioner_view_all_symptom_reports_of_athlete.png)

#### Advice an Athlete
![Advice an Athlete](https://github.com/setu-parekh/sports-concussion-assessment-system/blob/main/images/medical_practitioner_enter_advice_to_athlete.png)
