# rmm-services-server-app

Database used - MariaDb 10.3   (modify the application.properties as per your database settings)


Must, run the dbSetup.sql SQL-script.



IntelliJ workspace
------------------

1) Download the project as zip folder.
2) Extract the zip folder to any location.
3) Open Intellij-Idea. 
4) File > New > 'Project from Existing Sources' (Select extracted folder and select 'rmm-services-server-app-master' folder)
5) 'Import project from external model' as Maven. 'Trust Project'



Useful integration test-cases 
-----------------------------

	RMMIntegrationTest.java

	Change the USER to desired user (default ninja)


	> testAddDevice(), add 5 devices (2-win, 3-mac) as mocked up in requirement.
	> testAddService() [Change the serviceName to desired service to add to current user 'ninja']
	> testMonthlyBill(), Calculate and display the monthly cost as per the selected services and number of devices


	Other usefull testcases
		testDeleteService(), testGetDevice(), testDeleteDevice()


Small fiddling Web-application
-------------------------------

	(Run RmmServicesServerAppApplication as java-application)
	http://localhost:8090/
	http://localhost:8090/login    (Login page, Login with on-screen credential, or use one from dbSetup.sql)


	Useful curl commands to test the REST endpoints outside of work-space,     
    	curl --data "username=ninja&password=Ninja" --cookie-jar cookie -X POST http://localhost:8090/processLogin
     	curl --cookie cookie -u ninja:Ninja -X GET http://localhost:8090/devices/ninja
