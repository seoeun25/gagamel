package com.seoeun.server;

import com.seoeun.AvroRepoException;
import com.seoeun.rest.EntryPoint;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepoServer implements AppService {

    public static int DEFAULT_PORT = 18181;
    private static Logger LOG = LoggerFactory.getLogger(RepoServer.class);
    private static RepoServer repoServer;
    public int PORT = DEFAULT_PORT;
    private Server jettyServer;

    public RepoServer() {

    }

    public static RepoServer getInstance() {
        if (repoServer == null) {
            repoServer = new RepoServer();
            repoServer.init();
        }
        return repoServer;
    }

    public static void main(String[] args) {
        String cmd = args[0];
        System.out.println("command : " + cmd);

        if ("start".equals(cmd)) {
            AppService app = RepoServer.getInstance();
            ShutdownInterceptor shutdownInterceptor = new ShutdownInterceptor(app);
            Runtime.getRuntime().addShutdownHook(shutdownInterceptor);
            try {
                app.start();
            } catch (AvroRepoException e) {
                e.printStackTrace();
            }
        } else if ("stop".equals(cmd)) {

        }
    }

    private void init() {
        try {
            initContext();
            initServices();
        } catch (AvroRepoException e) {
            LOG.error("Fail to init services ", e);
        }
    }

    private void initContext() {
        String sPort = RepoContext.getContext().getConfig("repo.port");
        PORT = sPort == null ? DEFAULT_PORT : Integer.parseInt(sPort);
    }

    private void initServices() throws AvroRepoException {

    }

    public void start() throws AvroRepoException {
        LOG.info("========= Collector Master Starting ......   ========");

        //init();

        initServer();

        try {
            jettyServer.start();
            LOG.info("Collector Master Started !! ");
            jettyServer.join();
        } catch (Exception e) {
            LOG.error("Error starting Jetty. Collector Master may not be available.", e);
        }

    }

    private void initServer() {

        jettyServer = new Server(PORT);

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        jettyServer.setHandler(contexts);

        Context root = new Context(contexts, "/avro-repo", Context.SESSIONS);
        ServletHolder jerseyServlet = new ServletHolder(org.glassfish.jersey.servlet.ServletContainer.class);
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", EntryPoint.class.getCanonicalName());
        root.addServlet(jerseyServlet, "/*");
    }

    public void shutdown() throws AvroRepoException {

        try {
            jettyServer.stop();
            jettyServer.join();
        } catch (Exception ex) {
            LOG.error("Error stopping Jetty. Collector Master may not be available.", ex);
        }
        LOG.info("========= Collector Master Shutdown ======== \n");

    }

    private static class ShutdownInterceptor extends Thread {

        private AppService app;

        public ShutdownInterceptor(AppService app) {
            this.app = app;
        }

        public void run() {
            System.out.println("Call the shutdown routine");
            try {
                app.shutdown();
            } catch (AvroRepoException e) {
                e.printStackTrace();
            }
        }
    }

}
