package com.seoeun.jpa;

import com.seoeun.AvroRepoException;
import com.seoeun.schemaregistry.SchemaInfo;
import com.seoeun.server.JDBCService;
import com.seoeun.server.RepoServer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutor {

    public enum XtasQuery {
        UPDAT_XTAS_HADOOPUPLOAD,
        DELETE_XTAS,
        GET_SCHEMABYSCHEMA,
        GET_SCHEMABYID,
        GET_SCHEMALATEST;
    };

    public Query getUpdateQuery(XtasQuery namedQuery, SchemaInfo xtas, EntityManager em)
            throws AvroRepoException {
        Query query = em.createNamedQuery(namedQuery.name());
        switch (namedQuery) {
            case DELETE_XTAS:
                query.setParameter("collserver", xtas.getCollserver());
                query.setParameter("dirname", xtas.getDirname());
                query.setParameter("senddate", xtas.getSenddate());
                query.setParameter("hm", xtas.getHm());
                break;
            case UPDAT_XTAS_HADOOPUPLOAD:
                query.setParameter("hadoopload", xtas.getHadoopload());
                query.setParameter("collserver", xtas.getCollserver());
                query.setParameter("dirname", xtas.getDirname());
                query.setParameter("senddate", xtas.getSenddate());
                query.setParameter("hm", xtas.getHm());
                break;
            default:
                throw new AvroRepoException("QueryExecutor cannot set parameters for " + namedQuery.name());
        }
        return query;
    }

    public Query getSelectQuery(XtasQuery namedQuery, EntityManager em, Object... parameters)
            throws AvroRepoException {
        Query query = em.createNamedQuery(namedQuery.name());
        switch (namedQuery) {
            case GET_SCHEMABYSCHEMA:
            case GET_SCHEMALATEST:
                query.setParameter("schema", parameters[0]);
                break;
            case GET_SCHEMABYID:
                query.setParameter("id", parameters[0]);
                break;
            default:
                throw new AvroRepoException("QueryExecutor cannot set parameters for " + namedQuery.name());
        }
        return query;
    }

    public Xtas get(XtasQuery namedQuery, Object... parameters) throws AvroRepoException {
        JDBCService jdbcService = CMasterService.getInstance().getJdbcService();
        EntityManager em = jdbcService.getEntityManager();
        Query query = getSelectQuery(namedQuery, em, parameters);
        Object ret = jdbcService.executeGet(namedQuery.name(), query, em);
        if (ret == null) {
            throw new AvroRepoException(query.toString());
        }
        Xtas bean = constructBean(namedQuery, ret, parameters);
        return bean;
    }

    public List<Xtas> getList(XtasQuery namedQuery, Object... parameters) throws AvroRepoException {
        JDBCService jdbcService = CMasterService.getInstance().getJdbcService();
        EntityManager em = jdbcService.getEntityManager();
        Query query = getSelectQuery(namedQuery, em, parameters);
        List<?> retList = (List<?>) jdbcService.executeGetList(namedQuery.name(), query, em);
        List<Xtas> xtasList = new ArrayList<Xtas>();
        if (retList != null) {
            for (Object ret : retList) {
                xtasList.add(constructBean(namedQuery, ret));
            }
        }
        return xtasList;
    }

    public void insert(SchemaInfo xtas) throws AvroRepoException{
        JDBCService jdbcService = RepoServer.getInstance().getJdbcService();
        jdbcService.insert(xtas);
    }

    private Xtas constructBean(XtasQuery namedQuery, Object ret, Object... parameters)
            throws AvroRepoException {
        Xtas bean;
        Object[] arr;
        switch (namedQuery) {
            case GET_XTAS_UNFINISHED:
            case GET_XTAS:
                bean = new Xtas();
                arr = (Object[]) ret;
                bean.setCollserver((String) arr[0]);
                bean.setDirname((String) arr[1]);
                bean.setSenddate((String) arr[2]);
                bean.setHm((String) arr[3]);
                bean.setHadoopload((String) arr[4]);
                break;
            default:
                throw new AvroRepoException("QueryExecutor cannot construct job bean for " + namedQuery.name());
        }
        return bean;
    }
}
