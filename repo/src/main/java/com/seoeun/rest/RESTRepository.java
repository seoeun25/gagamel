package com.seoeun.rest;

import com.seoeun.jpa.QueryExecutor;
import com.seoeun.schemaregistry.SchemaInfo;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
public class RESTRepository {

    private QueryExecutor queryExecutor;
    public RESTRepository() {
        // TODO : inject
        queryExecutor = new QueryExecutor();
    }

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
    public Response registerSchema(@PathParam("subject") String subject, @FormParam("schema") String schema) {
        try {
            SchemaInfo schemaExist = queryExecutor.getListLimit1(QueryExecutor.SchemaInfoQuery.GET_SCHEMALATEST, new
                    Object[]{schema});
            if (schemaExist != null) {
                return Response.status(200).entity(schemaExist).build();
            }
            SchemaInfo schemaInfo = new SchemaInfo(subject, schema);
            queryExecutor.insert(schemaInfo);
            return Response.status(200).entity(schemaInfo).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
