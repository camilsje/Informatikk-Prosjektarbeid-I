# Release 3
This is the third and final release in the sportsRentals project and is going to be published 16.11.2023.

## Description of Release 3
Release 3 is the third milestone of our sportsRentals application. This release focuses on:
- Building a REST-API based on the core logic and making it accessble via a web server.   
- Modifying the UI to utilize the new REST API.
- Expanding the applications functionality from release 2. 
- Writing comprehensive JUnit tests.
- Ensuring good code quality.
- Updating the documentation.
- Creating PlantUML diagrams.
- Making sure the application is Eclipse Che ready. 
- Adding configuration with jpackage and jlink to make a shippable product.
- Continuing working on our work habits and git skills.

## Documentation
Within the classes, there are comments in the form of Javadoc that describe the methods. In the test classes there are display names over the test-methods which describe what is being tested. This helps us remember the purpose of methods and tests when the project improves and expands. Documentation is also important so that external parties or new members to a group can understand and work with the code easier.

Documentation can be found directly in the classes by following the links to the classes:

core-module:
- [Equipment](/sportsRentals/core/src/main/java/sportsRentals/core/Equipment.java)
- [EquipmentStorage](/sportsRentals/core/src/main/java/sportsRentals/core/EquipmentStorage.java)
- [EquipmentDeserializer](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentDeserializer.java)
- [EquipmentSerializer](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentSerializer.java)
- [EquipmentStorageDeserializer](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentStorageDeserializer.java)
- [EquipmentStorageSerializer](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentStorageSerializer.java)
- [SportsRentalsModule](/sportsRentals/core/src/main/java/sportsRentals/json/internal/SportsRentalsModule.java)
- [sportsRentalsPersistence](/sportsRentals/core/src/main/java/sportsRentals/json/sportsRentalsPersistence.java)
- [EquipmentTest](/sportsRentals/core/src/test/java/sportsRentals/core/EquipmentTest.java)
- [EquipmentStorageTest](/sportsRentals/core/src/test/java/sportsRentals/core/EquipmentStorageTest.java)
- [SportsRentalsModuleTest](/sportsRentals/core/src/test/java/sportsRentals/json/SportsRentalsModuleTest.java)
- [SportsRentalsPersistenceTest](/sportsRentals/core/src/test/java/sportsRentals/json/SportsRentalsPersistenceTest.java)

ui-module:
- [RemoteSportsRentalsAccess](/sportsRentals/ui/src/main/java/sportsRentals/ui/RemoteSportsRentalsAccess.java)
- [sportsRentalsApp](/sportsRentals/ui/src/main/java/sportsRentals/ui/sportsRentalsApp.java)
- [sportsRentalsController](/sportsRentals/ui/src/main/java/sportsRentals/ui/sportsRentalsController.java)
- [SportsRentalsDataAccess](/sportsRentals/ui/src/main/java/sportsRentals/ui/SportsRentalsDataAccess.java)
- [sportsRentalsAppTest](/sportsRentals/ui/src/test/java/sportsRentals/ui/sportsRentalsAppTest.java)

springboot/restserver-module:
- [SportsRentalsApplication](/sportsRentals/springboot/restserver/src/main/java/sportsRentals/springboot/restserver/SportsRentalsApplication.java)
- [SportsRentalsController](/sportsRentals/springboot/restserver/src/main/java/sportsRentals/springboot/restserver/SportsRentalsController.java)
- [SportsRentalsService](/sportsRentals/springboot/restserver/src/main/java/sportsRentals/springboot/restserver/SportsRentalsService.java)
- [SportsRentalsControllerTest](/sportsRentals/springboot/restserver/src/test/java/sportsRentals/springboot/restserver/SportsRentalsControllerTest.java)
- [SportsRentalsServiceTest](/sportsRentals/springboot/restserver/src/test/java/sportsRentals/springboot/restserver/SportsRentalsServiceTest.java)

## README Files 
[The readme.md file located at root level](/readme.md) of the code repository contains information about the structure and build of the project. 

[The readme.md file located inside the coding project](/sportsRentals/readme.md) includes a vision of the completed app. In other words, the readme.md file describes the applications functionalites and appearance when it is more or less completed. The file also includes a user story for the functionality we have at this stage. 

## Functionalities
### This release
![Screenshot of application as it is in release 3](/docs/illustrations/appIllustrationRelease3.png)
![Screenshot of application as it is in release 3](/docs/illustrations/appIllustration2Release3.png)

When the sportsRentalsApp is launched, users are presented with the page shown above.
- Firstly, the user will choose their preferred start date for their rental period from the datepicker. New in this release is that dates which are in the past, or more than six months in the future, are not possible to select. This ensures better validation.
- They will then select their preferred rental period. They are presented with four options they can choose between: "1 day", "3 days", "1 week" and "2 weeks". The selection is executed by pressing one of the four buttons. 
- They will then press the "Show available options" button which will fill up the listview with  the equipment which is available given their chosen period.
- Next they select the equipment they wish to rent. When the desired equipment is selected, the information section will fill up with information about the rental. New in this release is that the start- and end date are presented, rather than only displaying the amount of days. They can then overview the information and press "confirm the rental" which will close the page. 
- In this release we have added a new button called "Start over". This button allows the user to start anew. For example if they want to change the selected start date or rental period. 

