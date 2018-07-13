package top.macaulish.core;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

public class Replicator {
    public static void main(String args[]){

        Log log = LogFactory.getLog(Replicator.class);
        File currentDir = new File("");
        log.info("Current running directory is "+currentDir.getAbsolutePath());
        FileAlterationObserver observer;
        try{
            //文件系统观察者
            observer = new FileAlterationObserver(currentDir.getAbsolutePath().replace("\\","\\\\"),new SimpleFileFilter());
            //设置监听器
            observer.addListener(new SimpleLabelFilesListener());
            new FileAlterationMonitor(1000,observer).start();
        }catch (Exception e){
            log.error(e);
        }

    }

}
