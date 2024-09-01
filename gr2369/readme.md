# Group gr2369 repository 
 
## Description
This file describes the content and structure of the SportsRentals coding project. It also includes several commands which describe how to run different components of the project.

## Project Build
This project is built and runs on Maven.

### Commands for running the application
To launch the project successfully, follow these steps:
1. Navigate to the "restserver" folder and run the command `mvn spring-boot:run`. It is important that this terminal is not shut down so that the server stays up and running in the background. 
2. Next, open a new terminal. Navigate to the "sportsRentals" folder and run the command `mvn clean install`. Next, navigate to the "ui" folder and run the application using the command `mvn javafx:run`.

### Commands for running the tests
The server needs to run in the background when running tests. Navigate to the "restserver" folder and run the command `mvn spring-boot:run`. It is important that this terminal is not shut down so that the server stays up and running in the background. It is also recommended to  navigate to the "sportsRentals" folder and run `mvn clean install -DskipTests` before running tests. 

To test all modules simultaneously:
- In another terminal, run the command: `mvn test`

To only test the core-module:
- In another terminal, run the command: `mvn test -pl core`

To only test the ui-module:
- In another terminal, run the command: `mvn test -pl ui`

To only test the springboot-module:
- In another terminal, run the command: `mvn test -pl springboot/restserver`

### Restrictions regarding the tests for the ui-module
We have created automated tests for the ui-module. The test clicks on given dates, a rental period, and an equipment and rents it. Therefore, if the test is run again, the equipment will now be unavailable and the test will not be able to rent the same equipment once again.

Therefore, if you wish to run the tests for the ui-module consecutively you need to clear all rentals. This can be done by following these steps:
1. Close the springboot server
2. In the other terminal, navigate to the "sportsRentals" folder and run the command `mvn clean install -DskipTests`
3. The test can now be run once again using the procedure described in the paragraph above. 

### Commands for running Checkstyle, SpotBugs and JaCoCo
*A report is generated for each module. Replace the {module-name} with the desired module.* 

To run both Checkstyle, SpotBugs and JaCoCo simultaneously follow these steps:
1. Navigate to the "sportsRentals" folder and run the command: `mvn clean install`
2. The Checkstyle report can be found in this file: "sportsRentals/{module-name}/target/ckeckstyle-result.xml". To open the file in a more readable format you can right click on the file and select "Open In Default Browser".
3. The SpotBugs report can be found in this file: "sportsRentals/{module-name}/target/spotbugsXml.xml". To open the file in a more readable format you can right click on the file and select "Open In Default Browser".
4. The Java Code Coverage report can be found in this file: sportsRentals/{module-name}/target/site/jacoco/index.html. To open the file in a more readable format you can right click on the file and select "Open In Default Browser".

## Project Structure
The coding project is divided into three main modules: "core", "ui" and "springboot/restserver". 

### core module
The "core-module" contains the backend part of the code. Such as classes which define methods for computing calculations and reading/writing to file. 

The Equipment and EquipmentStorage classes are responsible for managing equipment-related data. They are located in the "core" folder inside the module: 
- [Equipment](/sportsRentals/core/src/main/java/sportsRentals/core/Equipment.java)
- [EquipmentStorage](/sportsRentals/core/src/main/java/sportsRentals/core/EquipmentStorage.java)

The following classes are collected in a folder called "json" inside the "core-module". The classes are involved in the writing and reading of JSON data:
- [EquipmentDeserializer](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentDeserializer.java)
- [EquipmentSerializer](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentSerializer.java)
- [EquipmentStorageDeserializer](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentStorageDeserializer.java)
- [EquipmentStorageSerializer](/sportsRentals/core/src/main/java/sportsRentals/json/internal/EquipmentStorageSerializer.java)

The following class is also located in the "internal" folder under the "json" folder. It configures how JSON data is handled. The class ensures proper serialization og deserialization:
- [SportsRentalsModule](/sportsRentals/core/src/main/java/sportsRentals/json/internal/SportsRentalsModule.java)

This class is also located in the "json" folder. It manages how our application stores and accesses JSON data:
- [sportsRentalsPersistence](/sportsRentals/core/src/main/java/sportsRentals/json/sportsRentalsPersistence.java)

The tests for the "core-module" are located in a seperate folder called "/test/core" inside the module:
- [EquipmentStorageTest](/sportsRentals/core/src/test/java/sportsRentals/core/EquipmentStorageTest.java)
- [EquipmentTest](/sportsRentals/core/src/test/java/sportsRentals/core/EquipmentTest.java)

The tests for the json folder inside the "core-module" are located in a seperate folder called "/test/json" inside the module:
- [SportsRentalsModuleTest](/sportsRentals/core/src/test/java/sportsRentals/json/SportsRentalsModuleTest.java)
- [SportsRentalsPersistenceTest](/sportsRentals/core/src/test/java/sportsRentals/json/SportsRentalsPersistenceTest.java)


### ui module 
The "ui-module" contains the frontend part of the code. It handles the user interface and the users interaction with the appplication. The "ui-module" has a dependency to the "core-module" because it relies on the classes and logic defined in the "core-module"

