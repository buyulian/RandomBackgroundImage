package com.me.image;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.me.image.ui.Settings;

import java.io.File;

import static java.lang.Thread.sleep;

/**
 * Author: Allan de Queiroz
 * Date:   07/05/17
 */
public class RandomBackgroundTask implements Runnable {

    private static ImagesHandler imagesHandler=new ImagesHandler();

    @Override
    public void run() {

        while (true){
            try {
                execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                PropertiesComponent prop = PropertiesComponent.getInstance();
                int interval = prop.getInt(Settings.INTERVAL, 0);
                if (interval < 1) {
                    return;
                }
                sleep(interval*1000*60);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public static void execute() {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        String folder = prop.getValue(Settings.FOLDER);
        if (folder == null || folder.isEmpty()) {
            NotificationCenter.notice("Image folder not set");
            return;
        }
        File file = new File(folder);
        if (!file.exists()) {
            NotificationCenter.notice("Image folder not set");
            return;
        }
        String image = imagesHandler.getRandomImage(folder);
        if (image == null) {
            NotificationCenter.notice("No image found");
            return;
        }
        if (image.contains(",")) {
            NotificationCenter.notice("Intellij wont load images with ',' character\n" + image);
        }
        prop.setValue(IdeBackgroundUtil.FRAME_PROP, null);
        prop.setValue(IdeBackgroundUtil.EDITOR_PROP, image);

        try {
            IdeBackgroundUtil.repaintAllWindows();
        } catch (Exception e) {
            //这里必出异常，先捕获再说
        }
    }

    public static void resetImageList(){
        ImagesHandler.resetImages();
    }

}
