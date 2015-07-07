package com.seoeun.kafka;

import com.linkedin.camus.etl.kafka.coders.KafkaAvroMessageEncoder;
import com.seoeun.camus.schemaregistry.SimpleSchemaRegistry;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import java.io.IOException;
import java.util.Properties;

public class AvroCamusCodecProducer extends AvroMessageProducer{

    private SimpleSchemaRegistry schemaRegistry;
    private KafkaAvroMessageEncoder encoder;
    public AvroCamusCodecProducer(String topic, boolean sync) {
        super(topic, sync);
        encoder = createCamusCode();
        schemaRegistry = new SimpleSchemaRegistry();
    }

    public Schema getSchema(String topicName) {
        return schemaRegistry.getSchema(topicName);
    }

    public byte[] datumToByteArray(Schema schema, GenericRecord datum) throws IOException {
        return encoder.toBytes(datum);
    }

    private KafkaAvroMessageEncoder createCamusCode() {
        KafkaAvroMessageEncoder encoder = new KafkaAvroMessageEncoder(topic, null);
        Properties properties = new Properties();
//        properties.put(KafkaAvroMessageEncoder.KAFKA_MESSAGE_CODER_SCHEMA_REGISTRY_CLASS, "com.linkedin.camus.schemaregistry" +
//                ".MemorySchemaRegistry");
        properties.put(KafkaAvroMessageEncoder.KAFKA_MESSAGE_CODER_SCHEMA_REGISTRY_CLASS, "com.seoeun.camus.schemaregistry" +
                ".DummySchemaRegistry");
        encoder.init(properties, topic);

        return encoder;
    }

}
