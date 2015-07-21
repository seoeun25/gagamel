package com.seoeun.server;

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
            properties.load(new FileInputStream(new File(SITE_XML)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getConfig(String name) {
        return properties.getProperty(name);
    }


}
