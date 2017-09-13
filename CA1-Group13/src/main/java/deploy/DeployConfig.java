/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deploy;

import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Bloch
 */
public class DeployConfig implements ServletContextListener {

    public static String PU_NAME = "devPU";

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        Map<String, String> env = System.getenv();

        if (env.keySet().contains("DODeployment"))
        {
            PU_NAME = "deployPU";
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
    }

}
