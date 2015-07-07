package com.seoeun.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class FTTHIF {

    private final String SCHEMA_FILE = "realtime/src/main/avro/ftthif.avsc";
    private Schema schema = null;
    private String AVRO_FILE = "ftthif.avro";

    public FTTHIF () throws IOException{
        initSchema(SCHEMA_FILE);
    }

    public FTTHIF (String schemaFile) throws IOException{
        initSchema(schemaFile);
    }

    public FTTHIF(Schema schema) {
        this.schema = schema;
    }

    private void initSchema(String schemaFile) throws IOException {
        schema = new Schema.Parser().parse(new File(schemaFile));
    }

    public Schema getSchema() {
        return schema;
    }

    public GenericRecord createFTTHIFRecord(String neossCode, String ifName, String onuId, String ontMac, String extraInfo) throws IOException {
        return createFTTHIFRecord(System.currentTimeMillis(), neossCode, ifName, onuId,  ontMac, extraInfo);
    }

    public GenericRecord createFTTHIFRecord(long eventTime, String neossCode, String ifName, String onuId, String ontMac, String extraInfo) throws IOException {
        GenericRecord record = new GenericData.Record(schema);
        record.put("eventtime", eventTime);
        record.put("neosscode", neossCode);
        record.put("ifname", ifName);
        record.put("onuid", onuId);
        record.put("ontmac", ontMac);
        record.put("extrainfo", extraInfo);
        return record;
    }

    public void  serializeInGeneric(GenericRecord[] records) throws IOException{
        // Serialize user1 and user2 to disk
        File file = new File(AVRO_FILE);
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        for (GenericRecord record: records) {
            dataFileWriter.append(record);
        }
        dataFileWriter.close();
    }

    public void deserializeInGeneric(File file) throws IOException {
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
        GenericRecord record = null;
        while (dataFileReader.hasNext()) {
            record = dataFileReader.next(record);
            System.out.println("-- deserialize record : " + record);
        }
    }

    public void testSerialDeserialize() throws IOException{
        GenericRecord record1 = createFTTHIFRecord("JNJH00982", "1/1", "1", "00:27:1C:01:82:85", "hello");
        GenericRecord record2 = createFTTHIFRecord("JNJH00982", "1/1", "2", "00:27:1C:EF:0B:89", "");
        GenericRecord record3 = createFTTHIFRecord("JNJH00982", "1/1", "3", "00:0E:DC:29:5E:10", "abc");
        GenericRecord record4 = createFTTHIFRecord("JNJH00982", "1/1", "4", "00:25:A6:C2:69:F5", null);

        serializeInGeneric(new GenericRecord[]{record1, record2, record3, record4});

        deserializeInGeneric(new File(AVRO_FILE));
    }

}
