package com.seoeun.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class SimpleMessageProducer {


    private String topic;
    private boolean sync;
    private KafkaProducer<String, String> stringStringKafkaProducer;

    public SimpleMessageProducer(String topic, boolean sync) {
        this.topic = topic;
        this.sync = sync;

        init();
    }

    private void init() {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        stringStringKafkaProducer = new KafkaProducer<String, String>(props);

    }

    public void send() throws InterruptedException, ExecutionException {
        String key = "simple";
        String value = "13th July 45";
        send(key, value);
    }

    public void send(String key, String value) throws InterruptedException, ExecutionException {
        send(new ProducerRecord<String, String>(topic, key, value));
    }

    public void send(ProducerRecord<String, String> record) throws InterruptedException, ExecutionException {

        if (sync) {
            RecordMetadata result = stringStringKafkaProducer.send(record).get();

            System.out.println("---- record meta topic: [" + result.topic() + "],  partition: [" +
                    result.partition() + "] , offset : [" + result.offset() + "]");

        } else {
            stringStringKafkaProducer.send(record);
        }
    }

    public void sendTestRecord() throws InterruptedException, ExecutionException{
        String key = "simple";
        for (int i = 0; i < 10; i++) {
            send(null, i + " :: " + getDateString());
            Thread.sleep(500);
        }
    }

    private String getDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String dateStrng = dateFormat.format(calendar.getTime());
        return dateStrng;
    }


    public void close() {
        stringStringKafkaProducer.close();
    }



}
