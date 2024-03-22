This program can import the csv file of Food Facility and store it in the database.  
You can run this program by following steps:

1. Create a database(I am using mysql8);
2. Find the ddl file and run this script in your database to create all tables;
3. Starting application, the default port is 20243;
4. Call the Import interface in local application.

I provided two interfaces, Import and Find.
The Find interface can query the Facility that can be accessed at the current time.

I obtained the business hours by parsing the "dayshours" column.

Due to time constraints, there are still some interesting functions that I have not to implement. For example, use
location coordinates to query the facilities closest to me. Or the "Like" function to get the most popular facilities...

I'd be honored that you cloud review my source code. Have a nice day :)