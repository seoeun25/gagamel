package com.seoeun.kafka;

import org.junit.Test;

public class SimpleMessageProducerTest {

    @Test
    public void testSendMessage() {

        String topic = "hi-topic";
        boolean sync = true;

        SimpleMessageProducer producer = null;
        try {
            producer = new SimpleMessageProducer(topic, sync);
            producer.sendTestRecord();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.close();
            }
        }

    }
}
