package com.zh.coherence.viewer.tools.backup.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.zh.coherence.viewer.tools.backup.BackupTableModel;
import com.zh.coherence.viewer.utils.icons.IconLoader;

public class CheckAllCachesAction extends AbstractAction{
    private BackupTableModel model;

    public CheckAllCachesAction(BackupTableModel model) {
        this.model = model;

        putValue(Action.SMALL_ICON, new IconLoader("icons/check-all.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < model.getRowCount(); i++){
            model.setValueAt(Boolean.TRUE, i, 0);
        }
        model.fireTableDataChanged();
    }
}
