package com.zh.coherence.viewer.config.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.zh.coherence.viewer.config.ConfigContainer;
import com.zh.coherence.viewer.config.ConfigPanel;

public class CancelAction extends AbstractAction {

    private ConfigContainer configContainer;

    public CancelAction(ConfigContainer configContainer) {
        this.configContainer = configContainer;

        putValue(Action.NAME, "Cancel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel panel = configContainer.getCurrentPanel();
        if (panel != null) {
            boolean res = panel.leaveThePage();
            if (res) {
                configContainer.dispose();
            }
        } else {
            configContainer.dispose();
        }
    }
}
