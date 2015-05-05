# JMS Post Utility
This app post messages to an Apache ActiveMQ server with 2 properties(serviceEP,serviceAction) attached to the message.  I use spring boot to read in the application.properties file and post a message to the queue.  

Steps to build project.

1.  git clone https://github.com/cothedeveloper/jmspostutility.git jms_utility
2.  Navigate into directory. cd /jms_utility
3.  Run mvn package
4.  > mvn package
5.  > java -jar target/jms_post_utility-1.0-SNAPSHOT.jar  (It is important to run this from the project root)

You may have to update the applciation.properties file if you want to post to somewhere other than localhost.  

application.properties - This is the congfiguration file inorder to run this app.  You will go here to update the connection information or any other settings realted to the app.  Below are the parameters that I let the user control from the properties file.

broker-url-  Tells the app which activeMQ server to communicate.
serviceEP=  This is a property on the message.  For my tutorial no changes are needed.  
serviceAction= This is a property on the message.  For my tutorial no changes are needed.
queueName -  This is the destination queue for the message in transit
inputFileLocation-  This tells springboot where to find the input file which is the message we put on the activemq server.
