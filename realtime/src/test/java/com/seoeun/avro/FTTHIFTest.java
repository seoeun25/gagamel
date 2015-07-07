package com.seoeun.avro;

import org.junit.Test;

import java.io.IOException;

public class FTTHIFTest {

    @Test
    public void testSerializeDeserialize() {
        try {
            FTTHIF ftthIf = new FTTHIF();
            ftthIf.testSerialDeserialize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
