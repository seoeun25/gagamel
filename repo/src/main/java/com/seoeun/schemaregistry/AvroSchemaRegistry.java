package com.seoeun.schemaregistry;

import com.linkedin.camus.schemaregistry.SchemaDetails;
import com.linkedin.camus.schemaregistry.SchemaNotFoundException;
import com.linkedin.camus.schemaregistry.SchemaRegistry;
import com.seoeun.client.RepoClient;
import org.apache.avro.Schema;

import java.util.Properties;

public class AvroSchemaRegistry implements SchemaRegistry<Schema>{

    private RepoClient client;
    public static final String ETL_SCHEMA_REGISTRY_URL = "etl.schema.registry.url";

    public AvroSchemaRegistry() {

    }

    @Override
    public void init(Properties properties) {
        client = new RepoClient(properties.getProperty(ETL_SCHEMA_REGISTRY_URL));
    }

    @Override
    public String register(String topicName, Schema schema) {
        return client.insert(schema.toString());
    }

    @Override
    public Schema getSchemaByID(String topicName, String id) {
        SchemaInfo schemaInfo = client.getSchemaByTopic(topicName);
        if (schemaInfo == null) {
            throw new SchemaNotFoundException("Schema not found for " + topicName);
        }

        if (!String.valueOf(schemaInfo.getId()).equals(id)) {
            throw new SchemaNotFoundException("Schema not found for " + topicName + " " + id);
        }

        return Schema.parse(schemaInfo.getSchema());
    }

    @Override
    public SchemaDetails<Schema> getLatestSchemaByTopic(String topicName) {
        SchemaInfo schemaInfo = client.getSchemaByTopic(topicName);
        if (schemaInfo == null) {
            throw new SchemaNotFoundException("Schema not found for " + topicName);
        }
        return new SchemaDetails<Schema>(topicName, String.valueOf(schemaInfo.getId()), Schema.parse(schemaInfo.getSchema()));
    }
}
