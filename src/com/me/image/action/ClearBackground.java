package com.me.image.action;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.me.image.BackgroundService;
import com.me.image.ui.Settings;

/**
 * Author: Lachlan Krautz
 * Date:   22/07/16
 */
public class ClearBackground extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        prop.setValue(IdeBackgroundUtil.EDITOR_PROP, null);
        prop.setValue(IdeBackgroundUtil.FRAME_PROP, null);
        prop.setValue(Settings.AUTO_CHANGE, false);
        BackgroundService.stop();
        IdeBackgroundUtil.repaintAllWindows();
    }

}
