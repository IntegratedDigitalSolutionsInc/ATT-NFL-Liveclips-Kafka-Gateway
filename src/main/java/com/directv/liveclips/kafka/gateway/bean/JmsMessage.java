package com.directv.liveclips.kafka.gateway.bean;

import org.springframework.stereotype.Component;

/**
 * Test bean for simulating the real bean.
 */
@Component(value = "jmsBean")
public class JmsMessage implements java.io.Serializable {

    private String testMessage;
    private String testMessageType;

    public JmsMessage() {
    }

    public JmsMessage(String testMessage,String testMessageType) {
        this.testMessage = testMessage;
        this.testMessageType = testMessageType;
    }

    public String getTestMessage() {
        return testMessage;
    }

    public void setTestMessage(String testMessage) {
        this.testMessage = testMessage;
    }

    public String getTestMessageType() {
        return testMessageType;
    }

    public void setTestMessageType(String testMessageType) {
        this.testMessageType = testMessageType;
    }

    @Override
    public String toString() {
        return "JmsMessage{" +
                "testMessage='" + testMessage + '\'' +
                ", testMessageType='" + testMessageType + '\'' +
                '}';
    }
}
