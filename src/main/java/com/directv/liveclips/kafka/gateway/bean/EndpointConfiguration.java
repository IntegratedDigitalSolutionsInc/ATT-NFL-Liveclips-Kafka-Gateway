package com.directv.liveclips.kafka.gateway.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration for various end points. Properties will be injected by spring
 */
@Component
public class EndpointConfiguration {
    @Value("${kafka.adaptor.asset.published.camel.route.from.uri}")
    private String assetPublishedCamelRouteFromUri;
    @Value("${kafka.adaptor.asset.published.route.camel.to.uri}")
    private String assetPublishedCamelRouteToUri;

    @Value("${kafka.adaptor.notify.asset.camel.route.from.uri}")
    private String notifyAssetCamelRouteFromUri;
    @Value("${kafka.adaptor.notify.asset.route.camel.to.uri}")
    private String notifyAssetCamelRouteToUri;

    public String getAssetPublishedCamelRouteFromUri() {
        return assetPublishedCamelRouteFromUri;
    }

    public String getAssetPublishedCamelRouteToUri() {
        return assetPublishedCamelRouteToUri;
    }

    public String getNotifyAssetCamelRouteFromUri() {
        return notifyAssetCamelRouteFromUri;
    }

    public String getNotifyAssetCamelRouteToUri() {
        return notifyAssetCamelRouteToUri;
    }

    @Override
    public String toString() {
        return "EndpointConfiguration{" +
                "assetPublishedCamelRouteFromUri='" + assetPublishedCamelRouteFromUri + '\'' +
                ", assetPublishedCamelRouteToUri='" + assetPublishedCamelRouteToUri + '\'' +
                ", notifyAssetCamelRouteFromUri='" + notifyAssetCamelRouteFromUri + '\'' +
                ", notifyAssetCamelRouteToUri='" + notifyAssetCamelRouteToUri + '\'' +
                '}';
    }
}
