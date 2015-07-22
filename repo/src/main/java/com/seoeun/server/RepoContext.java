package com.seoeun.server;

import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RepoContext {

    private static final String SITE_XML = "avro-repo-site.conf";
    private static Logger LOG = LoggerFactory.getLogger(RepoContext.class);
    private static RepoContext context;
    private Properties properties;

    private RepoContext() {
        initConfig();
    }

    public static RepoContext getContext() {
        if (context == null) {
            context = new RepoContext();
        }
        return context;
    }

    private void initConfig() {

        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("avro-repo-default.conf"));
        } catch (Exception e) {
            LOG.info("Fail to load avro-repo-default.conf");
        }

        try {
            properties.load(new FileInputStream(new File(SITE_XML)));
        } catch (IOException e) {
            LOG.info("Can not find config file {0}, Using default-config", SITE_XML);
        }

    }

    public String getConfig(String name) {
        return properties.getProperty(name);
    }

    @VisibleForTesting
    void setConfig(String name, String value) {
        properties.put(name, value);
    }


}
