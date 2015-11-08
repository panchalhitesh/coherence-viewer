package com.zh.coherence.viewer.config.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.zh.coherence.viewer.config.ConfigContainer;
import com.zh.coherence.viewer.config.ConfigPanel;

public class HelpAction extends AbstractAction {

    private ConfigContainer configContainer;

    public HelpAction(ConfigContainer configContainer) {
        this.configContainer = configContainer;

        putValue(Action.NAME, "Help");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel panel = configContainer.getCurrentPanel();
        if (panel != null) {
            panel.showHelp();
        }
    }
}
