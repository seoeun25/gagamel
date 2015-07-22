package com.seoeun.server;

import com.seoeun.AvroRepoException;
import com.seoeun.jpa.QueryExecutor;
import com.seoeun.schemaregistry.SchemaInfo;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class JDBCServiceTest {

    private static String FTTHIF = "{\"namespace\": \"example.avro\",\n" +
            " \"type\": \"record\",\n" +
            " \"name\": \"FtthIF\",\n" +
            " \"fields\": [\n" +
            "     {\"name\": \"eventtime\", \"type\": \"long\"},\n" +
            "     {\"name\": \"neosscode\", \"type\": \"string\"},\n" +
            "     {\"name\": \"ifname\",  \"type\": \"string\"},\n" +
            "     {\"name\": \"onuid\", \"type\": \"string\"},\n" +
            "     {\"name\": \"ontmac\", \"type\": \"string\"},\n" +
            "     {\"name\": \"extrainfo\", \"type\": [\"string\", \"null\"]}\n" +
            " ]\n" +
            "}";

    private static String EMPLOYEE = "{\"namespace\": \"example.avro\",\n" +
            " \"type\": \"record\",\n" +
            " \"name\": \"User\",\n" +
            " \"fields\": [\n" +
            "     {\"name\": \"name\", \"type\": \"string\"},\n" +
            "     {\"name\": \"favorite_number\",  \"type\": [\"int\", \"null\"]},\n" +
            "     {\"name\": \"favorite_color\", \"type\": [\"string\", \"null\"]}\n" +
            " ]\n" +
            "}";

    private static JDBCService jdbcService;

    @BeforeClass
    public static void setupClass() {
        try {
            jdbcService = new JDBCService();
            initJDBConfiguration();
            jdbcService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        jdbcService.shutdown();
        jdbcService = null;
    }

    private static void initJDBConfiguration() {
        RepoContext.getContext().setConfig(JDBCService.CONF_URL, "jdbc:derby:memory:myDB;create=true");
        RepoContext.getContext().setConfig(JDBCService.CONF_DRIVER, "org.apache.derby.jdbc.EmbeddedDriver");

//        RepoContext.getContext().setConfig(JDBCService.CONF_USERNAME, "ndap");
//        RepoContext.getContext().setConfig(JDBCService.CONF_PASSWORD, "ndap");
    }

    @Test
    public void testInsert() {

        try {
            insert("ftthif", FTTHIF);
            insert("employee", EMPLOYEE);

            Thread.sleep(1000);
            insert("ftthif", FTTHIF);

            // insert
            long currentTime = System.currentTimeMillis();
            String schemaStr = "{\"namespace\" : \"" + currentTime + "\"}";
            insert("ftthif-" + currentTime, schemaStr);

            QueryExecutor queryExecutor = new QueryExecutor();
            SchemaInfo schemaInfo = queryExecutor.get(QueryExecutor.SchemaInfoQuery.GET_SCHEMALATEST, new Object[]{schemaStr});
            Assert.assertEquals(schemaStr, schemaInfo.getSchemaStr());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void insert(String name, String schemaStr) throws Exception {
        SchemaInfo schemaInfo = new SchemaInfo(name, schemaStr);

        QueryExecutor queryExecutor = new QueryExecutor();
        queryExecutor.insert(schemaInfo);

    }

    @Test
    public void getGetSchemaBySchemaLatest() {
        try {
            QueryExecutor queryExecutor = new QueryExecutor();
            Object[] params = new java.lang.Object[]{FTTHIF};

            SchemaInfo schemaInfo = queryExecutor.getListLimit1(QueryExecutor.SchemaInfoQuery.GET_SCHEMALATEST, params);
            Assert.assertNotNull(schemaInfo);
            System.out.println("---- by schema(ftthi) : \n" + schemaInfo.toString());
            Assert.assertEquals(FTTHIF, schemaInfo.getSchemaStr());

        } catch (AvroRepoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetNegative() {
        try {
            QueryExecutor queryExecutor = new QueryExecutor();
            Object[] params = new java.lang.Object[]{"abc"};
            SchemaInfo schemaInfo = queryExecutor.get(QueryExecutor.SchemaInfoQuery.GET_SCHEMABYSCHEMA, params);

            Assert.fail("Should there no schemaInfo");
        } catch (AvroRepoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetSchemaInfos() {
        try {
            QueryExecutor queryExecutor = new QueryExecutor();
            Object[] params = new java.lang.Object[]{FTTHIF};
            List<SchemaInfo> schemaList = queryExecutor.getList(QueryExecutor.SchemaInfoQuery.GET_SCHEMABYSCHEMA, params);
            for (SchemaInfo schemaInfo : schemaList) {
                System.out.println("---- by schema(ftthi) : \n" + schemaInfo.toString());
                Assert.assertEquals(FTTHIF, schemaInfo.getSchemaStr());
            }

            params = new java.lang.Object[]{3};
            schemaList = queryExecutor.getList(QueryExecutor.SchemaInfoQuery.GET_SCHEMABYID, params);
            for (SchemaInfo schemaInfo : schemaList) {
                System.out.println("---- by id(3) : \n" + schemaInfo.toString());
                Assert.assertEquals(3, schemaInfo.getId());
            }

        } catch (AvroRepoException e) {
            e.printStackTrace();
        }
    }

}

