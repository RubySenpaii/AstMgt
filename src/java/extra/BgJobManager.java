/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author rubysenpaii
 */
@WebListener
public class BgJobManager implements ServletContextListener {
    private ScheduledExecutorService scheduler;
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newScheduledThreadPool(1);
        // scheduler.scheduleAtFixedRate(new CustomTas(), 0, 7, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new CustomTas(), 0, 7, TimeUnit.DAYS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            scheduler.shutdownNow();
        } catch (Exception ex) {
            Logger.getLogger(BgJobManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