### Change log
- In this release we have developed and integrated a REST-API based on the core logic of our project. We have also made it accessible via a web-server. Simultaneously, we have modified the UI to utilize the new REST-API. 
    - [Issue 64](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/64)

- The GUI has been improved by showing the start- and end date, instead of only presenting the rental periods length. In addition, we have added a hand-made logo. 
    - [Issue 70](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/70)

- We have extended the applications functionality by adding a "start over" button. This allows the user to start anew if they make a wrong selection in the beginning. When the button is pressed, all the information on the page will be cleared.   
    - [Issue 79](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/79)

-  In an earlier release we noticed that the user was able to select invalid dates. We have now added validation so that the user is not able to select dates which are in the past, or more that six months in the future. 
    - [Issue 78](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/78)

- We have moved as much logic as possible out of the controller and into the Equipment class. This means that we have added methods to the Equipment class and call those functions when needed, rather than having the logic inside the controller. 
    - [Issue 78](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/78)

- We have created three PlantUML diagrams to describe our projects architecture. We have created a Class Diagram, Package Diagram and Sequence Diagram. The diagrams can be found in the [readme.md file at root level](/readme.md).
    - [Issue 56](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/56)

- Since the project has been expanded we have ensured thorough testing across all layers. We have utilized JaCoCo to test our code coverage. Links to all test classes in both the "core-module", "ui-module" and "springboot/restserver-module" can be found in the [readme.md file at root level](/readme.md) and also in the "Documentation" section above.
    - [Issue 63](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/63)

- We have utilized jpackage and jlink to create a shippable product. 
    - [Issue 82](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/82)

- We have configured the project for Eclipse Che. The project can be opened in Eclipse Che using the link in the [readme.md file at root level](/readme.md).
    - [Issue 88](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/88)

- We have reviewed the SpotBugs and Checkstyle reports to improve our code quality. 
    - [Issue 60](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369/-/issues/60)

### Limitations 
Even though this is the final release of this project, if we had more time these are some of the improvements we would want to implement:

- The user can only rent one piece of equipment.
- The user can only choose from predefined rental periods.
- The user does not give any personal information, so the equipment can not be rented to a spesific user.

### Future release   
#### Improvements
- When pressing the "confirm rental" button, the user should be able to provide personal information, such as full name, email and phone number so that this is linked to their rental.
- The user should be able to rent several pieces of equipment at a time. 

#### Approach
- A class that implements the logic for personal info must be created.
- Need to implement logic for selecting more than one equipment from the ListView.

## Workflow
### Teamwork approach 
In this project we have focused on creating a solid group dynamic. A part of how we have tried to accomplish this is by having frequent meetings and mostly working on the project together, instead of individually. We find that  pair programming is a practice that works well for us. By doing this, everyone is familiar with all parts of the project and get to contribute at every step. Also, by meeting regularly, it is easy to map the progress of the project, and plan future tasks that need to be distributed and completed.

When we received the description of the third group deliverable we decided to get started early. We had learned from previous deliverables that it is much better to get an overview of what needed to be done early. We set up a meeting as soon as all three of us were free. The goal of the meeting was to plan the timeframe of the project. We began lightly to plan issues, tasks and dates for future meetings.

### Git practices 
In this project we highly value a well-structured git practice. We utilize different features in git to ensure that our project stays organized. 

To keep track of our tasks we create and assign issues. The issues are then linked to a milestone which helps us keep track of which tasks need to be completed within the given time period. Each issue has a tag connected to it so we can differentiate each category. The tags we use are: Feature, Documentation and Bug. The issue board in git makes it easy for us to monitor the status and category of different issues. We have utilized "tasks" in git as well. By using tasks we are able to break down larger issues into smaller tasks which are more managable. 

In this release we have improved our issue board by adding sections where each section corresponds to an existing label. This way we are able to map the distribution of issues among the different labels. 

Our main codebase is maintained by the usage of branches. As a standard practice we create a branch for each issue. When we commit our changes we follow [Conventional Commits 1.0.0](https://www.conventionalcommits.org/en/v1.0.0/) and reference the corresponding issue by ending the commit messsage with ", refs #issuenr". This ensures that all commits are in the same format. 

When an issue is resolved, commited and pushed into our branch regarding the issue, we create a merge request which at least one other person on the team has to approve before merging the branch into master. We make sure to go over all changes which have been made before accepting the merge request. 

We mostly practise pair programming. Therefore we have added Co-authored-by into our commit messages. This allows us to give credit to all authors who contributed with an issue. It also gives us a clear overview of who participated in the given commit so it is easy to approach the authors directly if needed. This makes it easier for the reviewer to ask questions if needed to specifically those who worked on the given commit.
