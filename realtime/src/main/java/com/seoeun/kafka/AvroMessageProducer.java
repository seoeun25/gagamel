package com.seoeun.kafka;

import com.linkedin.camus.etl.kafka.coders.KafkaAvroMessageEncoder;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AvroMessageProducer {

    protected String topic;
    protected boolean sync;
    protected KafkaProducer<String, byte[]> kafkaProducer;
    protected Integer partition = null;
    protected String key = "simple";

    public AvroMessageProducer(String topic, boolean sync) {
        this.topic = topic;
        this.sync = sync;

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
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        props.put(ProducerConfig.BLOCK_ON_BUFFER_FULL_CONFIG, "false");
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        kafkaProducer = new KafkaProducer<String, byte[]>(props);
    }

    public byte[] datumToByteArray(Schema schema, GenericRecord datum) throws IOException {
        GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            Encoder e = EncoderFactory.get().binaryEncoder(os, null);
            writer.write(datum, e);
            e.flush();
            byte[] byteData = os.toByteArray();
            return byteData;
        } finally {
            os.close();
        }

    }

    public void send(GenericRecord datum) throws IOException, InterruptedException, ExecutionException {
        ProducerRecord<String, byte[]> producerRecord = new ProducerRecord<String, byte[]>(topic, partition, key,
                datumToByteArray(datum.getSchema(), datum));
        send(producerRecord);
    }

    public void send(ProducerRecord<String, byte[]> record) throws InterruptedException, ExecutionException {
        if (sync) {
            System.out.println("---- before send : " + new String(record.value()));
            RecordMetadata result = kafkaProducer.send(record).get();
            System.out.println("---- record meta topic: [" + result.topic() + "],  partition: [" +
                    result.partition() + "] , offset : [" + result.offset() + "]");
        } else {
            kafkaProducer.send(record);
        }
    }

    public void close() {
        kafkaProducer.close();
    }

}
