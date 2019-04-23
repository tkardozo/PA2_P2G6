package common;

import alarmEntity.AlarmEntity;
import batchEntity.BatchEntity;
import reportEntity.ReportEntity;
import digestionEntity.DigestionEntity;
import collectEntity.CollectEntity;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A helper class to start all entities
 * @author P2G6
 */
public class Startup {

    /**
     * Main execution
     *
     * @param args
     */
    public static void main(String[] args){
        Thread alarm = new Thread(){
            public void run(){
                try {
                    BatchEntity.main(args);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Thread batch = new Thread(){
            public void run(){
                AlarmEntity.main(args);
            }
        };
        Thread report = new Thread(){
            public void run(){
                ReportEntity.main(args);
            }
        };
        Thread digestion = new Thread(){
            public void run(){
                DigestionEntity.main(args);
            }
        };
        Thread collect = new Thread(){
            public void run(){
                try {
                    CollectEntity.main(args);
                } catch (IOException ex) {
                    Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        
        alarm.start();
        batch.start();
        report.start();
        
        digestion.start();
        
        collect.start();
    }

}
