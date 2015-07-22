package com.seoeun.jpa;

import com.seoeun.AvroRepoException;
import com.seoeun.schemaregistry.SchemaInfo;
import com.seoeun.server.JDBCService;
import com.seoeun.server.RepoServer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QueryExecutor {

    public Query getSelectQuery(SchemaInfoQuery namedQuery, EntityManager em, Object... parameters)
            throws AvroRepoException {
        Query query = em.createNamedQuery(namedQuery.name());
        switch (namedQuery) {
            case GET_SCHEMABYSCHEMA:
            case GET_SCHEMALATEST:
                query.setParameter("schemaStr", parameters[0]);
                break;
            case GET_SCHEMABYID:
                query.setParameter("id", parameters[0]);
                break;
            default:
                throw new AvroRepoException("QueryExecutor cannot set parameters for " + namedQuery.name());
        }
        return query;
    }

    public SchemaInfo get(SchemaInfoQuery namedQuery, Object... parameters) throws AvroRepoException {
        JDBCService jdbcService = RepoServer.getInstance().getJdbcService();
        EntityManager em = jdbcService.getEntityManager();
        Query query = getSelectQuery(namedQuery, em, parameters);
        Object ret = jdbcService.executeGet(namedQuery.name(), query, em);
        if (ret == null) {
            throw new AvroRepoException(query.toString());
        }
        SchemaInfo bean = constructBean(namedQuery, ret, parameters);
        return bean;
    }

    public List<SchemaInfo> getList(SchemaInfoQuery namedQuery, Object... parameters) throws AvroRepoException {
        JDBCService jdbcService = RepoServer.getInstance().getJdbcService();
        EntityManager em = jdbcService.getEntityManager();
        Query query = getSelectQuery(namedQuery, em, parameters);
        List<?> retList = (List<?>) jdbcService.executeGetList(namedQuery.name(), query, em);
        List<SchemaInfo> list = new ArrayList<SchemaInfo>();
        if (retList != null) {
            for (Object ret : retList) {
                list.add(constructBean(namedQuery, ret));
            }
        }
        return list;
    }

    public SchemaInfo getListLimit1(SchemaInfoQuery namedQuery, Object... parameters) throws AvroRepoException {
        JDBCService jdbcService = RepoServer.getInstance().getJdbcService();
        EntityManager em = jdbcService.getEntityManager();
        Query query = getSelectQuery(namedQuery, em, parameters);
        List<?> retList = (List<?>) jdbcService.executeGetListLimit1(namedQuery.name(), query, em);
        List<SchemaInfo> list = new ArrayList<SchemaInfo>();
        if (retList != null) {
            for (Object ret : retList) {
                list.add(constructBean(namedQuery, ret));
            }
        }
        return list.size() == 1 ? list.get(0) : null;
    }

    public void insert(SchemaInfo schemaInfo) throws AvroRepoException {
        JDBCService jdbcService = RepoServer.getInstance().getJdbcService();
        jdbcService.insert(schemaInfo);
    }

    private SchemaInfo constructBean(SchemaInfoQuery namedQuery, Object ret, Object... parameters)
            throws AvroRepoException {
        SchemaInfo bean;
        Object[] arr;
        switch (namedQuery) {
            case GET_SCHEMABYSCHEMA:
            case GET_SCHEMALATEST:
            case GET_SCHEMABYID:
                bean = new SchemaInfo();
                arr = (Object[]) ret;
                bean.setName((String) arr[0]);
                bean.setId((Long) arr[1]);
                bean.setSchemaStr((String) arr[2]);
                bean.setCreated((Calendar) arr[3]);
                break;
            default:
                throw new AvroRepoException("QueryExecutor cannot construct job bean for " + namedQuery.name());
        }
        return bean;
    }

    public enum SchemaInfoQuery {
        GET_SCHEMABYSCHEMA,
        GET_SCHEMABYID,
        GET_SCHEMALATEST;
    }
}
