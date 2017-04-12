# Example WebApp on Google Cloud App Engine
Using java spring boot, angular, html for building a web app on google cloud.

I have not followed the google model to create the webapp, its just my own model, but its been inspired from what Google taught. Also I put Chanrith's name here as I took his help through out in developing this project.
https://developers.google.com/eclipse/docs/creating_new_webapp


Points to check:
- in pom.xml, check properties tag, values have to be replaced there for all strings with {replace-value}
- check application.properties, replace with appropriate values in flower brackets
- Only if you're using Google sign in do this: in resources/public/index.html, replace line 8 with appropriate value
 - if not remove the login part in the application
 - even if you proceed with login, you should reach the login page with app up and running
- check if src/main/appengine/app.yaml has everything right
- in src/main/java/in/mw/attendance/attendanceRepository.java replace {dbname} with your database name
- implement same step for feedback repo too

