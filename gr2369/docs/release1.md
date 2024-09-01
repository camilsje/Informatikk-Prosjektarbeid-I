# Release 1
This is the first release in the sportsRentals project and is going to be published 21.09.2023.

## Description of Release 1
Release 1 is the first milestone of our sportsRentals application. In this release we have focused on building the structure and code of a simple app with few functionalities. In future releases functionalities and features will be improved and added. A detailed list of issues and commits related to the spesific issues for this release is avalible in [the milestone for release 1](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/milestones/2#tab-issues).

## Documentation 
The documentation for each class can be found inside the class. Within the classes, there are comments in form of JavaDocs that describe their methods and fields. This helps us remember what the purpose of the field or method is when the project improves and expands. Documentation is also important so that external parties or new members to a group can understand and work with the code easier.

Documentation for the classes is available directly within the classes through Java Docs:

- [Link to Equipment class](sportsRentals/src/main/java/gr2369/Equipment.java)
- [Link to EquipmentStorage class](sportsRentals/src/main/java/gr2369/EquipmentStorage.java)
- [Link to EquipmentStorageHandler class](sportsRentals/src/main/java/gr2369/EquipmentStorageHandler.java)
- [Link to sportsRentalsApp](sportsRentals/src/main/java/gr2369/sportsRentalsApp.java)
- [Link to sportsRentalsController](sportsRentals/src/main/java/gr2369/sportsRentalsController.java)


## README Files 
The readme file located at the root level of the code repository contains information about the structure and build of the project, as well as information about the different folders. It also includes the java and maven versions required for this project.

The readme file located inside the coding project includes a vision of the completed app. In other words, the readme file describes the applications functionalites and appearance when it is more or less completed. The file also includes a user story for the functionality we have at this stage. 

## Functionalities
### This release
![Screenshot of application as it is in release 1](/docs/illustrations/appIllustrationRelease1Actual.png)
- When the sportsRentals app is opened, users can view a list of available equipment and interact with the list. Once the user has selected an equipment, it will be visible in the information area.
- Users can choose between predefined rental periods (3 hours, 1 day, 1 week, 2 weeks) by pressing one of the four buttons. When pressing one of the buttons, the rental period and the corresponding price will be shown in the information area.
- If the user decides to press another equipment than the one already selected, the rental period and price will be cleared, and the equipment type will be updated in the information area.
- When the "confirm rental" button is pressed, the information area will be cleared.

#### Classes
- Equipment.java: This class represents an equipment available for rent. It uses a hashmap to store the different rental periods as the keys and the corresponding prices as values, and has a field to describe the type of equipment. When the user selects an equipment and a rental period, the logic in this class is used to get the price stored in the hashmap.
- EquipmentStorage.java: This class uses the method in the EquipmentStorageHandler class to read from a file. The information read from the file is used to instantiate equipment objects which are then added to a list containing all equipment. This class is used in the controller to make the equipment visible in the listview.
- EquipmentStorageHandler.java: This class is responsible for reading data from a file and creating equipment objects.

#### Tests

We have created four test classes which test the main functionality of the application. 

The sportsRentalsAppTest is an automated test which is based on the user story described in this [readme file](sportsRentals/readme.md). The other three testclasses test the corresponding classes using JUnit.

We use JaCoCo for code coverage measurement. To run JaCoCo and generate a JaCoCo report run the command `mvn clean install` from the terminal

- [Link to Equipment test](sportsRentals/src/test/java/gr2369/EquipmentTest.java)
- [Link to EquipmentStorage test](sportsRentals/src/test/java/gr2369/EquipmentStorageTest.java)
- [Link to EquipmentStorageHandler test](sportsRentals/src/test/java/gr2369/EquipmentStorageHandlerTest.java)
- [Link to sportsRentalsAppTest](sportsRentals/src/test/java/gr2369/sportsRentalsAppTest.java)


#### Limitations and issues
- The user can only rent one piece of equipment.
- The user can only choose from predefined rental periods.
- In this release, the "confirm rental" button does not  have any function other than clearing the page. The equipment does not become unavailable. This issue needs to be fixed, so that no more than one user can rent the same equipment in the same time period.
- The user does not give any personal information, so the equipment can not be rented to a spesific user.

### Future releases
#### Improvements
- The user should have the option to choose a self-defined rental period.
- The user should also be able to rent more than one piece of equipment.
- When pressing the "confirm rental" button a new page will be loaded. On this page, the user can provide personal information, such as full name, email and phone number. 
- When pressing the "confirm rental button" the equipment should become unavailable in the selected rental period. 

#### Classes and resources
- A class that implements the logic for personal info must be created.
- A controller for the personal info page must be created.
- A fxml file for the personal info page must be created.
- In the EquitmentStorage class there will be methods for writing and reading rentals to/from file. This will make it possible to save the rental of sports equipment.

## Workflow
### Teamwork approach 
In this project we have focused on creating a solid group dynamic. A part of how we have tried to accomplish this is by having frequent meetings and mostly working on the project together, instead of individually. We find that  pair programming is a practice that works well for us. By doing this, everyone is familiar with all parts of the project and get to contribute at every step. Also, by meeting regularly, it is easy to map the progress of the project, and plan future tasks that need to be distributed and completed. 

### Git practices  
In this project we highly value a well-structured git practice. We utilize different features in git to ensure that our project stays organized. 

To keep track of our tasks we create and assign issues. The issues are then linked to a milestone which helps us keep track of which tasks need to be completed within the given time period. Each issue has a tag connected to it so we can differentiate each category. The tags we use are: Feature, Documentation and Bug. The issue board in git makes it easy for us to monitor the status and category of different issues. 

Our main codebase is maintained by the usage of branches. As a standard practice we create a branch for each issue. When we commit our changes we follow [Conventional Commits 1.0.0](https://www.conventionalcommits.org/en/v1.0.0/) and reference the corresponding issue by ending the commit messsage with ", refs #issuenr". This ensures that all commits are in the same format. When the issue is resolved and commited we create a merge request which at least one other person on the team has to approve before merging the branch into master. 






