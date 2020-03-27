# Chorazos
Application to assign students a project based on preference
Magnus Sanne 17323853
Conor Dunne 17379526
Gearoid Lynch 17459176

To generate the test files we read the data from the staff members spreadsheet and generated projects by 
assigning a general statement to one of the staff members research areas, for example "An app that...". 
To generate students we used a list of common names and assigned them projects. We then wrote the data 
into two spreadsheets, one including the members of staff and their projects and one with the students and their preferences.

We created three object classes, Student, Staff and Project. The Student class stores the students name, student number, 
focus of study and a list of their project preference. The Staff class stores a member of staffs name, research activity,
research area, their focus of study and a list of the projects they have put forward. Finally the Project class stores
a project id, the project title and the focus area of the project, it can be CS, DS or CS + DS.

To test our work we created two test classes, CreateTestCase and ReadTestCase. CreateTestCase reads the data from the input file 
and runs our generate spreadsheet file. It generates both .xlsx and .csv files.
ReadTestCase reads in all the projects and members of staff andf then points the members of staf to the projects they put forward
and then the class reads in students and points the students to their preferred projects.
