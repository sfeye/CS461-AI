# Project 1 

## Part 1 Guided Search
### Running the application - git checkout master

* Open the project in IntelliJ and import all dependencies using maven install
* Configure your SDK appropriately, I use 11.0.3 and a language level of 8+
* Run the driver class `Project1Application`

### Using the application

* After compilation a terminal window will pop up allowing for textual input
* Input your start and end locations (a list of these can be found in the cities text file [here](https://github.com/sfeye/CS461-AI/blob/master/Project1/src/main/resources/cities.txt))
* The program will return the path that you should take based on a best-fit algorithm

---

ABILENE to VIOLA
![image](https://user-images.githubusercontent.com/37005498/92854333-86d62500-f3b6-11ea-88db-e8e23a83e474.png)

SALINA to HARPER
![image](https://user-images.githubusercontent.com/37005498/92854574-ecc2ac80-f3b6-11ea-92a8-79b30aba6c77.png)


## Part 2 Displaying the map via Google Maps API
### Running the application - git checkout map

**Java Spring Boot Application - REST API**
* Don't forget to switch to the map branch to try this!
* I changed the driver class for my api/ai to a springboot application. You can run that after installing the maven dependencies.
* This application has an api endpoint at `localhost:8080/map/points`.

**ReactJS WebApp**
* Confirm that node and npm are installed on your cpu `node -v`, `npm -v`
* In a terminal cd to the folder `mapwebapp`
* Install all of the necesarry dependencies using `npm install --save`
* Launch it! with `npm start` (your browser should point you to localhost:3000)

### Using the application

* In the test box you can input start and end cities at the same time separated by a comma.
* Click the submit button and watch how an ~~overly watermarked~~ astounding google map will diplay your path using a line.

---

ABILENE to VIOLA
![image](https://user-images.githubusercontent.com/37005498/92966333-f7278980-f43c-11ea-955a-b5a8aacc100d.png)

SALINA to HARPER
![image](https://user-images.githubusercontent.com/37005498/92966412-1cb49300-f43d-11ea-8cbc-f3c4be688a1e.png)
