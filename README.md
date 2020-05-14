# Chorazos
Application to assign students a project based on preference
Magnus Sanne 17323853
Conor Dunne 17379526
Gearoid Lynch 17459176

To Run:

Assignment 1:
To generate the test files we read the data from the staff members spreadsheet and generated projects by 
assigning a general statement to one of the staff members research areas, for example "An app that...". 
To generate students we used a list of common names and assigned them projects. We then wrote the data 
into two spreadsheets, one including the members of staff and their projects and one with the students and their preferences.

Assignment 2:
We created three object classes, Student, Staff and Project. The Student class stores the students name, student number, 
focus of study and a list of their project preference. The Staff class stores a member of staffs name, research activity,
research area, their focus of study and a list of the projects they have put forward. Finally the Project class stores
a project id, the project title and the focus area of the project, it can be CS, DS or CS + DS.

To test our work we created two test classes, CreateTestCase and ReadTestCase. CreateTestCase reads the data from the input file 
and runs our generate spreadsheet file. It generates both .xlsx and .csv files.
ReadTestCase reads in all the projects and members of staff andf then points the members of staf to the projects they put forward
and then the class reads in students and points the students to their preferred projects.


Assignment 3:
We made a Solution class to generate and store random solutions. The code is ran from the 
ReadTestCases class and prints a random student with a random project. This solution is 
completely random and not yet based on the student preference list. We have made sure that
no student or project ever repeats itself so this is a valid solution. We created a static 
list in Solutions, so all solutions can only choose a project from a single list and is a
solution chooses a project, it is removed from the list so no other solutions can choose it
to ensure a valid solution, this is implemented in the modify() function.

Assignment 4:
We added hard and soft constraints to our solution class as well as adding GPA to students. 
If a hard constraint is broken then 1, the maximum value of energy, is returned, this means 
the solution is invalid. If no hard constraint is broken the energy functions returns the 
number of preference of the project assign to a student and multiplies it by their GPA, then
divides by 45.1 to get the energy of the student. The value 45.1 comes from multiplying the 
maximum possible value of a GPA with 11, the maximum possible preference index for a student.
We divided by this value to ensure that the energy value is between 0 and 1 and therefore the
fitness is 1 - the energy value.

Note: CreateTestCase must be ran before ReadTestCase file is ran.

Assignment 5:
First we implemented a hillclimbing method that can be ran from ReadTestCases. This method takes
a solution and makes 100 different changes to the solution and then chooses the change with the
minimum energy to become the new solution. We run this method until a better solution cannot be produced.
The code prints the energy of the best solution. Our simulated annealing method is also ran from ReadTestCases.
We create random changes in the modify function that are only accepted if the probability of keeping a solution is
greater then a random number. Our cooling method is implemented in our solve function in the Simulated Annealing
class.

Note: Simulated Annealing can take a few minutes to run. The energy can be improved if the initial temperature is 
increased however this took too long to run. We believe that changing how we calculate our energy will improve our solution
and is something we will look but for this sprint we focused on other issues involving the current assignment.

Assignment 6:
This week we created the genetic algorithms class. We had it create a list of solution permutations, this is our population.
We created a function to sort that list so we could get the top percent to breed and the bottom percent to cull.
It then culls the bottom M percent to make space for the new solutions.
To breed/combine two permutations we first either mutated or didn't mutate each one based on the probability E,
then it goes through the list of students and chooses the project from one of the solution permutations randomly.
It picks two random permutations from the top N percent and and breeds a new permutation, it does this until
the population is back at the original size. It will repeat this process until it doesn't make a new best solution
permutation in R iterations.
At first we used the default values of M, N, R, E, and P, but after we got our code working we changed these values to
optimise the results we got.
For each of the values, we ran the code, changed a single value, ran the code again, and if the result was better we kept the
change. This is a manual hill climb approach to these values.
We have generated a solution in the generatedSolution chorazos/GeneratedSolution
Note: Our code can take up to 5 minutes to run and sometimes longer.

Note: we have added instructions for running the code at the top of the readme

Assignment 7:
This week we designed the GUI for our application. We used Java Swing to implement it with full functionality. Our application
allows the user to import a .csv file of projects and students, the project file must be chosen first and then the student 
file, this is instructed in the title of the file selection window. After the user has imported their projects we have 
implemented functionality that allows for them to change the parameters of the search, for example they can change the population
size in a genetic algorithm search or the initial temperature in a simulated annealing search. This is done using JSliders.
We also allow the user to decide the influence that GPA has on the solution, so if the slider is at its max the students GPA
will fully influence the decision of project allocation and if it is at its minimum the students GPA will have no influence
on project allocation. If the user wishes to use the default parameters they can tick the box at the top of the window. The default
values are the values we found to give the best solution with the most efficient runtime. Once the parameters have been decided the
user can run the program. We have implemented a visualisation of the search being performedon the right side of the window. We decided
to implement this animation as we were concerned that the user would feel the program is not working due to the occasionally long run 
times. The animation shows the dots on the left hand side, which represent the students being connected to the dots on the right hand 
side, which represent the project. Once the program has finished a window pops up with the results of the search and the user then has 
the option to export the solution as a .csv to a location of their chosing on their computer.

NOTE: The application is run from a jar file that is located in the SampleOutput folder, the path is "C:\Chorazos\Chorazos\SampleOutput".
It can also be ran from run.java which is located at "C:\Chorazos\Chorazos\src\main\java\run.java"

Assignment 8/Final ReadMe:
This is a final write up of our application. We decided to add functionality to make our app extremely user friendly. We allow the user to
decide between using simple settings and advanced settings. When the app is launched the user is brought to the simple settings which is 
made up of 3 buttons, upload inputs, run the search and go to advanced settings. When the user user decides to use simple settings the 
genetic algorithm search is ran, we chose to use the Genetic Algorithm solution as this is our solution that produced the best results
On the right side of the screen the user can watch a visualization of the search being carried out. 
The animation shows the dots on the left hand side, which represent the students being connected to the dots on the right hand 
side, which represent the project. The user is also given frequent updates of how the search is going at the bottom of the screen. If
the user decides they are happy with the solution based on these updates they can press the cancel button and export the solution at the
stage it was when the cancel button was pressed. This tells the user how many students have been allocated a valid project at the time of 
the search. Once the program has finished running a final summary of the solution appears and the solution is shown in a table. This table is
colour coded, so if the student gets a top 5 preference the row will appear as white and the lower the allocated project is on the students 
preference list the more red the row will appear, a dark red row implies that the student did not get one of their preferences. The user then has 
the option to export the solution as a .csv to a location of their chosing on their computer. If the user decides to choose advanced they have 
the option of which algorithm they would like to use for the search. The advanced settings include functionality that allows for the user to 
change the parameters of the search, for example they can change the population size in a genetic algorithm search or the initial temperature in a simulated 
annealing search. We also allow the user to decide the influence that GPA has on the solution, so if the slider is at its max the students GPA
will fully influence the decision of project allocation and if it is at its minimum the students GPA will have no influence on project allocation. 
If the user wishes to use the default parameters they can tick the box at the top of the window. The default values are the values we found to give 
the best solution with the most efficient runtime. Once the parameters have been decided the user can run the program. Once the program is run using 
advanced settings it is the same as using the simple settings. We have implemented measures into the program to make sure that the application is 
fully accessible while the search is being performed and it does not freeze on the user. 