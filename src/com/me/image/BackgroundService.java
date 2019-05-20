package com.me.image;

import com.intellij.ide.util.PropertiesComponent;
import com.me.image.ui.Settings;

/**
 * Author: Lachlan Krautz
 * Date:   22/07/16
 */
public class BackgroundService {

    private static Thread thread=new Thread(new RandomBackgroundTask());

    private static boolean isRun=false;

    private static final Object lock=new Object();

    public static void start () {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        int interval = prop.getInt(Settings.INTERVAL, 0);
        if (interval<1) {
            return;
        }

        String folder = prop.getValue(Settings.FOLDER);
        if (folder == null || folder.isEmpty()) {
            return;
        }

        synchronized (lock){
            if(isRun){
                return;
            }
            isRun=true;
            thread.start();
        }

    }

    public static void stop () {
        synchronized (lock){
            thread.interrupt();
            isRun=false;
        }
    }

    public static void restart () {
        start();
    }

}
