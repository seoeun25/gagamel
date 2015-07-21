package com.seoeun.rest;

import com.seoeun.schemaregistry.SchemaInfo;
import org.apache.openjpa.persistence.jdbc.ForeignKey;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class RESTRepository {

    @GET
    @Path("schema/ids/{id}")
    public String getSchemabyId(@PathParam("id") String id) {
        System.out.println("---- id :" + id);

        Long idValue = Long.parseLong(id);

        SchemaInfo info = new SchemaInfo("hi-topic", idValue, "schema" );
        String schema = info.toJsonOblect().toJSONString();

        return schema;
    }

    @GET
    @Path("subjects")
    public String getSubjectList(){

        String subjects = "hi-topic, ftthif-topic";
        return subjects;
    }

    @GET
    @Path("subjects/{subject}/ids/{id}")
    public String getSchema(@PathParam("subject") String topicName, @PathParam("id") String id ) {

        System.out.println("---- topicName : " + topicName);
        System.out.println("---- id : " + id);

        String schema = "schema-topic-id-" + topicName + ":: " + id;

        return schema;
    }

    @POST
    @Path("subjects/{subject}")
    public String registerSchema(@PathParam("subject") String subject, @FormParam("schema") String schema) {

        System.out.println("---- subject : " + subject);
        System.out.println("---- schema : " + schema);

        String schemaResult = "-- POST schema-topic-id-" + subject + " :: " + schema + "---";
        return schemaResult;
    }
}
