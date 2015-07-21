package com.seoeun.server.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by seoeun on 7/20/15.
 */
public class SchemaServlet extends HttpServlet{

    private static Logger LOG = LoggerFactory.getLogger(SchemaServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hadoopload = request.getParameter("hadoopload");
        LOG.debug("hadoopload {} ", hadoopload);
//        if (hadoopload == null || hadoopload.equals("")) {
//            hadoopload = Xtas.START;
//        }

//        try {
//            String jsonString = getUnfinishedXtas();
//
//            response.setContentType("application/json");
//            response.getOutputStream().write(jsonString.getBytes());
//            response.setStatus(HttpServletResponse.SC_OK);
//
//        } catch (Exception e) {
//            LOG.warn("Can not get UnFinished Xtas ", e);
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//
//        }

    }
}
