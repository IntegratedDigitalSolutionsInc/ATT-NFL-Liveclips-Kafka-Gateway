package com.directv.liveclips.kafka.gateway.encoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

import java.nio.charset.Charset;

/**
 * Custom json encoder for kafka message
 */
@Deprecated
public class KafkaJsonEncoder implements Encoder<Object> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaJsonEncoder(VerifiableProperties verifiableProperties) {
        /* This constructor must be present for successful compile. */
    }

    /**
     * Custom implementation for encoding the input object using fasterxml json object mapper
     *
     * @param o
     * @return
     */
    public byte[] toBytes(Object o) {
        try {
            return o!= null ? o.toString().getBytes(Charset.forName("UTF-8")) : "".getBytes(Charset.forName("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
