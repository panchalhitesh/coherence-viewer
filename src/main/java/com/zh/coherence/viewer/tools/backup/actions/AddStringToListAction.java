package com.zh.coherence.viewer.tools.backup.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import com.zh.coherence.viewer.tools.backup.BackupContext;
import com.zh.coherence.viewer.tools.backup.BackupTool;
import com.zh.coherence.viewer.tools.backup.CacheInfo;
import com.zh.coherence.viewer.utils.icons.IconLoader;

public class AddStringToListAction extends AbstractAction{
    private BackupContext context;
    private BackupTool backupTool;

    public AddStringToListAction(BackupContext context, BackupTool backupTool) {
        this.context = context;
        this.backupTool = backupTool;

        putValue(Action.NAME, "Add");
        putValue(Action.SMALL_ICON, new IconLoader("icons/plus.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component parent = (Component) e.getSource();
        String name = JOptionPane.showInputDialog(parent, "input cache's name:",
                "Cache's name", JOptionPane.QUESTION_MESSAGE);
        if(name != null && !name.isEmpty()){
            context.getCacheInfoList().add(new CacheInfo(name));
            backupTool.updateTable();
        }
    }
}
