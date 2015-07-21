package com.seoeun.schemaregistry;

import org.json.simple.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Entity
@NamedQueries({

        @NamedQuery(name = "GET_SCHEMABYSCHEMA", query = "select a.name, a.id, a.schema, a.created " +
                "from schemainfo a where a.schema = :schema "),
        @NamedQuery(name = "GET_SCHEMABYID", query = "select a.name, a.id, a.schema, a.created " +
                "from schemainfo a where a.id = :id "),
        @NamedQuery(name = "GET_SCHEMALATEST", query = "select a.name, a.id, a.schema, a.created " +
                "from schemainfo a where a.schema = :schema ")

})
@Table(name = "schemainfo")
public class SchemaInfo {

    @Column(name = "name")

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private long id;

    @Column(name = "schema")
    private String schema;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    public SchemaInfo() {

    }

    public SchemaInfo(String name, String schema) {
        this.name = name;
        this.schema = schema;
        this.created = Calendar.getInstance();
    }

    public SchemaInfo(String name, long id, String schema) {
        this.name = name;
        this.id = id;
        this.schema = schema;
        this.created = Calendar.getInstance();
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject toJsonOblect() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("id", id);
        jsonObject.put("schema", schema);
        jsonObject.put("created", created.getTimeInMillis());
        return jsonObject;
    }

    public String toString() {
        return toJsonOblect().toString();
    }

    public Object[] toParams() {
        Object[] params = new java.lang.Object[]{getName(), getId(), getSchema(), getCreated().getTimeInMillis()};
        return params;
    }

}
