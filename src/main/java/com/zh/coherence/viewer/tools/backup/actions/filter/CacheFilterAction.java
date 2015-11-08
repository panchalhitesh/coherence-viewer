package com.zh.coherence.viewer.tools.backup.actions.filter;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTable;

import com.zh.coherence.viewer.tools.backup.BackupTableModel;
import com.zh.coherence.viewer.tools.backup.CacheInfo;
import com.zh.coherence.viewer.utils.icons.IconLoader;
import com.zh.coherence.viewer.utils.ui.ZHDialog;

public class CacheFilterAction extends AbstractAction {
    private BackupTableModel backupTableModel;
    private JTable table;

    public CacheFilterAction(BackupTableModel backupTableModel, JTable table) {
        putValue(Action.SMALL_ICON, new IconLoader("icons/filter.png"));
        putValue(Action.SHORT_DESCRIPTION, "Edit filter for selected cache");

        this.backupTableModel = backupTableModel;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent parent = (JComponent) e.getSource();
        final CacheFilterEditor cacheFilterEditor = new CacheFilterEditor(table, backupTableModel.getContext());

        final ZHDialog dialog = new ZHDialog(cacheFilterEditor, "Filter editor", new Runnable() {
            @Override
            public void run() {
                int row = table.getSelectedRow();
                CacheInfo info = backupTableModel.getContext().getCacheInfoList().get(row);
                info.setFilter(cacheFilterEditor.getFilter());
                backupTableModel.fireTableDataChanged();
            }
        }, "OK");

        dialog.setModal(true);
        dialog.show(parent, 600, 450);
    }

}