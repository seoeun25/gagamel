package com.seoeun.kafka;

import com.seoeun.avro.FTTHIF;
import org.apache.avro.generic.GenericRecord;
import org.junit.Test;

import java.io.IOException;

public class AvroMessageProducerConsumerTest {

    /**
     * Test to produce the message as avro format using byte encoding to kakfa.
     * Need to start kafka and create <code>avro-topic</code>.
     */
    @Test
    public void testAvroProducer() {

        AvroMessageProducer producer = new AvroMessageProducer("avro-topic", true);

        try {
            FTTHIF ftthif = new FTTHIF();
            GenericRecord datum = ftthif.createFTTHIFRecord("JNJH00982", "1/1", "1", "00:27:1C:EF:0B:91",
                    AvroMessageProducer.getDateString());
            producer.send(datum);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
    }

    /**
     * Test to consume the message as avro format from kafka topic.
     * Need to start kafka and exists the <code>avro-topic</code>.
     */
    @Test
    public void testAvroConsumer() {
        AvroMessageConsumer consumer = null;
        try {
            FTTHIF ftthif = new FTTHIF();
            consumer = new AvroMessageConsumer("avro-topic", true, ftthif.getSchema());

            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  finally {
            if (consumer != null) {
                consumer.close();
            }
        }
    }

    /**
     * Test to encode the message to avro format.
     * Use simple byte encoding and KafkaAvroMessageEncoder.
     */
    @Test
    public void testAvroEncoding() {
        AvroMessageProducer producer = new AvroMessageProducer("ftthif2-topic", true);
        AvroCamusCodecProducer camusCodecProducer = new AvroCamusCodecProducer("ftthif2-topic", true);
        try {
            FTTHIF ftthif = new FTTHIF(camusCodecProducer.getSchema("ftthif2-topic"));
            GenericRecord datum = ftthif.createFTTHIFRecord("JNJH00982", "1/1", "1", "00:27:1C:EF:0B:91",
                    AvroMessageProducer.getDateString());
            byte[] result = producer.datumToByteArray(ftthif.getSchema(), datum);
            System.out.println("---- simple avro encoding : " + new String(result));

            byte[] result2 = camusCodecProducer.datumToByteArray(ftthif.getSchema(), datum);
            System.out.println("---- camus avro encoding : " + new String(result2));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
    }

    /**
     * Test to send avro message to kafka topic using camus encoding so that camus can decode messages to upload in HDFS.
     * * Need to start kafka and create <code>ftthif2-topic</code>.
     */
    @Test
    public void testAvroCamusCodecProducer() {

        AvroCamusCodecProducer producer = new AvroCamusCodecProducer("ftthif2-topic", true);

        try {
            FTTHIF ftthif = new FTTHIF();
            GenericRecord datum = ftthif.createFTTHIFRecord("JNJH00982", "1/1", "1", "11:27:1C:EF:0B:80",
                    AvroMessageProducer.getDateString());
            producer.send(datum);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
    }
}
