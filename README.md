# JMS Post Utility
This app post messages to an Apache ActiveMQ server with 2 properties(serviceEP,serviceAction) attached to the message.  I use spring boot to read in the `application.properties` file and post a message to a queue.  

Steps to Run Project.
```sh
$  git clone https://github.com/cothedeveloper/jmspostutility.git jms_utility
$  cd jms_utility/
$  mvn package
$  java -jar target/jms_post_utility-1.0-SNAPSHOT.jar  (It is important to run this from the project root)
OR
$ mvn spring-boot:run
```
You may have to update the `applciation.properties` file if you want to post to somewhere other than localhost.  

### application.properties Overview

This is the configuration file inorder to run this app.  You will go here to update the connection information or any other settings related to the app.  Below are the parameters from the properties file.

`broker-url`-  Tells the app which activeMQ server to communicate.

`serviceEP`  This is a property on the message.  For my tutorial no changes are needed.  

`serviceAction` - This is a property on the message.  For my tutorial no changes are needed.

`queueName` -  This is the destination queue for the message in transit

`inputFileLocation` -  This tells the app where to find the input file which is the message we put on the activemq server.

