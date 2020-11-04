Alberto Rodriguez CPSC411-HW1-HTTPServer
Submission for CPSC411 Homework 1

Summary: This code handles POST methods sent to the localhost:8080 server. The POST handler then parses the data sent via JSON and submits that data to the database. 
Upon startup, the program creates this database and prepares it for INSERT methods. After parsing the JSON data from the POST Method, the program executes an INSERT function to
insert the desired values into the databse. Certain values such as the isSolved and UUID are not passed from the JSON and are instead created by the program and added to
the row being added to the database.
