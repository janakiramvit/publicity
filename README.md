# Example WebApp on Google Cloud App Engine

### What is this?
This is a webapp that can run on google cloud directly if you replace values as mentioned below in 'Points to check'. or you can design your web app for google cloud based on the layout of this code

### Scope
#### What you will learn from this?
- You will know what should be the basic layout of the web app for it to work on Google cloud.
- Configuration commands and hints needed for successful deployment of app on Google Cloud app engine
#### What you will not learn from this?
* How to create or setup Cloud App engine env, or Cloud DB or GCP env setup on local machine etc
  * But some of it is covered in the pre-deploy section below
- if you need more help for this, connect to me on LI - Janakiram Pulipati

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
* in resources/public/index.html, replace line 8 with appropriate value
  * i think this has to be replaced only if you're using google-sign-in in the application
- check if src/main/appengine/app.yaml has everything right
- in src/main/java/in/mw/attendance/attendanceRepository.java replace {dbname} with your database name
- implement same step for feedbackRepository too

Now code is all set to deploy.

### Pre Deploy:
- Check if app runs on your system by replacing values of LocalDB in application.properties
- If it successfully runs on LocalDB, then comment the localDB config and uncomment the CloudDB string value and keep the code ready to deploy
* Before deploy, make sure: 
  * Cloud app engine is up and running, billing is enabled etc for your app engine
  * Cloud DB is up and running
  * IMPORTANT: Incoming connection addresses in Cloud DB includes your VM IP addresses from App Engine
    * Go to your DB - 'Access control' -> 'Authorization' -> 'Add Networks' -> Add VM IPaddress from App Engine
  * IMPORTANT: Oauth is enabled for your app, this is how to do it:
    * API Manager -> Credentials -> Create Credentials -> OAuth Client ID -> Web Application -> Name - 'Any random name is fine' -> Authorized JavaScript origins -> https://yourapp.appspot.com, https://localhost:8080, http://localhost:8080

### Deploy
- Once all the above setup is done on cloud, deploy the app to GCP from your local machine, this is easy
* Go to the top level folder of your app and run
  * mvn appengine:deploy
- If it throws more questions at this stage, it means you have not setup the cloud environment properly

### After deploy
- Console would read Deployment 'Success' and you can browse the app by running 'gcloud app browse'


