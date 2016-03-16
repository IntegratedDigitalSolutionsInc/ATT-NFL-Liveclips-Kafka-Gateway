package com.directv.liveclips.kafka.gateway.test;

import com.directv.liveclips.kafka.gateway.bean.JmsMessage;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class SendMessageToJMSQueue {

    @Autowired
    private JmsComponent jmsComponent;
    @Autowired
    private ApplicationContext applicationContext;

    public SendMessageToJMSQueue() {
    }

    @Test
    public void sendMessageToQueue() throws Exception {
        CamelContext camelContext = jmsComponent.getCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    Date future = new Date(new Date().getTime() + 1000);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    final String time = sdf.format(future);
                    fromF("timer://simpleTimer?time=%s&pattern=dd-MM-yyyy HH:mm:ss", time).
                            process(new Processor() {
                                public void process(Exchange exchange) throws Exception {
                                    exchange.getIn().setBody(new JmsMessage("Hello from timer at " + time, "NOTIFY_ASSET"));
                                }
                            })
                            .to("jms:queue:assetCommands");
                }
            });
            camelContext.start();
            Thread.sleep(3000);
        } finally {
            camelContext.stop();
        }
    }


}
