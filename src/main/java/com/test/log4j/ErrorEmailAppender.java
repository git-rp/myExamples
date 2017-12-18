package com.test.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Arrays;
import java.util.List;

public class ErrorEmailAppender extends SMTPAppender {

    static Logger log = Logger.getLogger(ErrorEmailAppender.class.getName());

  /*  public ErrorEmailAppender()
    {
        //log.info("Initialising the data....");
        System.out.println("Initialising the data.....");
    }*/
    public String getCheckException() {
        return CheckException;
    }

    public void setCheckException(String checkException) {
        CheckException = checkException;
        populateList();
    }

    private List<String> listCheckException;
    private String CheckException=null;

    private void populateList()
    {
       //log.info("Exception list is  :: "+CheckException);
        listCheckException =  Arrays.asList(CheckException.split("\\s*,\\s*"));

    }

    public void append(LoggingEvent event) {
        if (this.checkEntryConditions()) {
            loadExceptionListFromDb();
            event.getThreadName();
            event.getNDC();
            event.getMDCCopy();
            if (super.getLocationInfo()) {
                event.getLocationInformation();
            }
            //System.out.println(event.getMessage());
            System.out.println("ErrorEmailAppender :: listCheckException :: "+listCheckException);
            if (listCheckException != null) {
                for (String exceptionName : listCheckException) {
                    if (event.getMessage() != null && event.getMessage().toString().contains(exceptionName)) {
                        //log.info("Found exception and adding it....");
                        this.cb.add(event);
                    } else if (event.getMessage() != null && exceptionName.equalsIgnoreCase("ALL")) {
                        System.out.println("Adding all exceptions...");
                        //log.info("Adding all exceptions...");
                        this.cb.add(event);
                    }
                }


                if (this.evaluator.isTriggeringEvent(event) && this.cb.length() > 0) {
                    //log.info("sending an email.....");
                    System.out.println(this.getTo());
                    this.sendBuffer();
                }

            }
        }
    }


   /* public void setEmailDetailsFromAppProperties()
    {
        //TODO Read from PropertyExtractor
        System.out.println("Setting email......");
        System.out.println(this.getTo());
        super.setTo("rapatil@ccrn.com");
        this.setFrom("testDemo@ccrn.com");
        this.setSubject("Demo subject");
        this.setSMTPHost("relay.crosscountry.com");

    }*/

    public void loadExceptionListFromDb()
    {
        //TODO Read from PropertyExctractor
        //CheckException = PropertyExtractor.getEnvSpecificProperty("sendExceptionNotification");

    }


    public void setFrom(String from) {
        //TODO read from PropertiesExtractor
        super.setFrom("testDemo@ccrn.com");
    }
    public void setTo(String to) {
        //TODO read from PropertiesExtractor
        super.setTo("rapatil@ccrn.com");
    }
    public void setSubject(String subject) {
        //TODO read from PropertiesExtractor
        super.setSubject("Demo subject");
    }
    public void setSMTPHost(String smtpHost) {
        //TODO read from PropertiesExtractor
       super.setSMTPHost(smtpHost);
    }
}
