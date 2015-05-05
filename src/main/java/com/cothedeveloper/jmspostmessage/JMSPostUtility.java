package com.cothedeveloper.jmspostmessage;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.FileSystemUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.File;
import java.io.IOException;

/**
 * Created by root on 5/5/15.
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(JMSUtilConfig.class)
public class JMSPostUtility {
    private static String jmsMessage,serviceAction,serviceEndpoint;
    final static Logger logger = LoggerFactory.getLogger(JMSPostUtility.class);

    public static void main(String[] args) throws InterruptedException {
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(JMSPostUtility.class, args);

        //InitailizeJMSSender
        initSender(context);



    }

    public static void initSender(ConfigurableApplicationContext c){
        JMSUtilConfig jmsUtilConfig = c.getBean(JMSUtilConfig.class);
        String queueName= jmsUtilConfig.getQueueName();
        serviceAction = jmsUtilConfig.getServiceAction();
        serviceEndpoint= jmsUtilConfig.getServiceEP();
        String fileLocation = jmsUtilConfig.getInputFileLocation();
        JMSPostUtility jmsPostUtility = new JMSPostUtility();
        String inputFile=jmsPostUtility.getFileWithUtil(fileLocation);
        jmsMessage=inputFile.toString();// System.exit(0);
        // Send a message
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage=  session.createTextMessage(jmsMessage);
                textMessage.setStringProperty("serviceEP", serviceEndpoint);
                textMessage.setStringProperty("serviceAction",serviceAction);
                return textMessage;

            }
        };
        JmsTemplate jmsTemplate = c.getBean(JmsTemplate.class);
        logger.info("Sending JMS Message.. ");
        logger.info("Input File Location : "+fileLocation);
        logger.info("DestinationQueue : "+ queueName);
        logger.info("ServiceAction Property : "+serviceAction);
        logger.info("ServiceEndpoint Property : "+serviceEndpoint);
        logger.info("Message being sent : "+jmsMessage);
        jmsTemplate.send(queueName, messageCreator);
        logger.info("Finished. Shutting down... ");
        System.exit(0);

    }


    //Reads a file from the files folder and returns a String.
    private String getFileWithUtil(String fileName) {

        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
