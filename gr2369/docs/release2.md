# Release 2
This is the second release in the sportsRentals project and is going to be published 12.10.2023.

## Description of Release 2
Release 2 is the second milestone of our sportsRentals application. This release focuses on:
- Dividing the project into appropriate modules. 
- Data persistence using JSON with the Jackson library
- Writing JUnit tests, and configuring other add-ons such as Checkstyle and Spotbugs.  
- Updating the documentation.
- Continuing working on our work habits and git skills    

## Documentation 
 Within the classes, there are comments in the form of JavaDocs that describe the methods and in the test classes there are display names over the test-methods which describe what is being tested. This helps us remember the purpose of methods and tests when the project improves and expands. Documentation is also important so that external parties or new members to a group can understand and work with the code easier.

Documentation can be found directly in the classes by following the links to the classes:

core-module:
- [Equipment class](/sportsRentals/core/src/main/java/sportsRentals/core/Equipment.java)
- [EquipmentStorage class](/sportsRentals/core/src/main/java/sportsRentals/core/EquipmentStorage.java)
- [EquipmentDeserializer class](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentDeserializer.java)
- [EquipmentSerializer class](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentSerializer.java)
- [EquipmentStorageDeserializer class](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentStorageDeserializer.java)
- [EquipmentStorageSerializer class](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentStorageSerializer.java)
- [SportsRentalsModule class](/sportsRentals/core/src/main/java/sportsRentals/json/internal/SportsRentalsModule.java)
- [sportsRentalsPersistence class](/sportsRentals/core/src/main/java/sportsRentals/json/sportsRentalsPersistence.java)
- [EquipmentTest class](/sportsRentals/core/src/test/java/sportsRentals/core/EquipmentTest.java)
- [EquipmentStorageTest class](/sportsRentals/core/src/test/java/sportsRentals/core/EquipmentStorageTest.java)
- [SportsRentalsModuleTest class](/sportsRentals/core/src/test/java/sportsRentals/json/SportsRentalsModuleTest.java)
- [SportsRentalsPersistenceTest class](/sportsRentals/core/src/test/java/sportsRentals/json/SportsRentalsPersistenceTest.java)

ui-module:
- [sportsRentalsApp class](/sportsRentals/ui/src/main/java/sportsRentals/ui/sportsRentalsApp.java)
- [sportsRentalsController class](/sportsRentals/ui/src/main/java/sportsRentals/ui/sportsRentalsController.java)
- [sportsRentalsAppTest class](/sportsRentals/ui/src/test/java/sportsRentals/ui/sportsRentalsAppTest.java)

## README Files 
The readme file located at the root level of the code repository contains information about the structure and build of the project. It also includes versions which are required for this project.

The readme file located inside the coding project includes a vision of the completed app. In other words, the readme file describes the applications functionalites and appearance when it is more or less completed. The file also includes a user story for the functionality we have at this stage. 

## Functionalities
### This release
![Screenshot of application as it is in release 1](/docs/illustrations/appIllustrationRelease2.png)
![Screenshot of application as it is in release 1](/docs/illustrations/appIllustration2Release2.png)

When the sportsRentalsApp is launched, users are presented with the page shown above.
- Firstly, the user will choose their preferred start date for their rental period from the datepicker. 
- They will then select their preferred rental period. They are presented with four options they can choose between: "1 day", "3 days", "1 week" and "2 weeks". The selection is executed by pressing one of the four buttons. 
- They will then press the "Show available options" button which will fill up the listview with  the equipment which is available given their chosen period.
- Next they select the equipment they wish to rent. When the desired equipment is selected, the information section will fill up with information about the rental. They can then overview the information and press "confirm the rental" which will close the page. 

### Change log
- In this release we have modularized the project by seperating the project into two modules: "core" and "ui". The structure and build of the project is well described in the [Readme file at root level](/readme.md).
    - [Issue 19](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/19)

- The Equipment class has been updated with new logic. Instead of having a Boolean field for each Equipment object, we have now added a method isAvailable(LocalDate startDate, int days) which returns true or false according to whether the Equipment object is available or unavailable. Due to the changes made in the Equipment class, some minor adjustments have also been made in the EquipmentStorage class.
    - [Issue 19](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/19)  

- We have implemented data persistence to file using JSON with the Jackson library. We have opted for a document-centric approach. This suits our project best because we only want to save confirmed rentals to file. The format of the JSON file is defined in the serializer classes: [EquipmentSerializer class](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentSerializer.java) and [EquipmentStorageSerializer class](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentStorageSerializer.java)
    - [Issue 21](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/21)

- We have written serializers and deserializers for the Equipment class and EquipmentStorage class. A new feature in this release is that Equipment objects are made unavailable in the time frame when rented. To achieve this we write the rental dates to file using the serializers when a rental is confirmed. The deserializers are utilized when reading from file and update the availability of the Equipment objects. 
    - [Issue 19](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/19)

- We have worked on improving the user interface for a more user-friendly experience. The biggest change is that the user now selects the startdate and rental period before selecting their equipment of choice. In other words, the ordering of actions has been changed. This is so that we only display the available equipment in the given time period.
    - [Issue 29](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/29)

