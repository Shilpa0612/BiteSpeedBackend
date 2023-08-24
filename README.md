**BiteSpeed Backend Application**

Here are the steps to set – up and run the application locally.

**Prerequisites**

-	JDK 8
  
**Steps to Run Application**

1.	**Clone the Repository**
   
git clone https://github.com/Shilpa0612/BiteSpeedBackend.git

cd BiteSpeedBackend

2. 	**Build the application Using Gradle Wrapper**
   
**For Windows**

gradlew.bat clean build

**For Linux/MacOS**

./gradlew clean build

This will generate a jar file in “build/libs” directory

3.	**Run the application**
   
java -jar build/libs/<jar-file-name>.jar

Now the application should be running and accessible at: ‘http://localhost:8080’ port

**Steps for Testing Using Postman**

1.	Create a new request with ‘+’ tab
   
2.	Set the request type to Post

3.  Enter the endpoint URL:
   
http://localhost:8080/identify

4.	Add request body
-	Click on ‘Body’ tab
  
-	Select ‘Raw’ option and from drop down select JSON
  
-	Now enter the sample input:
  
{

         "email":"abcdef",
         
        "phone":"77"
        
}

5.	Click on Send
6.	Response will be generated in response tab
