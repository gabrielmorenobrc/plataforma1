package plataforma1.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class WebContainer {
    private static final Logger logger = LoggerFactory.getLogger(WebContainer.class.getName());
    private static final Properties properties;

    private static final String WEBCONTAINER_PORT = "webcontainer_port";
    private static final String WEBCONTAINER_WEBAPP = "webcontainer_webapp";
    private static final String WEBCONTAINER_CONTEXT = "webcontainer_context";

    private static final String DEFAULT_WEBAPP = "webapp";
    private static final String DEFAULT_PORT = "8080";

    static {
        properties = new Properties();
        try {
            File propFile = new File("webservices.properties");
            if (propFile.exists()) {
                properties.load(new FileReader(propFile));
            }
        } catch (IOException e) {
            logger.error(null, e);
        }
    }

    public static void main(String[] args) {
        try {
            WebContainer server = new WebContainer();
            server.start();
        } catch (Throwable t) {
            logger.error(null, t);
            System.exit(1);
        }
    }

    private void startWebServer() throws Exception {
        int port = Integer.valueOf(properties.getProperty(WEBCONTAINER_PORT, DEFAULT_PORT));
        Server server = new Server(port);
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);
        String webapp = properties.getProperty(WEBCONTAINER_WEBAPP, DEFAULT_WEBAPP);
        String context = properties.getProperty(WEBCONTAINER_CONTEXT, "/");
        WebAppContext wac = new WebAppContext(webapp, context);

//        File file = new File("realm.properties");
//        if (file.exists()) {
//            HashLoginService loginService = new HashLoginService("realm", "realm.properties");
//            wac.getSecurityHandler().setLoginService(loginService);
//            server.addBean(loginService);
//        }

        server.setHandler(wac);
        server.setStopAtShutdown(true);
        server.start();
    }

    public void start() throws Exception {
        startWebServer();
    }

}