- Since we made changes to the GUI, we subsequently had to make some changes in the controller as well. The most noticable changes are ordering of methods and dividing methods into smaller, more managable methods. 
    - [Issue 29](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/29)

- We have added configuration for "Checkstyle" and "Spotbugs" for testing code quality. 
    - [Issue 46](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/46)

- Using "PlantUML" we have documented the architecture of the project using both a [Class Diagram](/sportsRentals/diagrams/classDiagram.puml) and a [Package Diagram](/sportsRentals/diagrams/packageDiagram.puml).
    - [Issue 45](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/45)

- Since the project has been expanded we have ensured thorough testing across all layers. We have utilized jacoco to test our code coverage. Links to all test classes in both the "core-module" and "ui-module" can be found in the [Readme file at root level](/readme.md) and also in the "Documentation" section above.
    - [Issue 28](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/28)

- We have configured the project for Eclipse Che. The project can be opened in Eclipse Che using the link in the [Readme file at root level](/readme.md).
    - [Issue 20](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/20)

### Limitations 
- The user can only rent one piece of equipment.
- The user can only choose from predefined rental periods.
- The user does not give any personal information, so the equipment can not be rented to a spesific user.
- The user is not able to start over if they change their mind or choose something by mistake. When they press "Show available options" the datepicker and rental period buttons are disabled. 
- The user is able to select dates from the datepicker which are in the past. We need to implement proper validation so that this is not possible.

### Future release   
#### Improvements
- When pressing the "confirm rental" button, the user should be able to provide personal information, such as full name, email and phone number so that this is linked to their rental.
- The user should be able to start anew if they want to. 
- It should not be possible to select invalid dates. 

#### Approach
- A class that implements the logic for personal info must be created.
- We need to create a button in the GUI which lets the user start over. 
- We need to create a validation method to make sure the date the user selects is valid. 

## Workflow
### Teamwork approach 
In this project we have focused on creating a solid group dynamic. A part of how we have tried to accomplish this is by having frequent meetings and mostly working on the project together, instead of individually. We find that  pair programming is a practice that works well for us. By doing this, everyone is familiar with all parts of the project and get to contribute at every step. Also, by meeting regularly, it is easy to map the progress of the project, and plan future tasks that need to be distributed and completed. 

In the first week of this second group deliverable, one of our group members withdrew from the course. We are therefore now only three people working together on this group project. This has resulted in an increased workload for the rest of us, which has required some adjustments, but we have managed the situation well and are now back on track.

### Git practices 
In this project we highly value a well-structured git practice. We utilize different features in git to ensure that our project stays organized. 

To keep track of our tasks we create and assign issues. The issues are then linked to a milestone which helps us keep track of which tasks need to be completed within the given time period. Each issue has a tag connected to it so we can differentiate each category. The tags we use are: Feature, Documentation and Bug. The issue board in git makes it easy for us to monitor the status and category of different issues. 

Our main codebase is maintained by the usage of branches. As a standard practice we create a branch for each issue. When we commit our changes we follow [Conventional Commits 1.0.0](https://www.conventionalcommits.org/en/v1.0.0/) and reference the corresponding issue by ending the commit messsage with ", refs #issuenr". This ensures that all commits are in the same format. When the issue is resolved and commited we create a merge request which at least one other person on the team has to approve before merging the branch into master. 

New in this release is that we have utilized "tasks" in git as well. By using tasks we are able to break down larger issues into smaller tasks which are more managable. 

We mostly practise pair programming. Therefore we have  added Co-authored-by into our commit messages. This allows us to give credit to all authors who contributed with an issue. It also gives us a clear overview of who participated in the given commit so it is easy to approach the authors directly if needed. This makes it easier for the reviewer to ask questions if needed to specifically those who worked on the given commit.

### Code and Test quality 
#### Checkstyle
We have added configuration for "Checkstyle" for testing our code quality. We have defined a custom ruleset for Checkstyle based on the "Sun/Oracle coding standards". Using checkstyle has helped us make sure that our code is in line with coding standards and best practices. 

Checkstyle has given us multiple suggestions for improvement which we have followed, however there is one we are still not able to correct. We started the project by naming our project "sportsRentals" with a lower case "s". Checkstyle wants us to change this to a capital "S" to match conventions. We have tried to do so, but have not succeded yet. We will try to improve this in the third group deliverable. For now we have removed this test from the checkstyle ruleset. 

#### SpotBugs
We have also added configuration for "SpotBugs" for testing code quality. We have removed some tests which are not relevant for our project. Using SpotBugs has helped us become aware of potential weaknesses in the code, bugs and issues. 

SpotBugs informs us about the same issue described in the paragraph above about "Checkstyle". We have chosen to remove this test from the SpotBugs ruleset as well and will focus on fixing this in the next group deliverable.

#### Java Code Coverage (JaCoCo)
We have incorporated configuration for "JaCoCo" to get an overview of our test coverage. Jacoco has helped us in getting information about what parts of the code are tested and which are not. This has made it easier for us to ensure that all parts of our codebase are thoroughly tested.