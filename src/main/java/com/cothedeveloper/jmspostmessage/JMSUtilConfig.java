package com.cothedeveloper.jmspostmessage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by root on 5/5/15.
 */
@ConfigurationProperties(prefix = "jms.cothedeveloper.settings")
public class JMSUtilConfig {
    private String serviceEP="";
    private String serviceAction="";
    private String queueName="";
    private String inputFileLocation;

    public String getServiceEP() {
        return serviceEP;
    }

    public void setServiceEP(String serviceEP) {
        this.serviceEP = serviceEP;
    }

    public String getServiceAction() {
        return serviceAction;
    }

    public void setServiceAction(String serviceAction) {
        this.serviceAction = serviceAction;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getInputFileLocation() {
        return inputFileLocation;
    }

    public void setInputFileLocation(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }


    

}
