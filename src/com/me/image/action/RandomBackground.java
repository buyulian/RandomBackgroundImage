package com.me.image.action;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.me.image.BackgroundService;
import com.me.image.RandomBackgroundTask;
import com.me.image.ui.Settings;

/**
 * Author: Lachlan Krautz
 * Date:   21/07/16
 */
public class RandomBackground extends AnAction {

    public RandomBackground() {
        super("Random Background Image");
        PropertiesComponent prop = PropertiesComponent.getInstance();
        if (prop.getBoolean(Settings.AUTO_CHANGE, false)) {
            BackgroundService.start();
        }
    }

    @Override
    public void actionPerformed(AnActionEvent evt) {
        RandomBackgroundTask.resetImageList();
        RandomBackgroundTask.execute();
        PropertiesComponent prop = PropertiesComponent.getInstance();
        if (prop.getBoolean(Settings.AUTO_CHANGE, false)) {
            BackgroundService.restart();
        }
    }

}
