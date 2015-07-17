package com.seoeun.camus.kafka.coders;

import com.linkedin.camus.coders.CamusWrapper;
import com.linkedin.camus.coders.MessageDecoder;
import org.apache.log4j.Logger;
import java.util.Properties;

public class StringMessageDecoder extends MessageDecoder<byte[], String>{

    private static final Logger log = Logger.getLogger(StringMessageDecoder.class);

    @Override
    public void init(Properties props, String topicName) {
        this.props = props;
        this.topicName = topicName;
    }

    @Override
    public CamusWrapper<String> decode(byte[] payload) {

        long timestamp = 0;
        String payloadString;

        payloadString = new String(payload);
        timestamp = System.currentTimeMillis();

        return new CamusWrapper<String>(payloadString, timestamp);
    }
}
