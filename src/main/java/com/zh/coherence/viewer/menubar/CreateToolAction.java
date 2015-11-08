package com.zh.coherence.viewer.menubar;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.zh.coherence.viewer.ApplicationPane;
import com.zh.coherence.viewer.tools.ToolLauncher;

public class CreateToolAction extends AbstractAction {
    private ToolLauncher launcher;
    private ApplicationPane applicationPane;

    public CreateToolAction(ToolLauncher launcher, ApplicationPane applicationPane) {
        this.launcher = launcher;
        this.applicationPane = applicationPane;

        this.putValue(Action.NAME, launcher.getName());
        putValue(Action.SMALL_ICON, launcher.getIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        applicationPane.addTool(launcher.newTool(), launcher.getName());
    }

    public ToolLauncher getLauncher() {
        return launcher;
    }
}