The RemoteSportsRentalsAccess class is responsible for interacting with the remote server to handle requests for managing data:  
- [RemoteSportsRentalsAccess](/sportsRentals/ui/src/main/java/sportsRentals/ui/RemoteSportsRentalsAccess.java)

The following classes are located in the "ui" folder inside the "ui-module". They are responsible for launching the app and handling user interactions with the GUI: 
- [sportsRentalsApp](sportsRentals/ui/src/main/java/sportsRentals/ui/sportsRentalsApp.java)
- [sportsRentalsController](sportsRentals/ui/src/main/java/sportsRentals/ui/sportsRentalsController.java)

SportsRentalsDataAccess is an interface describing which methods are necessary for accessing data:
- [SportsRentalsDataAccess](/sportsRentals/ui/src/main/java/sportsRentals/ui/SportsRentalsDataAccess.java)

The FXML file is located in the "resources" folder inside the "ui-module". The FXML file defines the structure and layout of the graphical user interface of the project: 
- [sportsRentals.fxml](sportsRentals/ui/src/main/resources/sportsRentals/ui/sportsRentals.fxml)

The tests for the  "ui-module" are located in a seperate folder called "test" inside the module:
- [sportsRentalsAppTest](sportsRentals/ui/src/test/java/sportsRentals/ui/sportsRentalsAppTest.java)

### springboot/restserver module
The "springboot-module" contains a simple and efficient backend for the REST-API.

The SportsRentalsApplication is where the Spring Boot Application is launched:
- [SportsRentalsApplication](/sportsRentals/springboot/restserver/src/main/java/sportsRentals/springboot/restserver/SportsRentalsApplication.java)

The SportsRentalsController class is responsible for handling HTTP requests and interacting with the service:
- [SportsRentalsController](/sportsRentals/springboot/restserver/src/main/java/sportsRentals/springboot/restserver/SportsRentalsController.java)

The SportsRentalsService class contains the logic for handling all requests from the controller (for example GET, POST, and so on) and ensures that data is managed properly: 
- [SportsRentalsService](/sportsRentals/springboot/restserver/src/main/java/sportsRentals/springboot/restserver/SportsRentalsService.java)

The tests for the "springboot/restserver-module" are located in a seperate folder called "test" inside the module:
- [SportsRentalsControllerTest](/sportsRentals/springboot/restserver/src/test/java/sportsRentals/springboot/restserver/SportsRentalsControllerTest.java)
- [SportsRentalsServiceTest](/sportsRentals/springboot/restserver/src/test/java/sportsRentals/springboot/restserver/SportsRentalsServiceTest.java)

## Documentation of the REST-Service 
### Base URL
The base URL for the Sports Rentals Service is: http://localhost:8080/springboot/sportsrentals/ 

### HTTP-Requests 
All four of the methods send a HTTP-request to a specified endpoint. For a more thorough description of the methods, read the javadocs in [RemoteSportsRentalsAccess class.](/sportsRentals/ui/src/main/java/sportsRentals/ui/RemoteSportsRentalsAccess.java) The requests are handled in the springboot/restserver-module. 

#### `getEquipmentStorage` Method
Sends a GET-request to the server with this endpoint: /springboot/sportsrentals/

#### `getAvailableEquipment` Method
Sends a GET-request to the server with this endpoint: /springboot/sportsrentals/equipment/available/{startdate}/{days}

#### `getSelectedEquipment` Method
Sends a GET-request to the server with this endpoint: /springboot/sportsrentals/equipment/{type}

#### `postAvailability` Method
Sends a POST-request to the server with this endpoint: /springboot/sportsrentals/equipment/available

## UML Diagrams
We have created three diagrams using PlantUML to document our projects architecture. The PlantUML code for all diagrams can be found in the ["diagrams" folder.](/sportsRentals/diagrams)

### Class Diagram
The class diagram shows the structure of the project by showing our main logic classes, their fields and methods, and the relationship between the classes. We have chosen to only include two classes in our class diagram. This is because it is in these two classes that our main logic is located. In addition, the other classes are better presented in the other two diagrams. 

![Class Diagram](/docs/illustrations/ClassDiagramImage.png)

### Package Diagram
The package diagram shows the relationship between different packages.

![Package Diagram](/docs/illustrations/PackageDiagramImage.png)

### Sequence Diagram
The sequence diagram shows the flow of the application. In other words, it shows the interaction between the user and the application, and how the application works to complete specific tasks. 

![Sequence Diagram](/docs/illustrations/SequenceDiagramImage.png)

## Shippable Product
The project is configured for creating a shippable product. This can be done by using the following commands:
1. Navigate to the "sportsRentals" folder. 
2. Run the command `mvn clean javafx:jlink -f ./ui/pom.xml`
3. Next, run the command `mvn jpackage:jpackage -f ./ui/pom.xml`
4. After the last command is run there will appear a popup on your screen asking you to move the application into  "applications" on your local computer. 
5. Once the application is moved and is available on your computer you can launch the application locally. 

*Note that the server needs to be up and running for the local application to work properly.* 

## Eclipse Che 
The project is configured for Eclipse Che and can be opened in Eclipse Che using the link provided here: 
- [Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2369/gr2369?new)
