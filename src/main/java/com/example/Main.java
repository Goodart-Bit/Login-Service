package com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;



/**
 *
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 *
 */
public class Main {

    /**
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        WebAppContext root = new WebAppContext();
        String webAppDirLocation = "src/main/webapp/";
        String webPort = System.getenv("port");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        Server server = new Server(Integer.valueOf(webPort));
        
        root.setContextPath("/");
        root.setDescriptor(webAppDirLocation + "/WEB_INF/web.xml");
        root.setResourceBase(webAppDirLocation);
        
        PersistenceManager.getInstance().getEntityManagerFactory();
        
        root.setParentLoaderPriority(true);
        server.setHandler(root);
        server.start();
        server.join();
        
        PersistenceManager.getInstance().closeEntityManagerFactory();
        server.stop();
    }

}
