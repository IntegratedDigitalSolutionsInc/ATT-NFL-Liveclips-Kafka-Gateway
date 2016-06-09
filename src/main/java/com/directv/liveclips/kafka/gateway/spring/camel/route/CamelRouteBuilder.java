package com.directv.liveclips.kafka.gateway.spring.camel.route;

import com.directv.liveclips.kafka.gateway.bean.EndpointConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class CamelRouteBuilder {
    public static final String PRINT_TO_SYSTEM_OUT = "stream:out";
    private static Logger logger = Logger.getLogger(CamelRouteBuilder.class);

    private final JmsComponent jmsComponent;

    private final EndpointConfiguration endpointConfiguration;

    @Autowired
    public CamelRouteBuilder(JmsComponent jmsComponent, EndpointConfiguration endpointConfiguration) {
        this.jmsComponent = jmsComponent;
        this.endpointConfiguration = endpointConfiguration;
    }

    /**
     * Configures routes between the JMS gateway and the kafka server
     *
     * @throws Exception
     */
    @PostConstruct
    public void configureRoutes() throws Exception {
        logger.debug(" ****************************************** Configure Route Start ***************** ");
        logger.debug(endpointConfiguration);
        final CamelContext camelContext = jmsComponent.getCamelContext();
        /**
         * asset_published	assetStatus	topic	asset_publish
         * Asset published routes
         */
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(endpointConfiguration.getAssetPublishedCamelRouteFromUri())
                        //.to(PRINT_TO_SYSTEM_OUT)
                        .to(endpointConfiguration.getAssetPublishedCamelRouteToUri());

            }
        });
        /**
         * notify_asset	assetCommands	queue	asset_notify
         * Notify asset routes
         */
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(endpointConfiguration.getNotifyAssetCamelRouteFromUri())
                        .to(PRINT_TO_SYSTEM_OUT)
                        .to(endpointConfiguration.getNotifyAssetCamelRouteToUri());

            }
        });
        camelContext.start();
        logger.debug(" ****************************************** Configure Route End ***************** ");
    }


}
