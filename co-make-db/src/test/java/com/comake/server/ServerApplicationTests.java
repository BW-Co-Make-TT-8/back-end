package com.comake.server;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@SpringBootTest

public class ServerApplicationTests
{
    private static final Logger logger = LoggerFactory.getLogger(com.comake.server.ServerApplicationTests.class);

    private static boolean stop = false;

    @Autowired
    private static Environment env;

    private static void checkEnvironmentVariable(String envvar)
    {
        if (System.getenv(envvar) == null)
        {
            logger.error("Environment Variable " + envvar + " missing");
        }
    }

    public static void main(String[] args)
    {
        checkEnvironmentVariable("OAUTHCLIENTID");
        checkEnvironmentVariable("OAUTHCLIENTSECRET");

        if (!stop)
        {
            SpringApplication.run(ServerApplicationTests.class,
                    args);
        }
    }
}
