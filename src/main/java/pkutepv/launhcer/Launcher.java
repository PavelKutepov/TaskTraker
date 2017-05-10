package pkutepv.launhcer;

import java.net.URL;
import java.security.ProtectionDomain;
import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pkutepv.Dao.Dao;
import pkutepv.model.Task;

/**
 * Starts jetty-server on the specified port
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/beans.xml");
        Dao dao = context.getBean(Dao.class);

        int port = 12135;
        try {
            if (args.length > 0) {
                port = Integer.parseInt(args[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Server server = new Server(port);

        ProtectionDomain domain = Launcher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());

        server.setHandler(webapp);
        server.start();

        server.join();
        context.close();

    }
}