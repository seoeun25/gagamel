package com.seoeun.kafka;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.util.Utf8;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import kafka.consumer.ConsumerConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AvroMessageConsumer {

    private String topic;
    private boolean sync;
    private Schema schema;
    private Integer partition = null;
    private String key = "simple";

    private ConsumerConnector consumerConnector = null;

    public AvroMessageConsumer(String topic, boolean sync, Schema schema) {
        this.topic = topic;
        this.sync = sync;
        this.schema = schema;

        init();
    }



    public static String getDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String dateStrng = dateFormat.format(calendar.getTime());
        return dateStrng;
    }

    private void init() {

        Properties props = new Properties();
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "group1");
        props.put("auto.offset.reset", "smallest");
        ConsumerConfig consumerConfig = new ConsumerConfig(props);
        consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);

    }

    public void readMessage() {
        try {
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
            topicCountMap.put(topic, 1);
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
            List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
            System.out.println("---- stream size : " + streams.size());
            if (streams.size() > 0) {
                readMessages(streams.get(0));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private void readMessages(KafkaStream<byte[], byte[]> stream) {
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        System.out.println("---- get iterator");
        while (iterator.hasNext()) {
            System.out.println("---- start iterator");
            try {
                MessageAndMetadata<byte[], byte[]> messageAndMetadata = iterator.next();

                byte[] messageBytes = messageAndMetadata.message();
                GenericRecord genericRecord = byteArrayToDatum(schema, messageBytes);
                Long a = getValue(genericRecord, "eventTime", Long.class);
                String b = getValue(genericRecord, "ontMac", String.class);
                System.out.println(" message : [" + a + "] , [" + b + "]");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static GenericRecord byteArrayToDatum(Schema schema, byte[] byteData) {
        GenericDatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schema);
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(byteData);
            Decoder decoder = DecoderFactory.get().binaryDecoder(byteArrayInputStream, null);
            return reader.read(null, decoder);
        } catch (IOException e) {
            return null;
        } finally {
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {

            }
        }
    }

    public static <T> T getValue(GenericRecord genericRecord, String name, Class<T> clazz) {
        Object obj = genericRecord.get(name);
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Utf8.class) {
            return (T) obj.toString();
        } else {
            return (T) obj;
        }
    }

    public void close() {
        consumerConnector.shutdown();
    }

}
