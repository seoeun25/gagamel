package com.seoeun.avro;

import example.avro.User;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

/**
 * Test serialize, deserialze using com.nexr.com.seoeun.avro format.
 *
 * To create Java Class corresponing com.nexr.com.seoeun.avro schema file(user.avsc):
 * <p>
 *     $ mvn clean compile
 * </p>
 * This command generate example.com.nexr.com.seoeun.avro.User.java. See com.nexr.com.seoeun.avro-maven-plugin.
 *
 *
 */
public class AvroTest {

    private static final String AVRO_FILE = "users.avro";
    private static final String AVRO_FILE2 = "users2.avro";

    private Schema schema = null;

    public AvroTest()  {
    }

    private void initSchema() throws IOException{
        schema = new Schema.Parser().parse(new File("realtime/src/main/avro/user.avsc"));
    }

    public User[] createUser() {
        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);

        User user2 = new User("Ben", 7, "red");

        User user3 = User.newBuilder().setName("Charlie").setFavoriteColor("blue").setFavoriteNumber(null).build();

        return new User[]{user1, user2, user3};
    }

    public void serialize(User[] users) throws IOException{
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(users[0].getSchema(), new File(AVRO_FILE));

        for (User user: users) {
            dataFileWriter.append(user);
        }
        dataFileWriter.close();
    }

    public void deSerialize(File file) throws IOException{
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader);

        User user = null;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println("-- deserialize user : " + user);
        }
    }

    private void serdeUsingJava() throws IOException{
        User[] users = createUser();
        try {
            serialize(users);
            deSerialize(new File(AVRO_FILE));
        } catch (IOException e) {
            System.out.println("--- fail to serialzie - deserialize  : " + AVRO_FILE);
            e.printStackTrace();
        }
    }

    private void serdeUsingGeneric() throws IOException{
        GenericRecord[] users = createUserInGeneric();
        try {
            serializeInGeneric(users);
            deserializeInGeneric(new File(AVRO_FILE2));
        } catch (IOException e) {
            System.out.println("--- fail to serialzie - deserialize 2  : " + AVRO_FILE);
            e.printStackTrace();
        }
    }

    private GenericRecord[] createUserInGeneric() throws IOException{
        GenericRecord user1 = new GenericData.Record(schema);
        user1.put("name", "JNJH00982");
        user1.put("favorite_number", 256);
        user1.put("favorite_color", "00:27:1C:01:82:85");

        // Leave favorite color null
        GenericRecord user2 = new GenericData.Record(schema);
        user2.put("name", "JNJH00982");
        user2.put("favorite_number", 7);
        user2.put("favorite_color", "00:27:1C:EF:0B:89");
        return new GenericRecord[]{user1, user2};
    }

    public void  serializeInGeneric(GenericRecord[] users) throws IOException{
        // Serialize user1 and user2 to disk
        File file = new File(AVRO_FILE2);
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(users[0]);
        dataFileWriter.append(users[1]);
        dataFileWriter.close();
    }

    public void deserializeInGeneric(File file) throws IOException {
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
        GenericRecord user = null;
        while (dataFileReader.hasNext()) {
        // Reuse user object by passing it to next(). This saves us from
        // allocating and garbage collecting many objects for files with
        // many items.
            user = dataFileReader.next(user);
            System.out.println("-- deserialize2 user : " + user);
        }
    }

    public static void main(String... args) {
        AvroTest test = new AvroTest();

        try {
            test.initSchema();

            test.serdeUsingJava();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            test.serdeUsingGeneric();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
