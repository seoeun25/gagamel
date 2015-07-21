package com.seoeun.schemaregistry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@NamedQueries({

        @NamedQuery(name = "GET_SCHEMAINFO", query = "select a.id, a.schema, a.create_time " +
                "from schemainfo a where a.schema = :schema ")

})
@Table(name = "schemainfo")
public class SchemaInfo {

    @Column(name = "id")
    private String id;

    @Column(name = "schema")
    private String schema;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created_time;

    public SchemaInfo() {

    }

    public SchemaInfo(String id, String schema) {
        this.id = id;
        this.schema = schema;
        created_time = Calendar.getInstance();
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Calendar getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Calendar created_time) {
        this.created_time = created_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
