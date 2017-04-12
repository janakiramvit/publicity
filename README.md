# Example WebApp on Google Cloud App Engine

### What is this?
This is a webapp that can run on google cloud directly if you replace values as mentioned below in 'Points to check'.

### Scope
#### What you will get from this?
-  You will know what should be the basic layout of the web app for it to work on Google cloud.
#### What you will not get from this?
- How to create or setup Cloud App engine env, or Cloud DB or GCP env setup on local machine etc
- if you need help for this, connect to me on LI - Janakiram Pulipati

### Tech Stack:
- Web Services: Java spring boot
- Front End: Angular, Html 
- Back End: Google Cloud SQL (MySQL)
- Deployed on Google App Engine
- Dependency mgmt: Maven

I have not followed the google model to create this webapp, but its been inspired from Google documentation. 
Also I put Chanrith's name here as I took his help through out in developing this project.
https://developers.google.com/eclipse/docs/creating_new_webapp


### Points to check:
- in pom.xml, check properties tag, values have to be replaced there for all strings with {replace-value}
- check application.properties, replace with appropriate values in flower brackets
- in resources/public/index.html, replace line 8 with appropriate value
 - i think this has to be replaced only if you're using google-sign-in in the application
- check if src/main/appengine/app.yaml has everything right
- in src/main/java/in/mw/attendance/attendanceRepository.java replace {dbname} with your database name
- implement same step for feedbackRepository too

### Pre Deploy:
- Check if app runs on your system by replacing values of LocalDB in application.properties
- Before deploy, make sure: 
 - Cloud app engine is up and running
 - Cloud DB is up and running
 - IMPORTANT: Incoming connection addresses in Cloud DB includes your VM IP addresses from App Engine
 - IMPORTANT: Oauth is enabled for your app
  - API Manager -> Credentials -> Create Credentials -> OAuth Client ID -> Web Application -> Name - 'Any random name is fine' -> Authorized JavaScript origins -> https://yourapp.appspot.com, https://localhost:8080, http://localhost:8080

### Deploy
- 

